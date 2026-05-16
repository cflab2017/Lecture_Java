package com.codingnow.lecture.spring23hw;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {
    private final String greeting;

    public PingController(@Value("${app.greeting}") String greeting) {
        this.greeting = greeting;
    }

    @GetMapping("/ping")
    public Map<String, String> ping() {
        return Map.of("message", greeting, "status", "ok");
    }
}
