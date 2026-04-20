package java_lecture_2;

/**
 * ## 섹션 8. 자바 메모리 구조와 static
 */
public class Section08_Static {
    public static void main(String[] args) {

        // 1~4. [강의: 자바 메모리 구조 / 스택과 힙 영역]
        /*
         * 메서드 영역(Method Area): 클래스 정보와 static 멤버가 저장되는 공간입니다.
         * 스택 영역(Stack Area): 메서드 호출 시 생성되는 지역 변수가 저장되는 공간입니다.
         * 힙 영역(Heap Area): new로 만든 객체가 저장되는 공간입니다.
         */

        // 5~7. [강의: static 변수]
        /*
         * static이 붙은 변수는 클래스에 한 번만 만들어집니다.
         * 따라서 모든 인스턴스가 같은 static 변수를 공유합니다.
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
