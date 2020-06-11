package uo.ri.cws.application.service.training.course.command;

import java.util.Date;
import java.util.Map;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.command.Command;
import uo.ri.cws.domain.Course;

public class AddCourse implements Command<CourseDto> {

	private CourseDto dto;
	private CourseRepository cr = Factory.repository.forCourse();

	public AddCourse(CourseDto course) {
		this.dto = course;
	}

	/**
	 * Registers a new course in the system
	 * 
	 * @param dto, it must specify: name, description, startDate, endDate, hours and
	 *             the table of percentages. The id and the version fields must be
	 *             null (will be assigned by the system).
	 * 
	 * @return the same Dto with the id field assigned to the created UUID
	 * 
	 * @throws BusinessException, if: - any field other than id and version is null
	 *                            or empty, or <br>
	 *                            - there already exists a course with the same
	 *                            name, or <br>
	 *                            - there is percentage devoted to a non existing
	 *                            vehicle type, or <br>
	 *                            - the initial and final dates are in the past or
	 *                            inverted, or <br>
	 *                            - the number of hours are zero or negative, or
	 *                            <br>
	 *                            - there are no dedications specified, or <br>
	 *                            - the sum of devoted percentages does not equals
	 *                            100%, or <br>
	 *                            - the are any dedication with an invalid
	 *                            percentage (empty, zero, negative)
	 */
	@Override
	public CourseDto execute() throws BusinessException {

		validateCourse(dto);

		Course c = new Course(dto.code, dto.name, dto.description, dto.startDate, dto.endDate, dto.hours);

		if (cr.findByName(dto.name) != null) {
			throw new BusinessException("Ya existe un curso con ese nombre");
		}
		
		cr.add(c);

		dto.id = c.getId();

		return dto;
	}

	/**
	 * @throws BusinessException if: <br>
	 *                           - any field other than id and version is null or
	 *                           empty, or <br>
	 *                           - there is percentage devoted to a non existing
	 *                           vehicle type, or <br>
	 *                           - the initial and final dates are in the past or
	 *                           inverted, or <br>
	 *                           - the number of hours are zero or negative, or <br>
	 *                           - there are no dedications specified, or <br>
	 *                           - the sum of devoted percentages does not equals
	 *                           100%, or <br>
	 *                           - the are any dedication with an invalid percentage
	 *                           (empty, zero, negative)
	 */
	public static void validateCourse(CourseDto course) throws BusinessException {
		// any field other than id and version is null
		if (course.code == null || course.name == null || course.description == null || course.startDate == null
				|| course.endDate == null) {
			throw new BusinessException("No puede haber campos vacios en un curso");
		}

		// any field other than id and version is empty
		if (course.code.equals("") || course.name.equals("") || course.description.equals("")) {
			throw new BusinessException("No puede haber campos vacios en un curso");
		}

		// the initial and final dates are inverted
		if (course.startDate.getTime() >= course.endDate.getTime()) {
			throw new BusinessException("Las fechas estan invertidas");
		}
		// the initial date are in the past
		Date now = new Date();
		if (course.startDate.getTime() < now.getTime()) {
			throw new BusinessException("La fecha de inicio ya han pasado");
		}
		// the final dates are in the past
		if (course.endDate.getTime() < now.getTime()) {
			throw new BusinessException("La fecha de fin ya han pasado");
		}

		// the number of hours are zero or negative
		if (course.hours <= 0) {
			throw new BusinessException("El numero de horas no puede ser menor o igual a 0");
		}

		// there is percentage devoted to a non existing vehicle type
		VehicleTypeRepository vtg = Factory.repository.forVehicleType();
		for (String key : course.percentages.keySet()) {
			if (vtg.findById(key) == null) {
				throw new BusinessException("Existen porcentajes asociados a vehiculos que no existen");
			}
		}

		Map<String, Integer> dedications = course.percentages;

		// there are no dedications specified
		if (dedications == null) {
			throw new BusinessException("No se han especificado los vehiculos dedicados");
		}
		if (dedications.isEmpty()) {
			throw new BusinessException("No se han especificado los vehiculos dedicados");
		}

		int suma = 0;
		for (String key : dedications.keySet()) {
			// the are any dedication with an invalid percentage (empty)
			if (dedications.get(key) == null) {
				throw new BusinessException("Hay procentajes mal definidos (Vacio)");
			}
			// the are any dedication with an invalid percentage (zero, negative)
			int dedication = dedications.get(key);
			if (dedication <= 0) {
				if (dedications.get(key) == null) {
					throw new BusinessException("Hay procentajes mal definidos (cero o negativo)");
				}
			}

			suma += dedications.get(key);
		}
		// the sum of devoted percentages does not equals 100%
		if (suma != 100) {
			throw new BusinessException("El porcentaje total debe ser del 100%");
		}

	}

}
