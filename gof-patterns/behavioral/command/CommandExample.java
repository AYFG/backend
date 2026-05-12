package gof.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Command Pattern (커맨드 패턴)
 * 
 * 목적: 요청을 객체의 형태로 캡슐화하여, 요청을 매개변수화하거나 
 * 요청을 큐에 저장, 로깅, 그리고 취소(Undo)할 수 있게 합니다.
 */

// 1. 커맨드 인터페이스
interface Command {
    void execute();
    void undo();
}

// 2. 리시버 (실제 동작을 수행하는 객체)
class Light {
    public void turnOn() { System.out.println("전등이 켜졌습니다."); }
    public void turnOff() { System.out.println("전등이 꺼졌습니다."); }
}

// 3. 구체적인 커맨드
class LightOnCommand implements Command {
    private final Light light;

    public LightOnCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.turnOn(); }

    @Override
    public void undo() { light.turnOff(); }
}

class LightOffCommand implements Command {
    private final Light light;

    public LightOffCommand(Light light) { this.light = light; }

    @Override
    public void execute() { light.turnOff(); }

    @Override
    public void undo() { light.turnOn(); }
}

// 4. 인보커 (요청을 발송하는 객체)
class RemoteControl {
    private final List<Command> history = new ArrayList<>();

    public void submit(Command command) {
        command.execute();
        history.add(command);
    }

    public void pressUndo() {
        if (!history.isEmpty()) {
            Command lastCommand = history.remove(history.size() - 1);
            lastCommand.undo();
        }
    }
}

class CommandMain {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        RemoteControl remote = new RemoteControl();

        Command on = new LightOnCommand(livingRoomLight);
        Command off = new LightOffCommand(livingRoomLight);

        remote.submit(on);
        remote.submit(off);
        
        System.out.println("--- Undo 실행 ---");
        remote.pressUndo();
        remote.pressUndo();
    }
}
