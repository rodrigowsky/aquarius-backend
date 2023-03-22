package com.rodrigowsky.aquarius.repositories.sql;

import com.rodrigowsky.aquarius.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository  extends JpaRepository<Authority,Integer> {
}
