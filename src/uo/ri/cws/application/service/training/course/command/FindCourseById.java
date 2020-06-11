package uo.ri.cws.application.service.training.course.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.DtoAssembler;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class FindCourseById implements Command<Optional<CourseDto>> {

    private String CourseId;
    private CourseRepository cr = Factory.repository.forCourse();

    public FindCourseById(String cId) {
	this.CourseId = cId;
    }

    /**
     * @param cId
     * @return an Optional, what if there is no course with the specified id? DOES
     *         NOT @throws BusinessException
     * @throws BusinessException
     */
    @Override
    public Optional<CourseDto> execute() throws BusinessException {
	Optional<Course> c = cr.findById(CourseId);
	return c.isPresent() ? Optional.of(DtoAssembler.toDto(c.get()))
		: Optional.empty();
    }

}
