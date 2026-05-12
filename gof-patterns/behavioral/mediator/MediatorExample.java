package gof.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * Mediator Pattern (중재자 패턴)
 * 
 * 목적: 객체들 간의 복잡한 상호작용을 캡슐화하여 하나의 중재자 객체에 위임합니다. 
 * 객체 사이의 의존성을 줄여 결합도를 낮춥니다.
 */

// 1. 중재자 인터페이스
interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}

// 2. 동료 클래스 (Colleague)
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator med, String name) {
        this.mediator = med;
        this.name = name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg);
}

// 3. 구체적인 중재자
class ChatRoom implements ChatMediator {
    private final List<User> users = new ArrayList<>();

    @Override
    public void addUser(User user) { users.add(user); }

    @Override
    public void sendMessage(String msg, User sender) {
        for (User u : users) {
            // 보낸 사람 제외하고 모두에게 메시지 전달
            if (u != sender) {
                u.receive(msg);
            }
        }
    }
}

// 4. 구체적인 동료 클래스
class ChatUser extends User {
    public ChatUser(ChatMediator med, String name) { super(med, name); }

    @Override
    public void send(String msg) {
        System.out.println(this.name + " -> 보내는 중: " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.name + " <- 수신 완료: " + msg);
    }
}

class MediatorMain {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User user1 = new ChatUser(chatRoom, "철수");
        User user2 = new ChatUser(chatRoom, "영희");
        User user3 = new ChatUser(chatRoom, "길동");

        chatRoom.addUser(user1);
        chatRoom.addUser(user2);
        chatRoom.addUser(user3);

        user1.send("안녕하세요!");
    }
}
