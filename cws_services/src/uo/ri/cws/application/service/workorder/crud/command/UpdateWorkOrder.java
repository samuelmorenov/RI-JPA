package uo.ri.cws.application.service.workorder.crud.command;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.command.Command;

public class UpdateWorkOrder  implements Command<WorkOrderDto>{

	@Override
	public WorkOrderDto execute() throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

}
