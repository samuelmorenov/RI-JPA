package uo.ri.cws.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import alb.util.assertion.Argument;
import alb.util.assertion.StateCheck;

@Entity
@Table(name = "TCOURSES")
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

    // Added in the extension - Course
    Course() {
    }

    public Course(String code) {
	super();
	this.code = code;

	Argument.isTrue(code != null && !code.trim().isEmpty(),
		"Code can not be empty");
    }

    public Course(String code, String name, String description,
	    Date startDate, Date endDate, int duration) {
	this(code);
	this.name = name;
	this.description = description;
	this.startDate = startDate;
	this.endDate = endDate;
	this.hours = duration;

	Argument.isTrue(name != null && !name.trim().isEmpty(),
		"Name can not be empty");
	Argument.isTrue(description != null && !description.trim().isEmpty(),
		"Description can not be empty");
	Argument.isNotNull(startDate, "Start date can not be empty");
	Argument.isNotNull(endDate, "End date can not be empty");
	Argument.isTrue(startDate.before(endDate),
		"Start date can not be after end date");
	Argument.isTrue(duration > 0, "Duration must be positive");

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
    ///////////////////////////////////////////////////////

    public void setCode(String code) {
	this.code = code;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    public void setHours(int hours) {
	this.hours = hours;
    }

    ///////////////////////////////////////////////////////

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
	return "Course [code=" + code + ", description=" + description
		+ ", endDate=" + endDate + ", hours=" + hours + ", name="
		+ name + ", startDate=" + startDate + ", enrollments="
		+ enrollments + ", dedications=" + dedications + "]";
    }

    public void addDedications(Map<VehicleType, Integer> percentages) {

	StateCheck.isTrue(this.dedications.isEmpty(),
		"Cannot add dedications if course already has dedications");

	int total = 0;
	for (Entry<VehicleType, Integer> e : percentages.entrySet())
	    total += e.getValue();
	Argument.isTrue(total == 100, "Percentages must be 100%");

	for (Entry<VehicleType, Integer> e : percentages.entrySet())
	    dedications.add(new Dedication(e.getKey(), this, e.getValue()));

    }

    public void clearDedications() {

	while (!dedications.isEmpty())
	    Associations.Dedicate.unlink(dedications.iterator().next());

    }

    Set<Enrollment> _getEnrollments() {
	return enrollments;
    }

    public Set<Enrollment> getEnrollments() {
	return new HashSet<Enrollment>(enrollments);
    }

}
