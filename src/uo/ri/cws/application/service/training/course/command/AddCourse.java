package uo.ri.cws.application.service.training.course.command;

import java.util.Date;
import java.util.Map;

import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.repository.VehicleTypeRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.application.util.BusinessCheck;
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

	Course c = new Course(dto.code, dto.name, dto.description,
		dto.startDate, dto.endDate, dto.hours);

	BusinessCheck.isFalse(cr.findByName(dto.name).isPresent(),
		"Ya existe un curso con ese nombre");

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
    public static void validateCourse(CourseDto course)
	    throws BusinessException {
	// any field other than id and version is null
	BusinessCheck.isNotNull(course.code,
		"The code field can not be null");
	BusinessCheck.isNotNull(course.name,
		"The name field can not be null");
	BusinessCheck.isNotNull(course.description,
		"The description field can not be null");
	BusinessCheck.isNotNull(course.startDate,
		"The startDate field can not be null");
	BusinessCheck.isNotNull(course.endDate,
		"The endDate field can not be null");

	// any field other than id and version is empty
	BusinessCheck.isNotEmpty(course.code,
		"The code field can not be empty");
	BusinessCheck.isNotEmpty(course.name,
		"The name field can not be empty");
	BusinessCheck.isNotEmpty(course.description,
		"The description field can not be empty");

	// the initial date are in the past
	Date now = new Date();
	BusinessCheck.isFalse(course.startDate.getTime() < now.getTime(),
		"La fecha de inicio ya han pasado");

	// the final dates are in the past
	BusinessCheck.isFalse(course.endDate.getTime() < now.getTime(),
		"La fecha de fin ya han pasado");

	// the initial and final dates are inverted
	BusinessCheck.isTrue(
		course.startDate.getTime() < course.endDate.getTime(),
		"Las fechas estan invertidas");

	// the number of hours are zero or negative
	BusinessCheck.isFalse(course.hours <= 0,
		"El numero de horas no puede ser menor o igual a 0");

	Map<String, Integer> dedications = course.percentages;

	// there are no dedications specified
	BusinessCheck.isNotNull(dedications,
		"No se han especificado los vehiculos dedicados");
	BusinessCheck.isFalse(dedications.isEmpty(),
		"No se han especificado los vehiculos dedicados");

	// there is percentage devoted to a non existing vehicle type
	VehicleTypeRepository vtg = Factory.repository.forVehicleType();
	for (String key : dedications.keySet()) {
	    BusinessCheck.isNotNull(vtg.findById(key),
		    "Existen porcentajes asociados a vehiculos que no existen");
	}

	int suma = 0;
	for (String key : dedications.keySet()) {
	    // the are any dedication with an invalid percentage (empty)
	    BusinessCheck.isNotNull(dedications.get(key),
		    "Hay procentajes mal definidos (Vacio)");

	    // the are any dedication with an invalid percentage (zero, negative)
	    BusinessCheck.isFalse(dedications.get(key) <= 0,
		    "Hay procentajes mal definidos (cero o negativo)");
	    suma += dedications.get(key);
	}
	// the sum of devoted percentages does not equals 100%
	BusinessCheck.isTrue(suma == 100,
		"El porcentaje total debe ser del 100%");

    }

}
