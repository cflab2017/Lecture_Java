# 03. 연산자와 표현식

연산자는 값(피연산자)을 받아 새로운 값을 만드는 기호입니다. Java 의 연산자는 C/C++ 에서 유래해 익숙한 모양이지만, **객체 비교(`==` vs `.equals()`)** 와 **정수/실수 나눗셈** 같은 함정이 있습니다. 이 단원에서 정리합니다.

## 학습 목표

- 산술·비교·논리·비트·삼항·증감 연산자를 구분해 사용한다
- `String` 결합 시 `+` 의 좌→우 평가 규칙을 이해한다
- `==` 와 `.equals()` 의 차이를 명확히 안다
- 짧은 회로 평가(short-circuit `&&` / `||`) 가 무엇인지 이해한다

## 핵심 개념

### 1) 산술 / 비교 / 논리

```java
int a = 10, b = 3;
System.out.println(a + b);   // 13
System.out.println(a / b);   // 3   (정수 나눗셈!)
System.out.println(a % b);   // 1
System.out.println(a > b);   // true
System.out.println(a == 10 && b > 0);   // true
```

### 2) 증감 연산자

```java
int x = 5;
System.out.println(x++);   // 5 (사용 후 증가)
System.out.println(++x);   // 7 (증가 후 사용)
```

`x++` 와 `++x` 는 결과 값이 다릅니다. 식 안에 섞어 쓰면 가독성이 떨어지니 주의하세요.

### 3) 삼항 연산자

```java
int score = 75;
String result = (score >= 60) ? "합격" : "불합격";
```

`if/else` 의 짧은 형태입니다. 한 줄로 값을 정할 때 유용합니다.

### 4) `==` vs `.equals()`

```java
String a = "hello";
String b = new String("hello");
System.out.println(a == b);         // false  (참조 다름)
System.out.println(a.equals(b));    // true   (내용 같음)
```

**객체(특히 String/Integer/리스트 등)는 항상 `.equals()`** 로 비교하세요. `==` 은 primitive 끼리 또는 참조 동일성 확인용입니다.

### 5) 비트 연산자

```java
int mask = 0b1010;
int val  = 0b1100;
System.out.println(mask & val);  // 8   (AND)
System.out.println(mask | val);  // 14  (OR)
System.out.println(mask ^ val);  // 6   (XOR)
System.out.println(mask << 1);   // 20  (왼쪽 시프트)
```

플래그 처리·해시·암호화 같은 곳에서 만나게 됩니다.

## 예제로 보기

### 예제 1 — `Arithmetic.java` : 산술과 나눗셈 함정

```java
public class Arithmetic {
    public static void main(String[] args) {
        int a = 17, b = 5;
        System.out.println(a + b);
        System.out.println(a - b);
        System.out.println(a * b);
        System.out.println(a / b);        // 정수 나눗셈
        System.out.println(a % b);
        System.out.println((double) a / b); // 실수 나눗셈
    }
}
```

**실행 결과**

```
22
12
85
3
2
3.4
```

**메모:** `int / int` 는 항상 정수입니다. 실수가 필요하면 한쪽을 `double` 로 캐스팅하세요.

### 예제 2 — `LogicAndTernary.java` : 비교·논리·삼항

```java
public class LogicAndTernary {
    public static void main(String[] args) {
        int score = 75;
        boolean passed = score >= 60;
        System.out.println("합격? " + passed);

        String grade = (score >= 90) ? "A"
                     : (score >= 80) ? "B"
                     : (score >= 70) ? "C"
                     : (score >= 60) ? "D"
                     : "F";
        System.out.println("등급: " + grade);

        boolean ok = score > 0 && score <= 100;
        System.out.println("유효 범위? " + ok);
    }
}
```

**실행 결과**

```
합격? true
등급: C
유효 범위? true
```

**메모:** `&&` 와 `||` 는 **단락 평가(short-circuit)** 입니다. `a != null && a.length() > 0` 처럼 안전한 null 체크가 가능합니다.

### 예제 3 — `EqualsVsEqEq.java` : 참조 비교 vs 내용 비교

```java
public class EqualsVsEqEq {
    public static void main(String[] args) {
        String a = "Java";
        String b = "Java";
        String c = new String("Java");
        System.out.println("a == b      : " + (a == b));
        System.out.println("a == c      : " + (a == c));
        System.out.println("a.equals(c) : " + a.equals(c));

        Integer x = 100;
        Integer y = 100;
        Integer z = 200;
        Integer w = 200;
        System.out.println("100==100? " + (x == y)); // 캐시로 true
        System.out.println("200==200? " + (z == w)); // 캐시 밖, false
        System.out.println("equals    : " + z.equals(w));
    }
}
```

**실행 결과**

```
a == b      : true
a == c      : false
a.equals(c) : true
100==100? true
200==200? false
equals    : true
```

**메모:** `Integer` 는 `-128 ~ 127` 만 캐시되어 `==` 가 우연히 true 가 됩니다. **객체는 항상 `.equals()`**.

### 예제 4 — `Bitwise.java` : 비트 연산

```java
public class Bitwise {
    public static void main(String[] args) {
        int a = 0b1010;
        int b = 0b1100;
        System.out.printf("a & b = %d (%s)%n", a & b, Integer.toBinaryString(a & b));
        System.out.printf("a | b = %d (%s)%n", a | b, Integer.toBinaryString(a | b));
        System.out.printf("a ^ b = %d (%s)%n", a ^ b, Integer.toBinaryString(a ^ b));
        System.out.printf("~a    = %d%n", ~a);
        System.out.printf("a<<1  = %d%n", a << 1);
        System.out.printf("a>>1  = %d%n", a >> 1);
    }
}
```

**실행 결과**

```
a & b = 8 (1000)
a | b = 14 (1110)
a ^ b = 6 (110)
~a    = -11
a<<1  = 20
a>>1  = 5
```

**메모:** `~a` 는 `-(a+1)` 입니다. 2의 보수 표현 때문에 그렇습니다.

## 자주 하는 실수

1. 객체 비교에 `==` 사용 → `.equals()` 로 교체
2. `int a = 1/2;` 가 0 인 줄 모르고 계산
3. 후위 `i++` 를 if 조건 안에 섞어 결과가 헷갈림
4. `&&` 와 `&` 혼동 (전자는 단락 평가, 후자는 항상 양쪽 평가)
5. `Integer` 캐시 때문에 가끔 우연히 `==` 가 통하는 버그

## 정리

- 정수 vs 실수 연산을 의식적으로 구분합니다
- 객체 비교는 무조건 `.equals()`
- 비트 연산은 플래그·해시 같은 특수 상황에서 유용
- 짧은 회로 평가는 null 체크의 핵심 도구

## 직접 해 보기

```bash
cd 01_기초/03_연산자와_표현식/src
javac Arithmetic.java
java Arithmetic
```

## 다음 단원

[04_제어문](../04_제어문/) — `if` / `switch` / 반복문을 다룹니다.
