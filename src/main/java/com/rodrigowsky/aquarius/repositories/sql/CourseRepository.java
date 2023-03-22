package com.rodrigowsky.aquarius.repositories.sql;

import com.rodrigowsky.aquarius.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
}
