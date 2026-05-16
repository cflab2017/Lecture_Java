package com.codingnow.lecture.spring24.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/greetings")
public class GreetingController {
    record Greeting(String message, int length) {}

    @GetMapping("/{name}")
    public Greeting greet(@PathVariable String name) {
        String msg = "Hello, " + name + "!";
        return new Greeting(msg, msg.length());
    }
}
