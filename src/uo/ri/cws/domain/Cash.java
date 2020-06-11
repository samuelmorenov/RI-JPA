package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TCASHES", uniqueConstraints = {
	@UniqueConstraint(columnNames = { "CLIENT_ID" }) })
public class Cash extends PaymentMean {

    Cash() {
    }

    public Cash(Client client) {
	super();
	Associations.Pay.link(this, client);
    }

}
