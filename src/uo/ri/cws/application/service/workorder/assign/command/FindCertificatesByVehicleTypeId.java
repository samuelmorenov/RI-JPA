package uo.ri.cws.application.service.workorder.assign.command;

import java.util.List;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CertificateRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Certificate;

/** Usado para asignar una orden de trabajo a un mecánico. */
/**
 * Returns a list of certificates (i.e, certified mechanics) for the vehicle
 * type. Every certificate includes full mechanic data (@see MechanicDto).
 *
 * @param id of the vehicle type
 *
 * @return the list. It might be empty if no mechanic is certified for the
 *         specified vehicle type.
 * @throws BusinessException
 */
public class FindCertificatesByVehicleTypeId
	implements Command<List<CertificateDto>> {

    private String id;
    private CertificateRepository cr = Factory.repository.forCertificate();

    public FindCertificatesByVehicleTypeId(String id) {
	this.id = id;
    }

    @Override
    public List<CertificateDto> execute() throws BusinessException {

	List<Certificate> lc = cr.findCertificatesByVehicleTypeId(id);
	return DtoAssembler.toCertificateDtoList(lc);

    }

}
