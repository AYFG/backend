# 2교시 — Hello World API의 전 과정

## "Hello World"가 브라우저에 보이기까지

### 전체 사슬

```
브라우저 ──GET /hello──→ 톰캣 ──→ DispatcherServlet ──→ HelloController.hello()
  │                                                                │
  │                                                                │ return "Hello World"
  │                                                                ▼
  │                              HTTP 응답 텍스트
  │◁──────── "Hello World" ◁── 톰캣 ◁── Spring이 변환 ◁───────────┘
```

---

## 1단계: 톰캣이 HTTP 요청을 받는다

브라우저가 `http://localhost:8080/hello` 를 치면, 톰캣(8080포트)으로 이 텍스트가 전송됨:

```
GET /hello HTTP/1.1
Host: localhost:8080
...
```

톰캣은 이 텍스트를 파싱해서 `HttpServletRequest` 객체로 만든다.
(1교시에서 직접 파싱하던 작업을 톰캣이 대신)

---

## 2단계: DispatcherServlet이 라우팅한다

```
요청: GET /hello
      ↓
DispatcherServlet: "누가 /hello를 처리하지?"
      ↓
HelloController.hello(): "내 @GetMapping("/hello")야!"
      ↓
"그래, 그럼 네 메서드를 실행해"
```

---

## 3단계: @RestController의 비밀

```java
@RestController   // = @Controller + @ResponseBody
public class HelloController {
```

| 어노테이션 | 역할 |
|---|---|
| `@Controller` | "이 클래스는 HTTP 요청을 처리하는 컨트롤러다" 라고 스프링에 등록 |
| `@ResponseBody` | 메서드가 리턴한 값을 HTTP 응답 바디에 그대로 써라 |

---

## 4단계: return "Hello World" 가 바디가 되는 순간

```java
public String hello() {
    return "Hello World";
}
```

`@ResponseBody`가 붙어있으므로 스프링은:

```
"View 이름을 찾아서 HTML을 렌더링해야지"  (X)
"이 String 값을 그대로 HTTP 응답 바디에 써야지"  (O)
```

`StringHttpMessageConverter`라는 변환기가 동작해서:

```
"Hello World" → HTTP 응답에 Content-Type: text/plain 으로写入
```

---

## 5단계: 톰캣이 HTTP 응답을 내보낸다

스프링이 만든 응답을 톰캣이 HTTP 텍스트로 변환해서 브라우저에 전송:

```
HTTP/1.1 200 OK
Content-Type: text/plain;charset=UTF-8
Content-Length: 11

Hello World
```

1교시에 `RawHttpClient`로 직접 봤던 그 HTTP 응답 형식

---

## 만약 @RestController가 없었다면?

```java
// @RestController 없이 @Controller만 있을 때
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
        // 스프링: "'Hello World'라는 이름의 뷰 템플릿을 찾으라는 뜻이군"
        // → /templates/Hello World.html 을 찾다가 404!
    }
}
```

Spring은 원래 MVC 프레임워크라서 `return "Hello World"`를
**"Hello World.html 이라는 뷰 파일로 가라"** 고 해석한다.

`@ResponseBody`를 붙이면 **"아니야, 이 문자열 자체를 응답으로 보내"** 로 바뀜.

---

## 서블릿이란?

### 서블릿 = "HTTP 요청을 처리하는 자바 클래스의 표준"

```java
// 서블릿의 원형
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) {
        res.setContentType("text/plain");
        res.getWriter().write("Hello World");
    }
}
```

### 서블릿이 해주는 일

| 1교시 당신의 코드 | 서블릿이 대신함 |
|---|---|
| `ServerSocket(8080)` | 톰캣이 자동 |
| `accept()` | 톰캣이 자동 |
| HTTP 텍스트 직접 파싱 | `req.getMethod()`, `req.getRequestURI()` 로 끝 |
| HTTP 응답 텍스트 직접 작성 | `res.getWriter().write()` 만 하면 됨 |
| 스레드 관리 | 톰캣 스레드 풀이 자동 |

### 서블릿 시대 vs Spring 시대

```
서블릿 시대:
/hello  → HelloServlet (서블릿 직접 구현) → 클래스 파일 하나
/users  → UserServlet   (서블릿 직접 구현) → 클래스 파일 하나
/orders → OrderServlet  (서블릿 직접 구현) → 클래스 파일 하나

Spring 시대:
모든 요청 → DispatcherServlet (유일한 서블릿)
              → @GetMapping("/hello")   ← 그냥 메서드
              → @GetMapping("/users")   ← 그냥 메서드
              → @PostMapping("/orders") ← 그냥 메서드
         → 클래스 하나에 메서드만 추가하면 끝
```

### 개념 비유

| 개념 | 비유 |
|---|---|
| **서블릿** | HTTP 요청을 처리하는 표준 인터페이스 (규격) |
| **톰캣** | 서블릿들을 관리하고 실행하는 컨테이너 (= 서블릿 컨테이너) |
| **DispatcherServlet** | Spring이 만든 유일한 서블릿. 모든 요청을 받아서 @Controller에게 나눠줌 |

---

## 전체 정리

```
브라우저에 "Hello World"가 보이는 이유:

1. 톰캣이 HTTP 요청 텍스트(GET /hello)를 파싱
2. DispatcherServlet이 @GetMapping("/hello")를 찾음
3. hello() 메서드 실행 → "Hello World" 리턴
4. @ResponseBody 덕분에 이 값이 HTTP 응답 바디에 그대로写入
5. 톰캣이 HTTP 응답 텍스트를 브라우저에 전송
6. 브라우저가 "Hello World"를 화면에 표시
```

---

## 1교시 vs 2교시

| 1교시 (Raw Socket) | 2교시 (Spring Boot) |
|---|---|
| `Socket` 열고 HTTP 텍스트 직접 작성 | `@GetMapping("/hello")` 한 줄 |
| `OutputStream.write()`로 전송 | `return "Hello World"` |
| `InputStream.read()`로 응답 읽음 | 스프링이 HTTP 응답을 자동 생성 |
| ServerSocket 직접 열고 관리 | 톰캣이 내장되어 자동 관리 |
| 30줄, 요청 1번 보내고 종료 | 7줄, 서버가 계속 떠서 대기 |
