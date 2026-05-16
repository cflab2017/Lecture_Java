package com.codingnow.lecture.spring26.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codingnow.lecture.spring26.dto.MemoRequest;
import com.codingnow.lecture.spring26.dto.MemoResponse;
import com.codingnow.lecture.spring26.service.MemoService;

@RestController
@RequestMapping("/api/memos")
public class MemoController {
    private final MemoService service;

    public MemoController(MemoService service) {
        this.service = service;
    }

    @GetMapping
    public List<MemoResponse> list(@RequestParam(required = false) String q) {
        return service.search(q).stream().map(MemoResponse::from).toList();
    }

    @GetMapping("/{id}")
    public MemoResponse one(@PathVariable Long id) {
        return MemoResponse.from(service.get(id));
    }

    @PostMapping
    public ResponseEntity<MemoResponse> create(@RequestBody MemoRequest req) {
        MemoResponse body = MemoResponse.from(service.create(req.title(), req.body()));
        return ResponseEntity.status(HttpStatus.CREATED).body(body);
    }

    @PutMapping("/{id}")
    public MemoResponse update(@PathVariable Long id, @RequestBody MemoRequest req) {
        return MemoResponse.from(service.update(id, req.title(), req.body()));
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

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
