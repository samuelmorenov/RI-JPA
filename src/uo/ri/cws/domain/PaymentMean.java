package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Define la herencia
@Table(name = "TPAYMENTMEANS")
public abstract class PaymentMean extends BaseEntity {
    private double accumulated = 0.0;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "paymentMean")
    private Set<Charge> charges = new HashSet<Charge>();

    public Client getClient() {
	return client;
    }

    void _setClient(Client client) {
	this.client = client;
    }

    Set<Charge> _getCharges() {
	return charges;
    }

    public Set<Charge> getCharges() {
	return new HashSet<Charge>(charges);
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
	return "PaymentMean [accumulated=" + accumulated + ", client="
		+ client + "]";
    }

    public void pay(double amount) {
	this.accumulated += amount;
    }

}
