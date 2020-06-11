package uo.ri.cws.application.service.training.report;

import java.util.List;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CertificateDto;
import uo.ri.cws.application.service.training.CourseReportService;
import uo.ri.cws.application.service.training.TrainingForMechanicRow;
import uo.ri.cws.application.service.training.TrainingHoursRow;
import uo.ri.cws.application.service.training.report.command.FindTrainingByVehicleTypeAndMechanic;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseReportServiceImpl implements CourseReportService {

    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public List<TrainingForMechanicRow> findTrainigByMechanicId(String id)
	    throws BusinessException {
	throw new RuntimeException("Not yet implemented.");
    }

    @Override
    public List<TrainingHoursRow> findTrainingByVehicleTypeAndMechanic()
	    throws BusinessException {

	return executor.execute(new FindTrainingByVehicleTypeAndMechanic());
	// Added in the extension

    }

    @Override
    public List<CertificateDto> findCertificatedByVehicleType()
	    throws BusinessException {
	throw new RuntimeException("Not yet implemented.");
    }

}
