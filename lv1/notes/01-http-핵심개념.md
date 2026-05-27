# 1교시 핵심 개념 — Socket, Stream, throws Exception

> HTTP 요청을 직접 날려보기 위해 알아야 할 최소한의 개념
> **외우지 말고, 그림으로 이해하자**

---

## 1. Socket — 전화기

```java
Socket socket = new Socket("example.com", 80);
```

### 전화 비유

| 전화 | 소켓 |
|---|---|
| 상대방 번호를 누름 | `new Socket("example.com", 80)` |
| 상대방 전화기까지 선이 연결됨 | **TCP 연결 성립** |
| 말을 함 (내 입) | `OutputStream` = 내 입 |
| 듣는 귀 | `InputStream` = 상대방 말이 들리는 귀 |
| "끊을게" 하고 끊음 | `socket.close()` |

### 정리

**Socket = 컴퓨터끼리 전화선을 연결하는 도구**
- `"example.com"` = 상대방 주소
- `80` = "웹 서버 담당자"에게 연결 (HTTP 기본 포트)
- 연결되면 데이터를 주고받을 수 있는 파이프가 생김

---

## 2. OutputStream — 내 입

```java
OutputStream out = socket.getOutputStream();
out.write(request.getBytes());
```

```
내 컴퓨터 ──── 파이프 ────→ example.com 서버
             (OutputStream)
```

| 개념 | 설명 |
|---|---|
| `OutputStream` | **데이터를 흘려보내는 출구** (= 내 입) |
| `write(byte[])` | 파이프에 **바이트 덩어리**를 흘려보냄 |
| `String.getBytes()` | 문자열(String) → 컴퓨터가 이해하는 **바이트 배열(byte[])**로 변환 |
| `flush()` | 버퍼에 남은 데이터를 강제로 밀어내서 전송 |

### flush() — 왜 "강제로" 밀어낼까?

#### 버퍼(Buffer) 개념

`OutputStream`은 데이터를 보낼 때 **한 글자씩 바로 안 보내고, 모아서 보냅니다.**
이 모아두는 임시 저장소를 **버퍼(Buffer)**라고 합니다.

```
write("G") → [버퍼: G]
write("E") → [버퍼: GE]
write("T") → [버퍼: GET]
                     ↓ 일정량 차거나 가득 차면
          → "GET" ---실제 전송---→ 서버
```

#### 왜 모아서 보낼까? — 효율

| 방식 | 비유 | 효율 |
|---|---|---|
| 한 글자씩 보냄 | 편지 1통 보내고, 다시 1통 보내고... | ❌ 느림, 비용 큼 |
| 모아서 한 번에 보냄 | 편지 100통을 한 상자에 담아 보냄 | ✅ 빠름, 비용 적음 |

#### flush()의 역할

```java
out.write(request.getBytes());
// "버퍼에 담겼겠지... 서버에 전송되었을까? 아직 안 갔을 수도 있음"
out.flush();
// "버퍼에 남은 거 전부 강제로 밀어내서 지금 당장 보내라!"
```

| 상태 | 버퍼 상황 | flush() 호출 결과 |
|---|---|---|
| 버퍼가 덜 참 | `[GET / HTT...` 아직 덜 참 | **강제로 밀어냄 → 전송됨** |
| 버퍼가 이미 가득 참 | `[GET / HTT...P/1.1\r\n...]` 다 참 | 자동으로 보내졌으므로 변화 없음 |

#### "강제로"의 의미

버퍼는 **알아서 차면 보내도록 설계**되어 있습니다.

```
버퍼가 찰 때까지 기다리자 → 언제 찰지 모름...
flush() → "기다리지 말고 지금 당장 보내!"
```

**즉, flush()는 "자연스러운 시점을 기다리지 말고, 지금 즉시 보내라"는 명령입니다.**

#### 지금 예제에서는 없어도 되는 이유

`socket.close()`를 호출하면 **자동으로 flush()가 실행됩니다.**

