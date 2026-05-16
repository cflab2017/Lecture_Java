# 과제 - 25. 서비스와 레이어

## 문제 — `Product` 등록·조회 API
- 위치: `answer/`
- 핵심 개념: 3 계층 분리, DTO 변환, 생성자 주입

### 요구사항
- 도메인: `Product(id, name, price)`
- DTO 입력: `ProductCreateRequest(name, price)`, 출력: `ProductResponse(id, name, price)`
- 서비스 검증: `price` 가 0 이하면 `IllegalArgumentException`
- 컨트롤러: `/api/products` 에 GET(list), POST(create)
- 저장: in-memory (`ConcurrentHashMap`)

### 예상 동작
```bash
$ curl -X POST localhost:8086/api/products -H "Content-Type: application/json" \
       -d '{"name":"Pen","price":1500}'
{"id":1,"name":"Pen","price":1500}

$ curl localhost:8086/api/products
[{"id":1,"name":"Pen","price":1500}]
```

## 정답 확인
[`answer/`](./answer/) 폴더의 Maven 프로젝트를 보세요.
