# Java + Spring Boot 입문 강의

> 한국어로 배우는 Java 기초부터 Spring Boot REST API까지, 26편으로 정리한 입문 강의입니다.

## 강의 소개

이 강의는 프로그래밍을 처음 시작하거나 다른 언어 경험이 있는 학습자가 **Java**의 기초 문법부터 **객체지향**, **모던 자바**, 그리고 **Spring Boot** 를 활용한 간단한 REST API 까지 한 번에 익히도록 만든 입문 코스입니다.

- 본문은 한국어, 코드와 명령은 영문 그대로 사용
- 모든 예제는 짧고(5~15줄) 한 가지 개념을 검증하기 좋게 구성
- 22편까지는 `javac` / `java` 만으로 실행할 수 있는 순수 Java
- 21편 이후는 **Maven** 또는 **Gradle** 프로젝트 구조 도입
- 23~26편은 **Spring Boot 3.x** 입문 (REST API + JPA + H2)

## 학습 목표

- Java 기본 문법(변수·타입·제어문·메서드) 이해
- 객체지향 4대 원칙(캡슐화·상속·다형성·추상화) 적용
- 컬렉션·제네릭·Stream API 로 데이터를 다루는 법 습득
- 예외 처리·파일 IO·문자열 처리 등 실용 기법 익히기
- 람다·Optional·`java.time` 등 모던 자바 기능 활용
- Maven/Gradle 빌드와 JUnit 테스트 작성
- Spring Boot 3.x 로 간단한 REST API + JPA + H2 CRUD 서비스 구현

## 개발 환경

| 항목 | 권장 |
|---|---|
| JDK | **JDK 21 LTS** (Eclipse Temurin 권장) |
| 빌드 도구 | **Maven 3.9+** 또는 **Gradle 8.x** |
| IDE | **IntelliJ IDEA Community** 또는 **VS Code + Extension Pack for Java** |
| 인코딩 | UTF-8 / LF |

### JDK 21 설치 확인

```bash
java --version
javac --version
```

`openjdk 21.x.x` 와 같이 출력되면 정상입니다.

## 디렉토리 구조

```
Lecture_Java/
├── 01_기초/
│   ├── 01_Java_시작하기/
│   ├── 02_변수와_타입/
│   ├── 03_연산자와_표현식/
│   ├── 04_제어문/
│   └── 05_메서드/
├── 02_객체지향/
│   ├── 06_클래스와_객체/
│   ├── 07_캡슐화/
│   ├── 08_상속/
│   ├── 09_다형성/
│   └── 10_인터페이스/
├── 03_컬렉션_제네릭/
│   ├── 11_배열/
│   ├── 12_List_Set_Map/
│   ├── 13_제네릭/
│   └── 14_Stream_API/
├── 04_예외_입출력/
│   ├── 15_예외처리/
│   ├── 16_파일_IO/
│   └── 17_문자열_처리/
├── 05_모던_자바/
│   ├── 18_람다와_함수형/
│   ├── 19_Optional/
│   ├── 20_Date_Time/
│   ├── 21_Maven_Gradle/
│   └── 22_JUnit_테스트/
└── 06_Spring_Boot/
    ├── 23_Spring_Boot_시작/
    ├── 24_REST_컨트롤러/
    ├── 25_서비스와_레이어/
    └── 26_데이터_저장/
```

각 단원에는 강의 본문 `README.md`, 예제 코드 `src/`, 과제 `homework/` 가 들어 있습니다.

## 26단원 인덱스

### Part 1. 기초

| # | 폴더 | 주제 |
|---|---|---|
| 01 | [01_Java_시작하기](./01_기초/01_Java_시작하기/) | JDK 설치·IDE·`main`·javac/java 실행 |
| 02 | [02_변수와_타입](./01_기초/02_변수와_타입/) | primitive 8종·형변환·`var` |
| 03 | [03_연산자와_표현식](./01_기초/03_연산자와_표현식/) | 산술/비교/논리/비트·`==` vs `.equals()` |
| 04 | [04_제어문](./01_기초/04_제어문/) | `if`/`switch`(`yield`)/`for`/`while` |
| 05 | [05_메서드](./01_기초/05_메서드/) | 선언·오버로딩·`static`·가변 인자 |

### Part 2. 객체지향

| # | 폴더 | 주제 |
|---|---|---|
| 06 | [06_클래스와_객체](./02_객체지향/06_클래스와_객체/) | `class`·필드·생성자·`this`·`new` |
| 07 | [07_캡슐화](./02_객체지향/07_캡슐화/) | `private`·getter/setter·불변·`record` |
| 08 | [08_상속](./02_객체지향/08_상속/) | `extends`·`super`·`@Override`·`final` |
| 09 | [09_다형성](./02_객체지향/09_다형성/) | 업/다운캐스팅·`instanceof` 패턴·`abstract` |
| 10 | [10_인터페이스](./02_객체지향/10_인터페이스/) | `interface`·`default` 메서드·함수형 인터페이스 |

