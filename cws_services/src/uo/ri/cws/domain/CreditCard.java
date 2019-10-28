package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CreditCard extends PaymentMean {
	@Column(unique = true)
	private String number;
	private String type;
	private Date validThru;

}
