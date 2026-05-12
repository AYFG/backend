package gof.structural.proxy;

/**
 * Proxy Pattern (프록시 패턴)
 * 
 * 목적: 다른 객체에 대한 접근을 제어하기 위한 대리자 또는 자리채움자 역할을 하는 객체를 제공합니다.
 */

// 1. 인터페이스
interface Image {
    void display();
}

// 2. 실제 객체 (Real Subject)
class RealImage implements Image {
    private final String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println(fileName + " 로딩 중... (시간이 오래 걸리는 작업)");
    }

    @Override
    public void display() {
        System.out.println(fileName + " 표시 중");
    }
}

// 3. 프록시 객체 (Proxy)
class ProxyImage implements Image {
    private RealImage realImage;
    private final String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        // 지연 로딩 (Lazy Loading) 구현
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

class ProxyMain {
    public static void main(String[] args) {
        Image image = new ProxyImage("high_res_photo.jpg");

        // 첫 번째 호출: 실제 객체가 생성되고 로딩됨
        System.out.println("--- 첫 번째 출력 ---");
        image.display();

        // 두 번째 호출: 이미 생성된 객체를 사용함
        System.out.println("\n--- 두 번째 출력 ---");
        image.display();
    }
}
