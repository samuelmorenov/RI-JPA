package uo.ri.cws.application.service.training.report.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingHours;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;

/**
 * Listado de mecánicos que han asistido a formación por tipo de vehículo
 *
 * Listado de mecánicos que han asistido a formación por tipo de vehículo. Para
 * cada tipo de vehículo se mostrará la información de cada mecánico que han
 * participado en cursos de ese tipo junto al número de horas a las que asistió.
 * 
 * Tipo de vehículo 1
 * 
 * Mecánico2, 999 horas
 * 
 * Mecánico5, 999 horas
 * 
 * Tipo de vehículo
 * 
 * 3 Mecánico2, 999 horas
 * 
 * Mecánico7, 999 horas
 */
public class FindTrainingByVehicleTypeAndMechanic
	implements Command<List<TrainingHoursRow>> {

    private MechanicRepository mechanicRepository =
	    Factory.repository.forMechanic();
    private VehicleTypeRepository vehicleRepository =
	    Factory.repository.forVehicleType();

    @Override
    public List<TrainingHoursRow> execute() throws BusinessException {
	List<TrainingHoursRow> list = new ArrayList<TrainingHoursRow>();

	for (VehicleType vehicleType : vehicleRepository.findAll()) {
	    for (Mechanic mechanic : mechanicRepository.findAll()) {
		TrainingHoursRow tfr = new TrainingHoursRow();
		tfr.mechanicFullName =
			mechanic.getName() + " " + mechanic.getSurname();
		tfr.vehicleTypeName = vehicleType.getName();
		tfr.enrolledHours =
			TrainingHours.Calculate(mechanic, vehicleType); 

		// Aclaracion: no se especifica si se tienen que mostrar en caso de que sean 0
		// de ser asi comentar la siguiente linea
		if (tfr.enrolledHours > 0)
		    list.add(tfr);

	    }
	}

	return list;

    }

}
