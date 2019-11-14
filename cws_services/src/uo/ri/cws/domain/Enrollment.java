package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TENROLLMENTS", uniqueConstraints = { @UniqueConstraint(columnNames = { "COURSE_ID", "MECHANIC_ID"}) })
public class Enrollment extends BaseEntity {
	private int attendance;
	private boolean passed;
	@ManyToOne
	private Course course;
	@ManyToOne
	private Mechanic mechanic;

	Enrollment() {
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

}
