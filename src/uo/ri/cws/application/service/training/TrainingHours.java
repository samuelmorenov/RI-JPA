package uo.ri.cws.application.service.training;

import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;
import uo.ri.cws.domain.Enrollment;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * Clase estatica <b>TrainingHours:</b> </br>
 * Sirve para calcular el numero de horas aprobadas que tiene un mecanico para
 * un tipo de vehiculo </br>
 * Se utiliza en las clases:</br>
 * <b>- FindTrainingByVehicleTypeAndMechanic</b></br>
 * <b>- GenerateCertificates</b>
 */
public class TrainingHours {

    // TODO Generación de certificado: TrainingHours::calculate debe dividir por
    // 10000
    public static int Calculate(Mechanic mechanic, VehicleType vehicleType) {
	// Calculamos las horas que ha recibido el mecanico de ese tipo de vehiclo
	int hoursPerVehicleType = 0;
	for (Enrollment enrollment : mechanic.getEnrollments()) {

	    // Solo si se ha pasado
	    if (enrollment.isPassed()) {
		Course curse = enrollment.getCourse();
		for (Dedication dedicarion : curse.getDedications()) {
		    if (vehicleType.equals(dedicarion.getVehicleType())) {
			hoursPerVehicleType +=
				curse.getHours() * dedicarion.getPercentage();
		    }
		}
	    }
	}
	return hoursPerVehicleType;
    }

}
