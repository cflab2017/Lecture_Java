# 과제 - 18. 람다와 함수형

## 문제 1 — `Predicate` 로 필터링
- 파일명: `Homework01.java`
- 핵심 개념: `Predicate`, `Stream.filter`

### 요구사항
- `List<Integer> nums = List.of(3, 7, 1, 9, 4, 6, 8)` 에서
  - 짝수만
  - 5 이상
  - 두 조건 모두(짝수 AND 5 이상)
- 각각 필터한 결과를 출력.

### 예상 출력
```
짝수: [4, 6, 8]
5 이상: [7, 9, 6, 8]
짝수 AND 5 이상: [6, 8]
```

## 문제 2 — 메서드 참조로 정렬
- 파일명: `Homework02.java`
- 핵심 개념: `Comparator`, 메서드 참조

### 요구사항
- `record Book(String title, int pages)` 리스트를
  - `pages` 오름차순
  - `title` 알파벳순
- 두 번 정렬해 출력.

### 예상 출력
```
페이지 오름차순: [Book[title=A, pages=100], Book[title=C, pages=200], Book[title=B, pages=300]]
제목 알파벳순: [Book[title=A, pages=100], Book[title=B, pages=300], Book[title=C, pages=200]]
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
