# 과제 - 05. 메서드

## 문제 1 — 소수 판별 메서드
- 파일명: `Homework01.java`
- 핵심 개념: 메서드 선언, 반환값

### 요구사항
- `static boolean isPrime(int n)` 메서드를 만들어 1 미만이면 false, 그 외엔 약수 검사로 소수 판별합니다.
- main 에서 2 ~ 10 의 결과를 출력합니다.

### 예상 출력
```
2 -> true
3 -> true
4 -> false
5 -> true
6 -> false
7 -> true
8 -> false
9 -> false
10 -> false
```

## 문제 2 — 평균 (오버로딩 + 가변 인자)
- 파일명: `Homework02.java`
- 핵심 개념: 오버로딩, 가변 인자

### 요구사항
- `static double avg(int... nums)` 와 `static double avg(double... nums)` 두 개를 작성합니다.
- 빈 입력이면 0 을 반환합니다.

### 예상 출력
```
int avg(1,2,3,4) = 2.5
double avg(1.5, 2.5) = 2.0
empty = 0.0
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
