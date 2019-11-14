package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TDEDICATIONS", uniqueConstraints = { @UniqueConstraint(columnNames = { "VEHICLETYPE_ID", "COURSE_ID"}) })
public class Dedication extends BaseEntity {
	private int percentage;
	@ManyToOne
	private Course course;
	@ManyToOne
	private VehicleType vehicleType;

	Dedication() {
	}
	
	public 	Dedication(VehicleType vehicleType, Course course) {
		super();
		Associations.Dedicate.link(vehicleType, course);
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

}
