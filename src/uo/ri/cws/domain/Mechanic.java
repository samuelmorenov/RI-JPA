package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TMECHANICS")
public class Mechanic extends BaseEntity {
    @Column(unique = true)
    private String dni;
    private String surname;
    private String name;

    @OneToMany(mappedBy = "mechanic")
    private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

    @OneToMany(mappedBy = "mechanic")
    private Set<Intervention> interventions = new HashSet<Intervention>();

    @OneToMany(mappedBy = "mechanic")
    private Set<Certificate> certificates = new HashSet<Certificate>();

    @OneToMany(mappedBy = "mechanic")
    private Set<Enrollment> enrollments = new HashSet<Enrollment>();

    // Added in the extension - Mechanic
    Mechanic() {
    }

    public Mechanic(String dni) {
	super();
	this.dni = dni;
    }

    public Mechanic(String dni, String name, String surname) {
	this(dni);
	this.name = name;
	this.surname = surname;
    }

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getSurname() {
	return surname;
    }

    public String getName() {
	return name;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public void setName(String name) {
	this.name = name;
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    public Set<WorkOrder> getWorkOrders() {
	return new HashSet<WorkOrder>(workOrders);
    }

    public Set<WorkOrder> getAssigned() {
	return this.getWorkOrders();
    }

    Set<Intervention> _getInterventions() {
	return interventions;
    }

    public Set<Intervention> getInterventions() {
	return new HashSet<Intervention>(interventions);
    }

    Set<Certificate> _getCertificates() {

	return this.certificates;
    }

    public Set<Certificate> getCertificates() {
	return new HashSet<Certificate>(certificates);
    }

    Set<Enrollment> _getEnrollments() {
	return enrollments;
    }

    public Set<Enrollment> getEnrollments() {
	return new HashSet<Enrollment>(enrollments);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
	Mechanic other = (Mechanic) obj;
	if (dni == null) {
	    if (other.dni != null)
		return false;
	} else if (!dni.equals(other.dni))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Mechanic [dni=" + dni + ", surname=" + surname + ", name="
		+ name + "]";
    }

    public Set<Enrollment> getEnrollmentsFor(VehicleType car) {
	HashSet<Enrollment> result = new HashSet<Enrollment>();

	for (Enrollment e : enrollments)
	    for (Dedication d : e.getCourse().getDedications())
		if (d.getVehicleType().equals(car))
		    result.add(e);

	return result;
    }

    public boolean isCertifiedFor(VehicleType car) {

	for (Certificate c : certificates)
	    if (c.getVehicleType().equals(car))
		return true;

	return false;
    }

}
