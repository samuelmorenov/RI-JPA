package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TDEDICATIONS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "VEHICLETYPE_ID", "COURSE_ID" }) })
public class Dedication extends BaseEntity {
    private int percentage;
    @ManyToOne
    private Course course;
    @ManyToOne
    private VehicleType vehicleType;

    // Added in the extension - Dedication
    Dedication() {
    }

    Dedication(VehicleType vehicleType, Course course) {
	super();
	Associations.Dedicate.link(vehicleType, this, course);
    }

    Dedication(VehicleType vehicleType, Course course, int percentage) {
	this(vehicleType, course);
	this.percentage = percentage;
    }

    public int getPercentage() {
	return percentage;
    }

    public Course getCourse() {
	return course;
    }

    public VehicleType getVehicleType() {
	return vehicleType;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((course == null) ? 0 : course.hashCode());
	result = prime * result
		+ ((vehicleType == null) ? 0 : vehicleType.hashCode());
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
	Dedication other = (Dedication) obj;
	if (course == null) {
	    if (other.course != null)
		return false;
	} else if (!course.equals(other.course))
	    return false;
	if (vehicleType == null) {
	    if (other.vehicleType != null)
		return false;
	} else if (!vehicleType.equals(other.vehicleType))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Dedication [percentage=" + percentage + ", course=" + course
		+ ", vehicleType=" + vehicleType + "]";
    }

    void _setVehicleType(VehicleType vehicleType2) {
	vehicleType = vehicleType2;

    }

    void _setCourse(Course course2) {
	course = course2;

    }

}
