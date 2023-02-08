package com.rodrigowsky.aquarius.repositories;

import com.rodrigowsky.aquarius.entities.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    List<Role> findRolesByUsersId(Long userId);
}
