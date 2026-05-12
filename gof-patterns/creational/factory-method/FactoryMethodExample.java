package gof.creational.factory_method;

/**
 * Factory Method Pattern (팩토리 메서드 패턴)
 * 
 * 목적: 객체 생성 인터페이스를 정의하지만, 어떤 클래스의 인스턴스를 생성할지는 서브클래스가 결정하도록 합니다.
 */

// 1. 제품 인터페이스
interface Transport {
    void deliver();
}

// 2. 구체적인 제품들
class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("트럭으로 육로 배송을 합니다.");
    }
}

class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("배로 해상 배송을 합니다.");
    }
}

// 3. 크리에이터 (추상 클래스)
abstract class Logistics {
    public void planDelivery() {
        Transport t = createTransport();
        t.deliver();
    }

    // 팩토리 메서드
    abstract Transport createTransport();
}

// 4. 구체적인 크리에이터들
class RoadLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Truck();
    }
}

class SeaLogistics extends Logistics {
    @Override
    Transport createTransport() {
        return new Ship();
    }
}

class FactoryMethodMain {
    public static void main(String[] args) {
        Logistics logistics;

        logistics = new RoadLogistics();
        logistics.planDelivery();

        logistics = new SeaLogistics();
        logistics.planDelivery();
    }
}
