package uo.ri.cws.CRUDCourses;

import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.conf.Factory;
import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.application.service.BusinessException;
import uo.ri.cws.application.service.BusinessFactory;
import uo.ri.cws.application.service.training.CourseCrudService;
import uo.ri.cws.application.service.training.CourseDto;
import uo.ri.cws.domain.Course;
import uo.ri.cws.infrastructure.persistence.jpa.executor.JpaExecutorFactory;
import uo.ri.cws.infrastructure.persistence.jpa.repository.JpaRepositoryFactory;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

public class AddCourseTests {

    private Date startDate = Dates.addDays(Dates.today(), 15);
    private Date endDate = Dates.addDays(startDate, 5);
    CourseDto curso;

    @Before
    public void setUp() throws Exception {
	Factory.service = new BusinessFactory();
	Factory.repository = new JpaRepositoryFactory();
	Factory.executor = new JpaExecutorFactory();
	curso = new CourseDto();
	inicializarCurso();

    }

    @After
    public void after() throws Exception {
	borrarBD(curso.name);
	borrarBD("Test course name 2");

    }

    private void inicializarCurso() {
	curso.code = "Test course code";
	curso.name = "Test course name";
	curso.description = "Test course descripcion";
	curso.hours = 15;
	curso.startDate = startDate;
	curso.endDate = endDate;
	curso.percentages = new HashMap<String, Integer>();
	curso.percentages.put("564b1950-d0bc-4fe9-8a5d-680a35a41e54", 100);
    }

    private void borrarBD(String name) throws BusinessException {

	Jpa.createEntityManager();
	CourseRepository cr = Factory.repository.forCourse();
	Optional<Course> aux = cr.findByName(name);
	if (aux.isPresent()) {
	    Course auxC = aux.get();
	    CourseCrudService ccs = Factory.service.forCourseCrudService();
	    ccs.deleteCourse(auxC.getId());
	}
    }

    /**
     * Todos los datos son correctos
     */
    @Test
    public void AniadirCursoCorrecto() throws BusinessException {
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	ccs.registerNew(curso);
	assertTrue(true);
    }

    /**
     * Hay un curso con el mismo nombre
     */
    @Test
    public void AniadirCursoNombreRepetido() throws BusinessException {
	CourseCrudService ccs = Factory.service.forCourseCrudService();

	CourseDto curso2 = new CourseDto();
	curso2.code = "Test course code 2";
	curso2.name = "Test course name";
	curso2.description = "Test course descripcion";
	curso2.hours = 15;
	curso2.startDate = startDate;
	curso2.endDate = endDate;
	curso2.percentages = new HashMap<String, Integer>();
	curso2.percentages.put("564b1950-d0bc-4fe9-8a5d-680a35a41e54", 100);

	ccs.registerNew(curso);

	try {
	    ccs.registerNew(curso2);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("Ya existe un curso con ese nombre"));
	}

    }

    /**
     * Hay campos null
     */
    @Test
    public void AniadirCursoCamposNull() {
	CourseCrudService ccs = Factory.service.forCourseCrudService();

	curso.code = null;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The code field can not be null"));
	}

	inicializarCurso();
	curso.name = null;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The name field can not be null"));
	}

	inicializarCurso();
	curso.description = null;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The description field can not be null"));
	}

	inicializarCurso();
	curso.startDate = null;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The startDate field can not be null"));
	}

	inicializarCurso();
	curso.endDate = null;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The endDate field can not be null"));
	}

    }

    /**
     * Hay campos vacios
     */
    @Test
    public void AniadirCursoCamposVacios() throws BusinessException {
	CourseCrudService ccs = Factory.service.forCourseCrudService();

	curso.code = "";
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The code field can not be empty"));
	}

	borrarBD(curso.name);
	inicializarCurso();
	curso.name = "";
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The name field can not be empty"));
	}

	inicializarCurso();
	curso.description = "";
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("The description field can not be empty"));
	}

    }

    /**
     * Fechas incorrectas
     */
    @Test
    public void AniadirCursoFechasIncorrectas() {
	// Inicio > Fin
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	curso.startDate = Dates.addDays(Dates.today(), 5);
	curso.endDate = Dates.addDays(startDate, 15);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(
		    e.getMessage().contains("Las fechas estan invertidas"));
	}
	// Inicio > Hoy
	inicializarCurso();
	curso.startDate = Dates.subDays(Dates.today(), 5);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("La fecha de inicio ya han pasado"));
	}
	// Fin > Hoy
	inicializarCurso();
	curso.endDate = Dates.subDays(Dates.today(), 5);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(
		    e.getMessage().contains("La fecha de fin ya han pasado"));
	}

    }

    /**
     * Horas incorrectas
     */
    @Test
    public void AniadirCursoNumeroDeHorasIncorrecto() {
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	curso.hours = -1;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage().contains(
		    "El numero de horas no puede ser menor o igual a 0"));
	}
	inicializarCurso();
	curso.hours = 0;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage().contains(
		    "El numero de horas no puede ser menor o igual a 0"));
	}

    }

    /**
     * Existen porcentajes asociados a vehiculos que no existen
     */
    @Test
    public void AniadirCursoVehiculosIncorrectos() {
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	curso.percentages = new HashMap<String, Integer>();
	curso.percentages.put("ID_Inexistente", 100);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage().contains(
		    "Existen porcentajes asociados a vehiculos que no existen"));
	}
    }

    /**
     * No se han especificado los vehiculos dedicados
     */
    @Test
    public void AniadirCursoSinVehiculos() {
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	curso.percentages = new HashMap<String, Integer>();
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage().contains(
		    "No se han especificado los vehiculos dedicados"));
	}

	inicializarCurso();
	curso.percentages = null;
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage().contains(
		    "No se han especificado los vehiculos dedicados"));
	}

    }

    /**
     * El porcentaje total debe ser del 100%
     */
    @Test
    public void AniadirCursoPorcentajeTotalIncorrecto() {
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	curso.percentages = new HashMap<String, Integer>();
	curso.percentages.put("564b1950-d0bc-4fe9-8a5d-680a35a41e54", 25);
	curso.percentages.put("ed4623fc-703a-4320-9147-37ac97b9736f", 25);
	curso.percentages.put("de69e6ce-875a-4ab6-9b6c-f622d96872a0", 25);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("El porcentaje total debe ser del 100%"));
	}

	inicializarCurso();
	curso.percentages = new HashMap<String, Integer>();
	curso.percentages.put("564b1950-d0bc-4fe9-8a5d-680a35a41e54", 35);
	curso.percentages.put("ed4623fc-703a-4320-9147-37ac97b9736f", 35);
	curso.percentages.put("de69e6ce-875a-4ab6-9b6c-f622d96872a0", 35);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage()
			.contains("El porcentaje total debe ser del 100%"));
	}
    }

    /**
     * Hay procentajes mal definidos
     */
    @Test
    public void AniadirCursoPorcentajesIncorrectos() {
	CourseCrudService ccs = Factory.service.forCourseCrudService();
	curso.percentages = new HashMap<String, Integer>();
	curso.percentages.put("564b1950-d0bc-4fe9-8a5d-680a35a41e54", -25);
	curso.percentages.put("ed4623fc-703a-4320-9147-37ac97b9736f", 25);
	curso.percentages.put("de69e6ce-875a-4ab6-9b6c-f622d96872a0", 100);
	try {
	    ccs.registerNew(curso);
	} catch (BusinessException e) {
	    assertTrue(e.getMessage().contains(
		    "Hay procentajes mal definidos (cero o negativo)"));
	}

    }

}
