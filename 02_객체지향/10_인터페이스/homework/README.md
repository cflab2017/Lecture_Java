# 과제 - 10. 인터페이스

## 문제 1 — `Greeting` 인터페이스
- 파일명: `Homework01.java`
- 핵심 개념: `interface`, `implements`, `default`

### 요구사항
- 인터페이스 `Greeting` 에 `String hello()` 추상 + `default String shout()` (대문자 변환).
- 두 구현체 `KoreanG`, `EnglishG` 작성.
- 각각의 `hello()` 와 `shout()` 결과 출력.

### 예상 출력
```
안녕하세요
Hello
안녕하세요
HELLO
```

## 문제 2 — 함수형 인터페이스로 거르기
- 파일명: `Homework02.java`
- 핵심 개념: `@FunctionalInterface`, lambda

### 요구사항
- `@FunctionalInterface IntFilter { boolean test(int n); }` 정의.
- `static int countIf(int[] arr, IntFilter f)` 메서드로 조건을 만족하는 개수 반환.
- main 에서 `[1..10]` 중 짝수 개수, 5 이상 개수 출력.

### 예상 출력
```
짝수: 5
5 이상: 6
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
