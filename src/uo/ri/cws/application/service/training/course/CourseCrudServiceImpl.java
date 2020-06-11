package uo.ri.cws.application.service.training.course;

import java.util.List;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.service.training.course.command.AddCourse;
import uo.ri.cws.application.service.training.course.command.DeleteCourse;
import uo.ri.cws.application.service.training.course.command.FindAllCourses;
import uo.ri.cws.application.service.training.course.command.FindAllVehicleTypes;
import uo.ri.cws.application.service.training.course.command.FindCourseById;
import uo.ri.cws.application.service.training.course.command.UpdateCourse;
import uo.ri.cws.application.service.vehicletype.VehicleTypeDto;
import uo.ri.cws.application.util.command.CommandExecutor;

public class CourseCrudServiceImpl implements CourseCrudService {

    // Added in the second extension
    private CommandExecutor executor = Factory.executor.forExecutor();

    @Override
    public CourseDto registerNew(CourseDto dto) throws BusinessException {
	// Added in the second extension
	// throw new RuntimeException("Not yet implemented.");
	return executor.execute(new AddCourse(dto));
    }

    @Override
    public void updateCourse(CourseDto dto) throws BusinessException {
	// Added in the second extension
	// throw new RuntimeException("Not yet implemented.");
	executor.execute(new UpdateCourse(dto));
    }

    @Override
    public void deleteCourse(String id) throws BusinessException {
	// Added in the second extension
	// throw new RuntimeException("Not yet implemented.");
	executor.execute(new DeleteCourse(id));
    }

    @Override
    public List<CourseDto> findAllCourses() throws BusinessException {
	// Added in the second extension
	// throw new RuntimeException("Not yet implemented.");
	return executor.execute(new FindAllCourses());
    }

    @Override
    public List<VehicleTypeDto> findAllVehicleTypes()
	    throws BusinessException {
	// Added in the second extension
	// throw new RuntimeException("Not yet implemented.");
	return executor.execute(new FindAllVehicleTypes());
    }

    @Override
    public Optional<CourseDto> findCourseById(String cId)
	    throws BusinessException {
	// Added in the second extension
	// throw new RuntimeException("Not yet implemented.");
	return executor.execute(new FindCourseById(cId));
    }

}
