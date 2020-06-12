package uo.ri.cws.application.service.workorder.assign.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.command.Command;

public class FindUnfinishedWorkOrders implements Command<List<WorkOrderDto>> {

    /**
     * @return a list of all work orders either in the OPEN or ASSIGN status
     * @throws BusinessException, DOES NOT
     */
    @Override
    public List<WorkOrderDto> execute() throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

}
