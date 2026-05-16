# 11. 배열

배열(array) 은 같은 타입 값을 **연속된 인덱스** 로 접근하는 가장 기본적인 자료구조입니다. 크기가 한 번 정해지면 바꿀 수 없지만(고정 크기) 속도가 빠르고 메모리 효율도 좋습니다.

## 학습 목표

- 1차원·2차원 배열을 선언·초기화·접근한다
- `for` 와 enhanced for 로 순회한다
- `Arrays.toString` / `sort` / `copyOf` 같은 유틸을 사용한다
- `int[]`(primitive) 과 `Integer[]`(객체) 의 차이를 안다

## 핵심 개념

### 1) 1차원 배열

```java
int[] nums = new int[5];         // {0,0,0,0,0}
nums[0] = 10;
String[] colors = {"red", "green", "blue"};
System.out.println(colors.length);   // 3
```

`length` 는 메서드가 아니라 **필드** 입니다 (괄호 없음).

### 2) 2차원 배열

```java
int[][] mat = {
    {1, 2, 3},
    {4, 5, 6}
};
System.out.println(mat[1][2]);   // 6
System.out.println(mat.length);     // 2 (행)
System.out.println(mat[0].length);  // 3 (열)
```

Java 의 2차원 배열은 **배열의 배열** 입니다. 각 행 길이가 달라도 됩니다 (jagged array).

### 3) `Arrays` 유틸

```java
import java.util.Arrays;

int[] a = {3, 1, 4, 1, 5, 9, 2};
Arrays.sort(a);
System.out.println(Arrays.toString(a));
int[] copy = Arrays.copyOf(a, 5);
```

### 4) primitive 배열 vs 객체 배열

```java
int[] xs = new int[3];        // 기본값 0
Integer[] ys = new Integer[3]; // 기본값 null
```

`int[]` 는 빠르고 메모리 작지만, 컬렉션(`List<Integer>`) 에 직접 못 넣습니다.

## 예제로 보기

### 예제 1 — `Array1D.java` : 1차원 배열 기본

```java
public class Array1D {
    public static void main(String[] args) {
        int[] nums = new int[5];
        for (int i = 0; i < nums.length; i++) nums[i] = (i + 1) * 10;

        for (int n : nums) System.out.print(n + " ");
        System.out.println();
        System.out.println("길이=" + nums.length);
    }
}
```

**실행 결과**

```
10 20 30 40 50 
길이=5
```

**메모:** 인덱스는 0 부터 시작합니다. 범위를 벗어나면 `ArrayIndexOutOfBoundsException`.

### 예제 2 — `Array2D.java` : 2차원 배열

```java
public class Array2D {
    public static void main(String[] args) {
        int[][] mat = {
            {1, 2, 3},
            {4, 5, 6}
        };
        for (int r = 0; r < mat.length; r++) {
            for (int c = 0; c < mat[r].length; c++) {
                System.out.print(mat[r][c] + " ");
            }
            System.out.println();
        }
    }
}
```

**실행 결과**

```
1 2 3 
4 5 6 
```

**메모:** 행 인덱스 → 열 인덱스 순으로 접근합니다.

### 예제 3 — `ArraysUtil.java` : 정렬·복사·toString

```java
import java.util.Arrays;

public class ArraysUtil {
    public static void main(String[] args) {
        int[] a = {3, 1, 4, 1, 5, 9, 2, 6};
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));

        int[] head = Arrays.copyOf(a, 4);
        System.out.println(Arrays.toString(head));

        int[] slice = Arrays.copyOfRange(a, 2, 6);
        System.out.println(Arrays.toString(slice));
    }
}
```

**실행 결과**

```
[1, 1, 2, 3, 4, 5, 6, 9]
[1, 1, 2, 3]
[2, 3, 4, 5]
```

**메모:** `copyOfRange(from, to)` 의 `to` 는 **exclusive** 입니다.

### 예제 4 — `IntVsInteger.java` : primitive vs 박싱

```java
import java.util.Arrays;
import java.util.List;

public class IntVsInteger {
    public static void main(String[] args) {
        int[] xs = new int[3];               // 기본값 0
        Integer[] ys = new Integer[3];        // 기본값 null
        System.out.println("xs[0]=" + xs[0]);
        System.out.println("ys[0]=" + ys[0]);

        Integer[] zs = {1, 2, 3};
        List<Integer> list = Arrays.asList(zs);
        System.out.println(list);
    }
}
```

**실행 결과**

```
xs[0]=0
ys[0]=null
[1, 2, 3]
```

**메모:** `Arrays.asList` 는 **고정 크기 List** 를 반환합니다 (`.add` 호출 시 예외).

## 자주 하는 실수

1. `nums.length()` 처럼 괄호 호출 → length 는 필드
2. 인덱스 범위 초과로 `ArrayIndexOutOfBoundsException`
3. 2차원 배열의 행 길이가 동일하다고 가정 (jagged 가능)
4. `Arrays.asList(int[])` 가 `List<int[]>` 가 되는 함정 (primitive 배열에는 `IntStream` 사용)
5. 배열 비교에 `==` 사용 (`Arrays.equals` 또는 `Arrays.deepEquals` 사용)

## 정리

- 배열은 빠른 고정 크기 자료구조
- 인덱스는 0..length-1
- 대부분의 유틸은 `Arrays` 클래스에 정적 메서드로 모여 있음
- 변경 가능한 컬렉션이 필요하면 `List` (다음 단원)

## 직접 해 보기

```bash
cd 03_컬렉션_제네릭/11_배열/src
javac ArraysUtil.java
java ArraysUtil
```

## 다음 단원

[12_List_Set_Map](../12_List_Set_Map/) — 자주 쓰는 세 가지 컬렉션을 배웁니다.
