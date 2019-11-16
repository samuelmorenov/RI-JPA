package uo.ri.cws.application.service.workorder.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderCrudService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.service.workorder.crud.command.AddWorkOrder;
import uo.ri.cws.application.util.command.CommandExecutor;

public class WorkOrderCrudServiceImpl implements WorkOrderCrudService {

	private CommandExecutor executor = Factory.executor.forExecutor();

	@Override
	/**
	 * 
	 */
	public WorkOrderDto registerNew(WorkOrderDto dto) throws BusinessException {

		/*
		 * Registrar una orden de trabajo.
		 *
		 * Podemos asumir que el vehículo ya existe en la aplicación. Se pedirá además
		 * del vehículo al que se refiere, una descripción del trabajo a hacer. Se
		 * asignará la fecha del sistema en la que se registra la orden. Inicialmente la
		 * orden de trabajo estará en estado ABIERTA.
		 */

		return executor.execute(new AddWorkOrder(dto));
	}

	@Override
	public void updateWorkOrder(WorkOrderDto dto) throws BusinessException {

		// TODO 3.2.2 - Modificar datos de una orden de trabajo.
		/*
		 * Solo la descripción puede ser cambiada y la orden de trabajo debe estar en
		 * estado ABIERTA o ASIGNADA.
		 */

		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public void deleteWorkOrder(String id) throws BusinessException {

		// TODO 3.3.1 - Eliminar una orden de trabajo.
		/* Solo si no tiene intervenciones. */

		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public Optional<WorkOrderDto> findWorkOrderById(String woId) throws BusinessException {

		// TODO 3.2.1 - Modificar datos de una orden de trabajo.

		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByVehicleId(String id) throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public List<WorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

}
