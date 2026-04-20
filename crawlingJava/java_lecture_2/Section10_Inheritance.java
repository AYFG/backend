package java_lecture_2;

/**
 * ## 섹션 10. 상속
 */
public class Section10_Inheritance {
    public static void main(String[] args) {

        // 1, 2. [강의: 상속 - 시작 / 관계]
        /*
         * 부모 클래스가 가진 필드와 메서드를 자식 클래스가 물려받습니다.
         * 이를 통해 코드 재사용과 계층 구조를 만들 수 있습니다.
         */
        ElectricCar evoc = new ElectricCar();
        evoc.move(); // 부모(Car)의 메서드 사용
        evoc.charge(); // 자식 고유의 메서드

        // 3. [강의: 상속과 메모리 구조]
        /*
         * 자식 객체에는 부모 객체 부분도 함께 포함됩니다.
         * 그래서 부모의 필드와 메서드를 사용할 수 있습니다.
         */

        // 5. [강의: 상속과 메서드 오버라이딩]
        /*
         * 자식 클래스가 부모 메서드를 재정의하면,
         * 자식 객체에서 재정의된 메서드가 실행됩니다.
         */
        GasCar gasCar = new GasCar();
        gasCar.move(); // @Override 된 메서드가 실행됨
        gasCar.parentMove(); // super로 부모 메서드 호출

        // 7, 8. [강의: super - 부모 참조 / 생성자]
        /* 부모의 필드나 메서드에 접근할 때 super를 사용합니다. */
    }
}

class Car {
    Car() {
        System.out.println("Car 생성자 호출");
    }

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
    GasCar() {
        super();
    }

    @Override
    void move() {
        System.out.println("가스차가 더 빠르게 이동합니다!");
    }

    void parentMove() {
        super.move();
    }
}
