package gof.behavioral.chain_of_responsibility;

/**
 * Chain of Responsibility Pattern (책임 연쇄 패턴)
 * 
 * 목적: 요청을 처리할 수 있는 객체들이 여러 개 있을 때, 이 객체들을 사슬(Chain)처럼 연결하여 
 * 요청을 처리할 수 있는 객체를 만날 때까지 요청을 전달합니다.
 */

// 1. 핸들러 추상 클래스
abstract class Logger {
    public static int INFO = 1;
    public static int DEBUG = 2;
    public static int ERROR = 3;

    protected int level;
    protected Logger nextLogger;

    public void setNextLogger(Logger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void logMessage(int level, String message) {
        if (this.level <= level) {
            write(message);
        }
        if (nextLogger != null) {
            nextLogger.logMessage(level, message);
        }
    }

    abstract protected void write(String message);
}

// 2. 구체적인 핸들러들
class ConsoleLogger extends Logger {
    public ConsoleLogger(int level) { this.level = level; }
    @Override
    protected void write(String message) {
        System.out.println("Standard Console::Logger: " + message);
    }
}

class ErrorLogger extends Logger {
    public ErrorLogger(int level) { this.level = level; }
    @Override
    protected void write(String message) {
        System.out.println("Error Console::Logger: " + message);
    }
}

class FileLogger extends Logger {
    public FileLogger(int level) { this.level = level; }
    @Override
    protected void write(String message) {
        System.out.println("File::Logger: " + message);
    }
}

class ChainMain {
    public static void main(String[] args) {
        Logger errorLogger = new ErrorLogger(Logger.ERROR);
        Logger fileLogger = new FileLogger(Logger.DEBUG);
        Logger consoleLogger = new ConsoleLogger(Logger.INFO);

        errorLogger.setNextLogger(fileLogger);
        fileLogger.setNextLogger(consoleLogger);

        System.out.println("--- INFO 레벨 요청 ---");
        errorLogger.logMessage(Logger.INFO, "일반 정보입니다.");

        System.out.println("\n--- ERROR 레벨 요청 ---");
        errorLogger.logMessage(Logger.ERROR, "심각한 오류가 발생했습니다.");
    }
}
