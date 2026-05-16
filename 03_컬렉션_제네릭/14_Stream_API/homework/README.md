# 과제 - 14. Stream API

## 문제 1 — 학생 점수 분석
- 파일명: `Homework01.java`
- 핵심 개념: `map`, `filter`, `reduce`, `Collectors.groupingBy`

### 요구사항
- `record Student(String name, String dept, int score)`
- 다음 데이터로:
  - Alice CS 85, Bob CS 70, Cathy MATH 92, Dan MATH 65, Eve PHY 80
- 전체 평균, 최고점 학생 이름, 학과별 평균을 출력하세요.

### 예상 출력 (학과 순서는 다를 수 있음)
```
전체 평균: 78.4
최고점: Cathy (92)
학과별 평균
CS -> 77.5
MATH -> 78.5
PHY -> 80.0
```

## 문제 2 — 짝수 제곱의 합
- 파일명: `Homework02.java`
- 핵심 개념: `IntStream`, `filter`, `map`, `sum`

### 요구사항
- 1 ~ 10 의 정수 중 짝수만 골라 제곱하고, 그 합을 출력합니다.

### 예상 출력
```
짝수 제곱 합 = 220
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
