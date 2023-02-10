package com.rodrigowsky.aquarius.controllers;

import com.rodrigowsky.aquarius.dto.RegisterDTO;
import com.rodrigowsky.aquarius.entities.User;
import com.rodrigowsky.aquarius.services.JpaUserDetailsService;
import com.rodrigowsky.aquarius.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    JpaUserDetailsService jpaUserService;
    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String testA() {
        return "Receiving Request and Sending Response";

    }


    @PostMapping("/test")
    public ResponseEntity<String> testC(@RequestBody RegisterDTO registerDto) {

//        if (jpaUserService.existsByUsername(registerDto.getUsername())) {
//            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
//        }
        User user = userService.createUser(registerDto);

//        try {
//            userService.createAuth(Auths.ROLE_ADMIN,user.getId());
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }


        return new ResponseEntity<>("User registered success!" + user.getId(), HttpStatus.OK);


    }

}
