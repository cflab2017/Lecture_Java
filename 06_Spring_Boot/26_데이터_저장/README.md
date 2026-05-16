# 26. 데이터 저장

지금까지는 인메모리 `Map` 으로만 데이터를 다뤘습니다. 이제 **Spring Data JPA** 와 **H2 인메모리 DB** 를 사용해 **재시작해도 같은 형태로 동작하는 영속화 계층** 을 만듭니다. JPA 는 Java 객체와 SQL 사이의 매핑을 자동화하는 ORM 표준입니다.

## 학습 목표

- `@Entity` 로 클래스를 테이블에 매핑한다
- `JpaRepository` 인터페이스만 정의하고 구현 없이 사용한다
- H2 콘솔에서 실제 SQL 을 본다
- CRUD 가 동작하는 작은 메모장 API 를 완성한다

## 핵심 개념

### 1) `@Entity`

```java
@Entity
@Table(name = "memos")
public class Memo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    protected Memo() {}    // JPA 가 사용
    public Memo(String title, String body) { this.title = title; this.body = body; }
    // getters/setters ...
}
```

- 기본 생성자(protected/public) 필수
- `@Id` 는 PK
- `@GeneratedValue(IDENTITY)` 는 DB 가 PK 를 자동 생성

### 2) `JpaRepository`

```java
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByTitleContaining(String keyword);
}
```

`save`, `findAll`, `findById`, `delete`, `count` 등은 **자동 구현** 됩니다.
이름 규칙(`findBy필드명`)으로 간단한 조회 메서드는 시그니처만 선언하면 됩니다.

### 3) H2 인메모리 DB

```properties
spring.datasource.url=jdbc:h2:mem:lecture26
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.show-sql=true
```

브라우저 `http://localhost:8087/h2-console` 로 콘솔 접속, JDBC URL 에 위 url 을 그대로 입력하면 테이블 조회 가능합니다.

### 4) 트랜잭션

```java
@Service
public class MemoService {
    @Transactional
    public Memo create(...) { ... }
}
```

`@Transactional` 메서드 안에서 일어난 DB 작업은 모두 성공해야 커밋, 하나라도 실패하면 자동 롤백됩니다.

## 예제로 보기

### 예제 1 — `Memo.java` : 엔티티

```java
@Entity
@Table(name = "memos")
public class Memo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String body;
    // ...
}
```

### 예제 2 — `MemoRepository.java` : Spring Data JPA

```java
public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findByTitleContaining(String keyword);
}
```

이 인터페이스를 정의만 하면 Spring 이 런타임에 구현체를 생성·주입합니다.

### 예제 3 — `MemoService.java` : 비즈니스 로직

```java
@Service
@Transactional
public class MemoService {
    private final MemoRepository repository;
    public MemoService(MemoRepository repository) { this.repository = repository; }

    public Memo create(String title, String body) { ... }
    public Memo update(Long id, String title, String body) { ... }
    public void delete(Long id) { ... }
    public Memo get(Long id) { ... }
    public List<Memo> search(String q) { ... }
}
```

### 예제 4 — `MemoController.java` : REST API

```java
@RestController
@RequestMapping("/api/memos")
public class MemoController {
    private final MemoService service;
    // ...
    @GetMapping public List<MemoResponse> list(...) { ... }
    @GetMapping("/{id}") public MemoResponse one(@PathVariable Long id) { ... }
    @PostMapping public MemoResponse create(@RequestBody MemoRequest req) { ... }
    @PutMapping("/{id}") public MemoResponse update(...) { ... }
    @DeleteMapping("/{id}") public ResponseEntity<Void> delete(...) { ... }
}
```

### 실행 + curl 시나리오

```bash
cd 06_Spring_Boot/26_데이터_저장
mvn spring-boot:run
```

```bash
# 생성
curl -X POST localhost:8087/api/memos -H "Content-Type: application/json" \
     -d '{"title":"Spring","body":"좋다"}'
# {"id":1,"title":"Spring","body":"좋다"}

# 조회
curl localhost:8087/api/memos
# [{"id":1, ...}]

curl localhost:8087/api/memos/1

# 검색
curl "localhost:8087/api/memos?q=Spr"

# 수정
curl -X PUT localhost:8087/api/memos/1 \
     -H "Content-Type: application/json" \
     -d '{"title":"Spring Boot","body":"매우 좋다"}'

# 삭제
curl -X DELETE localhost:8087/api/memos/1
```

## 자주 하는 실수

1. 엔티티에 기본 생성자가 없어서 시작 실패
2. `@Transactional` 누락 → lazy loading 에서 예외
3. `save` 가 update 인지 insert 인지 헷갈림 (id 가 있으면 update)
4. DDL 자동 (`ddl-auto=update`) 을 운영 환경에 그대로 적용
5. `findById(id).get()` 사용 → 없을 때 즉시 예외, `orElseThrow` 가 안전

## 정리

- `@Entity` + `JpaRepository` 만으로 CRUD 의 80% 가 끝남
- 비즈니스 로직은 서비스 계층 + `@Transactional`
- H2 콘솔로 실제 SQL · 데이터 확인 가능
- 외부 DB 로 교체할 때는 `application.properties` 만 바꾸면 됨

## 직접 해 보기

```bash
cd 06_Spring_Boot/26_데이터_저장
mvn spring-boot:run
# 다른 터미널에서 위 curl 시나리오 실행
```

## 다음 단원

수고하셨습니다! 26편 강의를 완주하셨습니다. 다음 단계로는

- 인증/인가 (Spring Security)
- 실제 DB (PostgreSQL/MySQL) 연동
- 외부 API 호출 (RestTemplate / WebClient)
- 테스트 (Mockito + MockMvc)
- 배포 (Docker / Cloud)

같은 주제로 이어 가 보세요. 루트 [README](../../) 의 미니 프로젝트 워크북도 참고가 됩니다.
