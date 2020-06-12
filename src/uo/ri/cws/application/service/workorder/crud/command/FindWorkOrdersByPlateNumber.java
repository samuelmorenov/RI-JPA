package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.command.Command;

public class FindWorkOrdersByPlateNumber implements Command<List<WorkOrderDto>> {

    public FindWorkOrdersByPlateNumber(String plate) {
	// TODO Auto-generated constructor stub
    }

    @Override
    public List<WorkOrderDto> execute() throws BusinessException {
	// TODO Auto-generated method stub
	return null;
    }

}
