package uo.ri.cws.infrastructure.persistence.jpa.repository;

import java.util.Optional;

import uo.ri.cws.application.repository.CourseRepository;
import uo.ri.cws.domain.Course;
import uo.ri.cws.infrastructure.persistence.jpa.util.BaseJpaRepository;
import uo.ri.cws.infrastructure.persistence.jpa.util.Jpa;

//Added in the second extension
public class CourseJpaRepository extends BaseJpaRepository<Course>
	implements CourseRepository {

    @Override
    public Optional<Course> findByName(String name) {
	// TODO: Esto es asi?
	Course m = Jpa.getManager().find(Course.class, name);
	return m != null ? Optional.of(m) : Optional.empty();
    }

}
