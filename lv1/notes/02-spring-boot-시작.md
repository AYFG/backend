# 2교시 — Spring Boot: 30줄의 고통이 7줄로

## 1교시 vs 2교시

### 당신이 1교시에서 한 일

```
Socket 열고
  → OutputStream에 HTTP 요청 텍스트 쓰고
  → InputStream으로 응답 읽고
  → Socket 닫고
```

30줄의 코드로 겨우 요청 하나 보내고 프로그램 종료.

### 지금 Spring Boot가 대신 해주는 일

```
Tomcat initialized with port 8080 (http)
```

**한 줄.** 그걸로 끝입니다.

---

## 로그로 보는 Spring Boot의 내부

| 로그 | 의미 | 1교시 연결 |
|---|---|---|
| `Tomcat initialized with port 8080` | 서버가 8080 포트를 열고 대기 시작 | `ServerSocket server = new ServerSocket(8080)` |
| `Tomcat started on port 8080` | 요청을 받을 준비 완료 | `socket.accept()` 무한 루프 |
| `HikariPool-1 - Starting...` | DB 연결을 미리 여러 개 만들어둠 | Connection Pool (직접 구현하려면 수십 줄) |
| `Initialized JPA EntityManagerFactory` | JPA 영속성 컨텍스트 준비 완료 | SQL 매핑 코드 전부 대체 |
| `Started TodoApplication in 2.646 seconds` | **전체 기동 시간 2.6초** | — |

### 핵심 차이

| 항목 | 1교시 (Raw Socket) | 2교시 (Spring Boot) |
|---|---|---|
| 서버 구동 | 직접 구현 (30줄) | 내장 톰캣이 자동 (0줄) |
| HTTP 파싱 | 직접 텍스트 파싱 | 톰캣이 자동 |
| 스레드 관리 | 직접 new Thread() | 톰캣 스레드 풀 (기본 200개) |
| 연결 유지 | 요청 1번 보내고 종료 | 계속 떠서 대기 |
| DB 연결 | 직접 DriverManager.getConnection() | HikariCP가 자동 관리 |
| SQL | 직접 PreparedStatement 작성 | JPA가 자동 생성 |

---

## 톰캣(Tomcat)이 해결하는 것

1교시에서 당신이 직접 겪은 고통을 톰캣이 대신 처리합니다:

```
1교시: Socket 직접 열고 accept() 로 연결 대기
톰캣:   포트 listen, accept, 연결 유지/종료 전부 담당

1교시: 요청 올 때마다 new Thread() 생성
톰캣:   스레드 풀 (기본 200개)로 효율적 관리

1교시: HTTP 텍스트를 직접 파싱 (substring, split...)
톰캣:   텍스트 → HttpServletRequest 객체로 자동 변환

1교시: URL에 따라 직접 if-else 분기
톰캣:   URL에 맞는 서블릿 찾아서 service() 실행
```

### Spring Boot의 역할

Spring Boot는 톰캣을 **내장(Embedded)** 해서 `jar` 하나만 있으면 바로 실행 가능하게 만듭니다.

```
Spring Boot App.jar
  ├── Tomcat (내장 웹 서버)
  ├── HikariCP (Connection Pool)
  ├── Spring Framework (DI, AOP, MVC)
  └── 내가 작성한 Controller/Service/Repository
```

예전에는 Tomcat을 별도 설치하고 war 파일을 배포해야 했지만, Spring Boot는 **Tomcat을 프로그램 안에 넣어서** `java -jar` 한 방에 실행됩니다.

---

## 서버가 "떠 있다"는 것의 의미

1교시: 요청 보내고 응답 받으면 **프로그램 종료**
```java
// 요청 1번 보내고 끝
socket.close();
// 프로그램 종료
```

2교시: 서버가 계속 살아서 **대기 중**
```
요청이 올 때까지 무한 대기
  → 요청 도착하면 스레드 할당해서 처리
  → 응답 보내고 스레드 반환
  → 다시 대기
```

이게 톰캣이 해주는 `server.accept()` 무한 루프이자, 스레드 풀의 힘입니다.

---

## 404 에러도 의미 있다

```
curl http://localhost:8080
→ 404 Not Found
```

**"아무것도 안 만들었는데 서버가 응답을 했다?"**

맞습니다. 톰캣이 최소한의 HTTP 응답을 알아서 만들어준 겁니다.
1교시였다면 요청을 파싱하고 "뭐가 없네? 404를 응답으로 보내야지" 하는 것도 직접 코딩해야 했습니다.

---

## 요약 그림

```
1교시 (30줄)                   2교시 (0줄)
┌──────────────────┐           ┌──────────────────────┐
│ ServerSocket     │           │ Spring Boot          │
│   .accept()      │           │   └── Tomcat (내장)  │
│   InputStream    │           │       ├─ Socket 관리  │
│     .read()      │           │       ├─ 스레드 풀   │
│   OutputStream   │           │       └─ HTTP 파싱   │
│     .write()     │           │                      │
│   socket.close() │           │      @RestController  │
└──────────────────┘           │      @GetMapping      │
                               │         public String │
                               │            hello() {} │
                               └──────────────────────┘
```

> **"프레임워크는 고통을 추상화한다."**
> 직접 겪어봤으니 이제 그 가치를 안다.
