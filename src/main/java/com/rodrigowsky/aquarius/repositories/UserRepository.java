package com.rodrigowsky.aquarius.repositories;

import com.rodrigowsky.aquarius.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {


    Optional<User> findUserByUsername(String username);

    Boolean existsByUsername(String username);



}

