package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;
import uo.ri.cws.domain.WorkOrder.WorkOrderStatus;

/**
 * Added in the extension Modificar datos de una orden de trabajo.<br/>
 * <br/>
 * Solo la descripción puede ser cambiada y la orden de trabajo debe estar en
 * estado ABIERTA o ASIGNADA.
 */
public class UpdateWorkOrder implements Command<Void> {

    private WorkOrderDto dto;
    private WorkOrderRepository wor = Factory.repository.forWorkOrder();

    public UpdateWorkOrder(WorkOrderDto dto) {
	this.dto = dto;
    }

    /**
     * Updates the description of the work order specified by the id and version
     * fields. No other field is taken for the update. Only work orders with status
     * of OPEN or ASSIGNED can be updated.
     *
     * @param dto, with work order id, version and description.
     *
     *             @throws BusinessException if <br>
     *             - there is no work order with that id, or <br>
     *             - there work order has not the specified version (optimistic
     *             lock), or <br>
     *             - the work order is not in the OPEN or ASSIGNED status
     */
    @Override
    public Void execute() throws BusinessException {
	Optional<WorkOrder> owo = wor.findById(dto.id);
	BusinessCheck.exists(owo, "There is no such work order");

	WorkOrder wo = owo.get();
	BusinessCheck.isTrue(
		wo.getStatus().equals(WorkOrderStatus.OPEN)
			|| wo.getStatus().equals(WorkOrderStatus.ASSIGNED),
		"The work order must be in status open or assigned");
	BusinessCheck.hasVersion(wo, dto.version);

	wo.setDescription(dto.description);

	return null;
    }

}
