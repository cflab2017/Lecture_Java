# 과제 - 24. REST 컨트롤러

## 문제 — 메모리 기반 Todo CRUD
- 위치: `answer/`
- 핵심 개념: 5 가지 HTTP 메서드를 한 컨트롤러로 처리

### 요구사항
`TodoController` 가 다음 엔드포인트를 제공:

| 메서드 | 경로 | 동작 |
|---|---|---|
| GET | `/todos` | 전체 목록 |
| GET | `/todos/{id}` | 단건 조회 (없으면 404) |
| POST | `/todos` | 생성 (id 자동) |
| PUT | `/todos/{id}` | 수정 |
| DELETE | `/todos/{id}` | 삭제 |

내부 저장소는 `ConcurrentHashMap<Long, Todo>` + `AtomicLong` (DB 는 26편).

### 예상 동작
```bash
$ curl -X POST localhost:8084/todos -H "Content-Type: application/json" \
       -d '{"title":"우유","done":false}'
{"id":1,"title":"우유","done":false}

$ curl localhost:8084/todos
[{"id":1,"title":"우유","done":false}]
```

## 정답 확인
[`answer/`](./answer/) 폴더의 Maven 프로젝트를 보세요.