### Part 3. 컬렉션·제네릭

| # | 폴더 | 주제 |
|---|---|---|
| 11 | [11_배열](./03_컬렉션_제네릭/11_배열/) | 1·2차원 배열·`Arrays` 유틸 |
| 12 | [12_List_Set_Map](./03_컬렉션_제네릭/12_List_Set_Map/) | `ArrayList`·`HashSet`·`HashMap` |
| 13 | [13_제네릭](./03_컬렉션_제네릭/13_제네릭/) | 제네릭 클래스/메서드·와일드카드 |
| 14 | [14_Stream_API](./03_컬렉션_제네릭/14_Stream_API/) | `map`/`filter`/`reduce`/`collect` |

### Part 4. 예외·입출력

| # | 폴더 | 주제 |
|---|---|---|
| 15 | [15_예외처리](./04_예외_입출력/15_예외처리/) | `try`/`catch`·checked vs unchecked |
| 16 | [16_파일_IO](./04_예외_입출력/16_파일_IO/) | `Files`·`Path`·try-with-resources |
| 17 | [17_문자열_처리](./04_예외_입출력/17_문자열_처리/) | `String`·`StringBuilder`·정규식 |

### Part 5. 모던 자바·도구

| # | 폴더 | 주제 |
|---|---|---|
| 18 | [18_람다와_함수형](./05_모던_자바/18_람다와_함수형/) | lambda·메서드 참조·`Function`/`Predicate` |
| 19 | [19_Optional](./05_모던_자바/19_Optional/) | `Optional` 사용 패턴·null 회피 |
| 20 | [20_Date_Time](./05_모던_자바/20_Date_Time/) | `LocalDate`·`Duration`·`DateTimeFormatter` |
| 21 | [21_Maven_Gradle](./05_모던_자바/21_Maven_Gradle/) | `pom.xml`·`build.gradle`·dependencies |
| 22 | [22_JUnit_테스트](./05_모던_자바/22_JUnit_테스트/) | JUnit 5·`@Test`·assertions |

### Part 6. Spring Boot

| # | 폴더 | 주제 |
|---|---|---|
| 23 | [23_Spring_Boot_시작](./06_Spring_Boot/23_Spring_Boot_시작/) | Spring Initializr·DI·`@SpringBootApplication` |
| 24 | [24_REST_컨트롤러](./06_Spring_Boot/24_REST_컨트롤러/) | `@RestController`·매핑·JSON 응답 |
| 25 | [25_서비스와_레이어](./06_Spring_Boot/25_서비스와_레이어/) | `@Service`·생성자 주입·DTO |
| 26 | [26_데이터_저장](./06_Spring_Boot/26_데이터_저장/) | Spring Data JPA·H2·CRUD API |

## 학습 순서

- 한 주에 **2~3편** 진도가 권장 페이스 → 약 **10~13주** 완주
- 예제는 직접 타이핑해 컴파일·실행해 본 뒤, 과제를 풀고 `answer/` 의 정답과 비교하세요
- 21편 전까지는 텍스트 에디터 + 터미널만으로 충분합니다
- Spring Boot 단원은 IntelliJ IDEA Community 사용을 추천합니다

## 미니 프로젝트 워크북

각 파트가 끝나면 다음과 같은 작은 프로젝트로 복습해 보세요.

- **Part 1 — 기초**: 콘솔 계산기 (사칙연산 + 입력 검증)
- **Part 2 — 객체지향**: 은행 계좌 시뮬레이션 (입금·출금·이체)
- **Part 3 — 컬렉션·제네릭**: 도서 관리 시스템 (검색·정렬·필터)
- **Part 4 — 예외·입출력**: 로그 파일 분석기 (CSV 읽기 → 통계)
- **Part 5 — 모던 자바**: TODO CLI (람다·Stream·`LocalDate` 활용)
- **Part 6 — Spring Boot**: REST 기반 메모장 API (CRUD + H2 영속화)

## 코딩 컨벤션

- 패키지명: `com.codingnow.lecture.partNN` (예: `com.codingnow.lecture.basics02`)
- 클래스 `PascalCase`, 메서드·필드 `camelCase`, 상수 `SCREAMING_SNAKE_CASE`
- 들여쓰기 **4 스페이스**, 중괄호 K&R 스타일
- import 와일드카드(`import java.util.*`) 금지, 명시적 import만 사용
- 파일 인코딩 **UTF-8**, 줄바꿈 **LF**

## 라이선스

이 강의 콘텐츠는 학습 목적의 예제이며 자유롭게 참고·수정할 수 있습니다.

---

**시작하기:** [01. Java 시작하기](./01_기초/01_Java_시작하기/) →
