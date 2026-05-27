# LV1 커리큘럼 — "왜"를 체득하는 백엔드 로드맵

> 이 커리큘럼은 따라하기가 아니라 **경험하기** 위해 설계됐습니다.
> 매 챕터의 목표는 "코드 실행 성공"이 아니라 **"그 코드가 왜 거기 있는지 설명할 수 있는 상태"** 입니다.

---

## 📐 진행 방식

| 단계 | 시간 | 행동 |
|---|---|---|
| **사전 질문** | 5분 | 코드 한 줄 없이 "왜 필요할까?" 스스로 답해본다 |
| **직접 부딪히기** | 30~60분 | 일부러 바닐라 상태로 만들어보고 고통을 체감한다 |
| **해결책 도입** | 30분 | 프레임워크/라이브러리가 그 고통을 어떻게 해결하는지 체험 |
| **면접 스토리** | 10분 | "이 기술을 왜 쓰는지" 3분 분량으로 말로 정리 |

---

## 1교시 ⎯ 인터넷, 웹, HTTP, JSON

### 사전 질문
- URL 주소창에 `google.com` 치고 엔터 누르면 어떤 일이 일어날까?
- 카톡 메시지를 보낼 때랑, 브라우저에서 유튜브를 볼 때랑 뭐가 다를까?

### 실전 과제 — `practice/01-http`
**직접 HTTP 요청을 텍스트로 만들어보기**

```java
// 1. 순수 Socket으로 HTTP 요청 직접 날려보기
Socket socket = new Socket("example.com", 80);
OutputStream out = socket.getOutputStream();
out.write("GET / HTTP/1.1\r\nHost: example.com\r\n\r\n".getBytes());
// 응답 읽어서 콘솔 출력
```

**체크포인트**: HTTP 응답 텍스트를 눈으로 직접 봤는가?

### 면접 스토리 준비
> "HTTP는 텍스트 기반의 Stateless 프로토콜입니다. 직접 Socket으로 요청을 보내보면 GET / HTTP/1.1 같은 평문을 서버에 전달하고, 서버도 HTTP/1.1 200 OK 같은 텍스트로 응답합니다. Stateless라 매 요청마다 인증 정보를 전부 담아야 합니다."

---

## 2교시 ⎯ 웹 서버: 소켓의 지옥과 톰캣의 구원

### 사전 질문
- `ServerSocket` 하나로 왜 트래픽을 감당 못 할까?
- 여러 사용자가 동시에 접속하면 어떤 문제가 생길까?

### 실전 과제 — `practice/02-tomcat`
**1단계: 순수 Java 서버 직접 만들고 고통 체험하기**
```java
// ServerSocket 30줄로 "웹 서버" 구현
// → 요청 파싱 직접, 스레드 직접, 응답 직접
```

**2단계: 톰캣 임베드해서 같은 기능 구현**
```java
// Spring Boot 없이 순수 톰캣만 embed 해보기
// Tomcat tomcat = new Tomcat(); ...
```

**3단계: Spring Boot로 7줄 컷**
```java
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() { return "Hello"; }
}
```

**체크포인트**: 30줄 → 7줄로 줄어드는 과정을 단계별로 직접 코딩했는가?

### 면접 스토리 준비
> "ServerSocket으로 30줄짜리 서버를 직접 만들어봤는데, 요청 파싱과 스레드 관리가 전부 수동이었습니다. 톰캣은 이걸 소켓 관리, 스레드 풀, HTTP 파싱 세 가지로 해결합니다. Spring Boot는 톰캣을 내장해 jar 하나로 실행 가능하게 만들죠."

---

## 3교시 ⎯ 데이터베이스: JDBC의 반복 노동에서 JPA까지

### 사전 질문
- Java 코드에서 DB로 SQL을 보낼 때 실제로 어떤 일이 일어날까?
- Connection을 매번 새로 만들면 왜 비효율적일까?

### 실전 과제 — `practice/03-database`
**1단계: 순수 JDBC로 CRUD 직접 구현**
```java
// try-catch-finally 지옥, ResultSet 매핑 직접, close() 직접
// "이게 사람이 할 짓인가" 체험
```

**2단계: JdbcTemplate으로 개선**
```java
// try-catch 사라짐, RowMapper로 자동 매핑
// "아, 이제 좀 낫네"
```

**3단계: JPA로 전환**
```java
@Entity public class User { ... }
// em.persist(user) → SQL 자동 생성
// "이게 마법이 아니라 JDBC 위에서 돌아가는 거구나"
```

**체크포인트**: `save()` 호출했는데 DB에 바로 안 들어가는 이유(영속성 컨텍스트)를 설명할 수 있는가?

### 면접 스토리 준비
> "JDBC로 직접 CRUD를 구현해보니 try-catch와 ResultSet 매핑이 수십 줄 반복됐습니다. JPA는 이걸 객체 상태 추적으로 자동화합니다. save()를 호출해도 바로 SQL이 나가지 않고, 트랜잭션 커밋 시점에 한 번에 반영됩니다. 결국 내부적으로는 JDBC를 호출합니다."

---

## 4교시 ⎯ 스프링의 마법: 프록시, DI, 컨테이너

### 사전 질문
- `@Transactional`만 붙였는데 트랜잭션이 걸리는 건 어떻게 가능할까?
- `new` 안 쓰고 객체를 주입받는 이유가 테스트 때문이라고?

