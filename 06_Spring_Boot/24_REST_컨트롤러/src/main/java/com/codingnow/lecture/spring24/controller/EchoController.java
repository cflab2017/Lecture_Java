package com.codingnow.lecture.spring24.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/echo")
public class EchoController {
    public record EchoRequest(String text, int repeat) {}
    public record EchoResponse(String result) {}

    @PostMapping
    public EchoResponse echo(@RequestBody EchoRequest req) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < req.repeat(); i++) sb.append(req.text());
        return new EchoResponse(sb.toString());
    }
}
