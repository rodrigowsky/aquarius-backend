package com.rodrigowsky.aquarius.repositories.sql;

import com.rodrigowsky.aquarius.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher,Long> {

//    @Query(value="insert into public.teacher(date_birth, email, firstname, lastname, phone, user) values (:dateOfBirth, :email, :firstName, :lastName, :phoneNumber, :userId)",nativeQuery = true)
//    public User save(@Param("dateOfBirth") Date date, @Param("email") String email,
//                     @Param("firstName") String firstName, @Param("lastName") String lastName,@Param("phoneNumber") Integer phoneNumber,@Param("userId") Long userId);

}
