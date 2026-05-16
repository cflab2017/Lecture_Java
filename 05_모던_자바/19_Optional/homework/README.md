# 과제 - 19. Optional

## 문제 1 — 사용자 조회
- 파일명: `Homework01.java`
- 핵심 개념: `Optional` 반환, `orElse`, `ifPresent`

### 요구사항
- `Optional<String> findEmail(String userId)` 메서드를 만들고, 내부 Map(`id1=a@x.com, id2=b@y.com`) 에서 검색.
- `id1`, `id3` 으로 조회해 다음 형식으로 출력.

### 예상 출력
```
id1 -> a@x.com
id3 -> 없음
```

## 문제 2 — 안전한 평균
- 파일명: `Homework02.java`
- 핵심 개념: `OptionalDouble`, `IntStream.average`

### 요구사항
- `static OptionalDouble safeAvg(int[] arr)` — 빈 배열이면 empty.
- `[1,2,3,4]`, `[]` 두 케이스 처리.

### 예상 출력
```
평균=2.5
평균 없음
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
