package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

/**
 * Added in the extension Metodo usado para modificar datos de una orden de
 * trabajo
 */
public class FindWorkOrderById implements Command<Optional<WorkOrderDto>> {

    private String id;
    private WorkOrderRepository wor = Factory.repository.forWorkOrder();

    public FindWorkOrderById(String woId) {
	this.id = woId;
    }

    @Override
    public Optional<WorkOrderDto> execute() throws BusinessException {
	Optional<WorkOrder> owd = wor.findById(id);
	return owd.isPresent() ? Optional.of(DtoAssembler.toDto(owd.get()))
		: Optional.empty();
    }

}
