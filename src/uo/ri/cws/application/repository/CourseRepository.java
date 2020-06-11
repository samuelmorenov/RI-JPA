package uo.ri.cws.application.repository;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.domain.Course;

//Added in the second extension
public interface CourseRepository extends Repository<Course> {

    List<Course> findAll();

    Optional<Course> findByName(String name);

}
