package uo.ri.cws.application.service.workorder.crud.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.VehicleRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.workorder.WorkOrderDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Vehicle;
import uo.ri.cws.domain.WorkOrder;

public class AddWorkOrder implements Command<WorkOrderDto> {
	private WorkOrderDto dto;

	public AddWorkOrder(WorkOrderDto dto) {
		this.dto = dto;
	}

	public WorkOrderDto execute() throws BusinessException {

		WorkOrderRepository wor = Factory.repository.forWorkOrder();
		VehicleRepository vr = Factory.repository.forVehicle();

		Optional<Vehicle> ov = vr.findById(dto.vehicleId);
		BusinessCheck.isTrue(ov.isPresent(), "This vehicle does not exist");
		Vehicle v = ov.get();

		WorkOrder wo = new WorkOrder(v, dto.description);

		wor.add(wo);

		dto.id = wo.getId();

		return dto;
	}
}
