package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.StateCheck;
import alb.util.date.Dates;
import alb.util.math.Round;

@Entity
@Table(name = "TINVOICES")
public class Invoice extends BaseEntity {
    public enum InvoiceStatus {
	NOT_YET_PAID, PAID
    }

    @Column(unique = true)
    private Long number;
    @Temporal(TemporalType.DATE)
    private Date date;
    private double amount;
    private double vat;
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;
    @OneToMany(mappedBy = "invoice")
    private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();
    @OneToMany(mappedBy = "invoice")
    private Set<Charge> charges = new HashSet<Charge>();

    Invoice() {
    }

    public Invoice(Long number, Date date) {
	super();
	this.number = number;
	this.date = new Date(date.getTime());
    }

    public Invoice(Long number) {
	this(number, new Date());
    }

    public Invoice(Long number, Date date, List<WorkOrder> workOrders) {
	this(number, date);
	for (WorkOrder wo : workOrders)
	    addWorkOrder(wo);
    }

    public Invoice(Long number, List<WorkOrder> workOrders) {
	this(number, new Date(), workOrders);

    }

    public Long getNumber() {
	return number;
    }

    public void setNumber(Long number) {
	this.number = number;
    }

    public Date getDate() {
	return new Date(date.getTime());
    }

    public void setDate(Date now) {
	this.date = new Date(now.getTime());

    }

    public double getAmount() {
	computeAmount();
	return amount;
    }

    public double getVat() {
	return vat;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public InvoiceStatus getStatus() {
	return status;
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    public Set<WorkOrder> getWorkOrders() {
	return new HashSet<WorkOrder>(workOrders);
    }

    Set<Charge> _getCharges() {
	return charges;
    }

    public Set<Charge> getCharges() {
	return new HashSet<Charge>(charges);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((number == null) ? 0 : number.hashCode());
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
	Invoice other = (Invoice) obj;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Invoice [number=" + number + ", date=" + date + ", amount="
		+ amount + ", vat=" + vat + ", status=" + status + "]";
    }

    /**
     * Computed amount and vat (vat depends on the date)
     */
    private void computeAmount() {
	amount = 0.0;
	for (WorkOrder workOrder : workOrders) {
	    amount += workOrder.getAmount();
	}
	vat = Dates.fromString("1/7/2012").before(date) ? 21.0 : 18.0;
	amount = amount * (1 + vat / 100); // vat included
	amount = Round.twoCents(amount);

    }

    /**
     * Adds (double links) the workOrder to the invoice and updates the amount and
     * vat
     * 
     * @param workOrder
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     */
    public void addWorkOrder(WorkOrder workOrder) {
	StateCheck.isTrue(InvoiceStatus.NOT_YET_PAID.equals(status),
		"The invoice is not in NOT_YET_PAID state");
	Associations.ToInvoice.link(workOrder, this);
	workOrder.markAsInvoiced();
	computeAmount();

    }

    /**
     * Removes a work order from the invoice and recomputes amount and vat
     * 
     * @param workOrder
     * @see State diagrams on the problem statement document
     * @throws IllegalStateException if the invoice status is not NOT_YET_PAID
     */
    public void removeWorkOrder(WorkOrder workOrder) {
	StateCheck.isTrue(InvoiceStatus.NOT_YET_PAID.equals(status),
		"The invoice is not in NOT_YET_PAID state");
	Associations.ToInvoice.unlink(workOrder, this);
	workOrder.markBackToFinished();
	computeAmount();

    }

    /**
     * Marks the invoice as PAID, but
     * 
     * @throws IllegalStateException if - Is already settled - Or the amounts paid
     *                               with charges to payment means do not cover the
     *                               total of the invoice
     */
    public void settle() {
	// Este metodo no se llama desde ningun sitio, no es necesaria su implementacion
	throw new RuntimeException("Not yet implemented.");
    }

}
