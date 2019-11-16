package uo.ri.cws.application.service.workorder.assign;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.workorder.AssignWorkOrderService;
import uo.ri.cws.application.service.workorder.WorkOrderDto;

public class AssignWorkOrderServiceImpl implements AssignWorkOrderService {

	@Override
	public void assignWorkOrderToMechanic(String woId, String mechanicId) throws BusinessException {

		// TODO 3.4.2 - Asignar una orden de trabajo a un mecánico.
		/*
		 * Al asignar una orden de trabajo a un mecánico se mostrará un listado de todos
		 * los mecánicos que están certificados para ese tipo de vehículo y el usuario
		 * elegirá de entre ellos. No se puede asignar una orden de trabajo a un
		 * mecánico que no esté certificado para ese tipo de vehículo
		 */

		throw new RuntimeException("Not yet implemented.");

	}

	@Override
	public List<CertificateDto> findCertificatesByVehicleTypeId(String id) throws BusinessException {
		// TODO 3.4.1 - Asignar una orden de trabajo a un mecánico.

		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public List<WorkOrderDto> findUnfinishedWorkOrders() throws BusinessException {
		throw new RuntimeException("Not yet implemented.");
	}

}
