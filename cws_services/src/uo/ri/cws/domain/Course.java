package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
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
	@Column(unique = true)
	private String code;
	private String description;
	@Temporal(TemporalType.DATE)
	private Date endDate;
	private int hours;
	private String name;
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@OneToMany(mappedBy = "course")
	private Set<Enrollment> enrollments = new HashSet<Enrollment>();
	
	@OneToMany(mappedBy = "course")
	private Set<Dedication> dedications = new HashSet<Dedication>();

	Course() {
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public int getHours() {
		return hours;
	}

	public String getName() {
		return name;
	}

	public Date getStartDate() {
		return startDate;
	}

	Set<Dedication> _getDedications() {
		return dedications;
	}
	public Set<Dedication> getDedications() {
		return new HashSet<Dedication>(dedications);
	}



}
