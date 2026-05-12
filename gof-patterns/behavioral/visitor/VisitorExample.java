package gof.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Visitor Pattern (비지터 패턴)
 * 
 * 목적: 객체 구조를 변경하지 않고 새로운 작업을 객체들에 추가할 수 있게 합니다. 
 * 데이터 구조와 연산을 분리합니다.
 */

// 1. 요소 인터페이스
interface Shape {
    void accept(Visitor visitor);
}

// 2. 구체적인 요소들
class Circle implements Shape {
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
    public String name() { return "원"; }
}

class Rectangle implements Shape {
    @Override
    public void accept(Visitor visitor) { visitor.visit(this); }
    public String name() { return "사각형"; }
}

// 3. 비지터 인터페이스
interface Visitor {
    void visit(Circle circle);
    void visit(Rectangle rectangle);
}

// 4. 구체적인 비지터 (새로운 연산 추가)
class DrawingVisitor implements Visitor {
    @Override
    public void visit(Circle circle) { System.out.println(circle.name() + "을 그립니다."); }
    @Override
    public void visit(Rectangle rectangle) { System.out.println(rectangle.name() + "을 그립니다."); }
}

class ExportVisitor implements Visitor {
    @Override
    public void visit(Circle circle) { System.out.println(circle.name() + " 데이터를 XML로 내보냅니다."); }
    @Override
    public void visit(Rectangle rectangle) { System.out.println(rectangle.name() + " 데이터를 XML로 내보냅니다."); }
}

class VisitorMain {
    public static void main(String[] args) {
        List<Shape> shapes = List.of(new Circle(), new Rectangle());

        System.out.println("--- 그리기 연산 ---");
        Visitor drawer = new DrawingVisitor();
        shapes.forEach(s -> s.accept(drawer));

        System.out.println("\n--- 데이터 내보내기 연산 ---");
        Visitor exporter = new ExportVisitor();
        shapes.forEach(s -> s.accept(exporter));
    }
}
