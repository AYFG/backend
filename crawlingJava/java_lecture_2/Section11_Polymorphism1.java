/**
 * ## 섹션 11. 다형성1
 */
public class Section11_Polymorphism1 {
    public static void main(String[] args) {

        // 1. [강의: 다형성 시작]
        /* 부모 타입의 변수가 자식 객체를 참조할 수 있는 능력 */
        SuperClass poly = new SubClass(); // 다형적 참조
        poly.method(); // 부모 메서드는 호출 가능
        // poly.subMethod(); // 자식 고유의 메서드는 직접 호출 불가 (컴파일 에러)

        // 2~4. [강의: 다형성과 캐스팅 / 다운캐스팅]
        /* 일시적으로 자식 타입으로 변환하여 자식 기능을 사용할 수 있음 */
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
