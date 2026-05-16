package com.codingnow.lecture.spring26.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codingnow.lecture.spring26.entity.Memo;
import com.codingnow.lecture.spring26.repository.MemoRepository;

@Service
@Transactional
public class MemoService {
    private final MemoRepository repository;

    public MemoService(MemoRepository repository) {
        this.repository = repository;
    }

    public Memo create(String title, String body) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title required");
        }
        return repository.save(new Memo(title, body));
    }

    @Transactional(readOnly = true)
    public List<Memo> findAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Memo> search(String keyword) {
        if (keyword == null || keyword.isBlank()) return repository.findAll();
        return repository.findByTitleContaining(keyword);
    }

    @Transactional(readOnly = true)
    public Memo get(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new IllegalStateException("memo not found: " + id));
    }

    public Memo update(Long id, String title, String body) {
        Memo memo = get(id);
        memo.update(title, body);
        return memo;
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalStateException("memo not found: " + id);
        }
        repository.deleteById(id);
    }
}
