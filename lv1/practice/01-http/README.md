# 1교시 — HTTP를 직접 눈으로 확인하자

## 목표
순수 Java `Socket`으로 HTTP 요청을 직접 날려보고, 응답 텍스트를 눈으로 본다.
→ "HTTP는 그냥 텍스트다" 라는 걸 몸으로 체험

---

## 1단계: Socket으로 HTTP 요청 날리기

`RawHttpClient.java` 를 생성하고 아래 코드를 **직접 타이핑**하세요.

```java
import java.io.*;
import java.net.*;

public class RawHttpClient {
    public static void main(String[] args) throws Exception {
        // 1. TCP 소켓 연결
        Socket socket = new Socket("example.com", 80);
        
        // 2. HTTP 요청을 텍스트로 직접 작성
        String request = "GET / HTTP/1.1\r\n"
                       + "Host: example.com\r\n"
                       + "Connection: close\r\n"
                       + "\r\n";
        
        // 3. 서버로 전송
        OutputStream out = socket.getOutputStream();
        out.write(request.getBytes());
        out.flush();
        
        // 4. 서버 응답을 텍스트로 읽기
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(socket.getInputStream())
        );
        
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        
        // 5. 연결 종료
        socket.close();
    }
}
```

## 2단계: 실행

```bash
cd practice/01-http
javac RawHttpClient.java
java RawHttpClient
```

## 3단계: 응답 확인

다음이 출력되면 성공입니다:

```
HTTP/1.1 200 OK
Content-Type: text/html
...
<!doctype html>
...
```

## 체크포인트

실행 후, 아래 질문에 스스로 답해보세요:

- [ ] HTTP 요청의 첫 줄(`GET / HTTP/1.1`)이 무엇을 의미하는지 설명할 수 있는가?
- [ ] 응답 첫 줄(`HTTP/1.1 200 OK`)에서 `200`이 뭔지 설명할 수 있는가?
- [ ] 헤더와 바디가 빈 줄(`\r\n`)로 구분되는 걸 직접 확인했는가?
- [ ] Socket이 닫히는 걸 직접 관리해야 하는 불편함을 체감했는가?

## 면접 스토리로 정리

```
"HTTP는 텍스트 기반 Stateless 프로토콜입니다.
직접 Socket으로 example.com에 GET 요청을 보내보면,
'GET / HTTP/1.1' 같은 평문을 OutputStream에 쓰고,
서버가 'HTTP/1.1 200 OK' 텍스트로 응답하는 걸 확인할 수 있습니다.
이렇게 직접 부딪혀보면 HTTP가 단순한 텍스트 규약이라는 게 몸에 박힙니다."
```

## 📝 경험 기록

실행 후 `notes/01-http-경험.md` 파일을 만들어 아래 내용을 기록하세요:
- 처음 실행했을 때 어떤 에러가 났는지
- 응답 텍스트 중 가장 인상 깊었던 부분
- "아하!" 하고 깨달은 점
