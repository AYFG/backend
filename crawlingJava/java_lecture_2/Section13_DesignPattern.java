package java_lecture_2;

/**
 * ## 섹션 13. 다형성과 설계 (객체 지향 핵심 원칙)
 */
public class Section13_DesignPattern {
    public static void main(String[] args) {

        // 1. [강의: 좋은 객체 지향 프로그래밍이란?]
        /*
         * 좋은 설계는 변경에 유연해야 합니다.
         * 다형성을 사용하면 구현을 쉽게 바꿀 수 있습니다.
         */

        // 5. [강의: OCP(Open-Closed Principle) 원칙]
        /*
         * 확장에는 열려 있고, 수정에는 닫혀 있어야 합니다.
         * 새로운 구현이 생겨도 기존 코드를 바꾸지 않아야 합니다.
         */
        CarInterface car = new K3Car(); // 다형성 덕분에 코드 수정 없이 자동차 교체 가능
        Driver driver = new Driver(car);
        driver.drive();
    }
}

interface CarInterface {
    void accel();
}

class K3Car implements CarInterface {
    @Override
    public void accel() {
        System.out.println("K3 가속");
    }
}

class Model3Car implements CarInterface {
    @Override
    public void accel() {
        System.out.println("테슬라 가속");
    }
}

class Driver {
    private CarInterface car;

    Driver(CarInterface car) {
        this.car = car;
    }

    void drive() {
        car.accel();
    }
}
