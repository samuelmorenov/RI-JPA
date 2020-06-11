package uo.ri.cws.application.service.training.certificate.command;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.training.TrainingHours;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * Added in the extension - Generar certificados
 *
 * Periódicamente se ejecutará el proceso que se encarga de generar los nuevos
 * certificados para los mecánicos que cumplan los requisitos especificados en
 * Generación de certificados. No requiere interacción del usuario. En un
 * despliegue real este proceso sería lanzado como una tarea programada del
 * sistema (cron, Windows task scheduler, etc.). Por simplicidad se deja como
 * una opción del menú del administrador.
 */
public class GenerateCertificates implements Command<Integer> {

    private MechanicRepository mechanicRepository =
	    Factory.repository.forMechanic();
    private VehicleTypeRepository vehicleRepository =
	    Factory.repository.forVehicleType();
    private CertificateRepository certificateRepository =
	    Factory.repository.forCertificate();

    @Override
    public Integer execute() {
	int generated = 0;

	// Se recorren todos los mecanicos para cada tipo de vehiculo
	for (VehicleType vehicleType : vehicleRepository.findAll()) {
	    for (Mechanic mechanic : mechanicRepository.findAll()) {

		// Si el certificado no existe
		if (!certificateRepository.findByMechanicAndVehicleType(
			mechanic.getId(), vehicleType.getId()).isPresent()) {

		    int trainingHours =
			    TrainingHours.Calculate(mechanic, vehicleType);

		    // Y si el numero de horas es suficiente
		    if (trainingHours >= vehicleType.getMinTrainingHours()) {

			// Se genera el certificado
			Certificate certificate =
				new Certificate(mechanic, vehicleType);
			certificateRepository.add(certificate);
			generated++;
		    }
		}
	    }
	}
	return generated;
    }
}
