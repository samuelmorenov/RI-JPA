package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.Argument;

@Entity
@Table(name = "TENROLLMENTS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "COURSE_ID", "MECHANIC_ID" }) })
public class Enrollment extends BaseEntity {
    private int attendance;
    private boolean passed;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Mechanic mechanic;

    // Added in the extension - Enrollment
    Enrollment() {
    }

    public Enrollment(Course course, Mechanic mechanic) {
	super();
	Associations.Enroll.link(course, this, mechanic);
    }

    public Enrollment(Mechanic mechanic2, Course course2, int attendance,
	    boolean passed) {
	this(course2, mechanic2);
	this.passed = passed;
	this.attendance = attendance;

	Argument.isTrue(!(attendance < 85 && passed),
		"If attendance < 85% can not be passed");
    }

    public int getAttendance() {
	return attendance;
    }

    public boolean isPassed() {
	return passed;
    }

    public Course getCourse() {
	return course;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    void _setCourse(Course course) {
	this.course = course;
    }

    void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((course == null) ? 0 : course.hashCode());
	result = prime * result
		+ ((mechanic == null) ? 0 : mechanic.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (!super.equals(obj))
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Enrollment other = (Enrollment) obj;
	if (course == null) {
	    if (other.course != null)
		return false;
	} else if (!course.equals(other.course))
	    return false;
	if (mechanic == null) {
	    if (other.mechanic != null)
		return false;
	} else if (!mechanic.equals(other.mechanic))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Enrollment [attendance=" + attendance + ", passed=" + passed
		+ ", course=" + course + ", mechanic=" + mechanic + "]";
    }

    public int getAttendedHoursFor(VehicleType car) {
	for (Dedication e : this.course.getDedications())
	    if (e.getVehicleType().equals(car))
		return (int) (e.getPercentage() * attendance / 100);
	return 0;
    }

}
