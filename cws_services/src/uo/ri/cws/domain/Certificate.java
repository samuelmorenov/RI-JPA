package uo.ri.cws.domain;

import java.util.Date;

//TODO Anotaciones
public class Certificate extends BaseEntity {
	private Date date;
	private Mechanic Mechanic;
	private VehicleType VehicleType;

	Certificate() {
	}

	public Date getDate() {
		return date;
	}

	public Mechanic getMechanic() {
		return Mechanic;
	}

	public VehicleType getVehicleType() {
		return VehicleType;
	}

}
