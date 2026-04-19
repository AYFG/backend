/**
 * ## 섹션 12. 다형성2 (추상 클래스와 인터페이스)
 */
public class Section12_Polymorphism2 {
    public static void main(String[] args) {

        // 4~5. [강의: 추상 클래스1, 2]
        /* 실체 없이 부모 역할만 하는 추상적인 클래스 (객체 생성 불가) */
        // AbstractAnimal animal = new AbstractAnimal(); // 에러!

        // 6. [강의: 인터페이스]
        /* 순수 추상 클래스와 흡사하며, 강력한 설계 규약(Interface) 역할을 함 */
        Cat cat = new Cat();
        cat.sound();
        cat.move();
    }
}

// 추상 클래스
abstract class AbstractAnimal {
    abstract void sound(); // 구현부가 없는 추상 메서드
    void move() { System.out.println("동물이 이동합니다."); }
}

// 인터페이스
interface InterfaceAnimal {
    void sound(); // public abstract 생략됨
    void move();
}

class Cat implements InterfaceAnimal {
    @Override
    public void sound() { System.out.println("냐옹"); }
    @Override
    public void move() { System.out.println("고양이가 살금살금 걷습니다."); }
}
