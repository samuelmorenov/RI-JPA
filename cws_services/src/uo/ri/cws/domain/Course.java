package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TCURSES")
public class Course extends BaseEntity {
	@Column(unique = true, nullable = false)
	private String code;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String description;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date startDate;

	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date endDate;

	@Column(nullable = false)
	private int hours;

	@OneToMany(mappedBy = "course")
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();

	@OneToMany(mappedBy = "course")
	private Set<Dedication> dedications = new HashSet<Dedication>();

	// DONE Clases de la extension - Course
	Course() {
	}

	public Course(String code) {
		super();
		this.code = code;
		if (code.isEmpty())
			throw new IllegalArgumentException("Code can not be empty");
	}

	public Course(String code, String name, String description, Date startDate, Date endDate, int duration) {
		this(code);
		this.name = name;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.hours = duration;
		if (name.isEmpty())
			throw new IllegalArgumentException("Name can not be empty");
		if (description.isEmpty())
			throw new IllegalArgumentException("Description can not be empty");
		if (startDate == null)
			throw new IllegalArgumentException("Start date can not be empty");
		if (endDate == null)
			throw new IllegalArgumentException("End date can not be empty");
		if (startDate.after(endDate))
			throw new IllegalArgumentException("Start date can not be after end date");
		if (duration <= 0)
			throw new IllegalArgumentException("Duration must be positive");

	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Date getEndDate() {
		return new Date(endDate.getTime());
	}

	public int getHours() {
		return hours;
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return new Date(startDate.getTime());
	}

	Set<Dedication> _getDedications() {
		return dedications;
	}

	public Set<Dedication> getDedications() {
		return new HashSet<Dedication>(dedications);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Course other = (Course) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Course [code=" + code + ", description=" + description + ", endDate=" + endDate + ", hours=" + hours
				+ ", name=" + name + ", startDate=" + startDate + ", enrollments=" + enrollments + ", dedications="
				+ dedications + "]";
	}

	public void addDedications(Map<VehicleType, Integer> percentages) {
		// TODO Metodo de servicio

	}

	public void clearDedications() {
		// TODO Metodo de servicio

	}

	Set<Enrollment> _getEnrollments() {
		return enrollments;
	}

	public Set<Enrollment> getEnrollments() {
		return new HashSet<Enrollment>(enrollments);
	}

}
