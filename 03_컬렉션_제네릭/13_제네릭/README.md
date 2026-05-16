# 13. 제네릭

제네릭(generic) 은 "어떤 타입을 다룰지" 를 **클래스/메서드 정의 시점에 미루는** 메커니즘입니다. `List<String>` 의 꺾쇠가 바로 그 자리 표시자입니다. 컴파일 시점에 타입을 강제해 안전하고, 다양한 타입을 다룰 수 있어 재사용성도 높습니다.

## 학습 목표

- 제네릭 클래스 `Box<T>` 를 정의한다
- 제네릭 메서드 `<T> T choose(T a, T b)` 를 작성한다
- 와일드카드 `?`, `? extends T`, `? super T` 를 구분한다
- bounded type (`<T extends Number>`) 의 효과를 안다
- 타입 소거(type erasure) 의 개념을 살짝 본다

## 핵심 개념

### 1) 제네릭 클래스

```java
public class Box<T> {
    private T value;
    public void set(T v) { this.value = v; }
    public T get() { return value; }
}

Box<String> b = new Box<>();
b.set("hi");
String s = b.get();      // 캐스팅 불필요
```

### 2) 제네릭 메서드

```java
static <T> T choose(boolean first, T a, T b) {
    return first ? a : b;
}
String r = choose(true, "yes", "no");
```

### 3) 와일드카드

```java
List<? extends Number> nums = ...;   // Number 또는 그 자식 (읽기 전용에 가까움)
List<? super Integer> ints = ...;     // Integer 또는 그 부모 (쓰기 가능)
```

**PECS**: Producer-Extends, Consumer-Super 라는 암기 규칙이 있습니다. 값을 **꺼내쓸 때 `extends`**, **넣어줄 때 `super`**.

### 4) bounded type

```java
static <T extends Number> double sum(List<T> xs) {
    double s = 0;
    for (T x : xs) s += x.doubleValue();
    return s;
}
```

`Number` 또는 그 자식만 받을 수 있어 `doubleValue()` 같은 부모 메서드를 안전하게 호출할 수 있습니다.

### 5) 타입 소거

런타임에는 제네릭 정보가 사라집니다 (`List<String>` ↔ `List<Integer>` 가 같은 클래스로 보임). 따라서 `new T()` 같은 코드는 불가능합니다.

## 예제로 보기

### 예제 1 — `BoxGeneric.java` : 제네릭 클래스

```java
public class BoxGeneric {
    static class Box<T> {
        private T value;
        public void set(T v) { this.value = v; }
        public T get() { return value; }
    }

    public static void main(String[] args) {
        Box<String> s = new Box<>();
        s.set("Hello");
        System.out.println(s.get());

        Box<Integer> n = new Box<>();
        n.set(42);
        System.out.println(n.get() + 1);
    }
}
```

**실행 결과**

```
Hello
43
```

**메모:** 다이아몬드 연산자 `<>` 가 우항 타입을 추론합니다.

### 예제 2 — `GenericMethod.java` : 제네릭 메서드

```java
import java.util.List;

public class GenericMethod {
    static <T> T first(List<T> list) {
        return list.isEmpty() ? null : list.get(0);
    }

    public static void main(String[] args) {
        System.out.println(first(List.of(1, 2, 3)));
        System.out.println(first(List.of("a", "b")));
        Object none = first(List.of());
        System.out.println(none);
    }
}
```

**실행 결과**

```
1
a
null
```

**메모:** 메서드 시그니처 안의 `<T>` 는 메서드 호출 시 추론됩니다.

### 예제 3 — `Wildcards.java` : `? extends` 와 `? super`

```java
import java.util.ArrayList;
import java.util.List;

public class Wildcards {
    /** 어떤 숫자 리스트든 합산. */
    static double sum(List<? extends Number> xs) {
        double s = 0;
        for (Number n : xs) s += n.doubleValue();
        return s;
    }

    /** Integer 또는 그 부모 자리에 1~3 추가. */
    static void addNums(List<? super Integer> xs) {
        xs.add(1); xs.add(2); xs.add(3);
    }

    public static void main(String[] args) {
        System.out.println(sum(List.of(1, 2, 3)));
        System.out.println(sum(List.of(1.5, 2.5)));

        List<Number> ns = new ArrayList<>();
        addNums(ns);
        System.out.println(ns);
    }
}
```

**실행 결과**

```
6.0
4.0
[1, 2, 3]
```

**메모:** **꺼내 쓸 때 `extends`**, **넣어 줄 때 `super`**.

### 예제 4 — `BoundedType.java` : `T extends Number`

```java
import java.util.List;

public class BoundedType {
    static <T extends Number> double average(List<T> xs) {
        if (xs.isEmpty()) return 0;
        double s = 0;
        for (T x : xs) s += x.doubleValue();
        return s / xs.size();
    }

    public static void main(String[] args) {
        System.out.println(average(List.of(10, 20, 30)));
        System.out.println(average(List.of(1.5, 2.5, 3.5)));
    }
}
```

**실행 결과**

```
20.0
2.5
```

**메모:** `T extends Number` 덕분에 `Integer`/`Double`/`Long` 등을 한 메서드로 처리할 수 있습니다.

## 자주 하는 실수

1. `Box<Object>` 와 `Box<String>` 이 호환된다고 착각 (서로 무관)
2. `new T()` / `new T[10]` 시도 → 타입 소거로 인해 불가
3. raw type 사용 (`List list = ...`) → 컴파일 경고 + 런타임 위험
4. `? extends T` 컬렉션에 `add` 호출 (대부분 막힘)
5. 와일드카드를 메서드 반환 타입에 남발 → 호출 측 불편

## 정리

- 제네릭은 컴파일 타임 타입 안전성을 위한 도구
- `<T>` 는 클래스/메서드 레벨에서 사용 가능
- 와일드카드는 PECS 규칙으로 외우면 편함
- 런타임에는 타입 소거되므로 일부 제약 존재

## 직접 해 보기

```bash
cd 03_컬렉션_제네릭/13_제네릭/src
javac BoxGeneric.java
java BoxGeneric
```

## 다음 단원

[14_Stream_API](../14_Stream_API/) — 컬렉션을 우아하게 다루는 Stream 을 배웁니다.
