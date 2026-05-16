# 과제 - 23. Spring Boot 시작

## 문제 — Ping 엔드포인트 + 설정값 노출
- 위치: `answer/`
- 핵심 개념: `@RestController`, `@Value`, `application.properties`

### 요구사항
- `application.properties` 에 `app.greeting=안녕하세요` 라는 키 추가
- `PingController` 의 `GET /ping` 이 다음 JSON 을 반환:
  ```json
  {"message": "안녕하세요", "status": "ok"}
  ```
- `@Value("${app.greeting}")` 로 설정값 주입

### 예상 동작
```bash
$ mvn spring-boot:run &
$ curl http://localhost:8082/ping
{"message":"안녕하세요","status":"ok"}
```

## 정답 확인
[`answer/`](./answer/) 폴더의 Maven 프로젝트를 확인하세요.
