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

		// store the amount
		this.amount = amount;
		// increment the paymentMean accumulated ( )
		paymentMean.pay(-amount);
		// link invoice, this and paymentMean
		Associations.Charges.link(invoice, this, paymentMean);
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public PaymentMean getPaymentMean() {
		return paymentMean;
	}

	public double getAmount() {
		return amount;
	}
	

	void _setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}


	void _setPaymentMean(PaymentMean paymentMean) {
		this.paymentMean = paymentMean;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((invoice == null) ? 0 : invoice.hashCode());
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
		Charge other = (Charge) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (invoice == null) {
			if (other.invoice != null)
				return false;
		} else if (!invoice.equals(other.invoice))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Charge [invoice=" + invoice + ", paymentMean=" + paymentMean + ", amount=" + amount + "]";
	}

	/**
	 * Unlinks this charge and restores the value to the payment mean
	 * 
	 * @throws IllegalStateException
	 *             if the invoice is already settled
	 */
	public void rewind() {
		// assert the invoice is not in PAID status
		// decrement the payment mean accumulated ( paymentMean.pay( -amount) )
		// unlink invoice, this and paymentMean
	}

}
