package com.codingnow.lecture.spring26hw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codingnow.lecture.spring26hw.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
