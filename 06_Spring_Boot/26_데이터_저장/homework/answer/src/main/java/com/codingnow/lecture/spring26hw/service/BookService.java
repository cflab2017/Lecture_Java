package com.codingnow.lecture.spring26hw.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingnow.lecture.spring26hw.entity.Book;
import com.codingnow.lecture.spring26hw.repository.BookRepository;

@Service
@Transactional
public class BookService {
    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public Book create(String title, String author, int pages) {
        return repository.save(new Book(title, author, pages));
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Book get(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalStateException("book not found: " + id));
    }

    public Book update(Long id, String title, String author, int pages) {
        Book b = get(id);
        b.update(title, author, pages);
        return b;
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("book not found: " + id);
        }
        repository.deleteById(id);
    }
}
