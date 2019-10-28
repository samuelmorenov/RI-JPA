package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "INVOICE_ID", "PAYMENTMEAN_ID" }) })
public class Charge extends BaseEntity {
	@ManyToOne
	private Invoice invoice;
	@ManyToOne
	private PaymentMean paymentMean;
	private double amount;

	Charge() {
	}

	public Charge(Invoice invoice, PaymentMean paymentMean, double amount) {
		this.amount = amount;
		// store the amount
		// increment the paymentMean accumulated ( paymentMean.pay( -amount) )
		// link invoice, this and paymentMean
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * 
	 * @throws IllegalStateException if the invoice is already settled
	 */
	public void rewind() {
		// assert the invoice is not in PAID status
		// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
		// unlink invoice, this and paymentMean
	}

}
