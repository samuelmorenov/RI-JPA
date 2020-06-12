package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.WorkOrder;

/**
 * Added in the extension Eliminar una orden de trabajo.<br/>
 * <br/>
 * Solo si no tiene intervenciones.
 */
public class DeleteWorkOrder implements Command<Void> {

    private String id;
    private WorkOrderRepository wor = Factory.repository.forWorkOrder();

    public DeleteWorkOrder(String id) {
	this.id = id;
    }

    /**
     * Removes the work order form the system if it still do not have interventions.
     *
     * @param id, of the work order
     *
     * @throws BusinessException if <br>
     *                           - the work order does not exist, or <br>
     *                           - there already is some intervention registered.
     */
    @Override
    public Void execute() throws BusinessException {
	Optional<WorkOrder> owo = wor.findById(id);

	BusinessCheck.isTrue(owo.isPresent(),
		"This work order does not exist");

	WorkOrder wo = owo.get();

	BusinessCheck.isTrue(wo.getInterventions().size() == 0,
		"This work order has interventions");

	wor.remove(wo);

	return null;
    }

}
