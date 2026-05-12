package gof.structural.adapter;

/**
 * Adapter Pattern (어댑터 패턴)
 * 
 * 목적: 클래스의 인터페이스를 사용자가 기대하는 다른 인터페이스로 변환합니다.
 * 호환되지 않는 인터페이스 때문에 함께 동작할 수 없는 클래스들이 함께 작동하도록 해줍니다.
 */

// 1. 타겟 인터페이스 (클라이언트가 기대하는 인터페이스)
interface ModernLogger {
    void log(String message);
}

// 2. 어댑티 (Adaptée, 호환되지 않는 기존 클래스)
class LegacySystem {
    public void oldLog(String msg, int level) {
        System.out.println("[Legacy Log] Level " + level + ": " + msg);
    }
}

// 3. 어댑터 클래스 (LegacySystem을 ModernLogger 인터페이스에 맞춤)
class LoggerAdapter implements ModernLogger {
    private final LegacySystem legacySystem;

    public LoggerAdapter(LegacySystem legacySystem) {
        this.legacySystem = legacySystem;
    }

    @Override
    public void log(String message) {
        // 기존 시스템의 메서드를 호출하되, 인터페이스에 맞게 변환
        legacySystem.oldLog(message, 1);
    }
}

/**
 * 실행 예제
 */
public class AdapterExample {
    public static void main(String[] args) {
        // 클라이언트는 ModernLogger 인터페이스만 알고 있음
        LegacySystem legacy = new LegacySystem();
        ModernLogger logger = new LoggerAdapter(legacy);

        logger.log("어댑터 패턴을 적용한 로그입니다.");
    }
}
