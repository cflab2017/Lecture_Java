# 과제 - 13. 제네릭

## 문제 1 — `Pair<A, B>`
- 파일명: `Homework01.java`
- 핵심 개념: 제네릭 클래스, 두 개의 타입 매개변수

### 요구사항
- 제네릭 클래스 `Pair<A, B>` 정의 (생성자 + getter 2개 + `toString`).
- `(1, "one")`, `("k", 3.14)` 두 가지를 만들고 출력.

### 예상 출력
```
(1, one)
(k, 3.14)
```

## 문제 2 — 제네릭 메서드 `max`
- 파일명: `Homework02.java`
- 핵심 개념: 제네릭 메서드, `Comparable`

### 요구사항
- `<T extends Comparable<T>> T max(List<T> xs)` 정의 (빈 리스트면 null).
- `Integer`, `String` 리스트 둘 다 호출해 출력.

### 예상 출력
```
9
ZZZ
null
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
