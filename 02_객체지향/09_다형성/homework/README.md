# 과제 - 09. 다형성

## 문제 1 — `Vehicle` 다형성
- 파일명: `Homework01.java`
- 핵심 개념: 추상 부모 + 자식 오버라이딩

### 요구사항
- 추상 클래스 `Vehicle` 에 `String describe()` 추상 메서드.
- 자식 `Car`, `Bike` 가 각자 구현.
- `Vehicle[]` 배열로 호출.

### 예상 출력
```
Car 4 wheels
Bike 2 wheels
```

## 문제 2 — `instanceof` 패턴 매칭
- 파일명: `Homework02.java`
- 핵심 개념: JDK 16+ 패턴 매칭

### 요구사항
- `Object` 타입 변수에 정수, 문자열, 리스트(List) 를 넣어 가며 다음을 출력:
  - `Integer` 면 `숫자: <값>`
  - `String` 면 `문자열: <길이>자`
  - 그 외엔 `알 수 없음`

### 예상 출력
```
숫자: 42
문자열: 5자
알 수 없음
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
