package java_lecture_3;

/**
 * ## 섹션 8. 중첩 클래스, 내부 클래스1
 */
public class Section08_NestedClass1 {
    public static void main(String[] args) {
        // 1. [강의: 정적 중첩 클래스]
        /*
         * 바깥 클래스의 인스턴스 없이도 생성 가능합니다.
         */
        Outer.StaticNested nested = new Outer.StaticNested();
        nested.print();

        // 2. [강의: 내부 클래스]
        /*
         * 바깥 클래스의 인스턴스가 있어야 생성 가능합니다.
         */
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();
        inner.print();
    }
}

class Outer {
    private static String staticField = "바깥 정적 필드";
    private String instanceField = "바깥 인스턴스 필드";

    static class StaticNested {
        void print() {
            System.out.println(staticField); 
        }
    }

    class Inner {
        void print() {
            System.out.println(staticField);
            System.out.println(instanceField); 
        }
    }
}
