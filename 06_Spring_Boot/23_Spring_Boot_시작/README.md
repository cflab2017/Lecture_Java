# 23. Spring Boot 시작

Spring Boot 는 "설정보다 관습(convention over configuration)" 철학으로 **수십 줄짜리 XML 없이도 즉시 실행 가능한 웹 애플리케이션** 을 만들어 주는 프레임워크입니다. 내장 Tomcat 까지 포함해 `java -jar` 한 줄로 서버가 뜹니다.

## 학습 목표

- Spring Initializr 가 제공하는 프로젝트 구조를 안다
- `@SpringBootApplication` 의 역할을 안다
- 의존성 주입(DI) 의 개념을 이해한다
- `application.properties` 로 포트·로그 등 기본 설정을 바꾼다
- `mvn spring-boot:run` 으로 실행한다

## 핵심 개념

### 1) Spring Initializr

브라우저에서 [start.spring.io](https://start.spring.io) 에 접속해 의존성을 고르면 프로젝트 zip 을 생성해 줍니다. 다음 옵션을 추천:

- Project: **Maven**
- Language: **Java**
- Spring Boot: **3.3.x**
- Java: **21**
- Dependencies: **Spring Web** (+ 26편의 H2/JPA)

### 2) 표준 프로젝트 구조

```
spring-boot-app/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/codingnow/lecture/spring23/Application.java
│   │   └── resources/application.properties
│   └── test/
│       └── java/com/codingnow/lecture/spring23/ApplicationTests.java
```

### 3) `@SpringBootApplication`

```java
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

이 한 어노테이션은 **3 개**(`@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`)를 합친 메타 어노테이션입니다. 같은 패키지·하위 패키지의 Bean 을 자동으로 찾아 등록합니다.

### 4) 의존성 주입(DI) 의 그림

```java
@Service
class HelloService {
    String greet() { return "Hello, Spring!"; }
}

@RestController
class HelloController {
    private final HelloService service;
    HelloController(HelloService service) { this.service = service; }
    @GetMapping("/hello") String hello() { return service.greet(); }
}
```

Spring 이 `HelloService` 객체를 만들어 컨트롤러 생성자에 자동으로 넘겨줍니다. 호출자가 직접 `new` 하지 않는 패턴이 DI 의 핵심입니다.

### 5) `application.properties`

```properties
server.port=8081
spring.application.name=lecture-spring23
logging.level.root=INFO
```

## 예제로 보기

### 예제 1 — `pom.xml` : Spring Boot 의존성

`pom.xml` (요약):

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.6</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
</dependencies>
```

`spring-boot-starter-web` 한 의존성에 내장 Tomcat·Jackson(JSON)·Spring MVC 가 모두 포함됩니다.

### 예제 2 — `Application.java` : 진입점

```java
package com.codingnow.lecture.spring23;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 예제 3 — `HelloService.java` + `HelloController.java` : DI 의 첫 사례

`HelloService.java` :

```java
package com.codingnow.lecture.spring23;

import org.springframework.stereotype.Service;

@Service
public class HelloService {
    public String greet(String name) {
        return "Hello, " + name + "!";
    }
}
```

`HelloController.java` :

```java
package com.codingnow.lecture.spring23;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private final HelloService service;

    public HelloController(HelloService service) {
        this.service = service;
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "Spring") String name) {
        return service.greet(name);
    }
}
```

### 예제 4 — `application.properties`

```properties
server.port=8081
spring.application.name=lecture-spring23
```

### 실행

```bash
cd 06_Spring_Boot/23_Spring_Boot_시작
mvn spring-boot:run
```

다른 터미널에서:

```bash
curl http://localhost:8081/hello
# Hello, Spring!

curl "http://localhost:8081/hello?name=지수"
# Hello, 지수!
```

## 자주 하는 실수

1. `Application.java` 가 다른 패키지에 있는 컴포넌트를 못 찾음 → 가장 위 패키지에 두기
2. 포트 충돌 (8080 점유 중) → `server.port` 변경
3. `mvn spring-boot:run` 으로 실행 안 끝남 (Ctrl+C 로 종료)
4. 생성자 주입 안 쓰고 `@Autowired` 필드 사용 (테스트 어려움)
5. Spring Boot 의존성 버전을 일일이 명시 (`parent` 가 관리)

## 정리

- `@SpringBootApplication` + `main` 한 쌍이 진입점
- DI 는 객체를 "직접 만들지 않는" 패턴
- `application.properties` 로 환경 설정
- `mvn spring-boot:run` 으로 즉시 실행 가능

## 직접 해 보기

```bash
cd 06_Spring_Boot/23_Spring_Boot_시작
mvn spring-boot:run
# 다른 터미널
curl http://localhost:8081/hello
```

## 다음 단원

[24_REST_컨트롤러](../24_REST_컨트롤러/) — `@RestController` 와 다양한 매핑을 본격적으로 다룹니다.
