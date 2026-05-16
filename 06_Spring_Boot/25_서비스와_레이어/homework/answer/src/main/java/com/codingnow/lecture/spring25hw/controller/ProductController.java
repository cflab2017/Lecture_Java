package com.codingnow.lecture.spring25hw.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingnow.lecture.spring25hw.dto.ProductCreateRequest;
import com.codingnow.lecture.spring25hw.dto.ProductResponse;
import com.codingnow.lecture.spring25hw.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ProductResponse create(@RequestBody ProductCreateRequest req) {
        return service.create(req);
    }

    @GetMapping
    public List<ProductResponse> all() {
        return service.all();
    }
}
