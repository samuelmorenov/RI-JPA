package uo.ri.cws.domain;

public abstract class PaymentMean {
	private double accumulated = 0.0;
	
	public void pay(double importe) {
		this.accumulated += importe;
	}

}
