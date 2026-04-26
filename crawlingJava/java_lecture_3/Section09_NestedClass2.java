
/**
 * ## 섹션 9. 중첩 클래스, 내부 클래스2
 */
public class Section09_NestedClass2 {
    public static void main(String[] args) {
        // 1. [강의: 지역 클래스]
        /*
         * 메서드 내부에서 정의되는 클래스입니다.
         * 접근하려는 지역 변수는 사실상 final(변경 불가)이어야 합니다.
         */
        int localVar = 100;
        class LocalPrinter {
            void print() {
                System.out.println("localVar = " + localVar);
            }
        }
        LocalPrinter printer = new LocalPrinter();
        printer.print();

        // 2. [강의: 익명 클래스]
        /*
         * 이름이 없는 클래스로, 인터페이스나 클래스를 상속받아 즉석에서 구현합니다.
         */
        Greeting koreanGreeting = new Greeting() {
            @Override
            public void greet() {
                System.out.println("안녕하세요");
            }
        };
        koreanGreeting.greet();
    }
}

interface Greeting {
    void greet();
}
