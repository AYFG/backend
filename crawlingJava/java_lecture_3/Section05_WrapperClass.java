package java_lecture_3;

/**
 * ## 섹션 5. 래퍼(Wrapper), Class 클래스
 */
public class Section05_WrapperClass {
    public static void main(String[] args) {
        // 1. [강의: 기본형(Primitive) vs 래퍼 클래스(Wrapper)]
        /*
         * - 기본형: 메모리 효율적, null 불가, 메서드 없음 (예: int, long)
         * - 래퍼 클래스: 객체이므로 null 가능, 메서드 제공, 제네릭 사용 가능 (예: Integer, Long)
         */
        
        // 2. [강의: 박싱(Boxing)과 언박싱(Unboxing)]
        /*
         * - 박싱: 기본형 -> 래퍼 클래스 (객체로 포장)
         * - 언박싱: 래퍼 클래스 -> 기본형 (포장을 뜯음)
         */
        int value = 7;
        
        // [수동 박싱/언박싱] (과거 방식 - 현재는 권장되지 않음)
        Integer manualBox = Integer.valueOf(value); 
        int manualUnbox = manualBox.intValue();

        // [오토 박싱/언박싱] (현재 방식 - 자바가 자동으로 처리)
        /*
         * 컴파일러가 자동으로 코드를 추가해줍니다.
         * 마치 기본형처럼 래퍼 클래스를 사용할 수 있게 해줍니다.
         */
        Integer autoBox = value;    // 오토 박싱 (int -> Integer)
        int autoUnbox = autoBox;    // 오토 언박싱 (Integer -> int)

        System.out.println("autoBox = " + autoBox);
        System.out.println("autoUnbox = " + autoUnbox);

        // 3. [강의: 래퍼 클래스 성능 비교]
        /*
         * 아주 많은 연산을 수행할 때는 오토 박싱/언박싱 과정에서 객체가 생성되므로 성능이 저하될 수 있습니다.
         */
        long startTime = System.currentTimeMillis();
        long sum = 0L;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i; // 기본형 연산 (빠름)
        }
        long endTime = System.currentTimeMillis();
        System.out.println("기본형 연산 시간: " + (endTime - startTime) + "ms");

        // 4. [강의: 주요 메서드]
        System.out.println("문자열을 숫자로: " + Integer.parseInt("123"));
        System.out.println("최대값: " + Integer.max(10, 20));

        // 5. [강의: Class 클래스]
        Class<Integer> clazz = Integer.class;
        System.out.println("클래스 정보: " + clazz.getName());
    }
}
