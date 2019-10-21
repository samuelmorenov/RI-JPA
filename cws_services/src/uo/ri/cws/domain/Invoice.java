package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Invoice {
	public enum InvoiceStatus { NOT_YET_PAID, PAID }

	private Long number;
	
	private Date date;
	private double amount;
	private double vat;
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;
	
	private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

	public Invoice(Long number) {
		this(number, new Date());
	}

	public Invoice(Long number, Date date) {
		// check arguments (always), through IllegalArgumentException
		// store the number
		// store a copy of the date
	}

	public Invoice(Long number, List<WorkOrder> workOrders) {

	}

	public Invoice(Long number, Date date, List<WorkOrder> workOrders) {

	}
	
	

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public Date getDate() {
		return date;
	}

	public double getAmount() {
		return amount;
	}

	public double getVat() {
		return vat;
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
		return "Invoice [number=" + number + ", date=" + date + ", amount=" + amount + ", vat=" + vat + ", status="
				+ status + "]";
	}

	/**
	 * Computed amount and vat (vat depends on the date)
	 */
	private void computeAmount() {

	}

	/**
	 * Adds (double links) the workOrder to the invoice and updates the amount and vat 
	 * @param workOrder
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID  
	 */
	public void addWorkOrder(WorkOrder workOrder) {

	}

	/**
	 * Removes a work order from the invoice and recomputes amount and vat
	 * @param workOrder
	 * @see State diagrams on the problem statement document
	 * @throws IllegalStateException if the invoice status is not NOT_YET_PAID  
	 */
	public void removeWorkOrder(WorkOrder workOrder) {

	}

	/**
	 * Marks the invoice as PAID, but
	 * @throws IllegalStateException if
	 * 	- Is already settled 
	 *  - Or the amounts paid with charges to payment means do not cover 
	 *  	the total of the invoice  
	 */
	public void settle() {

	}

}
