# 01. Java 시작하기

Java 는 1995년 발표 이후 30년째 산업 현장의 주력 언어로 쓰이는 정적 타입·객체지향 프로그래밍 언어입니다. 이번 편에서는 JDK 21 LTS 를 설치하고, IDE 를 정한 뒤, 첫 번째 프로그램을 컴파일·실행해 봅니다.

## 학습 목표

- JDK 21 LTS 를 설치하고 `java`, `javac` 명령을 확인한다
- IntelliJ IDEA Community 또는 VS Code 중 하나를 골라 환경을 갖춘다
- `public class` / `public static void main` 의 구조를 이해한다
- `javac` 로 컴파일하고 `java` 로 실행할 수 있다

## 핵심 개념

### 1) JDK 와 JRE

Java 코드를 작성하고 실행하려면 **JDK(Java Development Kit)** 가 필요합니다. JDK 안에는 컴파일러(`javac`), 실행기(`java`), 표준 라이브러리, JRE(Java Runtime Environment) 가 모두 포함되어 있습니다. 우리는 **Eclipse Temurin JDK 21 LTS** 를 권장합니다.

```bash
java --version    # openjdk 21.x.x
javac --version   # javac 21.x.x
```

### 2) `Hello.java` 파일 구조

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

- 파일명은 반드시 `public class` 이름과 같아야 합니다 → `Hello.java`
- `main(String[] args)` 가 **실행 진입점**입니다
- `System.out.println(...)` 은 한 줄을 출력하고 줄바꿈을 추가합니다

### 3) 컴파일과 실행

```bash
javac Hello.java   # Hello.class 생성
java Hello         # 실행 (확장자 없음!)
```

`javac` 는 `.java` 소스를 **바이트코드** `.class` 로 변환하고, `java` 는 JVM 위에서 그 바이트코드를 실행합니다.

### 4) IDE 선택

| 도구 | 장점 |
|---|---|
| IntelliJ IDEA Community | Java/Spring 작업에 가장 인기, 무료, 강력한 리팩터링 |
| VS Code + Extension Pack for Java | 가볍고 다목적, 멀티 언어 작업에 적합 |

본 강의는 두 도구 어느 쪽이든 동일하게 따라할 수 있게 작성되어 있습니다.

## 예제로 보기

### 예제 1 — `Hello.java` : 가장 작은 Java 프로그램

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, Java!");
    }
}
```

**실행 결과**

```
Hello, Java!
```

**메모:** 파일명과 클래스명이 다르면 `class Hello is public, should be declared in a file named Hello.java` 컴파일 에러가 납니다.

### 예제 2 — `Args.java` : 명령줄 인자 받기

```java
public class Args {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("이름을 인자로 전달해 보세요.");
            return;
        }
        System.out.println("Hello, " + args[0] + "!");
    }
}
```

**실행 결과**

```
$ java Args Java
Hello, Java!
```

**메모:** `args` 는 `main` 의 매개변수입니다. `args.length` 로 갯수를 알 수 있고, `args[0]` 처럼 인덱스로 접근합니다.

### 예제 3 — `PrintFormats.java` : print / println / printf

```java
public class PrintFormats {
    public static void main(String[] args) {
        System.out.print("줄바꿈 ");
        System.out.print("없음\n");
        System.out.println("줄바꿈 자동");
        System.out.printf("이름=%s, 나이=%d%n", "지수", 21);
    }
}
```

**실행 결과**

```
줄바꿈 없음
줄바꿈 자동
이름=지수, 나이=21
```

**메모:** `printf` 의 `%n` 은 OS 에 맞는 줄바꿈을, `\n` 은 항상 LF 를 출력합니다.

### 예제 4 — `CommentsAndJavadoc.java` : 주석 세 가지

```java
/** 주석 종류를 보여 주는 데모. */
public class CommentsAndJavadoc {
    // 한 줄 주석
    /* 여러 줄
       주석 */
    public static void main(String[] args) {
        System.out.println(greeting("Java")); // 인라인 주석
    }

    /** 인사말을 반환. */
    static String greeting(String name) {
        return "Hello, " + name + "!";
    }
}
```

**실행 결과**

```
Hello, Java!
```

**메모:** `/** ... */` 형식의 **Javadoc** 주석은 `javadoc` 도구가 HTML 문서로 변환할 수 있는 공식 API 주석입니다.

## 자주 하는 실수

1. 파일명을 `hello.java`(소문자) 로 저장해 클래스명과 어긋남
2. `java Hello.class` 처럼 확장자까지 입력 → `java Hello` 가 올바름
3. `main` 의 시그니처를 `void main()` 으로 바꿔 진입점을 잃음
4. `System.out.println` 의 `out` 을 빼먹고 `System.println` 으로 작성
5. 한글 출력이 깨질 때 인코딩 확인: 파일을 UTF-8 로 저장, 콘솔도 UTF-8 (`chcp 65001` 윈도우)

## 정리

- Java 프로그램은 `public class Xxx` 안의 `public static void main(String[] args)` 에서 시작합니다
- `.java` 를 `javac` 으로 컴파일하면 `.class` 가 생기고, `java` 로 실행합니다
- `System.out` 의 `print` / `println` / `printf` 를 상황에 맞게 사용합니다

## 직접 해 보기

```bash
cd 01_기초/01_Java_시작하기/src
javac Hello.java
java Hello
```

다른 예제도 같은 방식으로:

```bash
javac Args.java
java Args 코딩나우
```

## 다음 단원

[02_변수와_타입](../02_변수와_타입/) — primitive 8종, 형변환, `var` 키워드를 배웁니다.
