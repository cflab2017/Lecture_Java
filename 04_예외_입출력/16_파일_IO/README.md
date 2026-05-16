# 16. 파일 IO

파일을 읽고 쓰는 것은 거의 모든 프로그램이 필요로 하는 기능입니다. Java 의 신형 IO API 인 `java.nio.file` 의 `Files`, `Path` 를 중심으로, 자원 누수를 막는 **try-with-resources** 패턴까지 배웁니다.

## 학습 목표

- `Path` 와 `Files` 의 역할을 안다
- `Files.readAllLines` / `Files.writeString` 으로 텍스트 파일을 읽고 쓴다
- `BufferedReader` 와 `try-with-resources` 의 사용법을 안다
- `IOException` 이 checked 예외임을 이해한다

## 핵심 개념

### 1) `Path` 와 `Files`

```java
import java.nio.file.Path;
import java.nio.file.Files;

Path p = Path.of("data.txt");
Files.writeString(p, "Hello\nWorld\n");
String text = Files.readString(p);
```

`Path` 는 경로를 표현하는 불변 객체, `Files` 는 정적 유틸 모음입니다.

### 2) 줄 단위 입출력

```java
List<String> lines = Files.readAllLines(Path.of("data.txt"));
Files.write(Path.of("out.txt"), lines);   // 줄들 그대로 쓰기
```

### 3) try-with-resources

```java
try (BufferedReader br = Files.newBufferedReader(p)) {
    String line;
    while ((line = br.readLine()) != null) {
        System.out.println(line);
    }
}
// br.close() 가 자동 호출
```

`AutoCloseable` 을 구현한 자원이면 무엇이든 사용 가능합니다.

### 4) checked 예외 `IOException`

파일 IO 메서드는 대부분 `IOException` 을 던지는 **checked** 예외입니다. `throws IOException` 선언이나 `try/catch` 가 필요합니다.

## 예제로 보기

### 예제 1 — `WriteFile.java` : 파일 쓰기

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteFile {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("greeting.txt");
        Files.writeString(p, "Hello\n안녕\n");
        System.out.println(p.toAbsolutePath() + " 쓰기 완료");
    }
}
```

**실행 결과**

```
/.../greeting.txt 쓰기 완료
```

**메모:** 파일이 없으면 자동 생성, 있으면 덮어씁니다.

### 예제 2 — `ReadFile.java` : 파일 읽기

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("greeting.txt");
        Files.writeString(p, "Hello\n안녕\n");

        List<String> lines = Files.readAllLines(p);
        for (int i = 0; i < lines.size(); i++) {
            System.out.println((i + 1) + ": " + lines.get(i));
        }
    }
}
```

**실행 결과**

```
1: Hello
2: 안녕
```

**메모:** 줄 단위로 읽으면 번호 매기기 같은 처리가 쉽습니다.

### 예제 3 — `BufferedReadDemo.java` : try-with-resources

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BufferedReadDemo {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("greeting.txt");
        Files.writeString(p, "Hello\n안녕\n");

        try (BufferedReader br = Files.newBufferedReader(p)) {
            String line;
            int n = 0;
            while ((line = br.readLine()) != null) {
                n++;
                System.out.printf("L%d: %s%n", n, line);
            }
        }
    }
}
```

**실행 결과**

```
L1: Hello
L2: 안녕
```

**메모:** try 블록 종료 시 `br.close()` 가 자동 호출됩니다.

### 예제 4 — `AppendAndDelete.java` : 추가 + 삭제

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class AppendAndDelete {
    public static void main(String[] args) throws IOException {
        Path p = Path.of("notes.txt");
        Files.writeString(p, "첫 줄\n");
        Files.writeString(p, "두 번째 줄\n", StandardOpenOption.APPEND);
        System.out.println(Files.readString(p));
        Files.deleteIfExists(p);
        System.out.println("삭제 완료");
    }
}
```

**실행 결과**

```
첫 줄
두 번째 줄

삭제 완료
```

**메모:** `StandardOpenOption.APPEND` 로 기존 내용 뒤에 덧붙일 수 있습니다.

## 자주 하는 실수

1. 파일 자원을 close 하지 않아 OS 핸들 누수 → try-with-resources 사용
2. 경로 구분자를 OS 별로 하드코딩 → `Path.of` / `Path.resolve` 사용
3. 인코딩을 지정하지 않아 한글이 깨짐 → `Files.readString(path, StandardCharsets.UTF_8)` 권장
4. `IOException` 을 매번 잡지 않고 전파 → 입문 단계에선 main 의 `throws IOException` 도 충분
5. 같은 파일을 동시에 여러 스레드가 쓰는 상황을 고려하지 않음 (입문 범위 밖)

## 정리

- `Path` + `Files` 가 모던 Java IO 의 중심
- `try-with-resources` 는 자원 해제의 표준 패턴
- 대용량 파일은 줄 단위 처리 + 버퍼링이 안전
- 인코딩은 UTF-8 명시 권장

## 직접 해 보기

```bash
cd 04_예외_입출력/16_파일_IO/src
javac WriteFile.java
java WriteFile
```

## 다음 단원

[17_문자열_처리](../17_문자열_처리/) — `String`/`StringBuilder`/정규식을 배웁니다.
