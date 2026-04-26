import java.util.*;

/**
 * ## 섹션 10. 컬렉션 프레임워크 - Map, Stack, Queue
 *
 * [학습 내용]
 * 1. Map - 키(Key)와 값(Value)의 쌍으로 데이터를 저장
 * 2. Map 구현체: HashMap, LinkedHashMap, TreeMap
 * 3. Stack - LIFO (Last In, First Out) 후입선출
 * 4. Queue - FIFO (First In, First Out) 선입선출
 * 5. Deque - 양쪽 끝에서 삽입/삭제 가능
 */
public class Section10_MapStackQueue {
    public static void main(String[] args) {

        // ===== MAP =====
        // 1. [강의: Map 소개]
        /*
         * Map은 Key-Value 쌍으로 데이터를 저장합니다.
         * Key는 중복 불가, Value는 중복 가능합니다.
         * Key로 Value를 빠르게 조회할 수 있습니다: O(1)
         *
         * 비유: 학번(Key)으로 학생 이름(Value)을 찾는 것과 같습니다.
         */
        Map<String, Integer> studentScores = new HashMap<>();
        studentScores.put("홍길동", 95);
        studentScores.put("김철수", 88);
        studentScores.put("이영희", 92);
        studentScores.put("홍길동", 100); // 같은 키 → 값이 덮어쓰기 됨!

        System.out.println("[Map 기본 사용]");
        System.out.println("홍길동 점수: " + studentScores.get("홍길동")); // 100
        System.out.println("존재 여부: " + studentScores.containsKey("김철수")); // true
        System.out.println("전체 출력: " + studentScores);

        // Map 순회
        System.out.println("\n[Map 순회 - entrySet]");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }

        System.out.println("=========================================");

        // 2. [강의: Map 구현체]
        /*
         * | 구현체          | 순서   | 성능       |
         * |----------------|--------|-----------|
         * | HashMap        | 보장X  | O(1) 가장 빠름 |
         * | LinkedHashMap  | 입력순  | O(1)      |
         * | TreeMap        | 키 정렬 | O(log n)  |
         */
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("banana", "바나나");
        treeMap.put("apple", "사과");
        treeMap.put("cherry", "체리");
        System.out.println("[TreeMap] 키 정렬: " + treeMap);

        System.out.println("=========================================");

        // ===== STACK =====
        // 3. [강의: 스택 자료 구조]
        /*
         * LIFO (Last In, First Out): 마지막에 넣은 것이 먼저 나온다.
         * 비유: 프링글스 통 - 마지막에 넣은 감자칩이 가장 먼저 나옵니다.
         * 활용: 뒤로가기(Undo), 재귀 호출 스택 등
         *
         * 자바에서는 Stack 클래스 대신 Deque를 사용하는 것이 권장됩니다.
         */
        Deque<String> stack = new ArrayDeque<>();
        stack.push("1번 페이지"); // 넣기
        stack.push("2번 페이지");
        stack.push("3번 페이지");

        System.out.println("[Stack - LIFO]");
        System.out.println("맨 위 확인: " + stack.peek());  // 3번 페이지
        System.out.println("꺼내기: " + stack.pop());       // 3번 페이지
        System.out.println("꺼내기: " + stack.pop());       // 2번 페이지
        System.out.println("남은 것: " + stack.peek());      // 1번 페이지

        System.out.println("=========================================");

        // ===== QUEUE =====
        // 4. [강의: 큐 자료 구조]
        /*
         * FIFO (First In, First Out): 먼저 넣은 것이 먼저 나온다.
         * 비유: 줄 서기 - 먼저 줄 선 사람이 먼저 서비스를 받습니다.
         * 활용: 프린터 대기열, 작업 스케줄링, BFS 탐색 등
         */
        Queue<String> queue = new LinkedList<>();
        queue.offer("1번 고객");  // 넣기
        queue.offer("2번 고객");
        queue.offer("3번 고객");

        System.out.println("[Queue - FIFO]");
        System.out.println("다음 고객: " + queue.peek());   // 1번 고객
        System.out.println("처리: " + queue.poll());        // 1번 고객
        System.out.println("처리: " + queue.poll());        // 2번 고객
        System.out.println("남은 고객: " + queue.peek());    // 3번 고객

        System.out.println("=========================================");

        // ===== DEQUE =====
        // 5. [강의: Deque 자료 구조]
        /*
         * Double-Ended Queue: 양쪽 끝에서 삽입/삭제가 가능한 자료 구조
         * Deque 하나로 Stack과 Queue를 모두 구현할 수 있습니다.
         *
         * | 용도    | 넣기         | 빼기        |
         * |--------|-------------|-------------|
         * | Stack  | push (앞)   | pop (앞)    |
         * | Queue  | offer (뒤)  | poll (앞)   |
         */
        System.out.println("[Deque 정리]");
        System.out.println("Stack으로 사용: push/pop");
        System.out.println("Queue로 사용: offer/poll");
        System.out.println("실무에서는 Stack, Queue 대신 Deque(ArrayDeque) 사용을 권장합니다.");
    }
}
