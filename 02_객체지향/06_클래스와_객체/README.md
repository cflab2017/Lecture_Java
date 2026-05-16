# 06. 클래스와 객체

Java 는 객체지향(OOP) 언어입니다. 데이터를 **클래스(class)** 로 정의하고, 그 청사진에서 **객체(object, 인스턴스)** 를 찍어 사용합니다. 이 단원에서는 클래스를 정의하고, 객체를 만들어 필드와 메서드를 다루는 가장 기본적인 흐름을 봅니다.

## 학습 목표

- `class` 키워드로 클래스를 정의한다
- 필드·메서드·생성자의 역할을 구분한다
- `this` 가 가리키는 것이 무엇인지 안다
- 접근 제한자(`public` / `private`) 의 효과를 간단히 본다

## 핵심 개념

### 1) 클래스 정의

```java
public class Person {
    String name;
    int age;

    void hello() {
        System.out.println("Hi, I'm " + name);
    }
}
```

클래스는 **상태(필드)** 와 **행동(메서드)** 의 묶음입니다.

### 2) 객체 생성

```java
Person p = new Person();
p.name = "지수";
p.age = 21;
p.hello();
```

`new` 가 객체를 메모리에 만들고, 그 참조를 변수 `p` 에 저장합니다.

### 3) 생성자

```java
public class Person {
    String name;
    int age;

    Person(String name, int age) {  // 생성자
        this.name = name;
        this.age = age;
    }
}
```

생성자는 **반환형이 없는** 특별한 메서드입니다. 객체를 만들면서 초기값을 받습니다. 별도 정의 안 하면 컴파일러가 매개변수 없는 **기본 생성자** 를 자동 추가합니다.

### 4) `this` 키워드

`this` 는 **현재 객체 자신** 을 가리킵니다. 매개변수와 필드 이름이 같을 때 명확하게 구분하는 용도로 자주 씁니다.

```java
this.name = name;  // 왼쪽=필드, 오른쪽=매개변수
```

## 예제로 보기

### 예제 1 — `Person.java` : 클래스 정의와 객체 생성

```java
public class Person {
    String name;
    int age;

    void hello() {
        System.out.println("Hi, I'm " + name + " (" + age + ")");
    }

    public static void main(String[] args) {
        Person p = new Person();
        p.name = "지수";
        p.age = 21;
        p.hello();
    }
}
```

**실행 결과**

```
Hi, I'm 지수 (21)
```

**메모:** 필드를 따로 초기화하지 않으면 기본값(0, null, false) 이 자동 부여됩니다.

### 예제 2 — `PersonWithCtor.java` : 생성자와 `this`

```java
public class PersonWithCtor {
    String name;
    int age;

    PersonWithCtor(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        PersonWithCtor a = new PersonWithCtor("지수", 21);
        PersonWithCtor b = new PersonWithCtor("민수", 25);
        System.out.println(a.name + ", " + a.age);
        System.out.println(b.name + ", " + b.age);
    }
}
```

**실행 결과**

```
지수, 21
민수, 25
```

**메모:** 생성자는 `new` 와 함께만 호출됩니다.

### 예제 3 — `Counter.java` : 객체별 상태

```java
public class Counter {
    int count;

    void increment() {
        count++;
    }

    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        c1.increment();
        c1.increment();
        c2.increment();
        System.out.println("c1=" + c1.count + ", c2=" + c2.count);
    }
}
```

**실행 결과**

```
c1=2, c2=1
```

**메모:** 인스턴스가 다르면 필드도 **각자 따로** 관리됩니다.

### 예제 4 — `Rectangle.java` : 메서드로 동작 추가

```java
public class Rectangle {
    int width;
    int height;

    Rectangle(int w, int h) {
        this.width = w;
        this.height = h;
    }

    int area() {
        return width * height;
    }

    int perimeter() {
        return 2 * (width + height);
    }

    public static void main(String[] args) {
        Rectangle r = new Rectangle(3, 4);
        System.out.println("면적=" + r.area());
        System.out.println("둘레=" + r.perimeter());
    }
}
```

**실행 결과**

```
면적=12
둘레=14
```

**메모:** "데이터 + 그 데이터를 다루는 메서드" 를 한 클래스로 묶는 것이 OOP 의 첫 단계입니다.

## 자주 하는 실수

1. 객체를 만들지 않고 `Person.hello()` 처럼 호출 (인스턴스 메서드는 객체가 있어야)
2. 필드명과 매개변수 충돌인데 `this.` 빠뜨림 → 자기 할당
3. 생성자 이름을 잘못 쓰면 메서드로 간주됨 (반환형 없음 + 클래스명 동일이어야)
4. `new` 없이 변수만 선언하고 사용 → `NullPointerException`
5. 객체 참조 변수 비교에 `==` 사용 (객체는 일반적으로 `.equals()`)

## 정리

- 클래스는 **상태 + 행동** 의 청사진
- 객체는 클래스로부터 `new` 로 찍어낸 인스턴스
- 생성자는 객체 초기화 전용 메서드
- `this` 는 현재 객체 자신

## 직접 해 보기

```bash
cd 02_객체지향/06_클래스와_객체/src
javac Rectangle.java
java Rectangle
```

## 다음 단원

[07_캡슐화](../07_캡슐화/) — `private` 필드, getter/setter, `record` 입문을 배웁니다.
