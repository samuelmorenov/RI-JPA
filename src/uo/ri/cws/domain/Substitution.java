package uo.ri.cws.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TSUBSTITUTIONS", uniqueConstraints = { @UniqueConstraint(
	columnNames = { "SPAREPART_ID", "INTERVENTION_ID" }) })
public class Substitution extends BaseEntity {
    @ManyToOne
    private SparePart sparePart;
    @ManyToOne
    private Intervention intervention;
    private int quantity;

    Substitution() {
    }

    public Substitution(SparePart sparePart, Intervention intervention) {
	super();
	Associations.Sustitute.link(sparePart, this, intervention);
    }

    public Substitution(SparePart sparePart2, Intervention intervention2,
	    int i) {
	this(sparePart2, intervention2);
	if (i <= 0)
	    throw new IllegalArgumentException(
		    "Substitution with negative spare parts");
	this.quantity = i;
    }

    public SparePart getSparePart() {
	return sparePart;
    }

    public Intervention getIntervention() {
	return intervention;
    }

    public int getQuantity() {
	return quantity;
    }

    void _setSparePart(SparePart sparePart) {
	this.sparePart = sparePart;
    }

    void _setIntervention(Intervention intervention) {
	this.intervention = intervention;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((intervention == null) ? 0 : intervention.hashCode());
	result = prime * result
		+ ((sparePart == null) ? 0 : sparePart.hashCode());
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
	Substitution other = (Substitution) obj;
	if (intervention == null) {
	    if (other.intervention != null)
		return false;
	} else if (!intervention.equals(other.intervention))
	    return false;
	if (sparePart == null) {
	    if (other.sparePart != null)
		return false;
	} else if (!sparePart.equals(other.sparePart))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Substitution [sparePart=" + sparePart + ", intervention="
		+ intervention + ", quantity=" + quantity + "]";
    }

    public double getAmount() {
	return quantity * sparePart.getPrice();
    }

    public double getImporte() {
	return getAmount(); // Para pasar los test
    }

}
