# 과제 - 21. Maven · Gradle

## 문제 1 — 외부 라이브러리 의존성 추가
- 위치: `answer/maven-demo/`
- 핵심 개념: Maven 의존성 선언

### 요구사항
- 운영 코드에서 [Apache Commons Lang3](https://commons.apache.org/proper/commons-lang/) 의 `StringUtils.reverse(...)` 를 사용합니다.
- `pom.xml` 에 `commons-lang3` 의존성을 추가합니다.
- main 클래스에서 "Hello, Java!" 를 뒤집어 출력합니다.

### 예상 출력
```
원본: Hello, Java!
뒤집기: !avaJ ,olleH
```

### 힌트
- 의존성 좌표: `org.apache.commons:commons-lang3:3.17.0`
- 실행: `mvn -q compile exec:java -Dexec.mainClass="com.codingnow.lecture.modern21.HwApp"`

## 정답 확인
직접 풀어 본 후 [`answer/maven-demo/`](./answer/maven-demo/) 의 정답과 비교해 보세요.
