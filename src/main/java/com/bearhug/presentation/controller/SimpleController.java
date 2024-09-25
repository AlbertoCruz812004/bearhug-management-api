package com.bearhug.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleController {

    @GetMapping("/api/hello")
    public ResponseEntity<?> sayHello(){
        return ResponseEntity.ok("Hello World");
    }
}
