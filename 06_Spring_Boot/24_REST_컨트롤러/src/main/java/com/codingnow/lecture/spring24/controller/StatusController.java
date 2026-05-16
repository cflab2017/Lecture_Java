package com.codingnow.lecture.spring24.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @GetMapping("/teapot")
    public ResponseEntity<String> teapot() {
        return ResponseEntity.status(418).body("I'm a teapot");
    }

    @PostMapping("/items")
    public ResponseEntity<String> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
