# 과제 - 20. Date · Time

## 문제 1 — 오늘부터 D-Day 계산
- 파일명: `Homework01.java`
- 핵심 개념: `LocalDate`, `Period`, `Duration`

### 요구사항
- 기준일 2026-05-16, 목표일 2026-12-25 (성탄절).
- 두 날짜의 차이를 `Period.between` 으로 "X일" 형식으로 출력.

### 예상 출력
```
D-223일
```

### 힌트
- `ChronoUnit.DAYS.between(a, b)` 가 가장 직관적입니다.

## 문제 2 — 출근 포맷
- 파일명: `Homework02.java`
- 핵심 개념: `LocalDateTime`, `DateTimeFormatter`

### 요구사항
- 시각 `2026-05-16 09:00` 을 만들어 다음 두 형식으로 출력.
- 형식 A: `yyyy년 M월 d일 a h시 m분` (예: `2026년 5월 16일 오전 9시 0분`)
- 형식 B: `yyyy-MM-dd HH:mm`

### 예상 출력
```
2026년 5월 16일 오전 9시 0분
2026-05-16 09:00
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
