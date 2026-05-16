# 14. Stream API

Stream API 는 컬렉션을 **선언적으로** 변형·필터·집계하는 도구입니다. `for` 반복문 + 임시 변수 코드를 한 줄짜리 파이프라인으로 바꿔주어 의도가 한눈에 드러납니다.

## 학습 목표

- `stream()` 으로 스트림을 만든다
- `map`, `filter`, `reduce` 의 의미를 안다
- `collect(Collectors.toList())` 와 `Collectors.groupingBy` 를 사용한다
- 중간 연산(intermediate) 과 최종 연산(terminal) 을 구분한다

## 핵심 개념

### 1) 스트림 생성

```java
List<Integer> xs = List.of(1, 2, 3);
Stream<Integer> s = xs.stream();
Stream<Integer> s2 = Stream.of(1, 2, 3);
IntStream s3 = IntStream.rangeClosed(1, 5);
```

### 2) 중간 vs 최종 연산

```
[ source ] -> map -> filter -> map -> [ terminal ]
              \---- 중간 ----/        (count/collect/forEach/...)
```

중간 연산은 게으르게 동작하고, 최종 연산을 만나야 실제로 실행됩니다.

### 3) `map` / `filter` / `reduce`

```java
int squareSum = Stream.of(1, 2, 3, 4)
    .filter(n -> n % 2 == 1)
    .map(n -> n * n)
    .reduce(0, Integer::sum);
// 1*1 + 3*3 = 10
```

### 4) `collect` 와 `Collectors`

```java
import static java.util.stream.Collectors.*;

List<String> upper = list.stream().map(String::toUpperCase).collect(toList());
Map<String, List<Person>> byCity = people.stream().collect(groupingBy(Person::city));
```

## 예제로 보기

### 예제 1 — `StreamBasics.java` : 스트림 만들고 출력

```java
import java.util.List;

public class StreamBasics {
    public static void main(String[] args) {
        List<String> langs = List.of("Java", "Kotlin", "Scala", "Groovy");
        langs.stream()
             .forEach(System.out::println);

        long count = langs.stream().count();
        System.out.println("개수=" + count);
    }
}
```

**실행 결과**

```
Java
Kotlin
Scala
Groovy
개수=4
```

**메모:** `System.out::println` 은 **메서드 참조** 입니다 (18편에서 자세히).

### 예제 2 — `MapFilter.java` : 변환 + 거르기

```java
import java.util.List;
import java.util.stream.Collectors;

public class MapFilter {
    record Product(String name, int price) {}

    public static void main(String[] args) {
        List<Product> products = List.of(
            new Product("Pen", 1000),
            new Product("Notebook", 3000),
            new Product("Bag", 25000),
            new Product("Cup", 5000)
        );

        List<String> expensiveNames = products.stream()
            .filter(p -> p.price() >= 3000)
            .map(Product::name)
            .collect(Collectors.toList());

        System.out.println(expensiveNames);
    }
}
```

**실행 결과**

```
[Notebook, Bag, Cup]
```

**메모:** `Product::name` 처럼 메서드 참조로 `getter` 호출을 짧게 표현할 수 있습니다.

### 예제 3 — `Reduce.java` : 누적 합·최댓값

```java
import java.util.List;
import java.util.Optional;

public class Reduce {
    public static void main(String[] args) {
        List<Integer> xs = List.of(3, 1, 4, 1, 5, 9, 2, 6);
        int sum = xs.stream().reduce(0, Integer::sum);
        Optional<Integer> max = xs.stream().reduce(Integer::max);
        System.out.println("sum=" + sum);
        System.out.println("max=" + max.orElseThrow());
    }
}
```

**실행 결과**

```
sum=31
max=9
```

**메모:** 시작값이 있는 reduce 는 항상 결과를 돌려주지만, 시작값이 없으면 빈 스트림 대비 `Optional` 을 받습니다.

### 예제 4 — `GroupingBy.java` : 그룹별 카운트/평균

```java
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupingBy {
    record Student(String name, String dept, int score) {}

    public static void main(String[] args) {
        List<Student> ss = List.of(
            new Student("Alice", "CS", 85),
            new Student("Bob",   "CS", 70),
            new Student("Cathy", "MATH", 92),
            new Student("Dan",   "MATH", 65),
            new Student("Eve",   "PHY", 80)
        );

        Map<String, Double> avgByDept = ss.stream()
            .collect(Collectors.groupingBy(
                Student::dept,
                Collectors.averagingInt(Student::score)));

        avgByDept.forEach((d, a) -> System.out.printf("%s -> %.1f%n", d, a));
    }
}
```

**실행 결과 (순서 다를 수 있음)**

```
CS -> 77.5
MATH -> 78.5
PHY -> 80.0
```

**메모:** `groupingBy` 는 키 추출 함수 + (선택)다운스트림 콜렉터 조합으로 강력합니다.

## 자주 하는 실수

1. 스트림 객체를 두 번 사용 (한 번 소비하면 끝)
2. 무한 스트림에 `forEach` 호출 후 종료 못 함
3. 부수효과(side effect) 가 있는 람다 사용 → 병렬 스트림에서 위험
4. `Collectors.toList()` 와 `.toList()` (JDK 16+) 둘이 살짝 다름 (후자는 불변)
5. `IntStream` 등 primitive 스트림 변환을 잊고 박싱 비용 발생

## 정리

- 스트림은 **선언적 데이터 처리** 파이프라인
- 중간 연산은 게으르고, 최종 연산이 트리거
- `map` / `filter` / `reduce` 만 익히면 80% 활용 가능
- 그룹/집계가 필요할 때는 `Collectors` 가 강력

## 직접 해 보기

```bash
cd 03_컬렉션_제네릭/14_Stream_API/src
javac MapFilter.java
java MapFilter
```

## 다음 단원

[15_예외처리](../../04_예외_입출력/15_예외처리/) — `try`/`catch`, checked vs unchecked, 사용자 정의 예외를 배웁니다.
