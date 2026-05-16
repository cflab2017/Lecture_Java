# 과제 - 08. 상속

## 문제 1 — `Employee` 와 `Manager`
- 파일명: `Homework01.java`
- 핵심 개념: `extends`, `super`, 메서드 오버라이딩

### 요구사항
- 부모 `Employee(name, salary)` + `report()` 가 `홍길동 월급 3000000` 형식 출력.
- 자식 `Manager(name, salary, bonus)` 는 `report()` 를 오버라이딩해 보너스 포함 총액을 출력.
- main 에서 둘 다 호출.

### 예상 출력
```
홍길동 월급 3000000
김부장 월급 5000000 + 보너스 1000000 = 6000000
```

## 문제 2 — `Shape` 계층
- 파일명: `Homework02.java`
- 핵심 개념: 공통 부모 + 오버라이딩 + `@Override`

### 요구사항
- 부모 `Shape` 에 `double area()` 메서드(반환 0.0).
- 자식 `Square`, `Triangle` 가 각자 `area()` 오버라이딩.
- `Shape[]` 배열에 담아 면적 출력.

### 예상 출력
```
Square: 9.0
Triangle: 6.0
```

## 정답 확인
직접 풀어 본 후 [`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
