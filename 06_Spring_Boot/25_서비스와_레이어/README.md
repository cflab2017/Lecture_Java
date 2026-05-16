# 25. 서비스와 레이어

작은 예제에서는 컨트롤러 한 클래스에 모든 로직을 두어도 괜찮지만, 규모가 커지면 **계층 분리** 가 유지보수에 결정적입니다. Spring 진영의 관습적인 3 계층 구조와, 그 사이에서 안전하게 데이터를 전달하는 **DTO** 개념을 익힙니다.

## 학습 목표

- Controller / Service / Repository 의 역할 차이를 안다
- `@Service`, `@Repository` 어노테이션을 사용한다
- **생성자 주입(constructor injection)** 패턴을 안다
- DTO 와 도메인 객체를 구분한다
- "비즈니스 로직은 서비스" 라는 원칙을 적용한다

## 핵심 개념

### 1) 3 계층 그림

```
HTTP 요청 → Controller → Service → Repository → DB
              (얇음)     (두꺼움)    (얇음)
```

- Controller : HTTP 입출력만 (입력 검증·DTO 변환)
- Service : 비즈니스 로직 (검증·트랜잭션·도메인 행위)
- Repository : 데이터 저장소 접근 (DB · 인메모리)

### 2) 생성자 주입

```java
@Service
public class MemberService {
    private final MemberRepository repository;

    public MemberService(MemberRepository repository) {
        this.repository = repository;
    }
}
```

- `final` 필드로 불변 보장
- 테스트 시 mock 주입이 쉬움
- 순환 의존이 있으면 컴파일/시작 시점에 노출됨

### 3) DTO vs 도메인

| 종류 | 위치 | 특징 |
|---|---|---|
| **도메인** | Service/Repository 안 | 비즈니스 행위 포함, 외부 노출 X |
| **DTO** | Controller 경계 | 입출력 모양에 맞춤, 직렬화 친화 |

도메인을 그대로 컨트롤러가 노출하면 내부 구조가 외부에 묶여 변화에 약합니다.

### 4) `@Repository`

```java
@Repository
public class MemberRepository { ... }
```

`@Service` / `@Repository` / `@Controller` 는 의미만 다른 `@Component` 입니다. Spring 이 컴포넌트 스캔으로 자동 등록합니다.

## 예제로 보기

### 예제 1 — `Member.java` : 도메인 객체

```java
package com.codingnow.lecture.spring25.domain;

public class Member {
    private Long id;
    private String name;
    private String email;

    public Member(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    // getters...
}
```

### 예제 2 — `MemberRepository.java` : 메모리 저장소

```java
@Repository
public class MemberRepository {
    private final Map<Long, Member> store = new ConcurrentHashMap<>();
    private final AtomicLong seq = new AtomicLong();

    public Member save(Member m) { ... }
    public Optional<Member> findById(Long id) { ... }
    public List<Member> findAll() { ... }
    public boolean deleteById(Long id) { ... }
}
```

### 예제 3 — `MemberService.java` : 비즈니스 로직

```java
@Service
public class MemberService {
    private final MemberRepository repository;
    public MemberService(MemberRepository repository) { this.repository = repository; }

    public MemberResponse register(MemberCreateRequest req) {
        if (req.name() == null || req.name().isBlank())
            throw new IllegalArgumentException("name required");
        Member saved = repository.save(new Member(null, req.name(), req.email()));
        return MemberResponse.from(saved);
    }
}
```

### 예제 4 — `MemberController.java` : DTO 사용

```java
@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService service;
    public MemberController(MemberService service) { this.service = service; }

    @PostMapping
    public MemberResponse create(@RequestBody MemberCreateRequest req) {
        return service.register(req);
    }

    @GetMapping
    public List<MemberResponse> all() { return service.findAll(); }
}
```

### 실행

```bash
cd 06_Spring_Boot/25_서비스와_레이어
mvn spring-boot:run
```

```bash
curl -X POST http://localhost:8085/api/members \
     -H "Content-Type: application/json" \
     -d '{"name":"지수","email":"jisu@codingnow.com"}'
# {"id":1,"name":"지수","email":"jisu@codingnow.com"}

curl http://localhost:8085/api/members
# [{"id":1,"name":"지수","email":"jisu@codingnow.com"}]
```

## 자주 하는 실수

1. 컨트롤러가 비즈니스 로직 다 처리 → 테스트 어려움
2. 도메인을 그대로 노출 → 외부 API 가 내부 구조에 묶임
3. `@Autowired` 필드 주입 사용 → 생성자 주입 사용
4. 양방향 의존이 생긴 채 시작 → Spring 이 시작을 거부
5. 서비스가 HTTP 정보를 알게 됨 → 계층 침범

## 정리

- 3 계층 분리는 규모가 커질수록 효과 큼
- DTO 로 경계를 만들면 변화에 강해짐
- 의존은 생성자 주입 + final 권장

## 직접 해 보기

```bash
cd 06_Spring_Boot/25_서비스와_레이어
mvn spring-boot:run
```

## 다음 단원

[26_데이터_저장](../26_데이터_저장/) — Spring Data JPA 와 H2 로 실제 DB 영속화를 추가합니다.
