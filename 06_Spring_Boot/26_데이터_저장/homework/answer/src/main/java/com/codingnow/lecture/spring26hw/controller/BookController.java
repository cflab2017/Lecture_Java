package com.codingnow.lecture.spring26hw.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingnow.lecture.spring26hw.dto.BookRequest;
import com.codingnow.lecture.spring26hw.dto.BookResponse;
import com.codingnow.lecture.spring26hw.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<BookResponse> list() {
        return service.findAll().stream().map(BookResponse::from).toList();
    }

    @GetMapping("/{id}")
    public BookResponse one(@PathVariable Long id) {
        return BookResponse.from(service.get(id));
    }

    @PostMapping
    public ResponseEntity<BookResponse> create(@RequestBody BookRequest req) {
        BookResponse body = BookResponse.from(service.create(req.title(), req.author(), req.pages()));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{id}")
    public BookResponse update(@PathVariable Long id, @RequestBody BookRequest req) {
        return BookResponse.from(service.update(id, req.title(), req.author(), req.pages()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleMissing(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
