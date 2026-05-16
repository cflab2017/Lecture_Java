package com.codingnow.lecture.spring25.dto;

/** 회원 가입 요청 DTO. */
public record MemberCreateRequest(String name, String email) {}
