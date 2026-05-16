# 09. 다형성

다형성(polymorphism) 은 같은 메시지(메서드 호출) 가 객체 종류에 따라 다른 동작을 하도록 만드는 OOP 의 핵심입니다. 부모 타입 변수에 다양한 자식 객체를 담아 두고, 호출 시점에 알맞은 구현이 자동 선택됩니다.

## 학습 목표

- 업캐스팅(자식→부모) 과 다운캐스팅(부모→자식) 의 의미를 안다
- JDK 16+ `instanceof` 패턴 매칭 문법을 사용한다
- `abstract` 클래스로 미완성 부모를 정의한다
- 정적 타입과 동적 타입의 차이를 이해한다

## 핵심 개념

### 1) 업캐스팅과 다운캐스팅

```java
Animal a = new Dog("바둑이");   // 업캐스팅 (자동)
Dog d = (Dog) a;                  // 다운캐스팅 (명시적)
```

업캐스팅은 항상 안전합니다. 다운캐스팅은 실제 타입이 맞아야 하고, 아니라면 `ClassCastException` 이 납니다.

### 2) `instanceof` 패턴 매칭

```java
if (a instanceof Dog d) {
    d.bark();   // 이미 캐스팅된 변수
}
```

JDK 16+ 의 패턴 매칭은 타입 검사 + 캐스팅 + 새 변수 선언을 한 번에 합니다.

### 3) `abstract` 클래스

```java
public abstract class Shape {
    public abstract double area();   // 본문 없음
    public void describe() {
        System.out.println("area=" + area());
    }
}
```

- 본문이 없는 `abstract` 메서드를 한 개라도 가지면 클래스 자체도 `abstract` 여야 합니다
- `new` 로 직접 생성 불가 — 반드시 자식이 구현
- 일반 메서드와 추상 메서드를 혼합 가능 (인터페이스와 차이점)

### 4) 정적 타입 vs 동적 타입

```java
Animal a = new Dog("바둑이");
// 정적 타입: Animal, 동적 타입: Dog
a.speak();   // Dog.speak() 가 호출됨 (동적 디스패치)
```

## 예제로 보기

### 예제 1 — `Polymorphism.java` : 업/다운캐스팅

```java
public class Polymorphism {
    static class Animal {
        void speak() { System.out.println("..."); }
    }
    static class Cat extends Animal {
        @Override void speak() { System.out.println("야옹"); }
        void purr() { System.out.println("그르릉"); }
    }

    public static void main(String[] args) {
        Animal a = new Cat();   // 업캐스팅
        a.speak();              // 야옹 (동적 디스패치)

        // a.purr();             // 컴파일 에러: Animal 에는 없음
        Cat c = (Cat) a;        // 다운캐스팅
        c.purr();
    }
}
```

**실행 결과**

```
야옹
그르릉
```

**메모:** 부모 타입은 자식의 추가 메서드를 모르므로, 자식 전용 호출엔 다운캐스팅이 필요합니다.

### 예제 2 — `InstanceOfPattern.java` : 패턴 매칭

```java
public class InstanceOfPattern {
    sealed interface Animal permits Dog, Cat, Fish {}
    record Dog(String name) implements Animal {}
    record Cat(String name) implements Animal {}
    record Fish(String name) implements Animal {}

    static String introduce(Animal a) {
        if (a instanceof Dog d) return d.name() + "는 강아지";
        if (a instanceof Cat c) return c.name() + "는 고양이";
        if (a instanceof Fish f) return f.name() + "는 물고기";
        return "?";
    }

    public static void main(String[] args) {
        Animal[] zoo = { new Dog("바둑이"), new Cat("나비"), new Fish("니모") };
        for (Animal a : zoo) System.out.println(introduce(a));
    }
}
```

**실행 결과**

```
바둑이는 강아지
나비는 고양이
니모는 물고기
```

**메모:** `sealed` 와 record 를 결합하면 자식 목록을 명시적으로 제한할 수 있어 안전합니다.

### 예제 3 — `AbstractShape.java` : 추상 클래스

```java
public class AbstractShape {
    static abstract class Shape {
        abstract double area();
        void describe() { System.out.println("area=" + area()); }
    }
    static class Square extends Shape {
        double side;
        Square(double s) { this.side = s; }
        @Override double area() { return side * side; }
    }
    static class Circle extends Shape {
        double r;
        Circle(double r) { this.r = r; }
        @Override double area() { return Math.PI * r * r; }
    }

    public static void main(String[] args) {
        // new Shape();        // 컴파일 에러: abstract
        Shape[] xs = { new Square(2), new Circle(3) };
        for (Shape s : xs) s.describe();
    }
}
```

**실행 결과**

```
area=4.0
area=28.274333882308138
```

**메모:** 추상 클래스는 **공통 동작 + 자식이 채울 빈칸** 을 함께 둘 때 알맞습니다.

### 예제 4 — `SwitchPattern.java` : switch 패턴 매칭 (JDK 21)

```java
public class SwitchPattern {
    sealed interface Pet permits DogPet, CatPet {}
    record DogPet(String name, int age) implements Pet {}
    record CatPet(String name) implements Pet {}

    static String describe(Pet p) {
        return switch (p) {
            case DogPet d -> d.name() + "(" + d.age() + "살)";
            case CatPet c -> c.name();
        };
    }

    public static void main(String[] args) {
        System.out.println(describe(new DogPet("바둑이", 3)));
        System.out.println(describe(new CatPet("나비")));
    }
}
```

**실행 결과**

```
바둑이(3살)
나비
```

**메모:** `sealed` 와 `switch` 패턴이 만나면 컴파일러가 **모든 경우** 가 처리됐는지 검증해 줍니다.

## 자주 하는 실수

1. 다운캐스팅 전에 `instanceof` 검사 생략 → `ClassCastException`
2. `@Override` 누락으로 오버라이딩이 아니라 새 메서드가 됨
3. `abstract` 메서드에 본문을 적음
4. 추상 클래스를 `new` 로 직접 인스턴스화 시도
5. `sealed` 자식이 같은 모듈/패키지에 있어야 한다는 제약 망각

## 정리

- 부모 타입에 자식 객체를 담는 것이 다형성의 출발
- `instanceof` 패턴 매칭은 가독성을 크게 높여 줌
- `abstract` 와 `sealed` 는 "허용된 자식만" 만들 때 유용

## 직접 해 보기

```bash
cd 02_객체지향/09_다형성/src
javac Polymorphism.java
java Polymorphism
```

## 다음 단원

[10_인터페이스](../10_인터페이스/) — `interface`, `default` 메서드, 함수형 인터페이스를 배웁니다.
