package gof.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight Pattern (플라이웨이트 패턴)
 * 
 * 목적: 공유를 통해 대량의 소립 객체들을 효과적으로 지원합니다. 
 * 메모리 사용량을 줄이기 위해 유사한 객체들 간에 데이터를 공유합니다.
 */

// 1. 플라이웨이트 인터페이스
interface TreeType {
    void draw(int x, int y);
}

// 2. 구체적인 플라이웨이트 (공유될 데이터 포함)
class ConcreteTreeType implements TreeType {
    private final String name;
    private final String color;
    private final String texture;

    public ConcreteTreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    @Override
    public void draw(int x, int y) {
        System.out.println(name + " 나무 [" + color + ", " + texture + "] 를 (" + x + "," + y + ") 위치에 그립니다.");
    }
}

// 3. 플라이웨이트 팩토리
class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + color + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new ConcreteTreeType(name, color, texture));
            System.out.println("새로운 나무 타입 생성: " + name);
        }
        return treeTypes.get(key);
    }
}

// 4. 컨텍스트 (외적인 상태를 가짐)
class Tree {
    private final int x;
    private final int y;
    private final TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}

class FlyweightMain {
    public static void main(String[] args) {
        TreeType oak = TreeFactory.getTreeType("참나무", "녹색", "거칠음");
        TreeType pine = TreeFactory.getTreeType("소나무", "진한 녹색", "부드러움");

        // 수천 개의 나무를 생성해도 메모리에는 oak, pine 객체 하나씩만 존재
        Tree[] forest = {
            new Tree(1, 2, oak),
            new Tree(3, 4, oak),
            new Tree(5, 6, pine),
            new Tree(7, 8, pine),
            new Tree(9, 10, oak)
        };

        for (Tree tree : forest) {
            tree.draw();
        }
    }
}