### 실전 과제 — `practice/04-spring-core`
**1단계: 프록시 직접 만들어보기**
```java
// InvocationHandler로 인터페이스 프록시 구현
// → 메서드 호출 전후로 로그가 찍히는 원리 체험
```

**2단계: DI 없는 세상 체험**
```java
// 모든 의존성을 new로 생성하는 코드
// → Service 바꾸려면 코드 전체를 수정해야 하는 고통
```

**3단계: ApplicationContext에 Bean 수동 등록**
```java
// @Configuration + @Bean으로 직접 등록
// → @ComponentScan이 이걸 자동화하는 원리 이해
```

**체크포인트**: `@Transactional`이 붙은 메서드가 호출되면 실제로 어떤 객체가 대신 호출되는지 설명할 수 있는가?

### 면접 스토리 준비
> "@Transactional이 동작하는 원리는 프록시입니다. CGLib으로 런타임에 바이트코드를 조작해서 원본 객체를 감싼 가짜 클래스를 만들고, 메서드 호출 전에 BEGIN, 정상 종료면 COMMIT, 예외면 ROLLBACK을 자동으로 실행합니다."

---

## 5교시 ⎯ 계층 분리 & 트랜잭션

### 사전 질문
- Controller에 트랜잭션을 걸면 왜 위험할까?
- 왜 Service라는 계층이 따로 존재해야 할까?

### 실전 과제 — `practice/05-layered`
**직접 계층을 잘못 설계하고 문제 체험하기**
```java
// Controller에 @Transactional + HTTP 요청 파싱 로직
// → 트랜잭션이 HTTP 응답까지 물고 있는 문제 체험
```

**올바른 3계층으로 리팩터링**
```java
// Controller: HTTP만 담당
// Service: 비즈니스 로직 + 트랜잭션 경계
// Repository: 데이터 접근만 담당
```

**체크포인트**: "트랜잭션은 왜 Service 계층에 걸어야 하는가?"를 한 문장으로 답할 수 있는가?

### 면접 스토리 준비
> "Controller에 @Transactional을 걸면 HTTP 요청 파싱부터 응답 생성까지 전부 트랜잭션 범위에 들어갑니다. 트랜잭션은 최대한 짧게 가져가야 DB 커넥션을 빨리 반환할 수 있습니다. 그래서 Service 계층을 분리해 그곳에 트랜잭션 경계를 둡니다."

---

## 6교시 ⎯ 실전: Todo List REST API 완성

### 최종 프로젝트 — `projects/todo-api`

**구현할 기능:**
- [ ] 할 일 생성 (POST /todos)
- [ ] 할 일 목록 조회 + 페이징 (GET /todos?page=0&size=10)
- [ ] 할 일 단건 조회 (GET /todos/{id})
- [ ] 할 일 수정 (PUT /todos/{id})  
- [ ] 할 일 삭제 (DELETE /todos/{id})
- [ ] 완료/미완료 토글 (PATCH /todos/{id}/toggle)

**적용할 것:**
- [ ] 3계층 아키텍처 (Controller → Service → Repository)
- [ ] JPA Entity + Repository
- [ ] @Transactional
- [ ] @ExceptionHandler + @ControllerAdvice
- [ ] 페이징 (Pageable)
- [ ] SLF4J 로깅

**체크포인트**: 이 API가 어떻게 돌아가는지 흐름도를 그릴 수 있는가?

---

## 🎯 교시별 핵심 질문 (이걸 다 답할 수 있으면 수료)

| 교시 | 면접 질문 | 답할 수 있으면 OK |
|---|---|---|
| 1 | "인터넷과 웹의 차이가 뭔가요?" | 카톡/유튜브 예시로 설명 가능 |
| 1 | "HTTP가 뭔가요?" | 텍스트 기반 Stateless 프로토콜이라고 말하고, 직접 본 요청 형식 설명 가능 |
| 2 | "톰캣은 왜 쓰나요?" | 소켓 관리 + 스레드 풀 + HTTP 파싱 자동화라고 답변 가능 |
| 3 | "JPA가 뭔가요?" | "JdbcTemplate 지옥을 겪어봤는데..."로 시작 가능 |
| 4 | "@Transactional은 어떻게 동작하나요?" | 프록시 + AOP + CGLib 설명 가능 |
| 5 | "왜 Service 계층을 따로 두나요?" | 트랜잭션 경계 + 책임 분리 설명 가능 |
| 6 | "이 API 설명해보세요" | 요청 → 톰캣 → DispatcherServlet → Controller → Service → Repository → DB 흐름 설명 가능 |

---

## 📅 제안 일정

| 주차 | 내용 | 결과물 |
|---|---|---|
| 1주차 | 1~2교시: HTTP + 웹 서버 | `practice/01-http`, `practice/02-tomcat` |
| 2주차 | 3교시: JDBC → JPA | `practice/03-database` |
| 3주차 | 4~5교시: 스프링 핵심 + 계층 | `practice/04-spring-core`, `practice/05-layered` |
| 4주차 | 6교시: Todo API 완성 | `projects/todo-api` |

---

> _"정의를 외우는 사람이 아니라, 경험을 이야기하는 사람이 되기"_
