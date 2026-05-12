package gof.structural.decorator;

/**
 * Decorator Pattern (데코레이터 패턴)
 * 
 * 목적: 객체에 추가적인 요건을 동적으로 첨가하며, 서브클래싱이라는 방법 외에 
 * 기능을 확장할 수 있는 유연한 방법을 제공합니다.
 */

// 1. 컴포넌트 인터페이스
interface Coffee {
    String getDescription();
    double getCost();
}

// 2. 구체적인 컴포넌트
class SimpleCoffee implements Coffee {
    @Override
    public String getDescription() {
        return "일반 커피";
    }

    @Override
    public double getCost() {
        return 2000;
    }
}

// 3. 데코레이터 추상 클래스
abstract class CoffeeDecorator implements Coffee {
    protected final Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }

    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
}

// 4. 구체적인 데코레이터들
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", 우유 추가";
    }

    @Override
    public double getCost() {
        return super.getCost() + 500;
    }
}

class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", 설탕 추가";
    }

    @Override
    public double getCost() {
        return super.getCost() + 200;
    }
}

class DecoratorMain {
    public static void main(String[] args) {
        // 기본 커피
        Coffee myCoffee = new SimpleCoffee();
        System.out.println(myCoffee.getDescription() + " : " + myCoffee.getCost());

        // 우유 추가
        myCoffee = new MilkDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " : " + myCoffee.getCost());

        // 설탕 추가
        myCoffee = new SugarDecorator(myCoffee);
        System.out.println(myCoffee.getDescription() + " : " + myCoffee.getCost());
    }
}
