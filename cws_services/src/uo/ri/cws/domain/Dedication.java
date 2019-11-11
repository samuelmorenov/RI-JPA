package uo.ri.cws.domain;

//TODO Anotaciones
public class Dedication extends BaseEntity {
	private int percentage;
	private Course course;
	private VehicleType vehicleType;

	Dedication() {
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
