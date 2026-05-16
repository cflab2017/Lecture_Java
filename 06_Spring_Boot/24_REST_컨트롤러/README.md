# 24. REST 컨트롤러

REST API 는 HTTP 의 메서드(`GET` / `POST` / `PUT` / `DELETE`) 와 URL 만으로 자원을 다루는 약속입니다. Spring Boot 에서는 `@RestController` 와 다양한 매핑 어노테이션으로 매우 간결하게 구현할 수 있습니다.

## 학습 목표

- `@RestController` 와 `@Controller` 의 차이를 안다
- `@GetMapping` / `@PostMapping` / `@PutMapping` / `@DeleteMapping` 의 의미를 안다
- `@PathVariable` 과 `@RequestParam` 으로 입력을 받는다
- `@RequestBody` 로 JSON 을 객체로 받는다
- `ResponseEntity` 로 상태 코드를 함께 응답한다

## 핵심 개념

### 1) `@RestController`

```java
@RestController
@RequestMapping("/api/hello")
public class HelloController { ... }
```

`@Controller` + `@ResponseBody` 의 합성. 반환값을 **JSON 으로 직렬화** 해 응답 본문에 그대로 씁니다.

### 2) 매핑 어노테이션

| 어노테이션 | HTTP 메서드 |
|---|---|
| `@GetMapping` | GET |
| `@PostMapping` | POST |
| `@PutMapping` | PUT |
| `@DeleteMapping` | DELETE |

### 3) `@PathVariable` vs `@RequestParam`

```java
@GetMapping("/users/{id}")
public User get(@PathVariable Long id) { ... }      // /users/3

@GetMapping("/users")
public List<User> list(@RequestParam(defaultValue="0") int page) { ... }
// /users?page=2
```

### 4) `@RequestBody`

```java
@PostMapping("/users")
public User create(@RequestBody UserCreateRequest req) { ... }
```

JSON 본문이 자동으로 자바 객체로 변환됩니다 (Jackson 사용).

### 5) `ResponseEntity`

```java
return ResponseEntity.status(HttpStatus.CREATED).body(user);
```

상태 코드 + 헤더 + 본문을 함께 지정할 때 사용합니다.

## 예제로 보기

### 예제 1 — `HelloController.java` : `GET` + `@RequestParam`

```java
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "Spring") String name) {
        return "Hello, " + name + "!";
    }
}
```

`curl "http://localhost:8083/hello?name=지수"` → `Hello, 지수!`

### 예제 2 — `GreetingController.java` : `@PathVariable` + JSON

```java
@RestController
@RequestMapping("/api/greetings")
public class GreetingController {
    record Greeting(String message, int length) {}

    @GetMapping("/{name}")
    public Greeting greet(@PathVariable String name) {
        String msg = "Hello, " + name + "!";
        return new Greeting(msg, msg.length());
    }
}
```

`curl http://localhost:8083/api/greetings/Java` → `{"message":"Hello, Java!","length":12}`

### 예제 3 — `EchoController.java` : `POST` + `@RequestBody`

```java
@RestController
@RequestMapping("/api/echo")
public class EchoController {
    record EchoRequest(String text, int repeat) {}
    record EchoResponse(String result) {}

    @PostMapping
    public EchoResponse echo(@RequestBody EchoRequest req) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < req.repeat(); i++) sb.append(req.text());
        return new EchoResponse(sb.toString());
    }
}
```

```bash
curl -X POST http://localhost:8083/api/echo \
     -H "Content-Type: application/json" \
     -d '{"text":"hi","repeat":3}'
# {"result":"hihihi"}
```

### 예제 4 — `StatusController.java` : `ResponseEntity`

```java
@RestController
public class StatusController {
    @GetMapping("/teapot")
    public ResponseEntity<String> teapot() {
        return ResponseEntity.status(418).body("I'm a teapot");
    }

    @PostMapping("/items")
    public ResponseEntity<String> create() {
        return ResponseEntity.status(HttpStatus.CREATED).body("created");
    }
}
```

### 실행

```bash
cd 06_Spring_Boot/24_REST_컨트롤러
mvn spring-boot:run
```

## 자주 하는 실수

1. `@Controller` 로 두고 JSON 이 안 돌아옴 → `@RestController` 사용
2. `@RequestParam` 누락 → 메서드 시그니처 이름과 다르면 매칭 실패
3. `@RequestBody` 가 빠지면 본문 매핑이 안 됨
4. `@PathVariable` 이름이 URL 변수명과 다를 때 `("id")` 명시 누락
5. JSON 호환 안 되는 타입을 직접 반환

## 정리

- `@RestController` + 매핑 어노테이션 조합이 REST 의 기본
- 입력은 `@RequestParam` / `@PathVariable` / `@RequestBody` 로 분리
- 상태 코드가 중요하면 `ResponseEntity`

## 직접 해 보기

```bash
cd 06_Spring_Boot/24_REST_컨트롤러
mvn spring-boot:run
# 다른 터미널
curl http://localhost:8083/hello
curl http://localhost:8083/api/greetings/Spring
curl -X POST http://localhost:8083/api/echo -H "Content-Type: application/json" -d '{"text":"hi","repeat":3}'
```

## 다음 단원

[25_서비스와_레이어](../25_서비스와_레이어/) — Controller / Service / Repository 계층 분리와 DTO 를 다룹니다.
