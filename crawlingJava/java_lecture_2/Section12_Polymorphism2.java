package java_lecture_2;

/**
 * ## 섹션 12. 다형성2 (추상 클래스와 인터페이스)
 */
public class Section12_Polymorphism2 {
    public static void main(String[] args) {

        // 4~5. [강의: 추상 클래스1, 2]
        /*
         * 추상 클래스는 공통 기능을 미리 정의하고,
         * 서브 클래스가 공통 동작을 구현하도록 강제합니다.
         */
        // AbstractAnimal animal = new AbstractAnimal(); // 에러!

        // 6. [강의: 인터페이스]
        /*
         * 인터페이스는 클래스가 반드시 구현해야 할 메서드 목록만 정의합니다.
         * 구현 클래스는 이 규약을 따르게 됩니다.
         */
        Cat cat = new Cat();
        cat.sound();
        cat.move();
    }
}

// 추상 클래스
abstract class AbstractAnimal {
    abstract void sound(); // 구현부가 없는 추상 메서드

    void move() {
        System.out.println("동물이 이동합니다.");
    }
}

// 인터페이스
interface InterfaceAnimal {
    void sound(); // public abstract 생략됨

    void move();
}

class Cat implements InterfaceAnimal {
    @Override
    public void sound() {
        System.out.println("냐옹");
    }

    @Override
    public void move() {
        System.out.println("고양이가 살금살금 걷습니다.");
    }
}
