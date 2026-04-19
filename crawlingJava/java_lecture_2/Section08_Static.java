/**
 * ## 섹션 8. 자바 메모리 구조와 static
 */
public class Section08_Static {
    public static void main(String[] args) {

        // 1~4. [강의: 자바 메모리 구조 / 스택과 힙 영역]
        /* 
         * 메서드 영역: 클래스 정보, static 변수 보관
         * 스택 영역: 지역 변수, 메서드 호출 프레임 (LIFO)
         * 힙 영역: 실제 객체(인스턴스) 보관
         */

        // 5~7. [강의: static 변수]
        /* 
         * static 이 붙으면 인스턴스가 아닌 '클래스'에 소속됩니다. 
         * 여러 객체가 공유하는 변수가 됩니다.
         */
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        System.out.println("생성된 총 객체 수: " + Counter.totalCount); // 클래스명으로 접근!

        // 8~10. [강의: static 메서드]
        /* 객체 생성 없이 클래스 이름으로 바로 호출할 수 있는 공용 메서드 */
        int result = StaticUtils.add(10, 20);
        System.out.println("결과: " + result);
    }
}

class Counter {
    static int totalCount; // 공용 변수
    Counter() {
        totalCount++;
    }
}

class StaticUtils {
    static int add(int a, int b) {
        return a + b;
    }
}
