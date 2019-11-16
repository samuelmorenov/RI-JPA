package uo.ri.cws.application.service.workorder.assign.command;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.command.Command;

/**
 * TODO 3.4.2 - Asignar una orden de trabajo a un mecánico.
 * 
 * Al asignar una orden de trabajo a un mecánico se mostrará un listado de todos
 * los mecánicos que están certificados para ese tipo de vehículo y el usuario
 * elegirá de entre ellos. No se puede asignar una orden de trabajo a un
 * mecánico que no esté certificado para ese tipo de vehículo
 */
public class AssignWorkOrderToMechanic implements Command<Void> {

	public AssignWorkOrderToMechanic(String woId, String mechanicId) {
		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public Void execute() throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

}
