package java_lecture_3;

/**
 * ## 섹션 5. 래퍼, Class 클래스
 */
public class Section05_WrapperClass {
    public static void main(String[] args) {
        // 1. [강의: 래퍼 클래스 - 오토 박싱]
        /*
         * 기본형(primitive)을 객체(Wrapper)로 감싸는 것을 박싱, 반대를 언박싱이라 합니다.
         * 자바는 이를 자동으로 처리해주는 오토 박싱/언박싱을 지원합니다.
         */
        int primitive = 10;
        Integer wrapper = primitive; // 오토 박싱 (int -> Integer)
        int unboxed = wrapper;     // 오토 언박싱 (Integer -> int)

        // 2. [강의: Class 클래스]
        /*
         * 클래스의 메타 데이터(이름, 필드, 메서드 정보 등)를 담고 있는 클래스입니다.
         */
        Class<String> clazz = String.class; 

        System.out.println("Class Name: " + clazz.getName());

        // 3. [강의: System 클래스]
        long startTime = System.currentTimeMillis();
        // ... 작업 수행 ...
        long endTime = System.currentTimeMillis();
        System.out.println("소요 시간: " + (endTime - startTime) + "ms");
    }
}
