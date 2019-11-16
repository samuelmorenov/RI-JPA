package uo.ri.cws.application.service.workorder.assign.command;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.command.Command;

//TODO 3.4.1 - Asignar una orden de trabajo a un mecánico.
public class FindCertificatesByVehicleTypeId implements Command<List<CertificateDto>> {

	public FindCertificatesByVehicleTypeId(String id) {
		throw new RuntimeException("Not yet implemented.");
	}

	@Override
	public List<CertificateDto> execute() throws BusinessException {

		throw new RuntimeException("Not yet implemented.");
	}

}
