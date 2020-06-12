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

/**
 * Added in the extension Registrar una orden de trabajo. <br/>
 * <br/>
 * Podemos asumir que el vehículo ya existe en la aplicación. Se pedirá además
 * del vehículo al que se refiere, una descripción del trabajo a hacer. Se
 * asignará la fecha del sistema en la que se registra la orden. Inicialmente la
 * orden de trabajo estará en estado ABIERTA.
 */
public class AddWorkOrder implements Command<WorkOrderDto> {
    private WorkOrderDto dto;

    public AddWorkOrder(WorkOrderDto dto) {
	this.dto = dto;
    }

    /**
     * Registers a new work order out of the data received. Only this fields will be
     * taken into account: - the vehicle id, and - the description of the work to be
     * done The rest of fields will assigned by the service, thus any provided value
     * will be ignored.
     *
     * @param dto. Just vehicle id and description.
     *
     * @return another dto with the provided values and service-assigned fields
     *         filled: id, date and status
     *
     *         @throws BusinessException if <br>
     *         - there is another work order for the same vehicle at the same date
     *         and time (timestamp), or <br>
     *         - the vehicle does not exist
     */
    // Gestión de workOrders (fallos): Faltan comprobaciones: existe la
    // workorder, existe mecánico, existe tipo vehículo, existe id, …
    public WorkOrderDto execute() throws BusinessException {

	WorkOrderRepository wor = Factory.repository.forWorkOrder();
	VehicleRepository vr = Factory.repository.forVehicle();

	Optional<Vehicle> ov = vr.findById(dto.vehicleId);

	// Se pide que se asuma que existe el vehiculo,
	// pero creo que no pasa nada por
	// comprobarlo de todos modos
	BusinessCheck.isTrue(ov.isPresent(), "This vehicle does not exist");
	Vehicle v = ov.get();

	BusinessCheck.isFalse(
		wor.SearchWorkOrder(dto.vehicleId, dto.date).isPresent(),
		"Ya existe una work order para ese vehiculo con esta fecha");

	// Al crear una work order se establece como abierta y
	// se pone la fecha actual
	WorkOrder wo = new WorkOrder(v, dto.description);

	wor.add(wo);

	dto.id = wo.getId();

	return dto;
    }
}
