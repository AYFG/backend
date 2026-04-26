
/**
 * ## 섹션 11. 예외 처리2 - 실습
 */
public class Section11_Exception2 {
    public static void main(String[] args) {
        // 1. [강의: finally]
        /*
         * 예외 발생 여부와 상관없이 반드시 실행되는 블록입니다.
         */
        try {
            System.out.println("비즈니스 로직 실행");
            throw new RuntimeException("에러 발생!");
        } catch (Exception e) {
            System.out.println("예외 처리: " + e.getMessage());
        } finally {
            System.out.println("자원 정리 (finally 항상 실행)");
        }

        // 2. [강의: try-with-resources]
        /*
         * AutoCloseable 인터페이스를 구현한 객체를 자동으로 닫아줍니다.
         * 
         * [핵심 개념 정리]
         * 1. 외부 자원이란?: 파일 입출력, DB 커넥션, 네트워크 소켓 등 자바(JVM) 밖의 OS가 관리하는 자원입니다.
         * 2. 자원 누수(Resource Leak): 가비지 컬렉터(GC)는 메모리는 치워주지만 외부 자원의 반납(close)은 해주지 않습니다.
         *    이를 반납하지 않으면 OS의 자원이 고갈되어 프로그램이 멈출 수 있습니다.
         * 3. 타 언어와의 비교: 최신 JavaScript/TypeScript의 'using' 키워드와 완벽하게 동일한 역할을 합니다.
         *    (JS의 Symbol.dispose 인터페이스 = Java의 AutoCloseable 인터페이스)
         * 
         * 팁: AutoCloseable은 java.lang 패키지에 들어있는 자바 표준 인터페이스이므로 별도 import가 필요 없습니다.
         */
        try (MyResource resource = new MyResource()) {
            resource.doWork();
        } catch (Exception e) {
            System.out.println("에러 발생: " + e.getMessage());
        }
    }
}

class MyResource implements AutoCloseable {
    public void doWork() {
        System.out.println("자원 사용 중...");
    }

    @Override
    public void close() throws Exception {
        System.out.println("자원 자동 반환 (close 호출)");
    }
}
