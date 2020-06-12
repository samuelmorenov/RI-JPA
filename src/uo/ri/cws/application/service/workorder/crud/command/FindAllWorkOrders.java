package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.domain.WorkOrder;

public class FindAllWorkOrders {

    private WorkOrderRepository wor = Factory.repository.forWorkOrder();

    public List<WorkOrderDto> execute() {
	List<WorkOrder> ms = wor.findAll();
	return DtoAssembler.toWorkOrderDtoList(ms);
    }

}
