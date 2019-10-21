package uo.ri.cws.domain;

public class Mechanic {
	private String dni;
	private String surname;
	private String name;
	public Mechanic(String dni) {
		super();
		this.dni = dni;
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
		return "Mechanic [dni=" + dni + ", surname=" + surname + ", name=" + name + "]";
	}


}
