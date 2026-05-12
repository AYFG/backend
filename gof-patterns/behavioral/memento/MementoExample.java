package gof.behavioral.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * Memento Pattern (메멘토 패턴)
 * 
 * 목적: 객체의 구현 세부 사항을 노출하지 않으면서 해당 객체의 상태를 저장하고 
 * 나중에 복원할 수 있게 합니다 (실행 취소/Undo 기능 등에 활용).
 */

// 1. 메멘토 (상태를 저장하는 객체)
class EditorMemento {
    private final String content;
    public EditorMemento(String content) { this.content = content; }
    public String getContent() { return content; }
}

// 2. 오리지네이터 (상태를 가지고 있으며 메멘토를 생성/복원하는 객체)
class Editor {
    private String content;

    public void setContent(String content) { this.content = content; }
    public String getContent() { return content; }

    public EditorMemento save() {
        return new EditorMemento(content);
    }

    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
    }
}

// 3. 케어테이커 (메멘토 히스토리를 관리하는 객체)
class History {
    private final List<EditorMemento> states = new ArrayList<>();

    public void push(EditorMemento memento) { states.add(memento); }
    public EditorMemento pop() {
        if (states.isEmpty()) return null;
        return states.remove(states.size() - 1);
    }
}

class MementoMain {
    public static void main(String[] args) {
        Editor editor = new Editor();
        History history = new History();

        editor.setContent("상태 1");
        history.push(editor.save());

        editor.setContent("상태 2");
        history.push(editor.save());

        editor.setContent("현재 상태");
        System.out.println("현재 내용: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("Undo 1: " + editor.getContent());

        editor.restore(history.pop());
        System.out.println("Undo 2: " + editor.getContent());
    }
}
