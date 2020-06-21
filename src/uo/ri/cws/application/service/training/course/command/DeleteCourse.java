package uo.ri.cws.application.service.training.course.command;

import java.util.Optional;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.DedicationRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.util.BusinessCheck;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;
import uo.ri.cws.domain.Dedication;

public class DeleteCourse implements Command<Void> {

    private String courseId;
    private CourseRepository cr = Factory.repository.forCourse();
    private DedicationRepository dr = Factory.repository.forDedication();

    public DeleteCourse(String id) {
	this.courseId = id;
    }

    /**
     * A course can only be deleted if it still has no attendance registered. Delete
     * a course also implies remove all its dedications in cascade.
     * 
     * Note: A course and its dedications form an aggregate.
     * 
     * @param id
     * @throws BusinessException if: - there is no course with the specified id, or
     *                           - the course already has enrollments registered.
     */
    @Override
    public Void execute() throws BusinessException {
	Optional<Course> om = cr.findById(courseId);

	BusinessCheck.isTrue(om.isPresent(), "This course does not exist");

	Course c = om.get();
	BusinessCheck.isTrue(c.getEnrollments().size() == 0,
		"The course already has enrollments registered");

	for (Dedication dedication : c.getDedications()) {
	    dr.remove(dedication);
	}

	cr.remove(c);

	return null;
    }

}
