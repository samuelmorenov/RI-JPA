package uo.ri.cws.domain;

public abstract class PaymentMean {
	private double accumulated = 0.0;

	private Client client;

	public Client getClient() {
		return client;
	}

	void _setClient(Client client) {
		this.client = client;
	}

	public double getAccumulated() {
		return accumulated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((client == null) ? 0 : client.hashCode());
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
		PaymentMean other = (PaymentMean) obj;
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PaymentMean [accumulated=" + accumulated + ", client=" + client + "]";
	}

	public void pay(double importe) {
		this.accumulated += importe;
	}

}
