package uo.ri.cws.domain;

//TODO Anotaciones
public class Enrollment extends BaseEntity {
	private int attendance;
	private boolean passed;
	private Course course;
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
