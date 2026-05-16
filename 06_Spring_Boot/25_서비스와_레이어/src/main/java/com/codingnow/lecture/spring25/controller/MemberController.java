package com.codingnow.lecture.spring25.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingnow.lecture.spring25.dto.MemberCreateRequest;
import com.codingnow.lecture.spring25.dto.MemberResponse;
import com.codingnow.lecture.spring25.service.MemberService;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService service;

    public MemberController(MemberService service) {
        this.service = service;
    }

    @PostMapping
    public MemberResponse create(@RequestBody MemberCreateRequest req) {
        return service.register(req);
    }

    @GetMapping
    public List<MemberResponse> all() {
        return service.findAll();
    }
}
