# 17. 문자열 처리

문자열(`String`) 은 어떤 프로그램에서도 빠지지 않는 자료형입니다. Java 의 `String` 은 **불변(immutable)** 이며, 변경이 잦은 경우엔 `StringBuilder` 를 함께 사용합니다. 검색·치환에는 정규식(`Pattern`/`Matcher`) 이 강력한 도구입니다.

## 학습 목표

- `String` 의 주요 메서드 (`length`, `substring`, `indexOf`, `split`, `trim` 등) 를 안다
- `String` 이 불변이라는 의미와 결합 시 비용을 안다
- `StringBuilder` 로 반복 결합을 최적화한다
- `String.format` 으로 보기 좋은 문자열을 만든다
- 정규식 기본 사용법을 본다 (`Pattern.compile`, `matcher.find`)

## 핵심 개념

### 1) 불변성

```java
String a = "Hello";
String b = a.replace("e", "a");
System.out.println(a);   // Hello  (a 는 안 바뀜)
System.out.println(b);   // Hallo
```

`String` 메서드는 **새 문자열** 을 반환하지 원본을 바꾸지 않습니다.

### 2) 주요 메서드

```java
"Java".length();              // 4
"Java".substring(1, 3);        // av
"Hello, World".indexOf(",");   // 5
"  hi ".trim();                 // "hi"
"a,b,c".split(",");             // ["a","b","c"]
String.join("-", "a","b","c"); // "a-b-c"
```

### 3) `StringBuilder`

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 5; i++) sb.append(i).append(",");
System.out.println(sb.toString());   // 0,1,2,3,4,
```

루프 안에서의 문자열 결합은 `StringBuilder` 가 훨씬 빠릅니다.

### 4) `String.format` / text block

```java
String row = String.format("이름=%s, 점수=%d", "지수", 95);

String json = """
    {
        "name": "지수",
        "score": 95
    }
    """;
```

### 5) 정규식

```java
import java.util.regex.Pattern;
import java.util.regex.Matcher;

Pattern p = Pattern.compile("\\d+");
Matcher m = p.matcher("주문번호: 1234, 가격: 5678");
while (m.find()) {
    System.out.println(m.group());
}
```

## 예제로 보기

### 예제 1 — `StringMethods.java` : 주요 메서드

```java
public class StringMethods {
    public static void main(String[] args) {
        String s = " Hello, Java World ";
        System.out.println("length=" + s.length());
        System.out.println("trim=" + s.trim() + "|");
        System.out.println("upper=" + s.toUpperCase());
        System.out.println("startsWith Hello? " + s.trim().startsWith("Hello"));
        System.out.println("sub=" + s.trim().substring(7, 11));
        System.out.println("rep=" + s.trim().replace("Java", "Spring"));
        for (String w : s.trim().split(",\\s*")) {
            System.out.println("token=" + w);
        }
    }
}
```

**실행 결과**

```
length=19
trim=Hello, Java World|
upper= HELLO, JAVA WORLD 
startsWith Hello? true
sub=Java
rep=Hello, Spring World
token=Hello
token=Java World
```

**메모:** `substring(begin, end)` 의 `end` 는 **exclusive** 입니다.

### 예제 2 — `Immutability.java` : 불변성과 비용

```java
public class Immutability {
    public static void main(String[] args) {
        String s = "X";
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) s += "Y";
        long t1 = System.nanoTime() - start;
        System.out.println("String += 길이=" + s.length() + ", 시간=" + t1 + "ns");

        StringBuilder sb = new StringBuilder("X");
        long start2 = System.nanoTime();
        for (int i = 0; i < 1000; i++) sb.append("Y");
        long t2 = System.nanoTime() - start2;
        System.out.println("StringBuilder 길이=" + sb.length() + ", 시간=" + t2 + "ns");
    }
}
```

**실행 결과 (시간 값은 실행마다 다름)**

```
String += 길이=1001, 시간=...ns
StringBuilder 길이=1001, 시간=...ns
```

**메모:** 반복 결합은 거의 항상 `StringBuilder` 가 훨씬 빠릅니다.

### 예제 3 — `FormatAndTextBlock.java` : 포맷팅

```java
public class FormatAndTextBlock {
    public static void main(String[] args) {
        String row = String.format("이름=%s, 점수=%d, 평균=%.2f", "지수", 95, 82.345);
        System.out.println(row);

        String json = """
            {
                "name": "지수",
                "score": 95
            }
            """;
        System.out.println(json);
    }
}
```

**실행 결과**

```
이름=지수, 점수=95, 평균=82.35
{
    "name": "지수",
    "score": 95
}

```

**메모:** Text block 의 들여쓰기는 가장 짧은 줄의 공백 길이가 기준입니다.

### 예제 4 — `RegexDemo.java` : 정규식

```java
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args) {
        String text = "주문번호: 1234, 가격: 5678원, 수량: 3개";
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(text);
        while (m.find()) {
            System.out.println("매치=" + m.group());
        }

        Pattern email = Pattern.compile("[\\w.]+@[\\w.]+");
        System.out.println(email.matcher("hi jisu@example.com bye").find());
    }
}
```

**실행 결과**

```
매치=1234
매치=5678
매치=3
true
```

**메모:** 자바 문자열 안의 `\d` 는 `\\d` 로 두 번 백슬래시 해야 합니다.

## 자주 하는 실수

1. `String` 비교에 `==` 사용 → `.equals()` 또는 `.equalsIgnoreCase()`
2. 루프 안에서 `+` 결합 (성능) → `StringBuilder`
3. `substring(begin, end)` 의 `end` 가 inclusive 라고 착각
4. 정규식 백슬래시 한 개로 작성 (Java 문자열은 `\\` 필요)
5. `format` 에 `%n` 대신 `\n` 만 사용 (대부분 동작하지만 OS 호환은 `%n`)

## 정리

- `String` 은 불변, 반복 결합은 `StringBuilder`
- `String.format` 과 text block 으로 가독성을 챙기자
- 정규식은 강력하지만 가독성·디버깅 어려움 — 필요할 때만
- 빈 문자열·null 체크는 항상 의식

## 직접 해 보기

```bash
cd 04_예외_입출력/17_문자열_처리/src
javac StringMethods.java
java StringMethods
```

## 다음 단원

[18_람다와_함수형](../../05_모던_자바/18_람다와_함수형/) — 람다·메서드 참조·함수형 인터페이스를 본격적으로 다룹니다.
