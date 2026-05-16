package com.codingnow.lecture.spring25hw.dto;

import com.codingnow.lecture.spring25hw.domain.Product;

public record ProductResponse(Long id, String name, long price) {
    public static ProductResponse from(Product p) {
        return new ProductResponse(p.getId(), p.getName(), p.getPrice());
    }
}
