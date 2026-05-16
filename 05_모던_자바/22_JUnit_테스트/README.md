# 22. JUnit 테스트

코드를 "잘 동작한다" 고 말하려면 **검증** 이 필요합니다. JUnit 5 는 Java 의 사실상 표준 테스트 프레임워크로, 짧고 명확한 어노테이션 기반 API 를 제공합니다.

## 학습 목표

- Maven 프로젝트의 `src/test/java` 위치를 안다
- `@Test`, `@BeforeEach`, `@AfterEach`, `@DisplayName` 을 사용한다
- `assertEquals` / `assertTrue` / `assertThrows` 의 차이를 안다
- `mvn test` 로 테스트를 실행한다

## 핵심 개념

### 1) Maven 의존성

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.5</version>
    <scope>test</scope>
</dependency>
```

`scope=test` 는 운영 코드에는 포함되지 않게 합니다.

### 2) 기본 테스트

```java
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    @Test
    void add는_두_수의_합을_반환() {
        assertEquals(5, new Calculator().add(2, 3));
    }
}
```

### 3) 라이프사이클

```java
@BeforeEach void setUp() { ... }    // 각 @Test 전에 실행
@AfterEach  void tearDown() { ... } // 각 @Test 후에 실행
@BeforeAll  static void all() { ... }  // 클래스 전체 시작 시 1회
@AfterAll   static void clean() { ... } // 클래스 전체 끝에 1회
```

### 4) 예외 테스트

```java
@Test
void zero_division() {
    assertThrows(ArithmeticException.class,
        () -> new Calculator().divide(10, 0));
}
```

### 5) `mvn test`

```bash
cd 22_JUnit_테스트
mvn test
```

JUnit 5 가 자동으로 발견·실행해 결과를 출력합니다.

## 예제로 보기

### 예제 1 — `Calculator.java` : 운영 코드

```java
package com.codingnow.lecture.modern22;

public class Calculator {
    public int add(int a, int b) { return a + b; }
    public int subtract(int a, int b) { return a - b; }
    public int divide(int a, int b) {
        if (b == 0) throw new ArithmeticException("/ zero");
        return a / b;
    }
}
```

### 예제 2 — `CalculatorTest.java` : 가장 기본 테스트

```java
package com.codingnow.lecture.modern22;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    Calculator calc = new Calculator();

    @Test
    @DisplayName("add 는 두 수의 합을 반환")
    void add는_합을_반환() {
        assertEquals(5, calc.add(2, 3));
        assertEquals(0, calc.add(-1, 1));
    }

    @Test
    void divide_by_zero() {
        assertThrows(ArithmeticException.class, () -> calc.divide(10, 0));
    }
}
```

**`mvn test` 실행 결과 (일부)**

```
[INFO] Tests run: 2, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

### 예제 3 — `FixtureTest.java` : 라이프사이클

```java
package com.codingnow.lecture.modern22;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FixtureTest {
    Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        System.out.println("(setUp)");
    }

    @AfterEach
    void tearDown() {
        System.out.println("(tearDown)");
    }

    @Test
    void test_add() { assertEquals(7, calc.add(3, 4)); }

    @Test
    void test_sub() { assertEquals(1, calc.subtract(4, 3)); }
}
```

콘솔에 `(setUp)` 과 `(tearDown)` 이 각 테스트마다 출력됩니다.

### 예제 4 — `AssertionsTest.java` : 다양한 assertion

```java
package com.codingnow.lecture.modern22;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {
    @Test
    void variety() {
        assertTrue(2 + 2 == 4);
        assertFalse("hi".isEmpty());
        assertNotNull("");
        assertNull(null);
        assertArrayEquals(new int[]{1, 2, 3}, new int[]{1, 2, 3});
        assertIterableEquals(List.of("a"), List.of("a"));
    }
}
```

## 자주 하는 실수

1. JUnit 의존성을 `compile` 스코프로 추가 → 운영 jar 가 비대해짐
2. `@Test` 메서드를 `public` 으로만 작성 (5 에선 `package-private` 또는 `public` 모두 OK, `private` 만 안 됨)
3. 테스트끼리 의존성 (한 테스트 결과가 다른 테스트에 영향)
4. `assertEquals(actual, expected)` 처럼 인자 순서를 바꿈 (정답이 먼저)
5. 예외 발생을 직접 try/catch 로 잡고 만족 → `assertThrows` 사용

## 정리

- JUnit 5 + Maven 으로 자동화된 검증을 시작
- `@Test` 메서드는 작고, 한 가지만 검증
- `@BeforeEach` 로 공통 초기화 분리
- `assertThrows` 로 예외 흐름도 검증

## 직접 해 보기

```bash
cd 05_모던_자바/22_JUnit_테스트
mvn test
```

## 다음 단원

[23_Spring_Boot_시작](../../06_Spring_Boot/23_Spring_Boot_시작/) — Spring Initializr 로 첫 Spring Boot 앱을 만듭니다.