```java
out.write(request.getBytes());
// out.flush();  ← 생략해도
socket.close();  // ← 여기서 자동 flush!
```

하지만 `close()` 없이 데이터만 보내고 프로그램이 계속 실행되는 경우는 `flush()`가 필수입니다.

```java
out.write("hello");        // 아직 버퍼에 있음
out.write(" world");       // 여전히 버퍼에 있음
out.flush();               // → 드디어 "hello world" 가 서버에 도착
```

#### 비유 요약

```
write  write  write          flush!           close
  │      │      │              │               │
  ▼      ▼      ▼              ▼               ▼
┌─────────────────────────┐  강제         ┌────────────────┐
│  G  E  T  /  H  T  T  P │→ 전송 → 서버  │ (연결 종료)    │
│  ...                    │              └────────────────┘
└─────────────────────────┘
       버퍼                    flush()가
       (아직 덜 참)            밀어냄
```

### out.write(request.getBytes()) — 이 한 줄의 의미

```java
out.write(request.getBytes());
//  ↑      ↑       ↑
//  1      2       3
```

#### 1. `out` — 아까 저장해둔 OutputStream

```java
OutputStream out = socket.getOutputStream();
```

`socket.getOutputStream()`이 리턴한 **"출구"**를 `out` 변수에 담아뒀습니다.
`out`은 **"서버로 데이터를 보내는 파이프의 입구"**입니다.

#### 2. `.write()` — OutputStream이 가진 기능 (메서드)

`OutputStream` 클래스에는 `write()`라는 메서드(=기능)가 내장되어 있습니다.

```java
out.write( /* 여기 넣은 데이터를 상대방에게 보내줘 */ );
```

| 호출 | 뜻 |
|---|---|
| `out.write(x)` | **x를 상대방(서버)에게 보내줘** |

#### 3. `request` — 우리가 만든 HTTP 요청 문자열

```java
String request = "GET / HTTP/1.1\r\n...";
```

그냥 **텍스트(String)**입니다. 자바 기본 타입 중 하나.

#### 4. `.getBytes()` — 왜 필요한가?

**문제:** `write()`는 **바이트(byte[])**만 받습니다. 문자열(String)은 못 받아요.

```java
out.write("GET / HTTP/1.1");   // ❌ 컴파일 에러! String 못 넣음
out.write(/* byte[]만 가능 */);  // ✅
```

**해결:** `.getBytes()`가 **문자열을 바이트 덩어리로 변환**해줍니다.

```java
String text = "GET / HTTP/1.1";
byte[] bytes = text.getBytes();  // "GET" → [71, 69, 84, ...]
```

#### 왜 굳이 바이트로 변환해야 할까?

| 계층 | 데이터 형태 | 이유 |
|---|---|---|
| 자바 코드 | `String` ("GET / HTTP/1.1") | 사람이 읽기 편함 |
| 네트워크 선 | `byte[]` (0과 1의 나열) | 컴퓨터가 이해하는 형태 |
| 변환기 | `.getBytes()` | String → byte로 변환 |
| 역변환기 | `new String(bytes)` | byte → String으로 복원 |

**네트워크 선은 0과 1만 흐릅니다.**
"GET"이라는 글자가 아니라, G=71, E=69, T=84 라는 숫자가 흐르는 거예요.

#### 직접 확인해보기

아래 코드를 `RawHttpClient.java`에 추가해서 실행해보세요:

```java
System.out.println("문자열: " + request);
System.out.println("바이트: " + java.util.Arrays.toString(request.getBytes()));
```

예상 출력:
```
문자열: GET / HTTP/1.1\r\n...
바이트: [71, 69, 84, 32, 47, 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10, ...]
```

**71=G, 69=E, 84=T** 가 매핑되는 걸 직접 확인할 수 있습니다.

### OutputStream 종류

| 종류 | 데이터 방향 | 비유 |
|---|---|---|
| `OutputStream` | 내가 → 상대방 | 내 입 |
| `InputStream` | 상대방이 → 나 | 내 귀 |

