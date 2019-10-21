package uo.ri.cws.domain;

public class Cash extends PaymentMean {

	public Cash(Client client) {
		Associations.Pay.link(this, client);
	}
	

}
