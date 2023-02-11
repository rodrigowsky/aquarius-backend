package com.rodrigowsky.aquarius.repositories;

import com.rodrigowsky.aquarius.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
}
