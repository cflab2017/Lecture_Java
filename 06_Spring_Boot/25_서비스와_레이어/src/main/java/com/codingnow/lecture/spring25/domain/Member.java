package com.codingnow.lecture.spring25.domain;

/** 회원 도메인 객체. */
public class Member {
    private Long id;
    private final String name;
    private final String email;

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }

    public void assignId(Long id) {
        this.id = id;
    }
}
