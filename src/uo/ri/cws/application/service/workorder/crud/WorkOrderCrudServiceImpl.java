package uo.ri.cws.application.service.workorder.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.AddWorkOrder;
import uo.ri.cws.application.service.workorder.crud.command.DeleteWorkOrder;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrderById;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrdersByPlateNumber;
import uo.ri.cws.application.service.workorder.crud.command.FindWorkOrdersByVehicleId;
import uo.ri.cws.application.service.workorder.crud.command.UpdateWorkOrder;
import uo.ri.cws.application.util.command.CommandExecutor;

public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public WorkOrderDto registerNew(WorkOrderDto dto)
	    throws BusinessException {
	return executor.execute(new AddWorkOrder(dto));
	// Added in the extension
    }

    @Override
    public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
	executor.execute(new UpdateWorkOrder(dto));
	// Added in the extension
    }

    @Override
    public void deleteWorkOrder(String id) throws BusinessException {
	executor.execute(new DeleteWorkOrder(id));
	// Added in the extension
    }

    @Override
    public Optional<WorkOrderDto> findWorkOrderById(String woId)
	    throws BusinessException {
	return executor.execute(new FindWorkOrderById(woId));
	// Added in the extension
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByVehicleId(String id)
	    throws BusinessException {
	return executor.execute(new FindWorkOrdersByVehicleId(id));
	// Added in the second extension
    }

    @Override
    public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate)
	    throws BusinessException {
	return executor.execute(new FindWorkOrdersByPlateNumber(plate));
	// Added in the second extension
    }

}
