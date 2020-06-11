package uo.ri.cws.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TVEHICLES")
public class Vehicle extends BaseEntity {
    @Column(unique = true)
    private String plateNumber;
    @Basic(optional = false)
    // @Column(name = "brand")
    // Se puso brand para que funcionara con la bd, pero ya no es necesario
    private String make;
    private String model;

    @ManyToOne
    private Client client;
    @ManyToOne
    private VehicleType vehicleType;
    @OneToMany(mappedBy = "vehicle")
    private Set<WorkOrder> workOrders = new HashSet<WorkOrder>();

    Vehicle() {
    }

    public Vehicle(String plateNumber) {
	super();
	this.plateNumber = plateNumber;
    }

    public Vehicle(String plateNumber, String make, String model) {
	this(plateNumber);
	this.make = make;
	this.model = model;
    }

    public String getPlateNumber() {
	return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
	this.plateNumber = plateNumber;
    }

    public String getMake() {
	return make;
    }

    public String getModel() {
	return model;
    }

    // Es publico porque no se puede modificar
    public Client getClient() {
	return client;
    }

    // Es privado porque no se puede modificar
    void _setClient(Client client) {
	this.client = client;
    }

    public VehicleType getVehicleType() {
	return vehicleType;
    }

    void _setVehicleType(VehicleType vehicletype) {
	this.vehicleType = vehicletype;
    }

    Set<WorkOrder> _getWorkOrders() {
	return workOrders;
    }

    public Set<WorkOrder> getWorkOrders() {
	return new HashSet<WorkOrder>(workOrders);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((plateNumber == null) ? 0 : plateNumber.hashCode());
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
	Vehicle other = (Vehicle) obj;
	if (plateNumber == null) {
	    if (other.plateNumber != null)
		return false;
	} else if (!plateNumber.equals(other.plateNumber))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return "Vehicle [plateNumber=" + plateNumber + ", make=" + make
		+ ", model=" + model + "]";
    }

}
