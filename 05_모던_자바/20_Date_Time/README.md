# 20. Date · Time

날짜·시간을 다루는 표준 API 는 JDK 8 부터 `java.time` 패키지로 대대적으로 개편되었습니다. 불변 객체, 명확한 의미, 시간대 처리까지 갖춰 이전의 `Date`/`Calendar` 보다 압도적으로 사용하기 쉽습니다.

## 학습 목표

- `LocalDate`, `LocalTime`, `LocalDateTime`, `ZonedDateTime` 의 차이를 안다
- 날짜 산술(`plusDays` / `minusMonths` 등) 을 사용한다
- `DateTimeFormatter` 로 파싱·포맷팅한다
- `Duration` 과 `Period` 로 시간/기간을 표현한다

## 핵심 개념

### 1) 4 가지 타입

| 타입 | 표현 | 예 |
|---|---|---|
| `LocalDate` | 날짜 | 2026-05-16 |
| `LocalTime` | 시각 | 09:30 |
| `LocalDateTime` | 날짜+시각 | 2026-05-16T09:30 |
| `ZonedDateTime` | 위 + 시간대 | 2026-05-16T09:30+09:00[Asia/Seoul] |

### 2) 생성

```java
LocalDate today = LocalDate.now();
LocalDate xmas = LocalDate.of(2026, 12, 25);
LocalDateTime dt = LocalDateTime.now();
ZonedDateTime seoul = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
```

### 3) 산술

```java
LocalDate tomorrow = today.plusDays(1);
LocalDate lastWeek = today.minusWeeks(1);
boolean before = today.isBefore(xmas);
```

`java.time` 객체는 모두 **불변** — 메서드는 새 인스턴스를 반환합니다.

### 4) 포맷팅

```java
DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");
String s = LocalDateTime.now().format(fmt);
LocalDate parsed = LocalDate.parse("2026-05-16");
```

### 5) `Duration` vs `Period`

| 클래스 | 단위 | 용도 |
|---|---|---|
| `Duration` | 초·나노초 | 시각 간 간격 |
| `Period` | 년·월·일 | 날짜 간 간격 |

## 예제로 보기

### 예제 1 — `DateBasics.java` : 생성과 산술

```java
import java.time.LocalDate;

public class DateBasics {
    public static void main(String[] args) {
        LocalDate today = LocalDate.of(2026, 5, 16);
        LocalDate xmas = LocalDate.of(2026, 12, 25);
        System.out.println("오늘: " + today);
        System.out.println("크리스마스: " + xmas);
        System.out.println("내일: " + today.plusDays(1));
        System.out.println("한 달 전: " + today.minusMonths(1));
        System.out.println("크리스마스 전? " + today.isBefore(xmas));
    }
}
```

**실행 결과**

```
오늘: 2026-05-16
크리스마스: 2026-12-25
내일: 2026-05-17
한 달 전: 2026-04-16
크리스마스 전? true
```

**메모:** ISO-8601 (`yyyy-MM-dd`) 가 기본 표현입니다.

### 예제 2 — `TimeBasics.java` : 시각과 시간대

```java
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeBasics {
    public static void main(String[] args) {
        LocalDateTime ldt = LocalDateTime.of(2026, 5, 16, 9, 30);
        System.out.println("로컬: " + ldt);

        ZonedDateTime seoul = ldt.atZone(ZoneId.of("Asia/Seoul"));
        ZonedDateTime ny = seoul.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println("서울: " + seoul);
        System.out.println("뉴욕: " + ny);
    }
}
```

**실행 결과**

```
로컬: 2026-05-16T09:30
서울: 2026-05-16T09:30+09:00[Asia/Seoul]
뉴욕: 2026-05-15T20:30-04:00[America/New_York]
```

**메모:** `withZoneSameInstant` 는 **같은 순간** 을 다른 시간대 표현으로 바꿉니다.

### 예제 3 — `Formatter.java` : 포맷·파싱

```java
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2026, 5, 16, 9, 30, 45);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        System.out.println(dt.format(fmt));

        LocalDateTime parsed = LocalDateTime.parse("2026-05-16T09:30:45");
        System.out.println(parsed);
    }
}
```

**실행 결과**

```
2026.05.16 09:30:45
2026-05-16T09:30:45
```

**메모:** ISO 표준 형식은 별도 포매터 없이 `parse` 됩니다.

### 예제 4 — `DurationAndPeriod.java` : 기간 계산

```java
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

public class DurationAndPeriod {
    public static void main(String[] args) {
        LocalTime a = LocalTime.of(9, 0);
        LocalTime b = LocalTime.of(17, 30);
        Duration work = Duration.between(a, b);
        System.out.println("근무 분: " + work.toMinutes());

        LocalDate birth = LocalDate.of(2005, 3, 1);
        LocalDate now = LocalDate.of(2026, 5, 16);
        Period age = Period.between(birth, now);
        System.out.printf("나이: %d년 %d개월 %d일%n",
            age.getYears(), age.getMonths(), age.getDays());
    }
}
```

**실행 결과**

```
근무 분: 510
나이: 21년 2개월 15일
```

**메모:** 시간 간격은 `Duration`, 날짜 간격은 `Period` 를 사용하세요.

## 자주 하는 실수

1. 옛 `java.util.Date` / `Calendar` 사용 (비권장)
2. `Duration` 으로 날짜 간격, `Period` 로 시간 간격을 다루려 함 (역할 반대)
3. 시간대 누락한 채 서로 다른 지역 시간 비교
4. 객체가 불변이라 `today.plusDays(1)` 만 호출하고 결과를 안 받음 → 의미 없음
5. 로컬 시간을 그대로 DB 저장 → 다른 지역 사용자에게는 다른 시점이 됨

## 정리

- `java.time` 은 불변·명확·풍부한 모던 API
- 4가지 핵심 타입을 상황에 맞게 골라 사용
- 포맷팅은 `DateTimeFormatter` 로
- 기간은 `Duration` / `Period` 둘로 명확히 구분

## 직접 해 보기

```bash
cd 05_모던_자바/20_Date_Time/src
javac DateBasics.java
java DateBasics
```

## 다음 단원

[21_Maven_Gradle](../21_Maven_Gradle/) — 빌드 도구로 본격 프로젝트 구조를 익힙니다.
