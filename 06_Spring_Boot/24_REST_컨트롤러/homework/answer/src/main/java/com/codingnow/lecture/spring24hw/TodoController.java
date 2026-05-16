package com.codingnow.lecture.spring24hw;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
public class TodoController {
    public record Todo(Long id, String title, boolean done) {}
    public record TodoInput(String title, boolean done) {}

    private final Map<Long, Todo> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    @GetMapping
    public List<Todo> list() {
        return List.copyOf(store.values());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> get(@PathVariable Long id) {
        Todo t = store.get(id);
        return t == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(t);
    }

    @PostMapping
    public Todo create(@RequestBody TodoInput in) {
        long id = seq.incrementAndGet();
        Todo t = new Todo(id, in.title(), in.done());
        store.put(id, t);
        return t;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody TodoInput in) {
        if (!store.containsKey(id)) return ResponseEntity.notFound().build();
        Todo t = new Todo(id, in.title(), in.done());
        store.put(id, t);
        return ResponseEntity.ok(t);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return store.remove(id) == null
            ? ResponseEntity.notFound().build()
            : ResponseEntity.noContent().build();
    }
}
