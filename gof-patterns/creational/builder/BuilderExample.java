package gof.creational.builder;

/**
 * Builder Pattern (빌더 패턴)
 * 
 * 목적: 복잡한 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 합니다.
 * Java 17에서는 불변 객체를 만들기 위해 자주 사용됩니다.
 */

public class Computer {
    // 필수 매개변수
    private final String cpu;
    private final String ram;

    // 선택 매개변수
    private final int storage;
    private final boolean hasGraphicsCard;

    // Private 생성자로 외부 생성을 막고 빌더를 통해서만 생성
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGraphicsCard = builder.hasGraphicsCard;
    }

    @Override
    public String toString() {
        return "Computer [CPU=" + cpu + ", RAM=" + ram + ", Storage=" + storage + "GB, GPU=" + hasGraphicsCard + "]";
    }

    // 정적 내부 빌더 클래스
    public static class Builder {
        private final String cpu;
        private final String ram;
        private int storage = 256; // 기본값
        private boolean hasGraphicsCard = false;

        public Builder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public Builder storage(int storage) {
            this.storage = storage;
            return this;
        }

        public Builder hasGraphicsCard(boolean hasGraphicsCard) {
            this.hasGraphicsCard = hasGraphicsCard;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

class BuilderMain {
    public static void main(String[] args) {
        // 체이닝을 통한 객체 생성
        Computer myPc = new Computer.Builder("Intel i9", "32GB")
                .storage(1024)
                .hasGraphicsCard(true)
                .build();

        System.out.println(myPc);
    }
}
