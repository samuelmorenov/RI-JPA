package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TCERTIFICATES", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "VEHICLETYPE_ID", "MECHANIC_ID" }) })
public class Certificate extends BaseEntity {
	@Temporal(TemporalType.DATE)
	private Date date;
	@ManyToOne
	private Mechanic mechanic;
	@ManyToOne
	private VehicleType vehicleType;

	// Added in the extension - Certificate
	Certificate() {
	}

	public Certificate(Mechanic mechanic, VehicleType vehicleType) {
		super();
		this.date = new Date();
		Associations.Certify.link(mechanic, this, vehicleType);
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;

	}

	void _setVehicleType(VehicleType vehicleType2) {
		vehicleType = vehicleType2;

	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result + ((vehicleType == null) ? 0 : vehicleType.hashCode());
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
		Certificate other = (Certificate) obj;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
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
		return "Certificate [date=" + date + ", mechanic=" + mechanic + ", vehicleType=" + vehicleType + "]";
	}

}
