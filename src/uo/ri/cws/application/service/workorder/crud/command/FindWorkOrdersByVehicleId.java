package uo.ri.cws.application.service.workorder.crud.command;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

public class FindWorkOrdersByVehicleId implements Command<List<WorkOrderDto>> {

    private String id;
    private WorkOrderRepository wor = Factory.repository.forWorkOrder();
    
    public FindWorkOrdersByVehicleId(String id) {
	this.id = id;
    }

    @Override
    public List<WorkOrderDto> execute() throws BusinessException {
	List<WorkOrder> ms = wor.findByVehicleId(id);
	return DtoAssembler.toWorkOrderDtoList(ms);
    }

}
