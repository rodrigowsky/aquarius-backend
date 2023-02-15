package com.rodrigowsky.aquarius.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTest {



    @GetMapping("/test2")
    public String test() {
        return "Test2";
    }
}

