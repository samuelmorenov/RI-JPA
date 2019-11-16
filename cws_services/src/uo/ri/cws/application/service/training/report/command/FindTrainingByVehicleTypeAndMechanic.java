package uo.ri.cws.application.service.training.report.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.util.command.Command;

/**
 * TODO 2 - Listado de mecánicos que han asistido a formación por tipo de
 * vehículo
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
public class FindTrainingByVehicleTypeAndMechanic implements Command<List<TrainingHoursRow>> {

	@Override
	public List<TrainingHoursRow> execute() throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

}
