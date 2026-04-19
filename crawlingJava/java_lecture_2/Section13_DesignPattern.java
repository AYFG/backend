/**
 * ## 섹션 13. 다형성과 설계 (객체 지향 핵심 원칙)
 */
public class Section13_DesignPattern {
    public static void main(String[] args) {

        // 1. [강의: 좋은 객체 지향 프로그래밍이란?]
        /* 유연하고 변경이 용이한 설계 (다형성을 최대한 활용) */

        // 5. [강의: OCP(Open-Closed Principle) 원칙]
        /* 확장에는 열려 있고, 수정에는 닫혀 있어야 한다. */
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
    public void accel() { System.out.println("K3 가속"); }
}

class Model3Car implements CarInterface {
    @Override
    public void accel() { System.out.println("테슬라 가속"); }
}

class Driver {
    private CarInterface car;
    Driver(CarInterface car) { this.car = car; }
    void drive() { car.accel(); }
}
