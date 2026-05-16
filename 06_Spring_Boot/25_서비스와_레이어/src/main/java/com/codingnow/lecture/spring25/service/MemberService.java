package com.codingnow.lecture.spring25.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingnow.lecture.spring25.domain.Member;
import com.codingnow.lecture.spring25.dto.MemberCreateRequest;
import com.codingnow.lecture.spring25.dto.MemberResponse;
import com.codingnow.lecture.spring25.repository.MemberRepository;

/** 회원 비즈니스 로직. */
@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }

    public MemberResponse register(MemberCreateRequest req) {
        if (req.name() == null || req.name().isBlank()) {
            throw new IllegalArgumentException("name required");
        }
        Member saved = repository.save(new Member(null, req.name(), req.email()));
        return MemberResponse.from(saved);
    }

    public List<MemberResponse> findAll() {
        return repository.findAll().stream().map(MemberResponse::from).toList();
    }
}
