package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TINTERVENTIONS", uniqueConstraints = { @UniqueConstraint(
	columnNames = { "WORKORDER_ID", "MECHANIC_ID", "DATE" }) })
public class Intervention extends BaseEntity {
    @ManyToOne
    private WorkOrder workOrder;
    @ManyToOne
    private Mechanic mechanic;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private int minutes;
    @OneToMany(mappedBy = "intervention")
    private Set<Substitution> substitutions = new HashSet<Substitution>();

    Intervention() {
    }

    public Intervention(WorkOrder workOrder, Mechanic mechanic) {
	super();
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
	return new Date(date.getTime());
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

    public Set<Substitution> getSustitutions() {
	// Para arreglar los test
	return this.getSubstitutions();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result
		+ ((mechanic == null) ? 0 : mechanic.hashCode());
	result = prime * result
		+ ((workOrder == null) ? 0 : workOrder.hashCode());
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
	return "Intervention [workOrder=" + workOrder + ", mechanic="
		+ mechanic + ", date=" + date + ", minutes=" + minutes + "]";
    }

    public double getAmount() {
	double amount = 0.0;
	for (Substitution s : substitutions)
	    amount = amount + s.getAmount();
	amount = amount + (minutes
		* workOrder.getVehicle().getVehicleType().getPricePerHour()
		/ 60);
	return amount;
    }

}
