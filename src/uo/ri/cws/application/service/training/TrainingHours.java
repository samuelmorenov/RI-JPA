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

    /*
     * TODO: Generación de certificado: TrainingHours::calculate debe dividir por
     * 10000
     * 
     * Al calcular las horas (GenerateDertificates.java línea 42), el método
     * TrainingHours::Calculate utiliza el porcentaje de dedicación como un valor a
     * multiplicar de forma absoluta. Pero es un porcentaje sobre 100; multiplicas
     * las horas por 25 (por ejemplo) en lugar de dividir entre 4.
     * 
     * Listados: utiliza los porcentajes como horas Revision: Todo OK, excepto que
     * olvidó dividir por 100
     * 
     * Exactamente lo mismo. Utilizas los porcentajes como valores absolutos, en
     * lugar de dividir por 100.
     * 
     */
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
				curse.getHours() * (dedicarion.getPercentage() /100);
		    }
		}
	    }
	}
	return hoursPerVehicleType;
    }

}
