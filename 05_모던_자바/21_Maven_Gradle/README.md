# 21. Maven · Gradle

지금까지는 `.java` 파일 한두 개를 직접 `javac` / `java` 로 다뤘습니다. 실무는 **빌드 도구** 가 의존성·컴파일·테스트·배포까지 자동화합니다. Java 진영의 양대 빌드 도구가 **Maven** 과 **Gradle** 입니다.

## 학습 목표

- Maven 의 `pom.xml` 구조를 안다
- Gradle 의 `build.gradle` 구조를 안다
- 외부 의존성을 추가해 본다
- 표준 디렉토리 구조 (`src/main/java`, `src/test/java`) 를 안다
- `mvn` / `gradle` 명령으로 컴파일·실행·테스트를 자동화한다

## 핵심 개념

### 1) 표준 디렉토리 구조

```
project/
├── pom.xml         (또는 build.gradle)
└── src/
    ├── main/
    │   ├── java/        # 운영 코드
    │   └── resources/   # 설정 파일
    └── test/
        ├── java/        # 테스트 코드
        └── resources/
```

### 2) Maven `pom.xml`

```xml
<project>
    <groupId>com.codingnow</groupId>
    <artifactId>lecture-modern21</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- 예: SLF4J 로깅 API -->
    </dependencies>
</project>
```

주요 명령:

```bash
mvn compile      # 컴파일
mvn package      # jar 생성
mvn test         # 테스트
mvn clean        # 빌드 결과 삭제
mvn exec:java -Dexec.mainClass="..."   # 실행
```

### 3) Gradle `build.gradle`

```groovy
plugins {
    id 'application'
    id 'java'
}

group = 'com.codingnow'
version = '1.0.0'

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass = 'com.codingnow.lecture.modern21.App'
}
```

주요 명령:

```bash
gradle build      # 빌드
gradle run        # application 플러그인이 있을 때 실행
gradle test       # 테스트
gradle clean
```

### 4) Maven vs Gradle 한눈 비교

| 항목 | Maven | Gradle |
|---|---|---|
| 설정 | XML | Groovy/Kotlin DSL |
| 학습 곡선 | 낮음 | 약간 가파름 |
| 속도 | 표준적 | 점진적 빌드 캐시로 빠름 |
| 유연성 | 표준 흐름 | 매우 유연 |
| 사용처 | 전통 기업 | Android, 모던 프로젝트 |

본 강의의 22편 이후는 **Maven** 을 기본으로 사용합니다.

## 예제로 보기

### 예제 1 — `maven-demo/pom.xml` : 최소 Maven 프로젝트

이 폴더의 `maven-demo/pom.xml` 과 `src/main/java/com/codingnow/lecture/modern21/App.java` 를 보세요.

```bash
cd 05_모던_자바/21_Maven_Gradle/maven-demo
mvn -q compile
mvn -q exec:java -Dexec.mainClass="com.codingnow.lecture.modern21.App"
```

**실행 결과**

```
[Maven] Hello, Java 21!
```

**메모:** `mvn exec:java` 는 `exec-maven-plugin` 이 없으면 자동 다운로드 받아 실행해 줍니다.

### 예제 2 — `maven-demo/src/.../App.java` : 운영 코드

```java
package com.codingnow.lecture.modern21;

public class App {
    public static void main(String[] args) {
        System.out.println("[Maven] Hello, Java " + Runtime.version().feature() + "!");
    }
}
```

**메모:** Maven 프로젝트의 소스는 `src/main/java/<패키지 경로>/` 아래에 둡니다.

### 예제 3 — `gradle-demo/build.gradle` : 최소 Gradle 프로젝트

```bash
cd 05_모던_자바/21_Maven_Gradle/gradle-demo
gradle -q run
```

**실행 결과**

```
[Gradle] Hello, Java 21!
```

**메모:** `application` 플러그인이 `gradle run` 을 사용 가능하게 만들어 줍니다.

### 예제 4 — `gradle-demo/src/.../App.java` : 운영 코드

```java
package com.codingnow.lecture.modern21;

public class App {
    public static void main(String[] args) {
        System.out.println("[Gradle] Hello, Java " + Runtime.version().feature() + "!");
    }
}
```

## 자주 하는 실수

1. 디렉토리 구조를 안 맞춤 → 빌드 도구가 코드를 못 찾음
2. JDK 21 미지원 빌드 도구 버전을 사용 → 컴파일 에러
3. `mvn compile` 만 하고 메인 클래스 실행 명령을 모름
4. Gradle 의 task 이름과 옵션을 헷갈림 (예: `clean build` 순서)
5. 인터넷이 없는 환경에서 의존성 첫 다운로드 실패

## 정리

- 실무는 빌드 도구가 표준
- `src/main/java` + `src/test/java` 가 표준 레이아웃
- Maven 은 단순·전통, Gradle 은 유연·빠름
- 본 강의 22편 이후는 Maven 중심

## 직접 해 보기

```bash
cd 05_모던_자바/21_Maven_Gradle/maven-demo
mvn -q compile exec:java -Dexec.mainClass="com.codingnow.lecture.modern21.App"

cd ../gradle-demo
gradle -q run
```

## 다음 단원

[22_JUnit_테스트](../22_JUnit_테스트/) — JUnit 5 로 단위 테스트를 작성합니다.
