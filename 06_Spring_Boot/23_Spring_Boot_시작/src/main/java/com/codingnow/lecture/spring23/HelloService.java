package com.codingnow.lecture.spring23;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
