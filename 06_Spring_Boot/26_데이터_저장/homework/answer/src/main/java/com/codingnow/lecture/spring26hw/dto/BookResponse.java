package com.codingnow.lecture.spring26hw.dto;

import com.codingnow.lecture.spring26hw.entity.Book;

public record BookResponse(Long id, String title, String author, int pages) {
    public static BookResponse from(Book b) {
        return new BookResponse(b.getId(), b.getTitle(), b.getAuthor(), b.getPages());
    }
}
