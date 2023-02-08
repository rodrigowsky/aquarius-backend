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
    private JpaUserDetailsService jpaUserService;

    @PostMapping("/register")
    public ResponseEntity<String> Register(@RequestBody RegisterDTO registerDto){


        if (jpaUserService.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        User user = userService.createUser(registerDto);

        return new ResponseEntity<>("User registered success!" + user.getId(), HttpStatus.OK);

    }
}
