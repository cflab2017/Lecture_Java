package com.codingnow.lecture.spring25.repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.codingnow.lecture.spring25.domain.Member;

/** 메모리 기반 회원 저장소. */
@Repository
public class MemberRepository {
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    public Member save(Member m) {
        if (m.getId() == null) m.assignId(seq.incrementAndGet());
        store.put(m.getId(), m);
        return m;
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Member> findAll() {
        return List.copyOf(store.values());
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
