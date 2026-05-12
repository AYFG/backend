package gof.behavioral.template_method;

/**
 * Template Method Pattern (템플릿 메서드 패턴)
 * 
 * 목적: 알고리즘의 구조를 메서드에 정의하고, 하위 클래스에서 알고리즘의 특정 단계들을 
 * 재정의할 수 있게 합니다. 알고리즘의 구조는 유지하면서 특정 단계만 변경 가능합니다.
 */

// 1. 추상 클래스 (템플릿 정의)
abstract class CaffeineBeverage {
    // 템플릿 메서드 (final로 선언하여 구조 변경을 막음)
    public final void prepareRecipe() {
        boilWater();
        brew();
        pourInCup();
        addCondiments();
    }

    abstract void brew();
    abstract void addCondiments();

    void boilWater() {
        System.out.println("물 끓이는 중");
    }

    void pourInCup() {
        System.out.println("컵에 따르는 중");
    }
}

// 2. 구체적인 클래스들
class Coffee extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("필터로 커피 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("설탕과 우유를 추가하는 중");
    }
}

class Tea extends CaffeineBeverage {
    @Override
    void brew() {
        System.out.println("차를 우려내는 중");
    }

    @Override
    void addCondiments() {
        System.out.println("레몬을 추가하는 중");
    }
}

class TemplateMethodMain {
    public static void main(String[] args) {
        System.out.println("--- 차 준비 ---");
        CaffeineBeverage tea = new Tea();
        tea.prepareRecipe();

        System.out.println("\n--- 커피 준비 ---");
        CaffeineBeverage coffee = new Coffee();
        coffee.prepareRecipe();
    }
}
