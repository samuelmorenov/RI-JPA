package uo.ri.cws.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TCREDITCARDS")
public class CreditCard extends PaymentMean {
    @Column(unique = true)
    private String number;
    private String type;
    @Temporal(TemporalType.DATE)
    private Date validThru;

    CreditCard() {
    }

    public CreditCard(String number) {
	super();
	this.number = number;
    }

    public CreditCard(String number, String type, Date validThru) {
	this(number);
	this.type = type;
	this.validThru = validThru;
    }

    public String getNumber() {
	return number;
    }

    public String getType() {
	return type;
    }

    public Date getValidThru() {
	return new Date(validThru.getTime());
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = super.hashCode();
	result = prime * result + ((number == null) ? 0 : number.hashCode());
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
	CreditCard other = (CreditCard) obj;
	if (number == null) {
	    if (other.number != null)
		return false;
	} else if (!number.equals(other.number))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "CreditCard [number=" + number + ", type=" + type
		+ ", validThru=" + validThru + "]";
    }

}
