# 과제 - 26. 데이터 저장

## 문제 — `Book` CRUD with JPA
- 위치: `answer/`
- 핵심 개념: `@Entity`, `JpaRepository`, `@Transactional`, H2

### 요구사항
- 엔티티 `Book(id, title, author, pages)`
- `BookRepository extends JpaRepository<Book, Long>`
- `BookService` 의 CRUD: create, list, get, update, delete (없으면 404)
- 컨트롤러 `/api/books` 가 5 가지 메서드 모두 제공
- DB 는 H2 인메모리 + `ddl-auto=update`

### 예상 동작
```bash
$ curl -X POST localhost:8088/api/books -H "Content-Type: application/json" \
       -d '{"title":"Effective Java","author":"Joshua Bloch","pages":384}'
$ curl localhost:8088/api/books
$ curl localhost:8088/api/books/1
$ curl -X PUT localhost:8088/api/books/1 -H "Content-Type: application/json" \
       -d '{"title":"...","author":"...","pages":400}'
$ curl -X DELETE localhost:8088/api/books/1
```

## 정답 확인
[`answer/`](./answer/) 폴더의 Maven 프로젝트를 보세요.
