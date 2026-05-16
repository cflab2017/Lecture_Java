package com.codingnow.lecture.spring25.dto;

import com.codingnow.lecture.spring25.domain.Member;

/** 회원 응답 DTO. */
public record MemberResponse(Long id, String name, String email) {
    public static MemberResponse from(Member m) {
        return new MemberResponse(m.getId(), m.getName(), m.getEmail());
    }
}
