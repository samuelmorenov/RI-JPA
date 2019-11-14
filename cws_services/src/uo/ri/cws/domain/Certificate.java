package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TCERTIFICATES", uniqueConstraints = { @UniqueConstraint(columnNames = { "VEHICLETYPE_ID", "MECHANIC_ID"}) })
public class Certificate extends BaseEntity {
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne
	private Mechanic mechanic;
	@ManyToOne
	private VehicleType vehicleType;

	Certificate() {
	}

	public Date getDate() {
		return date;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

}
