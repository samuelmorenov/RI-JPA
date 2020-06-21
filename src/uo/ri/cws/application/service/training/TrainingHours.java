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

    public static int Calculate(Mechanic mechanic, VehicleType vehicleType) {
	// Calculamos las horas que ha recibido el mecanico de ese tipo de vehiclo
	int hoursPerVehicleType = 0;
	for (Enrollment enrollment : mechanic.getEnrollments()) {

	    // Solo si se ha pasado
	    if (enrollment.isPassed()) {
		Course curse = enrollment.getCourse();
		for (Dedication dedicarion : curse.getDedications()) {
		    
		    if(vehicleType.getId() == dedicarion.getVehicleType().getId()) {		
			hoursPerVehicleType += curse.getHours()
				* (dedicarion.getPercentage() * 0.01);
		    }
		}
	    }
	}
	return hoursPerVehicleType;
    }

}
