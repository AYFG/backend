package gof.structural.bridge;

/**
 * Bridge Pattern (브릿지 패턴)
 * 
 * 목적: 구현부에서 추상층을 분리하여 각자 독립적으로 변형할 수 있게 합니다.
 * 상속 대신 합성을 사용하여 복잡성을 줄입니다.
 */

// 1. 구현부 인터페이스 (Implementor)
interface Color {
    void applyColor();
}

// 2. 구체적인 구현부들
class RedColor implements Color {
    public void applyColor() { System.out.println("빨간색 적용"); }
}

class BlueColor implements Color {
    public void applyColor() { System.out.println("파란색 적용"); }
}

// 3. 추상층 (Abstraction)
abstract class Shape {
    protected Color color; // Bridge

    protected Shape(Color color) {
        this.color = color;
    }

    abstract void draw();
}

// 4. 확장된 추상층
class Circle extends Shape {
    public Circle(Color color) { super(color); }

    @Override
    void draw() {
        System.out.print("원 그리기 - ");
        color.applyColor();
    }
}

class Square extends Shape {
    public Square(Color color) { super(color); }

    @Override
    void draw() {
        System.out.print("정사각형 그리기 - ");
        color.applyColor();
    }
}

class BridgeMain {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new RedColor());
        Shape blueSquare = new Square(new BlueColor());

        redCircle.draw();
        blueSquare.draw();
    }
}
