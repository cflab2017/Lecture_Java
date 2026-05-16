# 과제 - 07. 캡슐화

## 문제 1 — `Temperature` 캡슐화
- 파일명: `Homework01.java`
- 핵심 개념: `private` 필드, setter 검증

### 요구사항
- 클래스 `Temperature` 에 `private double celsius` 필드를 둡니다.
- `setCelsius(double)` 는 `-273.15` 미만이면 `IllegalArgumentException` 을 던집니다.
- `toFahrenheit()` 는 화씨로 변환해 반환합니다.

### 예상 출력
```
25.0°C = 77.0°F
거부: -300.0°C 는 절대영도 이하
```

## 문제 2 — `Money` record
- 파일명: `Homework02.java`
- 핵심 개념: `record`, equals/hashCode 자동 생성

### 요구사항
- record `Money(long amount, String currency)` 를 정의합니다.
- 두 `Money` 가 동일한 amount/currency 면 `equals` 가 true 입니다.

### 예상 출력
```
m1=Money[amount=1000, currency=KRW]
m1.equals(m2)=true
m1.equals(m3)=false
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
