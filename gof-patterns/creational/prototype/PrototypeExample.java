package gof.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Prototype Pattern (프로토타입 패턴)
 * 
 * 목적: 원형(Prototype)이 되는 인스턴스를 사용하여 객체를 생성하며, 
 * 새로운 객체를 생성하는 것보다 기존 객체를 복사(Clone)하는 것이 효율적일 때 사용합니다.
 */

// 1. 프로토타입 인터페이스
abstract class Shape implements Cloneable {
    public String id;
    protected String type;

    abstract void draw();

    public String getType() { return type; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}

// 2. 구체적인 프로토타입
class Rectangle extends Shape {
    public Rectangle() { type = "사각형"; }
    @Override
    void draw() { System.out.println("사각형을 그립니다."); }
}

class Circle extends Shape {
    public Circle() { type = "원"; }
    @Override
    void draw() { System.out.println("원을 그립니다."); }
}

// 3. 프로토타입 매니저
class ShapeCache {
    private static final java.util.Map<String, Shape> shapeMap = new java.util.HashMap<>();

    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }

    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);

        Rectangle rect = new Rectangle();
        rect.setId("2");
        shapeMap.put(rect.getId(), rect);
    }
}

class PrototypeMain {
    public static void main(String[] args) {
        ShapeCache.loadCache();

        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape : " + clonedShape1.getType());

        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape : " + clonedShape2.getType());
    }
}
