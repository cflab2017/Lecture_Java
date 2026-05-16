# 18. 람다와 함수형

람다(lambda) 는 짧게 표현하는 **익명 함수** 입니다. JDK 8 이후 컬렉션·Stream·Spring 등 거의 모든 곳에서 만나는 핵심 문법입니다. 메서드 참조와 표준 함수형 인터페이스(`Function`/`Predicate`/`Consumer`/`Supplier`) 도 함께 익혀 둡니다.

## 학습 목표

- lambda 식의 형태를 안다 (`(a, b) -> a + b`)
- 메서드 참조(`Foo::bar`) 4가지를 안다
- `java.util.function` 의 핵심 4 인터페이스를 사용한다
- 람다가 캡처할 수 있는 변수의 제약(effective final) 을 안다

## 핵심 개념

### 1) lambda 표현식

```java
Runnable r = () -> System.out.println("hi");
Comparator<String> byLen = (a, b) -> a.length() - b.length();
```

타입은 대상 함수형 인터페이스로부터 추론됩니다.

### 2) 메서드 참조 4가지

| 종류 | 예 |
|---|---|
| 정적 메서드 | `Integer::parseInt` |
| 인스턴스 메서드 (특정 객체) | `System.out::println` |
| 인스턴스 메서드 (클래스 명) | `String::toUpperCase` |
| 생성자 | `ArrayList::new` |

### 3) 표준 함수형 인터페이스

| 인터페이스 | 추상 메서드 | 의미 |
|---|---|---|
| `Function<T, R>` | `R apply(T)` | T → R 변환 |
| `Predicate<T>` | `boolean test(T)` | T 조건 검사 |
| `Consumer<T>` | `void accept(T)` | T 소비 (출력 등) |
| `Supplier<T>` | `T get()` | 매개변수 없이 T 공급 |
| `BiFunction<T,U,R>` | `R apply(T, U)` | 두 입력 → 한 출력 |

### 4) effective final

```java
int x = 10;
Runnable r = () -> System.out.println(x);
// x = 20;   // 컴파일 에러: 캡처된 변수는 사실상 final 이어야 함
```

람다 내부에서 캡처하는 지역 변수는 (final 키워드 없이도) **한 번 정해진 뒤 안 바뀌어야** 합니다.

## 예제로 보기

### 예제 1 — `LambdaBasics.java` : 다양한 형태

```java
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaBasics {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println("hi");
        r.run();

        Comparator<String> byLen = (a, b) -> a.length() - b.length();
        List<String> words = Arrays.asList("ccc", "a", "bb");
        words.sort(byLen);
        System.out.println(words);
    }
}
```

**실행 결과**

```
hi
[a, bb, ccc]
```

**메모:** 람다 본문이 한 줄이면 `return` 과 중괄호를 생략할 수 있습니다.

### 예제 2 — `MethodReference.java` : 메서드 참조

```java
import java.util.List;
import java.util.stream.Collectors;

public class MethodReference {
    public static void main(String[] args) {
        List<String> langs = List.of("java", "kotlin", "scala");
        // (s) -> s.toUpperCase()
        List<String> upper = langs.stream()
            .map(String::toUpperCase)
            .collect(Collectors.toList());
        System.out.println(upper);

        // (s) -> System.out.println(s)
        langs.forEach(System.out::println);
    }
}
```

**실행 결과**

```
[JAVA, KOTLIN, SCALA]
java
kotlin
scala
```

**메모:** 람다가 단순히 "어떤 메서드 호출만" 한다면 메서드 참조가 더 짧고 명확합니다.

### 예제 3 — `FunctionInterfaces.java` : 표준 4종

```java
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionInterfaces {
    public static void main(String[] args) {
        Function<Integer, Integer> square = n -> n * n;
        Predicate<Integer> isEven = n -> n % 2 == 0;
        Consumer<String> printUp = s -> System.out.println(s.toUpperCase());
        Supplier<String> hello = () -> "Hello";

        System.out.println(square.apply(5));
        System.out.println(isEven.test(10));
        printUp.accept("java");
        System.out.println(hello.get());
    }
}
```

**실행 결과**

```
25
true
JAVA
Hello
```

**메모:** 람다는 **무엇을 받아 무엇을 반환하느냐** 만 보면 자연스럽게 인터페이스가 결정됩니다.

### 예제 4 — `Capture.java` : 변수 캡처

```java
public class Capture {
    public static void main(String[] args) {
        int x = 10;
        Runnable r = () -> System.out.println("x=" + x);
        r.run();
        // x = 20;   // 주석 풀면 컴파일 에러

        for (int i = 0; i < 3; i++) {
            final int idx = i;
            Runnable rr = () -> System.out.println("idx=" + idx);
            rr.run();
        }
    }
}
```

**실행 결과**

```
x=10
idx=0
idx=1
idx=2
```

**메모:** `for` 의 `i` 자체는 매 회 새로 만들어지지만, 람다 안에서 캡처하려면 그 회차의 final 사본을 만들어야 합니다.

## 자주 하는 실수

1. 한 줄 람다에 `return` 을 강제로 적기 (없어도 됨)
2. 캡처 변수에 재할당 → 컴파일 에러
3. 람다 안의 예외 처리를 잊고 `Function` 시그니처와 충돌 (checked 예외)
4. `String::toUpperCase` 가 인스턴스 메서드인지 정적 메서드인지 헷갈림
5. 메서드 참조 사용 가능한데도 길게 람다 사용

## 정리

- 람다는 함수형 인터페이스에 대한 짧은 구현
- 메서드 참조로 더욱 간결하게
- 표준 4 함수형 인터페이스만 익혀도 80% 활용 가능
- 캡처 변수는 effective final 이어야 함

## 직접 해 보기

```bash
cd 05_모던_자바/18_람다와_함수형/src
javac LambdaBasics.java
java LambdaBasics
```

## 다음 단원

[19_Optional](../19_Optional/) — null 회피 패턴과 `Optional` 사용법을 배웁니다.
