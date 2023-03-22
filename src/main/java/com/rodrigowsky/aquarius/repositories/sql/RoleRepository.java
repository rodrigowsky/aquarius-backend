package com.rodrigowsky.aquarius.repositories.sql;

import com.rodrigowsky.aquarius.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

//    List<Role> findRolesByUsersId(Long userId);
}
