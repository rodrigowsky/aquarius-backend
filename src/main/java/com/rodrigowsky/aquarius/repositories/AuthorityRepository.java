package com.rodrigowsky.aquarius.repositories;

import com.rodrigowsky.aquarius.entities.Authority;
import com.rodrigowsky.aquarius.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository  extends JpaRepository<Authority,Integer> {
}
