package gof.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composite Pattern (컴포지트 패턴)
 * 
 * 목적: 객체들의 관계를 트리 구조로 구성하여 부분-전체 계층을 표현합니다. 
 * 사용자가 단일 객체와 복합 객체 모두를 동일하게 다룰 수 있게 합니다.
 */

// 1. 컴포넌트 인터페이스
interface FileSystemNode {
    void print(String structure);
    int getSize();
}

// 2. Leaf 객체 (단일 객체)
class File implements FileSystemNode {
    private final String name;
    private final int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void print(String structure) {
        System.out.println(structure + " 파일: " + name + " (" + size + "KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// 3. Composite 객체 (복합 객체)
class Directory implements FileSystemNode {
    private final String name;
    private final List<FileSystemNode> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public void add(FileSystemNode node) {
        children.add(node);
    }

    @Override
    public void print(String structure) {
        System.out.println(structure + " 디렉토리: " + name);
        for (FileSystemNode node : children) {
            node.print(structure + "  ");
        }
    }

    @Override
    public int getSize() {
        return children.stream().mapToInt(FileSystemNode::getSize).sum();
    }
}

class CompositeMain {
    public static void main(String[] args) {
        Directory root = new Directory("root");
        Directory home = new Directory("home");
        Directory user = new Directory("user");

        File file1 = new File("config.txt", 10);
        File file2 = new File("image.png", 500);
        File file3 = new File("data.csv", 200);

        root.add(home);
        home.add(user);
        user.add(file1);
        user.add(file2);
        root.add(file3);

        root.print("");
        System.out.println("전체 크기: " + root.getSize() + "KB");
    }
}
