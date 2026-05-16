# 과제 - 15. 예외처리

## 문제 1 — 안전한 나눗셈
- 파일명: `Homework01.java`
- 핵심 개념: `try`/`catch`, `ArithmeticException`

### 요구사항
- `safeDiv(int a, int b)` 메서드: 0 으로 나누면 `0` 반환, 그 외엔 결과 반환.
- `(10,2)`, `(10,0)`, `(7,3)` 호출 결과 출력.

### 예상 출력
```
10 / 2 = 5
10 / 0 = 0
7 / 3 = 2
```

## 문제 2 — 사용자 정의 예외
- 파일명: `Homework02.java`
- 핵심 개념: 커스텀 예외 + `throw`

### 요구사항
- `class NotEnoughBalanceException extends RuntimeException` 정의.
- `withdraw(balance, amount)` : amount > balance 면 위 예외, 아니면 차감 후 잔액 반환.
- (1000, 300), (1000, 2000) 시나리오 처리.

### 예상 출력
```
잔액: 700
실패: 잔액 부족 1000 < 2000
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
