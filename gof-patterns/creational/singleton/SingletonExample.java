package gof.creational.singleton;

/**
 * Singleton Pattern (싱글톤 패턴)
 * 
 * 목적: 클래스의 인스턴스가 오직 하나만 생성됨을 보장하고, 이에 대한 전역적인 접근점을 제공합니다.
 * Java 17에서는 Enum을 사용한 방식이 가장 안전하고 권장되는 방식 중 하나입니다.
 */
public enum Singleton {
    INSTANCE;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void showMessage() {
        System.out.println("Singleton Instance: " + this.hashCode() + ", Value: " + value);
    }
}

/**
 * 실행 예제 및 설명
 */
class SingletonMain {
    public static void main(String[] args) {
        // 1. 인스턴스 참조 (new 생성 불가)
        Singleton s1 = Singleton.INSTANCE;
        Singleton s2 = Singleton.INSTANCE;

        s1.setValue("Hello Design Patterns!");

        // 2. 두 인스턴스가 동일한지 확인
        s1.showMessage();
        s2.showMessage();

        if (s1 == s2) {
            System.out.println("동일한 인스턴스입니다.");
        }
    }
}
