/**
 * ## 섹션 5. 컬렉션 프레임워크 - LinkedList
 *
 * [학습 내용]
 * 1. 노드(Node)와 연결 (데이터 + 다음 노드 참조)
 * 2. 직접 구현하는 연결 리스트 (추가, 삭제, 조회)
 * 3. ArrayList vs LinkedList 성능 차이
 */
public class Section05_LinkedList {
    public static void main(String[] args) {

        // 1. [강의: 노드와 연결]
        /*
         * LinkedList는 배열과 달리 메모리에 연속으로 저장되지 않습니다.
         * 각 요소(노드)는 "데이터"와 "다음 노드의 주소(참조)"를 함께 가지고 있어
         * 마치 기차의 객차처럼 서로 연결되어 있습니다.
         *
         * [노드1] -> [노드2] -> [노드3] -> null
         *  data:A      data:B      data:C
         */
        System.out.println("[노드 연결 구조]");
        System.out.println("[A] -> [B] -> [C] -> null");

        System.out.println("=========================================");

        // 2. [강의: 직접 구현하는 연결 리스트]
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("사과");
        list.add("바나나");
        list.add("체리");
        System.out.println("크기: " + list.size());
        System.out.println("인덱스 0: " + list.get(0));
        System.out.println("인덱스 1: " + list.get(1));
        System.out.println("인덱스 2: " + list.get(2));

        // 앞에 삽입 (LinkedList의 강점: O(1))
        list.addFirst("포도");
        System.out.println("앞에 추가 후 인덱스 0: " + list.get(0)); // 포도

        // 삭제
        list.remove(1); // "사과" 삭제
        System.out.println("삭제 후 인덱스 1: " + list.get(1)); // 바나나
        System.out.println("최종 크기: " + list.size());

        System.out.println("=========================================");

        // 3. [강의: ArrayList vs LinkedList 성능 비교 정리]
        /*
         * | 기능         | ArrayList | LinkedList |
         * |-------------|-----------|------------|
         * | 인덱스 조회  | O(1) 빠름  | O(n) 느림   |
         * | 앞에 추가    | O(n) 느림  | O(1) 빠름   |
         * | 끝에 추가    | O(1) 빠름  | O(n) 느림   |
         * | 중간 삽입    | O(n)      | O(n)       |
         *
         * 결론: 대부분의 경우 ArrayList가 유리합니다.
         * LinkedList가 유리한 경우는 앞쪽에 빈번하게 삽입/삭제가 발생할 때입니다.
         */
        System.out.println("[성능 비교 요약]");
        System.out.println("조회가 많으면 -> ArrayList");
        System.out.println("앞쪽 삽입/삭제가 많으면 -> LinkedList");
    }
}

// --- 직접 구현한 연결 리스트 ---

/**
 * 연결 리스트의 내부 노드
 * 각 노드는 자신의 데이터와 다음 노드의 참조를 가집니다.
 */
class Node<E> {
    E data;
    Node<E> next;

    Node(E data) {
        this.data = data;
    }
}

/**
 * LinkedList 직접 구현
 *
 * [핵심 성능 정리]
 * - 인덱스 조회: O(n) - 처음부터 하나씩 따라가야 함
 * - 앞에 추가: O(1) - head만 바꾸면 됨
 * - 끝에 추가: O(n) - 끝까지 따라가야 함
 * - 중간 삽입/삭제: O(n) - 해당 위치까지 따라간 뒤 참조만 변경
 */
class MyLinkedList<E> {
    private Node<E> head;
    private int size = 0;

    // 끝에 추가
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // 앞에 추가 O(1) - LinkedList의 강점!
    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = head; // 새 노드가 기존 head를 가리킴
        head = newNode;      // head를 새 노드로 변경
        size++;
    }

    // 삭제
    public E remove(int index) {
        if (index == 0) {
            E removed = head.data;
            head = head.next;
            size--;
            return removed;
        }
        Node<E> prev = getNode(index - 1);
        E removed = prev.next.data;
        prev.next = prev.next.next; // 연결 끊고 다음 노드와 연결
        size--;
        return removed;
    }

    // 인덱스 조회 O(n)
    public E get(int index) {
        return getNode(index).data;
    }

    private Node<E> getNode(int index) {
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    public int size() { return size; }
}
