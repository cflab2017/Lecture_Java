package com.codingnow.lecture.spring25hw.repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.codingnow.lecture.spring25hw.domain.Product;

@Repository
public class ProductRepository {
    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    public Product save(Product p) {
        if (p.getId() == null) p.assignId(seq.incrementAndGet());
        store.put(p.getId(), p);
        return p;
    }

    public List<Product> findAll() {
        return List.copyOf(store.values());
    }
}
