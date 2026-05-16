# 07. 캡슐화

캡슐화(encapsulation) 는 객체의 **상태(필드)** 를 외부로부터 숨기고, 정해진 메서드(getter/setter, 또는 도메인 메서드) 로만 접근하게 만드는 OOP 원칙입니다. 이를 통해 잘못된 값으로 객체가 깨지는 것을 막고, 내부 구현을 자유롭게 바꿀 수 있는 여지가 생깁니다.

## 학습 목표

- `public` / `private` / package-private 접근 제한자의 차이를 안다
- `private` 필드 + getter/setter 패턴을 작성한다
- 불변(immutable) 객체의 의미와 장점을 이해한다
- JDK 16+ `record` 가 어떤 보일러플레이트를 줄여 주는지 본다
- 패키지 개념을 한 번 짚는다

## 핵심 개념

### 1) 접근 제한자

| 제한자 | 같은 클래스 | 같은 패키지 | 자식 클래스 | 다른 패키지 |
|---|---|---|---|---|
| `public` | ✅ | ✅ | ✅ | ✅ |
| `protected` | ✅ | ✅ | ✅ | ❌ |
| (default) | ✅ | ✅ | ❌ | ❌ |
| `private` | ✅ | ❌ | ❌ | ❌ |

대부분의 필드는 **`private`** 로 두는 것이 권장됩니다.

### 2) getter/setter

```java
public class Account {
    private long balance;

    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount > 0");
        this.balance += amount;
    }
}
```

setter 를 **무조건 만들지 마세요**. 입금 같은 **도메인 행위** 가 따로 있다면 그것이 setter 보다 좋습니다.

### 3) 불변 객체

값이 한 번 정해지면 절대 바뀌지 않는 객체를 **불변 객체** 라 합니다. 멀티스레드에서도 안전하고 디버깅이 쉬워집니다.

```java
public final class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) { this.x = x; this.y = y; }
    public int getX() { return x; }
    public int getY() { return y; }
}
```

`final` 클래스 + `final` 필드 + setter 없음 + 생성자에서만 값 결정.

### 4) `record` (JDK 16+)

불변 데이터 캐리어를 한 줄로 정의할 수 있습니다.

```java
public record Point(int x, int y) {}
```

자동으로 생성자·`getX()` 대신 `x()`·`equals`/`hashCode`/`toString` 이 만들어집니다.

### 5) 패키지

같은 의미의 클래스를 묶는 **이름공간** 입니다. `package com.codingnow.lecture.oop07;` 같이 파일 맨 위에 한 번 선언합니다. 본 단원에서는 디렉토리 구조 단순화를 위해 패키지를 사용하지 않지만, 21편 이후 Maven 프로젝트에선 표준 디렉토리 구조와 함께 자동으로 사용됩니다.

## 예제로 보기

### 예제 1 — `PrivateField.java` : 캡슐화의 첫 단계

```java
public class PrivateField {
    private int age;

    public int getAge() { return age; }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("age >= 0");
        this.age = age;
    }

    public static void main(String[] args) {
        PrivateField p = new PrivateField();
        p.setAge(21);
        System.out.println("age=" + p.getAge());
        try {
            p.setAge(-1);
        } catch (IllegalArgumentException e) {
            System.out.println("거부: " + e.getMessage());
        }
    }
}
```

**실행 결과**

```
age=21
거부: age >= 0
```

**메모:** setter 에 **검증 로직** 을 두면 잘못된 상태를 미리 막을 수 있습니다.

### 예제 2 — `Account.java` : 도메인 행위 중심

```java
public class Account {
    private long balance;

    public Account(long initial) { this.balance = initial; }

    public long getBalance() { return balance; }

    public void deposit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount > 0");
        balance += amount;
    }

    public void withdraw(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("amount > 0");
        if (amount > balance) throw new IllegalStateException("잔액 부족");
        balance -= amount;
    }

    public static void main(String[] args) {
        Account a = new Account(10_000);
        a.deposit(5_000);
        a.withdraw(3_000);
        System.out.println("잔액=" + a.getBalance());
    }
}
```

**실행 결과**

```
잔액=12000
```

**메모:** setter 보다 `deposit` / `withdraw` 같은 도메인 메서드가 의미가 분명합니다.

### 예제 3 — `ImmutablePoint.java` : 불변 객체

```java
public final class ImmutablePoint {
    private final int x;
    private final int y;

    public ImmutablePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }

    public ImmutablePoint translate(int dx, int dy) {
        return new ImmutablePoint(x + dx, y + dy);
    }

    public static void main(String[] args) {
        ImmutablePoint p = new ImmutablePoint(1, 2);
        ImmutablePoint q = p.translate(10, 20);
        System.out.println("p=(" + p.getX() + "," + p.getY() + ")");
        System.out.println("q=(" + q.getX() + "," + q.getY() + ")");
    }
}
```

**실행 결과**

```
p=(1,2)
q=(11,22)
```

**메모:** 상태를 바꾸는 대신 **새 객체를 반환** 하는 것이 불변 객체의 사용 패턴입니다.

### 예제 4 — `PointRecord.java` : `record` 로 한 줄

```java
public class PointRecord {
    public record Point(int x, int y) {}

    public static void main(String[] args) {
        Point a = new Point(1, 2);
        Point b = new Point(1, 2);
        System.out.println(a);
        System.out.println("a.x = " + a.x());
        System.out.println("a.equals(b) = " + a.equals(b));
    }
}
```

**실행 결과**

```
Point[x=1, y=2]
a.x = 1
a.equals(b) = true
```

**메모:** `record` 는 자동으로 `toString` / `equals` / `hashCode` 를 만들어 줍니다.

## 자주 하는 실수

1. 모든 필드에 setter 부터 만들기 → 의미 있는 도메인 메서드부터 고민
2. 불변을 의도했는데 내부 컬렉션을 그대로 노출 (얕은 불변)
3. `record` 안에서 가변 객체 필드를 그대로 받음 (방어 복사 필요)
4. `public` 필드 남발 → 캡슐화 무너짐
5. setter 에서 검증 누락

## 정리

- 필드는 우선 `private`, 외부와의 통로는 메서드
- 불변 객체는 안전하고 단순
- `record` 는 데이터 클래스의 보일러플레이트를 줄여 줌

## 직접 해 보기

```bash
cd 02_객체지향/07_캡슐화/src
javac Account.java
java Account
```

## 다음 단원

[08_상속](../08_상속/) — `extends`, `super`, `@Override` 와 메서드 오버라이딩을 배웁니다.
