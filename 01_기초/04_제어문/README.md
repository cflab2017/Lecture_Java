# 04. 제어문

코드의 흐름을 갈라거나 반복할 때 쓰는 문장이 **제어문(control flow)** 입니다. Java 의 제어문은 C 와 닮았지만, JDK 14+ 의 **switch expression** 처럼 모던한 형태도 함께 사용합니다.

## 학습 목표

- `if` / `else if` / `else` 구조를 사용한다
- 새로운 **switch expression** (`->` / `yield`) 문법을 익힌다
- `for`, `while`, `do-while`, **enhanced for** 의 차이를 안다
- `break`, `continue`, 레이블 점프의 의미를 안다

## 핵심 개념

### 1) `if` / `else if` / `else`

```java
int score = 85;
if (score >= 90) {
    System.out.println("A");
} else if (score >= 80) {
    System.out.println("B");
} else {
    System.out.println("C 이하");
}
```

### 2) Switch expression (JDK 14+)

```java
int day = 3;
String name = switch (day) {
    case 1, 2, 3, 4, 5 -> "평일";
    case 6, 7 -> "주말";
    default -> "오류";
};
System.out.println(name);
```

- `case 1, 2, 3 ->` 처럼 여러 값을 한 줄에 묶을 수 있음
- 값이 여러 줄이 필요하면 블록 + `yield`:
  ```java
  String s = switch (n) {
      case 1 -> "one";
      case 2 -> {
          System.out.println("two!");
          yield "two";
      }
      default -> "?";
  };
  ```

### 3) 반복문 4 가지

```java
for (int i = 0; i < 3; i++) { ... }
int j = 0; while (j < 3) { j++; }
int k = 0; do { k++; } while (k < 3);
int[] nums = {1, 2, 3};
for (int n : nums) { System.out.println(n); }   // enhanced for
```

### 4) `break` / `continue`

```java
for (int i = 0; i < 10; i++) {
    if (i == 3) continue;   // 이번 회차 건너뜀
    if (i == 6) break;      // 반복 종료
    System.out.print(i + " ");   // 0 1 2 4 5
}
```

## 예제로 보기

### 예제 1 — `IfElseDemo.java` : 분기 기본

```java
public class IfElseDemo {
    public static void main(String[] args) {
        int score = 72;
        String grade;
        if (score >= 90) grade = "A";
        else if (score >= 80) grade = "B";
        else if (score >= 70) grade = "C";
        else if (score >= 60) grade = "D";
        else grade = "F";
        System.out.println("점수 " + score + " -> " + grade);
    }
}
```

**실행 결과**

```
점수 72 -> C
```

**메모:** 한 줄짜리 if 본문은 중괄호를 생략할 수 있지만, **공식 컨벤션은 중괄호 사용**입니다.

### 예제 2 — `SwitchExpression.java` : `->` / `yield`

```java
public class SwitchExpression {
    public static void main(String[] args) {
        int day = 3;
        String kind = switch (day) {
            case 1, 2, 3, 4, 5 -> "평일";
            case 6, 7 -> "주말";
            default -> "오류";
        };
        System.out.println(day + "요일: " + kind);

        int n = 2;
        String word = switch (n) {
            case 1 -> "one";
            case 2 -> {
                System.out.println("two!");
                yield "two";
            }
            default -> "?";
        };
        System.out.println(word);
    }
}
```

**실행 결과**

```
3요일: 평일
two!
two
```

**메모:** Switch expression 은 **반드시 값이 결정**되어야 합니다 (`default` 가 필요).

### 예제 3 — `Loops.java` : for / while / enhanced for

```java
public class Loops {
    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 5; i++) sum += i;
        System.out.println("1+...+5 = " + sum);

        int j = 0;
        while (j < 3) {
            System.out.print(j + " ");
            j++;
        }
        System.out.println();

        int[] nums = {10, 20, 30};
        for (int n : nums) System.out.print(n + " ");
        System.out.println();
    }
}
```

**실행 결과**

```
1+...+5 = 15
0 1 2 
10 20 30 
```

**메모:** **enhanced for** 는 인덱스가 필요 없을 때 가장 안전합니다.

### 예제 4 — `BreakContinue.java` : break / continue

```java
public class BreakContinue {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 3) continue;
            if (i == 6) break;
            System.out.print(i + " ");
        }
        System.out.println();

        outer:
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (i + k == 3) break outer;
                System.out.print("(" + i + "," + k + ") ");
            }
        }
        System.out.println();
    }
}
```

**실행 결과**

```
0 1 2 4 5 
(0,0) (0,1) (0,2) (1,0) (1,1) 
```

**메모:** **레이블 break** 는 깊은 중첩 탈출에 유용하지만 남용은 가독성을 해칩니다.

## 자주 하는 실수

1. `if (a = 1)` 처럼 `==` 대신 `=` 사용 → 컴파일 에러(boolean 자리에 int)
2. 반복 변수 범위를 잘못 잡아 off-by-one (`<` vs `<=`)
3. classic `switch` 의 `break` 잊고 fall-through
4. enhanced for 에서 컬렉션 요소를 수정하려다 `ConcurrentModificationException`
5. switch expression 에 `default` 누락

## 정리

- 분기에는 `if` 또는 switch expression
- 반복에는 `for` / `while` / enhanced for
- `break`/`continue` 와 레이블 점프는 가끔 유용

## 직접 해 보기

```bash
cd 01_기초/04_제어문/src
javac SwitchExpression.java
java SwitchExpression
```

## 다음 단원

[05_메서드](../05_메서드/) — 메서드 선언·오버로딩·`static`·가변 인자를 배웁니다.
