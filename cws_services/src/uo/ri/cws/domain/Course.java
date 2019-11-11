package uo.ri.cws.domain;

import java.util.Date;

//TODO Anotaciones
public class Course extends BaseEntity {
	private String code;
	private String description;
	private Date endDate;
	private int hours;
	private String name;
	private Date startDate;

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

	public Object getDedications() {
		// TODO Falta la lista de esto
		return null;
	}

}
