package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Intervention extends BaseEntity {
	@Transient
	private WorkOrder workOrder;
	@Transient
	private Mechanic mechanic;

	private Date date;
	private int minutes;
	@Transient
	private Set<Substitution> substitutions = new HashSet<Substitution>();

	Intervention() {
	}

	public Intervention(WorkOrder workOrder, Mechanic mechanic) {
		this.date = new Date();
		Associations.Intervene.link(workOrder, this, mechanic);
	}

	public Intervention(Mechanic mechanic, WorkOrder workOrder, int minutes) {
		this(workOrder, mechanic);
		this.minutes = minutes;
	}

	public WorkOrder getWorkOrder() {
		return workOrder;
	}

	void _setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}

	public Mechanic getMechanic() {
		return mechanic;
	}

	void _setMechanic(Mechanic mechanic) {
		this.mechanic = mechanic;
	}

	public Date getDate() {
		return date;
	}

	public int getMinutes() {
		return minutes;
	}

	Set<Substitution> _getSubstitutions() {
		return substitutions;
	}

	public Set<Substitution> getSubstitutions() {
		return new HashSet<Substitution>(substitutions);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result + ((workOrder == null) ? 0 : workOrder.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervention other = (Intervention) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		if (workOrder == null) {
			if (other.workOrder != null)
				return false;
		} else if (!workOrder.equals(other.workOrder))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Intervention [workOrder=" + workOrder + ", mechanic=" + mechanic + ", date=" + date + ", minutes="
				+ minutes + "]";
	}

	public Set<Substitution> getSustitutions() {
		// TODO para arreglar los test
		return this.getSubstitutions();
	}

}
