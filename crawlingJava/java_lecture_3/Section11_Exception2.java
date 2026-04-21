package java_lecture_3;

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
         */
        try (MyResource resource = new MyResource()) {
            resource.doWork();
        } catch (Exception e) {
            System.out.println("에러 발생: " + e.getMessage());
        }
    }
}

class MyResource implements AutoCloseable {
    public void doWork() { System.out.println("자원 사용 중..."); }
    @Override
    public void close() throws Exception {
        System.out.println("자원 자동 반환 (close 호출)");
    }
}