---

## 3. InputStream — 내 귀

```java
BufferedReader reader = new BufferedReader(
    new InputStreamReader(socket.getInputStream())
);
String line;
while ((line = reader.readLine()) != null) {
    System.out.println(line);
}
```

| 개념 | 설명 |
|---|---|
| `InputStream` | **상대방의 데이터를 읽는 입구** (= 내 귀) |
| `InputStreamReader` | 바이트 → 문자로 변환 (디코더) |
| `BufferedReader` | 데이터를 한 줄씩 읽기 편하게 감싸주는 래퍼 |
| `readLine()` | 한 줄을 읽어서 문자열로 반환 |
| `null` 반환 | 더 이상 읽을 데이터가 없음 = 연결 종료 |

### 래퍼 구조

```java
socket.getInputStream()     ← 원시 바이트 (0, 1)
        ↓
InputStreamReader           ← 바이트 → 문자(char) 변환
        ↓
BufferedReader              ← 문자를 모아서 한 줄(String) 단위로 제공
        ↓
readLine()                  ← 한 줄씩 꺼내 씀
```

---

## 5. HTTP 요청 문자열 — 진짜 핵심

```java
String request = "GET / HTTP/1.1\r\n"
                + "Host: example.com\r\n"
                + "Connection: close\r\n"
                + "\r\n";
```

**이 문자열이 바로 HTTP 요청 그 자체입니다.**
소켓이 연결한 파이프를 통해 이 텍스트를 서버에 보내는 게 전부입니다.

---

### 5.1 시작 라인 (Request Line): `GET / HTTP/1.1`

```
[HTTP 메서드] [경로] [HTTP 버전]
```

| 부분 | 뜻 | 설명 |
|---|---|---|
| `GET` | **"이 리소스를 주세요"** | `POST`는 "받아서 처리해주세요", `PUT`은 "교체해주세요", `DELETE`는 "지워주세요" |
| `/` | **루트 경로** | `https://example.com` 의 홈페이지를 요청. `/users/1` 이면 그 경로의 리소스 |
| `HTTP/1.1` | **HTTP 프로토콜 버전** | 맞습니다. 지금 우리가 쓰는 버전입니다. HTTP/2, HTTP/3도 있지만 1.1이 가장 보편적 |

---

### 5.2 `Host: example.com` — 필수 헤더

**왜 필요한가?** 한 대의 서버가 여러 도메인을 서비스할 수 있기 때문입니다.

```
하나의 서버 (IP: 93.184.216.34)
    ├── example.com
    ├── example.org
    └── example.net
```

소켓 연결은 IP 주소까지만 갑니다.
서버는 "아, 93.184.216.34:80까지 왔는데... 어느 도메인 얘기지?"
→ `Host` 헤더를 보고 `example.com` 용 응답을 보내줍니다.

> HTTP/1.1부터 **Host는 필수**입니다. 없으면 400 Bad Request가 떠요.

---

### 5.3 `Connection: close` — 연결 종료 방식

| 값 | 뜻 |
|---|---|
| `Connection: close` | "응답 받고 나면 연결 끊어주세요" |
| 없음 (= keep-alive) | "연결 유지했다가 다음 요청에도 재사용할게요" (HTTP/1.1 기본값) |

우리는 실험용으로 하나 받고 끝낼 거라서 일부러 `close`를 보낸 겁니다.

---

### 5.4 `\r\n` — 줄바꿈 문자

```
\r = 캐리지 리턴 (Carriage Return) — 커서를 줄 맨 앞으로 이동
\n = 라인 피드 (Line Feed) — 커서를 다음 줄로 이동
```

HTTP는 **텍스트 프로토콜**이라서 줄 단위로 데이터를 구분합니다.
**모든 줄은 `\r\n`으로 끝납니다.**

---

### 5.5 마지막 `\r\n` — 빈 줄 (헤더와 바디의 구분자)

