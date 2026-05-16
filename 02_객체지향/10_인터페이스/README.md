# 10. 인터페이스

인터페이스(interface) 는 클래스가 "무엇을 할 수 있는지" 만 정한 **계약(contract)** 입니다. Java 의 클래스는 한 부모만 상속할 수 있지만, **인터페이스는 여러 개 구현(`implements`)** 할 수 있어 행위 조합이 자유롭습니다.

## 학습 목표

- `interface` 키워드로 계약을 정의한다
- `implements` 로 인터페이스를 구현한다
- JDK 8+ `default` 메서드의 의미를 안다
- 함수형 인터페이스(`@FunctionalInterface`) 개념을 익힌다 (자세한 활용은 18편)

## 핵심 개념

### 1) 인터페이스 정의

```java
public interface Printable {
    void print();   // public abstract 가 기본
}
```

- 모든 메서드는 기본적으로 `public abstract`
- 필드는 모두 `public static final` (상수)
- 별도 키워드를 적지 않아도 됨

### 2) `implements`

```java
public class Document implements Printable {
    private String text;
    public Document(String t) { this.text = t; }
    @Override
    public void print() {
        System.out.println(">> " + text);
    }
}
```

여러 인터페이스를 동시에 구현 가능: `implements A, B, C`.

### 3) `default` 메서드

```java
public interface Greeter {
    String name();
    default String greet() {
        return "Hello, " + name();
    }
}
```

기본 구현을 제공해 인터페이스에 메서드를 추가해도 **기존 구현 클래스가 깨지지 않게** 해줍니다.

### 4) 함수형 인터페이스

```java
@FunctionalInterface
interface Calc {
    int apply(int a, int b);
}
```

추상 메서드가 **정확히 1개** 인 인터페이스. lambda 로 짧게 구현할 수 있습니다.

```java
Calc add = (a, b) -> a + b;
add.apply(2, 3);   // 5
```

## 예제로 보기

### 예제 1 — `Printable.java` + `Document.java` : 인터페이스 구현

`Printable.java`:

```java
public interface Printable {
    void print();
}
```

`Document.java`:

```java
public class Document implements Printable {
    private String text;
    public Document(String t) { this.text = t; }

    @Override
    public void print() {
        System.out.println(">> " + text);
    }

    public static void main(String[] args) {
        Printable p = new Document("Hello, Interface!");
        p.print();
    }
}
```

**실행 결과**

```
>> Hello, Interface!
```

**메모:** 변수 타입을 인터페이스로 두면, 어떤 구현체로도 갈아끼울 수 있습니다.

### 예제 2 — `MultipleInterface.java` : 여러 인터페이스 구현

```java
public class MultipleInterface {
    interface Flyable { void fly(); }
    interface Swimmable { void swim(); }

    static class Duck implements Flyable, Swimmable {
        @Override public void fly() { System.out.println("오리가 난다"); }
        @Override public void swim() { System.out.println("오리가 헤엄친다"); }
    }

    public static void main(String[] args) {
        Duck d = new Duck();
        d.fly();
        d.swim();

        Flyable f = d;
        Swimmable s = d;
        f.fly();
        s.swim();
    }
}
```

**실행 결과**

```
오리가 난다
오리가 헤엄친다
오리가 난다
오리가 헤엄친다
```

**메모:** 같은 객체를 다른 인터페이스로 바라볼 수 있습니다.

### 예제 3 — `DefaultMethod.java` : `default` 메서드

```java
public class DefaultMethod {
    interface Greeter {
        String name();
        default String greet() { return "Hello, " + name(); }
    }

    static class Korean implements Greeter {
        public String name() { return "지수"; }
    }
    static class English implements Greeter {
        public String name() { return "John"; }
        @Override
        public String greet() { return "Hi, " + name(); }
    }

    public static void main(String[] args) {
        System.out.println(new Korean().greet());
        System.out.println(new English().greet());
    }
}
```

**실행 결과**

```
Hello, 지수
Hi, John
```

**메모:** `default` 메서드는 자식이 그대로 쓰거나 오버라이딩할 수 있습니다.

### 예제 4 — `FunctionalDemo.java` : 함수형 인터페이스 + lambda

```java
public class FunctionalDemo {
    @FunctionalInterface
    interface Calc {
        int apply(int a, int b);
    }

    public static void main(String[] args) {
        Calc add = (a, b) -> a + b;
        Calc mul = (a, b) -> a * b;
        Calc max = (a, b) -> a > b ? a : b;

        System.out.println("add(2,3)=" + add.apply(2, 3));
        System.out.println("mul(2,3)=" + mul.apply(2, 3));
        System.out.println("max(2,3)=" + max.apply(2, 3));
    }
}
```

**실행 결과**

```
add(2,3)=5
mul(2,3)=6
max(2,3)=3
```

**메모:** lambda 는 18편에서 본격적으로 다룹니다. 여기서는 "함수형 인터페이스 = 람다의 대상" 이라는 개념만 챙기세요.

## 자주 하는 실수

1. `implements` 와 `extends` 혼동 (인터페이스는 `implements`)
2. 인터페이스 메서드를 구현 안 함 → 클래스가 추상 클래스가 되어버림
3. `default` 메서드 충돌 (같은 시그니처가 두 인터페이스에 있을 때 오버라이딩 필수)
4. 함수형 인터페이스에 추상 메서드 2개 (`@FunctionalInterface` 컴파일 에러)
5. 인터페이스에 인스턴스 필드 두려고 시도 (불가)

## 정리

- 인터페이스는 "할 수 있는 일" 의 계약
- 클래스 다중 상속 X, 인터페이스 다중 구현 O
- `default` 메서드는 인터페이스 확장의 안전판
- 함수형 인터페이스는 람다와 만나면 매우 강력

## 직접 해 보기

```bash
cd 02_객체지향/10_인터페이스/src
javac Printable.java Document.java
java Document
```

## 다음 단원

[11_배열](../../03_컬렉션_제네릭/11_배열/) — 1·2차원 배열과 `Arrays` 유틸을 배웁니다.
