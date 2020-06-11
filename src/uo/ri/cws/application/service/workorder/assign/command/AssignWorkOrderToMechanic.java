package uo.ri.cws.application.service.workorder.assign.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.MechanicRepository;
import uo.ri.cws.application.repository.WorkOrderRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Associations;
import uo.ri.cws.domain.Certificate;
import uo.ri.cws.domain.Mechanic;
import uo.ri.cws.domain.VehicleType;
import uo.ri.cws.domain.WorkOrder;

/**
 * DONE - Asignar una orden de trabajo a un mecánico.<br/>
 * <br/>
 * Al asignar una orden de trabajo a un mecánico se mostrará un listado de todos
 * los mecánicos que están certificados para ese tipo de vehículo y el usuario
 * elegirá de entre ellos. No se puede asignar una orden de trabajo a un
 * mecánico que no esté certificado para ese tipo de vehículo<br/>
 * <br/>
 * Para testear esta funcionalidad:<br/>
 * Work order id: dbd60eae-e488-4578-bde8-bed86d45c6b0<br/>
 * Mechanic id correcto: 16b2e998-f4c8-4e22-842d-7dbacab45c68<br/>
 * Mechanic id incorrecto: 0fd819d8-fad6-4ff5-8436-b18a5b69e7cf<br/>
 */
public class AssignWorkOrderToMechanic implements Command<Void> {

	private String woId;
	private String mechanicId;
	private MechanicRepository mr = Factory.repository.forMechanic();
	private WorkOrderRepository wor = Factory.repository.forWorkOrder();

	public AssignWorkOrderToMechanic(String woId, String mechanicId) {
		this.woId = woId;
		this.mechanicId = mechanicId;
	}

	@Override
	public Void execute() throws BusinessException {

		Optional<Mechanic> om = mr.findById(mechanicId);
		BusinessCheck.exists(om, "No se ha encontrado ese mecanico");
		Mechanic m = om.get();

		Optional<WorkOrder> owo = wor.findById(woId);
		BusinessCheck.exists(om, "No se ha encontrado esa work order");
		WorkOrder wo = owo.get();

		VehicleType vt = wo.getVehicle().getVehicleType();
		boolean encontrado = false;
		for (Certificate c : m.getCertificates()) {
			if (vt == c.getVehicleType()) {
				encontrado = true;
				break;
			}
		}
		BusinessCheck.isTrue(encontrado, "El mecanico no esta certificado para ese vehiculo");

		Associations.Assign.link(m, wo);
		// TODO: Gestión de workOrders (fallos): asignar usa Associations.link y no el
		// método correspondiente de la entidad

		return null;

	}

}
