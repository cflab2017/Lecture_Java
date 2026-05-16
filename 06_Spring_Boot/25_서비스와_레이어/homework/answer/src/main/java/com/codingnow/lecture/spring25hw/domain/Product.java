package com.codingnow.lecture.spring25hw.domain;

public class Product {
    private Long id;
    private final String name;
    private final long price;

    public Product(Long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public long getPrice() { return price; }

    public void assignId(Long id) { this.id = id; }
}
