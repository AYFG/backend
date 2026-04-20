package java_lecture_2;

/**
 * ## 섹션 11. 다형성1
 */
public class Section11_Polymorphism1 {
    public static void main(String[] args) {

        // 1. [강의: 다형성 시작]
        /*
         * 부모 타입의 변수에 자식 객체를 담을 수 있습니다.
         * 이 때 부모 타입으로는 공통된 기능만 바로 사용할 수 있습니다.
         */
        SuperClass poly = new SubClass(); // 다형적 참조
        poly.method(); // 실행되는 것은 자식의 오버라이딩된 메서드입니다.
        // poly.subMethod(); // 자식 고유의 메서드는 부모 타입으로 호출할 수 없습니다.

        // 2~4. [강의: 다형성과 캐스팅 / 다운캐스팅]
        /*
         * 필요할 때 자식 타입으로 형변환(다운캐스팅)하면
         * 자식에만 있는 기능을 사용할 수 있습니다.
         */
        if (poly instanceof SubClass) {
            SubClass sub = (SubClass) poly; // 다운캐스팅
            sub.subMethod();
        }

        // 6. [강의: 다형성과 메서드 오버라이딩]
        /* 오버라이딩 된 메서드가 항상 우선권을 가집니다! (다형성의 핵심) */
    }
}

class SuperClass {
    void method() {
        System.out.println("Super method");
    }
}

class SubClass extends SuperClass {
    void subMethod() {
        System.out.println("Sub unique method");
    }

    @Override
    void method() {
        System.out.println("Sub Override method");
    }
}
