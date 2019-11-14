package uo.ri.cws.application.service.workorder.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;

//TODO Impl - WorkOrderCrudServiceImpl
public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {

	@Override
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {
		
		return null;
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {
		

	}

	@Override
	public void deleteWorkOrder(String id) throws BusinessException {
		

	}

	@Override
	public Optional<WorkOrderDto> findWorkOrderById(String woId) throws BusinessException {
		
		return null;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(String id) throws BusinessException {
		
		return null;
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		
		return null;
	}

}
