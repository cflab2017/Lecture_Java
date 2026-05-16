# 19. Optional

`Optional<T>` 은 "값이 있을 수도, 없을 수도 있다" 는 의미를 **타입** 으로 명시하는 컨테이너입니다. 반환값이 null 일 수 있는 메서드에 사용하면 호출자가 null 체크를 잊지 못하게 강제할 수 있어 NPE 를 줄여 줍니다.

## 학습 목표

- `Optional.of` / `ofNullable` / `empty` 를 사용한다
- `map` / `filter` / `orElse` / `orElseThrow` / `ifPresent` 를 안다
- Optional 을 **반환 타입** 으로 사용하는 패턴을 익힌다
- Optional 을 안티패턴(필드, 매개변수, 컬렉션 요소) 에 쓰지 않는다

## 핵심 개념

### 1) 생성

```java
Optional<String> a = Optional.of("hi");          // null 이면 NPE
Optional<String> b = Optional.ofNullable(maybe); // null 허용
Optional<String> c = Optional.empty();
```

### 2) 값 꺼내기

```java
String v = a.orElse("default");
String w = a.orElseThrow(() -> new IllegalStateException("없음"));
a.ifPresent(System.out::println);
```

`get()` 은 값이 없을 때 예외를 던지므로 권장하지 않습니다.

### 3) 변환과 필터

```java
Optional<Integer> len = a.map(String::length);
Optional<String> upper = a.filter(s -> s.length() > 3).map(String::toUpperCase);
```

### 4) 안티패턴

- 필드 타입으로 Optional 사용 X (직렬화·성능 손해)
- 매개변수 타입으로 Optional 사용 X (오버로딩 또는 null 허용으로 충분)
- 컬렉션 요소 타입으로 Optional 사용 X (빈 컬렉션이면 충분)

## 예제로 보기

### 예제 1 — `OptionalBasics.java` : 생성·꺼내기

```java
import java.util.Optional;

public class OptionalBasics {
    public static void main(String[] args) {
        Optional<String> hi = Optional.of("hi");
        Optional<String> empty = Optional.empty();

        System.out.println(hi.isPresent());
        System.out.println(empty.isPresent());

        System.out.println(hi.orElse("default"));
        System.out.println(empty.orElse("default"));

        hi.ifPresent(s -> System.out.println("값=" + s));
        empty.ifPresent(s -> System.out.println("실행 안 됨"));
    }
}
```

**실행 결과**

```
true
false
hi
default
값=hi
```

**메모:** `ifPresent` 는 값이 있을 때만 람다를 실행합니다.

### 예제 2 — `OptionalMapFilter.java` : 변환·필터 체이닝

```java
import java.util.Optional;

public class OptionalMapFilter {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Java");
        Optional<Integer> len = name.map(String::length);
        len.ifPresent(n -> System.out.println("길이=" + n));

        Optional<String> longName = Optional.of("hi")
            .filter(s -> s.length() > 3)
            .map(String::toUpperCase);
        System.out.println("긴 이름? " + longName);
    }
}
```

**실행 결과**

```
길이=4
긴 이름? Optional.empty
```

**메모:** `filter` 가 false 면 체인 끝까지 빈 Optional 이 흐릅니다.

### 예제 3 — `OptionalReturn.java` : 반환 타입으로 사용

```java
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OptionalReturn {
    static Map<String, Integer> ages = Map.of("지수", 21, "민수", 25);

    static Optional<Integer> findAge(String name) {
        return Optional.ofNullable(ages.get(name));
    }

    public static void main(String[] args) {
        System.out.println(findAge("지수").orElse(-1));
        System.out.println(findAge("미상").orElse(-1));

        findAge("민수").ifPresent(a -> System.out.println("민수=" + a));
    }
}
```

**실행 결과**

```
21
-1
민수=25
```

**메모:** 메서드 반환 타입에 Optional 을 쓰면 호출자가 null 처리를 의식적으로 하게 됩니다.

### 예제 4 — `OrElseThrow.java` : 없을 때 예외

```java
import java.util.Optional;

public class OrElseThrow {
    public static void main(String[] args) {
        Optional<String> ok = Optional.of("값");
        Optional<String> none = Optional.empty();

        System.out.println(ok.orElseThrow());

        try {
            none.orElseThrow(() -> new IllegalStateException("필수 값 누락"));
        } catch (IllegalStateException e) {
            System.out.println("예외: " + e.getMessage());
        }
    }
}
```

**실행 결과**

```
값
예외: 필수 값 누락
```

**메모:** `orElseThrow` 는 도메인 의미가 분명한 예외와 함께 자주 사용됩니다.

## 자주 하는 실수

1. `Optional.of(null)` → 즉시 NPE. null 가능성 있으면 `ofNullable`
2. `get()` 무비판적 사용
3. 필드 타입 Optional (직렬화/JPA 와 충돌)
4. `Optional<List<X>>` 보다 빈 리스트 `List<X>` 가 더 자연스러움
5. Optional 을 if-else 로 풀어 쓰면 의미가 사라짐 — `map`/`orElse` 체이닝 활용

## 정리

- Optional 은 **반환 타입** 으로 가장 잘 어울림
- `get()` 보다 `orElse`/`orElseThrow`/`ifPresent` 가 안전
- `map`/`filter` 체이닝으로 if 중첩 제거

## 직접 해 보기

```bash
cd 05_모던_자바/19_Optional/src
javac OptionalReturn.java
java OptionalReturn
```

## 다음 단원

[20_Date_Time](../20_Date_Time/) — `LocalDate`/`LocalTime`/`Duration` 등 모던 시간 API 를 다룹니다.
