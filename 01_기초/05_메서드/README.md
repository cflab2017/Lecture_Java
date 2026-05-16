# 05. 메서드

메서드는 "이름이 붙은 코드 한 묶음" 입니다. 같은 일을 여러 번 해야 할 때 메서드로 분리하면 가독성과 재사용성이 동시에 좋아집니다. 이 단원에서는 메서드의 선언법, 오버로딩, `static` 의 의미, 가변 인자(`...`) 를 다룹니다.

## 학습 목표

- 메서드 선언 형식(접근 제한자·반환형·이름·매개변수)을 안다
- 오버로딩과 오버라이딩의 차이를 안다 (오버라이딩은 08편)
- `static` 메서드와 인스턴스 메서드의 차이를 이해한다
- 가변 인자 `String...` 의 동작을 본다

## 핵심 개념

### 1) 메서드 선언

```java
public static int add(int a, int b) {
    return a + b;
}
```

- 접근 제한자: `public` / `private` / (기본)package-private / `protected`
- 반환형: 없으면 `void`
- 매개변수: 0 개 이상
- 본문: 중괄호 `{ ... }`

### 2) 오버로딩

```java
static int max(int a, int b) { return a > b ? a : b; }
static double max(double a, double b) { return a > b ? a : b; }
```

같은 이름, **다른 매개변수 시그니처** 를 갖는 여러 메서드를 만들 수 있습니다.

### 3) `static` 메서드

```java
class MathUtil {
    static int square(int x) { return x * x; }
}
MathUtil.square(5);  // 인스턴스 없이 호출
```

`static` 은 **클래스 자체** 에 속하는 메서드입니다. 객체를 만들지 않고 `클래스명.메서드()` 로 호출합니다.

### 4) 가변 인자 (`...`)

```java
static int sumAll(int... nums) {
    int s = 0;
    for (int n : nums) s += n;
    return s;
}
sumAll(1, 2, 3, 4);    // 10
sumAll();              // 0
```

내부적으로 배열로 처리되며, 시그니처에서 **맨 끝에 한 개만** 둘 수 있습니다.

## 예제로 보기

### 예제 1 — `MethodBasics.java` : 선언과 호출

```java
public class MethodBasics {
    public static void main(String[] args) {
        int s = add(3, 4);
        System.out.println("3 + 4 = " + s);
        greet("지수");
    }

    /** 두 정수의 합. */
    static int add(int a, int b) {
        return a + b;
    }

    /** 인사 출력. */
    static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }
}
```

**실행 결과**

```
3 + 4 = 7
Hello, 지수!
```

**메모:** `main` 도 결국 `static` 메서드라는 사실이 보입니다.

### 예제 2 — `Overloading.java` : 같은 이름, 다른 시그니처

```java
public class Overloading {
    public static void main(String[] args) {
        System.out.println(max(3, 7));
        System.out.println(max(3.5, 2.1));
        System.out.println(max("apple", "banana"));
    }

    static int max(int a, int b) { return a > b ? a : b; }
    static double max(double a, double b) { return a > b ? a : b; }
    static String max(String a, String b) { return a.compareTo(b) > 0 ? a : b; }
}
```

**실행 결과**

```
7
3.5
banana
```

**메모:** 반환형만 다르고 매개변수가 같다면 **오버로딩이 안 됩니다**.

### 예제 3 — `StaticVsInstance.java` : 클래스 vs 인스턴스

```java
public class StaticVsInstance {
    static int staticCount;     // 클래스 1개 공유
    int instanceCount;          // 인스턴스마다 분리

    void increment() {
        staticCount++;
        instanceCount++;
    }

    public static void main(String[] args) {
        StaticVsInstance a = new StaticVsInstance();
        StaticVsInstance b = new StaticVsInstance();
        a.increment(); a.increment();
        b.increment();
        System.out.println("a.instance=" + a.instanceCount);
        System.out.println("b.instance=" + b.instanceCount);
        System.out.println("static=" + StaticVsInstance.staticCount);
    }
}
```

**실행 결과**

```
a.instance=2
b.instance=1
static=3
```

**메모:** `static` 필드는 **클래스 전체에 1개**, 인스턴스 필드는 **객체마다 별도** 입니다.

### 예제 4 — `Varargs.java` : 가변 인자

```java
public class Varargs {
    public static void main(String[] args) {
        System.out.println(sumAll());
        System.out.println(sumAll(1, 2, 3));
        System.out.println(sumAll(10, 20, 30, 40));
    }

    /** 가변 개수의 정수를 모두 합산. */
    static int sumAll(int... nums) {
        int s = 0;
        for (int n : nums) s += n;
        return s;
    }
}
```

**실행 결과**

```
0
6
100
```

**메모:** `int...` 는 내부적으로 `int[]` 와 동일하게 동작합니다.

## 자주 하는 실수

1. 반환형이 `void` 인데 `return value;` 작성
2. 매개변수에 같은 이름을 두 번 사용
3. `static` 메서드 안에서 인스턴스 필드 직접 접근 (불가)
4. 오버로딩과 오버라이딩 혼동 — 오버라이딩은 상속(08편)
5. 가변 인자를 메서드 시그니처 중간에 두기

## 정리

- 메서드는 코드 재사용의 기본 단위
- 같은 이름·다른 시그니처는 **오버로딩**
- `static` 은 클래스 소속, 인스턴스 없이 호출
- 가변 인자는 마지막 자리에 한 개만

## 직접 해 보기

```bash
cd 01_기초/05_메서드/src
javac Overloading.java
java Overloading
```

## 다음 단원

[06_클래스와_객체](../../02_객체지향/06_클래스와_객체/) — 객체지향의 첫 걸음, `class` 정의와 객체 생성을 배웁니다.
