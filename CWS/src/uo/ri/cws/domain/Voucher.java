package uo.ri.cws.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Voucher extends PaymentMean {
	@Column(unique = true)
	private String code;
	private double available;
	private String description;

	Voucher() {
	}

	public Voucher(String code) {
		super();
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public double getAvailable() {
		return available;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Voucher other = (Voucher) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Voucher [code=" + code + ", available=" + available + ", description=" + description + "]";
	}

	/**
	 * Augments the accumulated and decrements the available
	 * 
	 * @throws IllegalStateException
	 *             if not enough available to pay
	 */
	@Override
	public void pay(double amount) {

	}

}
