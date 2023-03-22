package com.rodrigowsky.aquarius.services;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.Student;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.repositories.sql.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void registerStudent(User user, RegisterDTO registerDto) {
        //gets user domain from User service and creates a new Student to associate with the already created user

        //SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        Student student = new Student();
        student.setFirstName(registerDto.getFirstName()+ "student");
        student.setLastName(registerDto.getLastName()+"student");
        student.setEmail(registerDto.getEmail()+ "@");
        student.setPhoneNumber(registerDto.getPhoneNumber() + "1234");
        student.setUser(user); //foreign key
        Student student1 = studentRepository.save(student);


    }
}
