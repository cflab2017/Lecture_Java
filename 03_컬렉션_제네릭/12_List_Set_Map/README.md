# 12. List · Set · Map

배열은 크기가 고정이지만 실무에서는 **추가/삭제가 자유로운 컬렉션** 을 더 자주 사용합니다. 가장 많이 쓰이는 세 가지는 `List`, `Set`, `Map` 입니다.

## 학습 목표

- `ArrayList` 와 `LinkedList` 의 차이를 안다
- `HashSet`(순서 없음) 과 `TreeSet`(정렬) 의 동작 차이를 안다
- `HashMap` 으로 key → value 매핑을 만든다
- 컬렉션을 `for-each` 로 순회한다
- `List.of(...)`, `Set.of(...)`, `Map.of(...)` 같은 **불변 컬렉션 팩토리** 를 안다

## 핵심 개념

### 1) `List<E>`

```java
import java.util.ArrayList;
import java.util.List;

List<String> langs = new ArrayList<>();
langs.add("Java");
langs.add("Spring");
langs.remove(0);
System.out.println(langs.size());        // 1
System.out.println(langs.get(0));        // Spring
```

- `ArrayList` : 인덱스 접근이 빠르고, 끝에 추가가 빠름. 일반적인 기본 선택
- `LinkedList` : 양쪽 끝에서 추가/삭제가 빠름. 큐 용도로 가끔 사용

### 2) `Set<E>`

```java
import java.util.HashSet;
import java.util.Set;

Set<String> tags = new HashSet<>();
tags.add("java");
tags.add("java");           // 중복 무시
System.out.println(tags.size());   // 1
```

- `HashSet` : 빠른 추가/검색, **순서 없음**
- `TreeSet` : 자동 정렬, 약간 느림

### 3) `Map<K, V>`

```java
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> score = new HashMap<>();
score.put("국어", 90);
score.put("수학", 85);
System.out.println(score.get("국어"));      // 90
System.out.println(score.containsKey("영어"));  // false
```

### 4) 불변 팩토리

```java
List<String> l = List.of("A", "B", "C");
Set<Integer> s = Set.of(1, 2, 3);
Map<String, Integer> m = Map.of("a", 1, "b", 2);
// l.add("D");   // UnsupportedOperationException
```

수정 불가, 가벼움, 자주 쓰는 패턴입니다.

## 예제로 보기

### 예제 1 — `ArrayListBasics.java` : `ArrayList` 사용

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListBasics {
    public static void main(String[] args) {
        List<String> langs = new ArrayList<>();
        langs.add("Java");
        langs.add("Spring");
        langs.add("Kotlin");
        langs.remove("Kotlin");

        for (String s : langs) System.out.println(s);
        System.out.println("size=" + langs.size());
    }
}
```

**실행 결과**

```
Java
Spring
size=2
```

**메모:** `remove(Object)` 와 `remove(int index)` 가 시그니처로 구분됩니다.

### 예제 2 — `SetBasics.java` : `HashSet` vs `TreeSet`

```java
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetBasics {
    public static void main(String[] args) {
        Set<Integer> h = new HashSet<>();
        Set<Integer> t = new TreeSet<>();
        for (int v : new int[]{3, 1, 4, 1, 5, 9, 2, 6}) {
            h.add(v);
            t.add(v);
        }
        System.out.println("HashSet : " + h);
        System.out.println("TreeSet : " + t);   // 정렬
    }
}
```

**실행 결과 (HashSet 순서는 다를 수 있음)**

```
HashSet : [1, 2, 3, 4, 5, 6, 9]
TreeSet : [1, 2, 3, 4, 5, 6, 9]
```

**메모:** `HashSet` 의 출력 순서는 보장되지 않습니다. **순서가 중요하면 `LinkedHashSet`/`TreeSet`** 을 쓰세요.

### 예제 3 — `MapBasics.java` : `HashMap`

```java
import java.util.HashMap;
import java.util.Map;

public class MapBasics {
    public static void main(String[] args) {
        Map<String, Integer> score = new HashMap<>();
        score.put("국어", 90);
        score.put("수학", 85);
        score.put("영어", 78);

        for (Map.Entry<String, Integer> e : score.entrySet()) {
            System.out.println(e.getKey() + " -> " + e.getValue());
        }
        System.out.println("국어=" + score.get("국어"));
        System.out.println("프랑스어=" + score.getOrDefault("프랑스어", -1));
    }
}
```

**실행 결과 (순서는 다를 수 있음)**

```
국어 -> 90
수학 -> 85
영어 -> 78
국어=90
프랑스어=-1
```

**메모:** `getOrDefault` 는 키가 없을 때의 기본값을 한 줄로 처리합니다.

### 예제 4 — `ImmutableCollections.java` : 불변 팩토리

```java
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImmutableCollections {
    public static void main(String[] args) {
        List<String> l = List.of("A", "B", "C");
        Set<Integer> s = Set.of(1, 2, 3);
        Map<String, Integer> m = Map.of("a", 1, "b", 2);

        System.out.println(l);
        System.out.println(s);
        System.out.println(m);

        try {
            l.add("D");
        } catch (UnsupportedOperationException e) {
            System.out.println("불변: 수정 불가");
        }
    }
}
```

**실행 결과 (Set/Map 순서는 다를 수 있음)**

```
[A, B, C]
[3, 2, 1]
{b=2, a=1}
불변: 수정 불가
```

**메모:** 코드 안에서 자주 쓰는 작은 상수 컬렉션엔 이 팩토리들이 깔끔합니다.

## 자주 하는 실수

1. `HashSet` 순서가 일정하다고 가정
2. `HashMap` 에 `null` 키를 넣은 다음 잊고 `NullPointerException`
3. 컬렉션을 순회 중 직접 `remove` 호출 → `ConcurrentModificationException`
4. `Arrays.asList(new int[]{1,2,3})` 가 `List<Integer>` 가 아니라 `List<int[]>` 인 점
5. `List.of(...)` 결과를 수정하려고 시도

## 정리

- 가장 흔한 선택: `ArrayList`, `HashSet`, `HashMap`
- 정렬이 필요하면 `TreeSet`/`TreeMap`
- 작은 상수 컬렉션엔 `List.of` / `Map.of`
- 컬렉션 순회 중 수정은 반드시 `iterator.remove` 등 안전한 방식으로

## 직접 해 보기

```bash
cd 03_컬렉션_제네릭/12_List_Set_Map/src
javac MapBasics.java
java MapBasics
```

## 다음 단원

[13_제네릭](../13_제네릭/) — 제네릭 클래스/메서드와 와일드카드를 배웁니다.
