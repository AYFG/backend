/**
 * ## 섹션 10. 상속
 */
public class Section10_Inheritance {
    public static void main(String[] args) {

        // 1, 2. [강의: 상속 - 시작 / 관계]
        /* 기존 클래스(부모)의 필드와 메서드를 자식 클래스가 물려받는 것 */
        ElectricCar evoc = new ElectricCar();
        evoc.move();   // 부모(Car)의 메서드 사용
        evoc.charge(); // 자식 고유의 메서드 

        // 3. [강의: 상속과 메모리 구조]
        /* 자식 인스턴스를 생성하면 내부에는 부모 인스턴스도 함께 생성됩니다. (중요!) */

        // 5. [강의: 상속과 메서드 오버라이딩]
        /* 부모의 기능을 자식이 재정의하는 것 */
        GasCar gasCar = new GasCar();
        gasCar.move(); // @Override 된 메서드가 실행됨

        // 7, 8. [강의: super - 부모 참조 / 생성자]
        /* 부모의 필드나 메서드에 접근할 때 super를 사용합니다. */
    }
}

class Car {
    void move() {
        System.out.println("차가 이동합니다.");
    }
}

class ElectricCar extends Car {
    void charge() {
        System.out.println("품질 좋게 충전합니다.");
    }
}

class GasCar extends Car {
    @Override
    void move() {
        System.out.println("가스차가 더 빠르게 이동합니다!");
    }
}
