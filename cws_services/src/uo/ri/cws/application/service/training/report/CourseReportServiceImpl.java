package uo.ri.cws.application.service.training.report;

import java.util.List;

import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;

//TODO Impl - CourseReportServiceImpl
public class CourseReportServiceImpl implements CourseReportService {

	@Override
	public List<TrainingForMechanicRow> findTrainigByMechanicId(String id) throws BusinessException {
		
		return null;
	}

	@Override
	public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic() throws BusinessException {
		
		return null;
	}

	@Override
	public List<CertificateDto> findCertificatedByVehicleType() throws BusinessException {
		
		return null;
	}

}
