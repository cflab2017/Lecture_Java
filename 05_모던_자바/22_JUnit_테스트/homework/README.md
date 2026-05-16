# 과제 - 22. JUnit 테스트

## 문제 — `StringUtils` 검증
- 위치: `answer/` 안의 Maven 프로젝트 구조
- 핵심 개념: TDD, `assertEquals`, `assertTrue`

### 요구사항
- `StringUtils.reverse(String)` — 문자열 뒤집기
- `StringUtils.isPalindrome(String)` — 회문 여부 (대소문자 무시, 공백 무시)
- 위 두 메서드에 대해 각각 정상/엣지 케이스 테스트를 작성합니다.

### 예상 동작
- `mvn test` 실행 시 모든 테스트 통과.

### 힌트
- `StringUtils` 는 `src/main/java/.../StringUtils.java`
- 테스트는 `src/test/java/.../StringUtilsTest.java`

## 정답 확인
[`answer/`](./answer/) 폴더의 정답과 비교해 보세요.
