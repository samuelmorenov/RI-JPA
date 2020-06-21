package uo.ri.cws.application.service.training.course.command;

import java.util.Date;
import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class UpdateCourse implements Command<Void> {

    private CourseDto course;
    private CourseRepository cr = Factory.repository.forCourse();

    public UpdateCourse(CourseDto dto) {
	this.course = dto;
    }

    /**
     * Updates the course specified by the id with the new data. A course an only be
     * modified if it has not yet started. If the start date is wrong then remove
     * the course and start again... The dedications of the course to the vehicle
     * types are not modified by this operation.
     * 
     * @param dto. Must specify all the fields. The id and version fields must match
     *             the current state of the course. All the rest of fields must be
     *             filled, even if there is no change in the data. So it must pass
     *             the same validation as for new courses.
     * 
     * @throws BusinessException if: <br>
     *                           - it does not exist the course with the specified
     *                           id, or <br>
     *                           - the current version of the course does not match
     *                           the version of the dto, or <br>
     *                           - the course has its start date in the past, or
     *                           <br>
     *                           - the new data does not pass the validation
     *                           specified in @see registerNew
     * 
     */
    @Override
    public Void execute() throws BusinessException {
	AddCourse.validateCourse(course);

	Optional<Course> oc = cr.findById(course.id);
	BusinessCheck.exists(oc, "There is no such course");

	Course c = oc.get();
	BusinessCheck.hasVersion(c, course.version);
	BusinessCheck.isFalse(c.getStartDate().before(new Date()),
		"El curso ya ha empezado");

	BusinessCheck.isTrue(course.version == c.getVersion(),
		"The current version of the course does not match the new version");

	if (c.getName() != course.name) {
	    BusinessCheck.isFalse(cr.findByName(course.name).isPresent(),
		    "Ya existe un curso con ese nombre");
	}

	update(c, course);

	return null;
    }

    private void update(Course c, CourseDto dto) {
	c.setCode(dto.code);
	c.setDescription(dto.description);
	c.setEndDate(dto.endDate);
	c.setHours(dto.hours);
	c.setName(dto.name);
	c.setStartDate(dto.startDate);
    }

}
