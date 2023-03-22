package com.rodrigowsky.aquarius.controllers;

import com.rodrigowsky.aquarius.dto.*;
import com.rodrigowsky.aquarius.entities.*;
import com.rodrigowsky.aquarius.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
public class RegisterController {


    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

//    @Autowired
//    private JpaUserDetailsService jpaUserService;

    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody RegisterDTO registerDto) {

//        if (jpaUserService.existsByUsername(registerDto.getUsername())) {
//            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//        }

        if (registerDto.getUserRole().equals("ADMIN")) {
            User user = userService.createUser(registerDto);
            return new ResponseEntity<>("User registered success!" + user.getId(), HttpStatus.OK);
        }

        if (registerDto.getUserRole().equals("STUDENT")) {
            User user = userService.createUser(registerDto);
            studentService.registerStudent(user, registerDto);
            return new ResponseEntity<>("Student registered success!" + user.getId(), HttpStatus.OK);
        }

        if (registerDto.getUserRole().equals("TEACHER")) {
            User user = userService.createUser(registerDto);
            teacherService.registerTeacher(user, registerDto);
            return new ResponseEntity<>("Teacher registered success!" + user.getId(), HttpStatus.OK);
        }

        return new ResponseEntity<>("To Register Please Provide a Role From: ADMIN , STUDENT OR TEACHER.", HttpStatus.BAD_REQUEST);

    }



}
