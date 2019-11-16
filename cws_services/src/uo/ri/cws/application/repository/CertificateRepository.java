package uo.ri.cws.application.repository;

import java.util.List;

import uo.ri.cws.domain.Certificate;

//DONE
public interface CertificateRepository extends Repository<Certificate> {

	List<Certificate> findCertificatesByVehicleTypeId(String id);

}
