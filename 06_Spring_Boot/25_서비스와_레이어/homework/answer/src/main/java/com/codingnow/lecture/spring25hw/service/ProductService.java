package com.codingnow.lecture.spring25hw.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codingnow.lecture.spring25hw.domain.Product;
import com.codingnow.lecture.spring25hw.dto.ProductCreateRequest;
import com.codingnow.lecture.spring25hw.dto.ProductResponse;
import com.codingnow.lecture.spring25hw.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ProductResponse create(ProductCreateRequest req) {
        if (req.price() <= 0) throw new IllegalArgumentException("price > 0");
        Product saved = repository.save(new Product(null, req.name(), req.price()));
        return ProductResponse.from(saved);
    }

    public List<ProductResponse> all() {
        return repository.findAll().stream().map(ProductResponse::from).toList();
    }
}
