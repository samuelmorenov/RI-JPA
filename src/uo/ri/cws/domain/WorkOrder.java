package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import alb.util.assertion.StateCheck;

@Entity
@Table(name = "TWORKORDERS", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "DATE", "VEHICLE_ID" }) })
public class WorkOrder extends BaseEntity {
    public enum WorkOrderStatus {
	OPEN, ASSIGNED, FINISHED, INVOICED
    }

    // DONE para todas las fechas (mirar el modelo, puede ser DATE o TIMESTAMP)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private String description;
    private double amount = 0.0;
    // DONE hacer esto para los tipos enumerados
    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status = WorkOrderStatus.OPEN;

    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne
    private Mechanic mechanic;
    @ManyToOne
    private Invoice invoice;
    @OneToMany(mappedBy = "workOrder")
    private Set<Intervention> interventions = new HashSet<Intervention>();

    WorkOrder() {
    }

    public WorkOrder(Vehicle vehicle) {
	super();
	this.date = new Date();
	Associations.Order.link(vehicle, this);
    }

    public WorkOrder(Vehicle vehicle, String description) {
	this(vehicle);
	this.description = description;
    }

    public Date getDate() {
	return new Date(date.getTime());
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public double getAmount() {
	return amount;
    }

    public WorkOrderStatus getStatus() {
	return status;
    }

    public Vehicle getVehicle() {
	return vehicle;
    }

    void _setVehicle(Vehicle vehicle) {
	this.vehicle = vehicle;
    }

    public Mechanic getMechanic() {
	return mechanic;
    }

    void _setMechanic(Mechanic mechanic) {
	this.mechanic = mechanic;
    }

    public Invoice getInvoice() {
	return invoice;
    }

    void _setInvoice(Invoice invoice) {
	this.invoice = invoice;
    }

    Set<Intervention> _getInterventions() {
	return interventions;
    }

    public Set<Intervention> getInterventions() {
	return new HashSet<Intervention>(interventions);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	result = prime * result
		+ ((vehicle == null) ? 0 : vehicle.hashCode());
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
	WorkOrder other = (WorkOrder) obj;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	if (vehicle == null) {
	    if (other.vehicle != null)
		return false;
	} else if (!vehicle.equals(other.vehicle))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "WorkOrder [date=" + date + ", description=" + description
		+ ", amount=" + amount + ", status=" + status + ", vehicle="
		+ vehicle + "]";
    }

    /**
     * Changes it to INVOICED state given the right conditions This method is called
     * from Invoice.addWorkOrder(...)
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not FINISHED, or - The
     *                               work order is not linked with the invoice
     */
    public void markAsInvoiced() {
	StateCheck.isTrue(WorkOrderStatus.FINISHED.equals(status),
		"The work order is not in FINISHED state");
	StateCheck.isTrue(this.invoice != null,
		"The work order has not a invoice");
	status = WorkOrderStatus.INVOICED;
    }

    /**
     * Changes it to FINISHED state given the right conditions and computes the
     * amount
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in ASSIGNED state,
     *                               or - The work order is not linked with a
     *                               mechanic
     */
    public void markAsFinished() {
	StateCheck.isTrue(WorkOrderStatus.ASSIGNED.equals(status),
		"The work order is not in ASSIGNED state");
	StateCheck.isTrue(this.mechanic != null,
		"The work order has not a invoice");
	status = WorkOrderStatus.FINISHED;
	computeAmount();
    }

    private void computeAmount() {
	amount = 0.0;
	for (Intervention i : interventions)
	    amount = amount + i.getAmount();
    }

    /**
     * Changes it back to FINISHED state given the right conditions This method is
     * called from Invoice.removeWorkOrder(...)
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not INVOICED, or - The
     *                               work order is still linked with the invoice
     */
    public void markBackToFinished() {
	StateCheck.isTrue(WorkOrderStatus.INVOICED.equals(status),
		"The work order is not in INVOICED state");
	StateCheck.isTrue(this.invoice == null,
		"The work order has a invoice");
	status = WorkOrderStatus.FINISHED;
    }

    /**
     * Links (assigns) the work order to a mechanic and then changes its status to
     * ASSIGNED
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in OPEN status, or -
     *                               The work order is already linked with another
     *                               mechanic
     */
    public void assignTo(Mechanic mechanic) {
	StateCheck.isTrue(WorkOrderStatus.OPEN.equals(status),
		"The work order is not in OPEN state");
	StateCheck.isTrue(this.mechanic == null,
		"The work order has a mechanic already");
	Associations.Assign.link(mechanic, this);
	status = WorkOrderStatus.ASSIGNED;
    }

    /**
     * Unlinks (deassigns) the work order and the mechanic and then changes its
     * status back to OPEN
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in ASSIGNED status
     */
    public void desassign() {
	StateCheck.isTrue(WorkOrderStatus.ASSIGNED.equals(status),
		"The work order is not in ASSIGNED state");
	Associations.Assign.unlink(mechanic, this);
	status = WorkOrderStatus.OPEN;
    }

    /**
     * In order to assign a work order to another mechanic is first have to be moved
     * back to OPEN state and unlinked from the previous mechanic.
     * 
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if - The work order is not in FINISHED status
     */
    public void reopen() {
	StateCheck.isTrue(WorkOrderStatus.FINISHED.equals(status),
		"The work order is not in FINISHED state");
	status = WorkOrderStatus.OPEN;
	Associations.Assign.unlink(mechanic, this);
    }

}
