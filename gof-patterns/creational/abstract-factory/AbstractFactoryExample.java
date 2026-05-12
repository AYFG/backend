package gof.creational.abstract_factory;

/**
 * Abstract Factory Pattern (추상 팩토리 패턴)
 * 
 * 목적: 상세 클래스를 지정하지 않고도 서로 관련성이 있거나 독립적인 여러 객체의 
 * 군(Family)을 생성하기 위한 인터페이스를 제공합니다.
 */

// 1. 추상 제품들
interface Button { void render(); }
interface Checkbox { void render(); }

// 2. 구체적인 제품군 (Windows)
class WindowsButton implements Button {
    public void render() { System.out.println("Windows 스타일 버튼 렌더링"); }
}
class WindowsCheckbox implements Checkbox {
    public void render() { System.out.println("Windows 스타일 체크박스 렌더링"); }
}

// 3. 구체적인 제품군 (MacOS)
class MacOSButton implements Button {
    public void render() { System.out.println("MacOS 스타일 버튼 렌더링"); }
}
class MacOSCheckbox implements Checkbox {
    public void render() { System.out.println("MacOS 스타일 체크박스 렌더링"); }
}

// 4. 추상 팩토리 인터페이스
interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// 5. 구체적인 팩토리들
class WindowsFactory implements GUIFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}

class MacOSFactory implements GUIFactory {
    public Button createButton() { return new MacOSButton(); }
    public Checkbox createCheckbox() { return new MacOSCheckbox(); }
}

class AbstractFactoryMain {
    public static void main(String[] args) {
        GUIFactory factory;
        
        // OS 설정에 따라 팩토리 선택 (가정)
        String os = "mac"; 
        
        if (os.equals("windows")) {
            factory = new WindowsFactory();
        } else {
            factory = new MacOSFactory();
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }
}
