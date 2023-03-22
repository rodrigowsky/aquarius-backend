package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.Teacher;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.repositories.sql.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Locale;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    public void registerTeacher(User user, RegisterDTO registerDto) {
        //gets user domain from User service and creates a new Teacher to associate with the already created user
        // teacher domain is set with the info from registerDTO sent by the client


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);


        Teacher teacher = new Teacher();
        teacher.setFirstName(registerDto.getFirstName());
        teacher.setLastName(registerDto.getLastName());
        teacher.setPhoneNumber(registerDto.getPhoneNumber());
        teacher.setEmail(registerDto.getEmail());

        teacher.setDateOfBirth(registerDto.getDateOfBirth());

        teacher.setUser(user);

        teacherRepository.save(teacher);

    }
}
