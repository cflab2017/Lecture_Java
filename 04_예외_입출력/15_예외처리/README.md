# 15. 예외처리

프로그램이 실행 중 만나는 비정상 상황(파일 없음·잘못된 입력·네트워크 장애 등) 을 안전하게 다루는 메커니즘이 **예외(exception) 처리** 입니다. Java 는 try/catch/finally 와 checked/unchecked 라는 두 종류의 예외 체계를 제공합니다.

## 학습 목표

- `try` / `catch` / `finally` 의 흐름을 안다
- checked 예외와 unchecked(RuntimeException) 의 차이를 안다
- `throws` 로 예외를 위임할 수 있다
- 사용자 정의 예외 클래스를 만든다
- multi-catch 와 예외 체이닝(`cause`) 을 본다

## 핵심 개념

### 1) try / catch / finally

```java
try {
    int n = Integer.parseInt("abc");
} catch (NumberFormatException e) {
    System.out.println("숫자 아님: " + e.getMessage());
} finally {
    System.out.println("항상 실행");
}
```

`finally` 는 예외 여부와 상관없이 **무조건** 실행됩니다 (자원 정리에 사용).

### 2) Checked vs Unchecked

| 종류 | 예시 | 의무 처리 |
|---|---|---|
| Checked (`Exception` 자손) | `IOException`, `SQLException` | 반드시 `try/catch` 또는 `throws` 선언 |
| Unchecked (`RuntimeException` 자손) | `NullPointerException`, `IllegalArgumentException` | 강제 처리 없음 |
| Error | `OutOfMemoryError` | 처리 시도 X (시스템 오류) |

### 3) `throws`

```java
static void readFile(String path) throws IOException {
    Files.readString(Path.of(path));
}
```

호출자에게 "이 예외를 처리하라" 고 떠넘기는 키워드입니다.

### 4) 사용자 정의 예외

```java
class InvalidAgeException extends RuntimeException {
    public InvalidAgeException(String msg) { super(msg); }
}
```

도메인 의미가 분명한 예외 클래스를 만들면 호출자가 처리 흐름을 명확히 짤 수 있습니다.

### 5) Multi-catch

```java
try {
    ...
} catch (IOException | NumberFormatException e) {
    System.out.println("실패: " + e.getMessage());
}
```

JDK 7+ 부터 한 번의 catch 절에 여러 예외 타입을 허용합니다.

## 예제로 보기

### 예제 1 — `TryCatch.java` : 기본 흐름

```java
public class TryCatch {
    public static void main(String[] args) {
        try {
            int n = Integer.parseInt("abc");
            System.out.println(n);
        } catch (NumberFormatException e) {
            System.out.println("숫자 아님: " + e.getMessage());
        } finally {
            System.out.println("종료 정리");
        }
        System.out.println("프로그램 계속");
    }
}
```

**실행 결과**

```
숫자 아님: For input string: "abc"
종료 정리
프로그램 계속
```

**메모:** catch 가 예외를 잡으면 프로그램은 죽지 않고 그 다음 줄을 계속 실행합니다.

### 예제 2 — `MultiCatch.java` : 여러 예외 동시 처리

```java
public class MultiCatch {
    public static void main(String[] args) {
        Object[] inputs = { "10", "abc", null };
        for (Object o : inputs) {
            try {
                String s = (String) o;
                int n = Integer.parseInt(s.trim());
                System.out.println("성공: " + n);
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("실패: " + e.getClass().getSimpleName());
            }
        }
    }
}
```

**실행 결과**

```
성공: 10
실패: NumberFormatException
실패: NullPointerException
```

**메모:** 두 예외가 의미적으로 같은 처리라면 한 줄로 묶을 수 있습니다.

### 예제 3 — `ThrowsAndCustom.java` : `throws` + 사용자 예외

```java
public class ThrowsAndCustom {
    static class InvalidAgeException extends RuntimeException {
        public InvalidAgeException(String msg) { super(msg); }
    }

    static void register(String name, int age) {
        if (age < 0) {
            throw new InvalidAgeException("나이는 0 이상: " + age);
        }
        System.out.println("등록: " + name + " (" + age + ")");
    }

    public static void main(String[] args) {
        try {
            register("지수", 21);
            register("미상", -1);
        } catch (InvalidAgeException e) {
            System.out.println("거부: " + e.getMessage());
        }
    }
}
```

**실행 결과**

```
등록: 지수 (21)
거부: 나이는 0 이상: -1
```

**메모:** unchecked 예외(`RuntimeException` 상속) 는 `throws` 선언이 필요 없습니다.

### 예제 4 — `ExceptionChaining.java` : 예외 원인 보존

```java
public class ExceptionChaining {
    static void load() {
        try {
            Integer.parseInt("nope");
        } catch (NumberFormatException low) {
            throw new IllegalStateException("설정 로드 실패", low);
        }
    }

    public static void main(String[] args) {
        try {
            load();
        } catch (IllegalStateException e) {
            System.out.println("바깥: " + e.getMessage());
            System.out.println("원인: " + e.getCause());
        }
    }
}
```

**실행 결과**

```
바깥: 설정 로드 실패
원인: java.lang.NumberFormatException: For input string: "nope"
```

**메모:** 낮은 레벨의 예외를 상위 의미로 **감싸면서도 원인을 보존** 하는 패턴은 디버깅에 매우 유용합니다.

## 자주 하는 실수

1. `catch (Exception e)` 로 모든 예외 통째로 잡고 무시 (silent failure)
2. 빈 catch 블록 → 문제 묻어 버림
3. `finally` 에서 `return` 사용해 본문 return 을 덮어씀
4. checked 예외를 throws 도 try/catch 도 없이 무시 시도 (컴파일 에러)
5. 도메인 의미 없는 `RuntimeException` 남발 (사용자 정의 예외가 더 명확)

## 정리

- 예외는 비정상 흐름을 표현하는 **객체**
- checked 는 반드시 처리, unchecked 는 선택적 처리
- 사용자 정의 예외로 도메인 의미를 분명히
- 원인을 보존하면 추적이 쉬워짐

## 직접 해 보기

```bash
cd 04_예외_입출력/15_예외처리/src
javac ThrowsAndCustom.java
java ThrowsAndCustom
```

## 다음 단원

[16_파일_IO](../16_파일_IO/) — `Files`, `Path`, try-with-resources 를 배웁니다.
