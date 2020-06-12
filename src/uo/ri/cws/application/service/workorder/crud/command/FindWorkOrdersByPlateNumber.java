package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrdersByPlateNumber implements Command<List<WorkOrderDto>> {
    
    private String plate;
    private WorkOrderRepository wor = Factory.repository.forWorkOrder();

    public FindWorkOrdersByPlateNumber(String plate) {
	this.plate = plate;
    }

    @Override
    public List<WorkOrderDto> execute() {
	List<WorkOrder> ms = wor.findByPlateNumber(plate);
	return DtoAssembler.toWorkOrderDtoList(ms);
    }

}
