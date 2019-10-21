package uo.ri.cws.domain;

import java.util.Date;
import java.util.List;

public class Invoice {
	public enum InvoiceStatus { NOT_YET_PAID, PAID }

	private Long number;
	
	private Date date;
	private double amount;
	private double vat;
	private InvoiceStatus status = InvoiceStatus.NOT_YET_PAID;

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
