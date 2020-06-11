package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCLIENTS") // DONE hacer esto para todas las tablas
public class Client extends BaseEntity {
    @Column(unique = true)
    private String dni;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private Address address;

    @OneToMany(mappedBy = "client")
    private Set<Vehicle> vehicles = new HashSet<Vehicle>();

    @OneToMany(mappedBy = "client")
    private Set<PaymentMean> paymentMeans = new HashSet<PaymentMean>();

    Client() {
    }

    public Client(String dni) {
	super();
	this.dni = dni;
    }

    public Client(String dni, String name, String surname) {
	this(dni);
	this.name = name;
	this.surname = surname;
    }

    public String getDni() {
	return dni;
    }

    public String getName() {
	return name;
    }

    public String getSurname() {
	return surname;
    }

    public String getEmail() {
	return email;
    }

    public String getPhone() {
	return phone;
    }

    public Address getAddress() {
	return address;
    }

    public void setAddress(Address address2) {
	address = address2;

    }

    // El _ es solo de paquetes
    Set<Vehicle> _getVehicles() {
	return vehicles;
    }

    // El que no tiene _ devuelve una copia, es publico
    public Set<Vehicle> getVehicles() {
	return new HashSet<Vehicle>(vehicles);
    }

    Set<PaymentMean> _getPaymentMeans() {
	return paymentMeans;
    }

    public Set<PaymentMean> getPaymentMeans() {
	return new HashSet<PaymentMean>(paymentMeans);
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
	Client other = (Client) obj;
	if (dni == null) {
	    if (other.dni != null)
		return false;
	} else if (!dni.equals(other.dni))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Client [dni=" + dni + ", name=" + name + ", surname="
		+ surname + ", email=" + email + ", phone=" + phone
		+ ", address=" + address + "]";
    }

}
