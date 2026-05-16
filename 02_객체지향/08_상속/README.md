# 08. 상속

상속(inheritance) 은 기존 클래스의 필드·메서드를 **물려받아** 새로운 클래스를 정의하는 OOP 메커니즘입니다. 부모(super) 가 정해 놓은 공통 동작을 자식(sub) 에서 그대로 쓰거나 일부만 재정의(`@Override`) 할 수 있어 코드 중복을 줄여 줍니다.

## 학습 목표

- `extends` 로 클래스를 상속한다
- `super(...)` 로 부모 생성자를 호출한다
- `@Override` 어노테이션의 역할을 안다
- `final` 클래스/메서드의 의미를 안다
- `protected` 가 자식에게만 열린 접근 제한이라는 점을 안다

## 핵심 개념

### 1) `extends` 와 부모-자식 관계

```java
class Animal {
    String name;
    Animal(String name) { this.name = name; }
    void speak() { System.out.println(name + ": ..."); }
}

class Dog extends Animal {
    Dog(String name) { super(name); }
    @Override
    void speak() { System.out.println(name + ": 멍멍!"); }
}
```

Java 는 **단일 상속(single inheritance)** 만 허용합니다. 한 클래스는 최대 한 부모만 가질 수 있습니다.

### 2) `super` 키워드

- 생성자에서 `super(...)` 는 부모 생성자를 호출합니다 (반드시 첫 줄!)
- 메서드 안에서 `super.method()` 는 오버라이딩된 부모 메서드를 호출합니다

### 3) `@Override`

오버라이딩하려는 메서드가 실제로 부모에 존재함을 컴파일러에게 알리고 검증받는 어노테이션입니다. **항상 붙이는 것을 권장**합니다.

### 4) `final`

- `final class` : 더 이상 상속 불가
- `final` 메서드 : 자식에서 오버라이딩 불가
- `final` 필드 : 한 번 초기화 후 변경 불가

### 5) `protected`

`protected` 멤버는 같은 패키지 + 자식 클래스에서 접근 가능합니다. `private` 보다 넓고 `public` 보다 좁습니다.

## 예제로 보기

### 예제 1 — `Animal.java` + `Dog.java` : 가장 기본적인 상속

`Animal.java`:

```java
public class Animal {
    protected String name;

    public Animal(String name) { this.name = name; }

    public void speak() {
        System.out.println(name + ": (기본 울음)");
    }
}
```

`Dog.java`:

```java
public class Dog extends Animal {
    public Dog(String name) { super(name); }

    @Override
    public void speak() {
        System.out.println(name + ": 멍멍!");
    }

    public static void main(String[] args) {
        Animal a = new Animal("동물");
        Dog d = new Dog("바둑이");
        a.speak();
        d.speak();
    }
}
```

**실행 결과**

```
동물: (기본 울음)
바둑이: 멍멍!
```

**메모:** `Dog` 는 `Animal` 의 `name` 필드를 그대로 사용합니다 (`protected` 라 접근 가능).

### 예제 2 — `SuperKeyword.java` : `super` 활용

```java
class Base {
    String tag() { return "[BASE]"; }
}

public class SuperKeyword extends Base {
    @Override
    String tag() {
        return super.tag() + " + " + "[SUB]";
    }

    public static void main(String[] args) {
        System.out.println(new SuperKeyword().tag());
    }
}
```

**실행 결과**

```
[BASE] + [SUB]
```

**메모:** 자식이 부모 메서드를 **확장** 하고 싶을 때 `super.method()` 가 유용합니다.

### 예제 3 — `FinalDemo.java` : 상속·재정의 차단

```java
public class FinalDemo {
    static final class Constants {
        static final double PI = 3.141592;
    }

    static class Base {
        final void fixed() { System.out.println("재정의 금지"); }
        void open() { System.out.println("Base.open"); }
    }

    static class Sub extends Base {
        @Override
        void open() { System.out.println("Sub.open"); }
        // void fixed() { ... }  // 컴파일 에러: final 메서드 오버라이딩 금지
    }

    public static void main(String[] args) {
        Sub s = new Sub();
        s.fixed();
        s.open();
        System.out.println("PI=" + Constants.PI);
    }
}
```

**실행 결과**

```
재정의 금지
Sub.open
PI=3.141592
```

**메모:** `final` 은 의도적으로 상속을 끊고 싶을 때 사용합니다.

### 예제 4 — `MethodOverride.java` : 다양한 자식

```java
class Shape {
    double area() { return 0.0; }
}

class Square extends Shape {
    double side;
    Square(double s) { this.side = s; }
    @Override double area() { return side * side; }
}

class Circle extends Shape {
    double radius;
    Circle(double r) { this.radius = r; }
    @Override double area() { return Math.PI * radius * radius; }
}

public class MethodOverride {
    public static void main(String[] args) {
        Shape[] shapes = { new Square(3), new Circle(2) };
        for (Shape s : shapes) {
            System.out.printf("area=%.3f%n", s.area());
        }
    }
}
```

**실행 결과**

```
area=9.000
area=12.566
```

**메모:** 부모 타입 배열에 자식 객체를 담아 같은 메서드를 호출하면 각자 다른 결과 — **다형성** 의 맛보기입니다 (다음 단원).

## 자주 하는 실수

1. 부모 생성자 호출이 생성자 첫 줄이어야 한다는 규칙 위반
2. `@Override` 빼먹고 시그니처가 살짝 달라서 오버로딩이 됨 (조용한 버그)
3. `private` 메서드를 오버라이딩한다고 착각 (사실은 새 메서드)
4. 무리한 상속 (is-a 관계가 아닌데 `extends`)
5. 깊은 상속 계층 → 가독성 저하; 보통 **2~3 단계** 이내가 권장

## 정리

- 상속은 코드 재사용 + 다형성의 기반
- `@Override` 는 무조건 붙이는 게 안전
- `final` 로 의도적 차단 가능
- 상속보다 **조합(composition)** 이 종종 더 유연한 대안

## 직접 해 보기

```bash
cd 02_객체지향/08_상속/src
javac Animal.java Dog.java
java Dog
```

## 다음 단원

[09_다형성](../09_다형성/) — 업/다운캐스팅, `instanceof` 패턴 매칭, `abstract` 클래스를 배웁니다.
