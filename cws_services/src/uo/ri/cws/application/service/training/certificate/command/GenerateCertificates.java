package uo.ri.cws.application.service.training.certificate.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * DONE - Generar certificados
 *
 * Manager Main: 4 - 4 Periódicamente se ejecutará el proceso que se encarga de
 * generar los nuevos certificados para los mecánicos que cumplan los requisitos
 * especificados en Generación de certificados. No requiere interacción del
 * usuario. En un despliegue real este proceso sería lanzado como una tarea
 * programada del sistema (cron, Windows task scheduler, etc.). Por simplicidad
 * se deja como una opción del menú del administrador.
 */
public class GenerateCertificates implements Command<Integer> {

	private MechanicRepository mechanicRepository = Factory.repository.forMechanic();
	private VehicleTypeRepository vehicleRepository = Factory.repository.forVehicleType();
	private CertificateRepository certificateRepository = Factory.repository.forCertificate();

	@Override
	public Integer execute() throws BusinessException {
		int generated = 0;

		// Se recorren todos los mecanicos para cada tipo de vehiculo
		for (VehicleType vehicleType : vehicleRepository.findAll()) {
			for (Mechanic mechanic : mechanicRepository.findAll()) {

				//Si el certificado no existe
				if (!certificateAlreadyExist(mechanic, vehicleType)) {

					int hoursPerVehicleType = CalculateHours(mechanic, vehicleType);

					// Y si el numero de horas es suficiente
					if (hoursPerVehicleType >= vehicleType.getMinTrainingHours()) {

						//Se genera el certificado
						Certificate certificate = new Certificate(mechanic, vehicleType);
						certificateRepository.add(certificate);
						generated++;
					}
				}
			}
		}
		return generated;
	}

	private int CalculateHours(Mechanic mechanic, VehicleType vehicleType) {
		// Calculamos las horas que ha recibido el mecanico de ese tipo de vehiclo
		int hoursPerVehicleType = 0;
		for (Enrollment enrollment : mechanic.getEnrollments()) {

			// Solo si se ha pasado
			if (enrollment.isPassed()) {
				Course curse = enrollment.getCourse();
				for (Dedication dedicarion : curse.getDedications()) {
					if (vehicleType.equals(dedicarion.getVehicleType())) {
						hoursPerVehicleType += curse.getHours() * dedicarion.getPercentage();
					}
				}
			}
		}
		return hoursPerVehicleType;
	}

	private boolean certificateAlreadyExist(Mechanic mechanic, VehicleType vehicleType) {
		return certificateRepository.findByMechanicAndVehicleType(mechanic.getId(), vehicleType.getId()).isPresent();
	}
}
