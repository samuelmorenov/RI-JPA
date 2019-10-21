package uo.ri.cws.domain;

public class Voucher extends PaymentMean {
	private String code;
	private double available;
	private String description;

	/**
	 * Augments the accumulated and decrements the available 
	 * @throws IllegalStateException if not enough available to pay
	 */
	@Override
	public void pay(double amount) {

	}

}