```
GET / HTTP/1.1\r\n
Host: example.com\r\n
Connection: close\r\n
\r\n    ← "여기까지가 헤더고, 바디는 없습니다"
```

**HTTP는 헤더와 바디를 빈 줄로 구분합니다.**
`GET` 요청은 보통 바디가 없기 때문에, 헤더 끝난 직후 `\r\n` 한 번 더 쳐서 끝냅니다.

---

### 5.6 전체 해석

```
"GET / HTTP/1.1"         → "홈페이지 좀 주세요, HTTP 1.1로 대화할게요"
"Host: example.com"      → "example.com 으로 온 요청이에요"
"Connection: close"      → "응답 주시고 바로 끊을게요"
                         → (빈 줄) "헤더 끝, 요청은 여기까지"
```

### 서버가 받으면?

```
1. 시작 라인 해석 → "GET / 요청이구나. 홈페이지 주면 되겠다"
2. Host 헤더 확인 → "example.com 용 응답을 보내야지"
3. Connection 확인 → "응답 보내고 연결 끊으라는 뜻이군"
4. 빈 줄 확인 → "헤더 끝. 바디는 없네. 바로 응답 준비"
```

---

## 6. throws Exception

```java
public static void main(String[] args) throws Exception {
```

### 뜻

**"이 코드에서 문제 생기면, 나는 처리하지 않고 JVM(자바 실행기)한테 떠넘긴다"**

### 왜 쓰는가?

네트워크 코드는 문제가 많다:

| 상황 | 발생할 수 있는 예외 |
|---|---|
| 서버가 다운됨 | `UnknownHostException` |
| 와이파이 끊김 | `SocketException` |
| 포트 막힘 | `ConnectException` |
| 데이터 읽기 실패 | `IOException` |

이걸 초보자가 하나하나 `try-catch`로 처리하면:
- 코드가 2배로 길어짐
- 핵심 로직보다 예외처리 코드가 더 많아짐
- HTTP 통신 자체를 이해하는 게 더 어려워짐

### 실무 vs 학습

| 상황 | 방식 |
|---|---|
| **지금 (학습)** | `throws Exception` — 개념 이해에 집중 |
| **실무** | `try-catch`로 예외를 구체적으로 처리 |

> **지금은 "나중에 제대로 배울게" 하고 넘어가는 것도 전략입니다.**

---

## 전체 흐름 그림

```
내 컴퓨터                          example.com 서버
┌─────────────────┐              ┌─────────────────┐
│                 │   전화 연결    │                 │
│   Socket        │═══════════════│   Socket        │
│                 │  TCP:80       │                 │
│  ┌───────────┐  │              │  ┌───────────┐  │
│  │ OutputStream│─┼─── "GET /" ──┼─→│ InputStream │  │
│  │  (내 입)   │  │              │  │ (서버 귀)  │  │
│  └───────────┘  │              │  └───────────┘  │
│                 │              │                 │
│  ┌───────────┐  │              │  ┌───────────┐  │
│  │ InputStream │←┼── "200 OK" ──┼──│ OutputStream│ │
│  │ (내 귀)   │  │              │  │ (서버 입)  │  │
│  └───────────┘  │              │  └───────────┘  │
└─────────────────┘              └─────────────────┘

내가 하는 일:
1. Socket   → 전화 검
2. OutputStream.write("GET / ...")  → "이거 주세요" 하고 말함
3. InputStream.read()              → "여기 있습니다" 응답을 들음
4. socket.close()                  → 전화 끊음
```

---

## 용어 퀴즈 (스스로 답해보기)

```
Q1. Socket은 무엇의 비유인가?
→

Q2. OutputStream은 입일까 귀일까?
→

Q3. throws Exception을 쓰는 이유는?
→

Q4. BufferedReader가 필요한 이유는?
→

Q5. flush()는 왜 필요한가?
→
```

> **모르면 다시 읽고, 그래도 모르면 질문하세요.**
> 넘어가면 2~6교시 전부 흔들립니다.
