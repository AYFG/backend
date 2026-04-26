import java.util.*;

/**
 * ## 섹션 9. 컬렉션 프레임워크 - Set
 *
 * [학습 내용]
 * 1. 자바가 제공하는 Set 구현체: HashSet, LinkedHashSet, TreeSet
 * 2. 각 Set 구현체의 특징과 순서 보장 여부
 * 3. Set 활용 예제
 */
public class Section09_Set {
    public static void main(String[] args) {

        // 1. [강의: HashSet]
        /*
         * 가장 빠른 Set 구현체입니다. O(1)
         * 순서를 보장하지 않습니다.
         * 내부적으로 HashMap을 사용합니다.
         */
        Set<String> hashSet = new HashSet<>();
        hashSet.add("C");
        hashSet.add("A");
        hashSet.add("B");
        hashSet.add("A"); // 중복 무시
        System.out.println("[HashSet] 순서 보장 X: " + hashSet);

        System.out.println("=========================================");

        // 2. [강의: LinkedHashSet]
        /*
         * 입력 순서를 보장하는 Set입니다.
         * 내부적으로 해시 테이블 + 이중 연결 리스트(Doubly Linked List)를 사용합니다.
         * HashSet보다 약간 느리지만 순서가 필요할 때 유용합니다.
         */
        Set<String> linkedHashSet = new LinkedHashSet<>();
        linkedHashSet.add("C");
        linkedHashSet.add("A");
        linkedHashSet.add("B");
        System.out.println("[LinkedHashSet] 입력 순서 보장 O: " + linkedHashSet);

        System.out.println("=========================================");

        // 3. [강의: TreeSet]
        /*
         * 데이터를 자동으로 정렬하는 Set입니다.
         * 내부적으로 이진 탐색 트리(Red-Black Tree)를 사용합니다.
         * 조회/추가/삭제 모두 O(log n)입니다.
         * 정렬이 필요한 경우에만 사용합니다 (HashSet보다 느림).
         */
        Set<String> treeSet = new TreeSet<>();
        treeSet.add("C");
        treeSet.add("A");
        treeSet.add("B");
        System.out.println("[TreeSet] 정렬 순서 보장 O: " + treeSet);

        System.out.println("=========================================");

        // 4. [강의: Set 활용 예제 - 중복 제거]
        List<Integer> numbers = List.of(3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5);
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        System.out.println("[중복 제거 예제]");
        System.out.println("원본 리스트: " + numbers);
        System.out.println("중복 제거 후: " + uniqueNumbers);

        System.out.println("=========================================");

        // 5. [성능 비교 정리]
        /*
         * | 구현체          | 순서   | 성능          | 사용 시기                 |
         * |----------------|--------|--------------|--------------------------|
         * | HashSet        | 보장X  | O(1) 가장 빠름 | 기본 선택 (대부분 이것)     |
         * | LinkedHashSet  | 입력순  | O(1) 약간 느림 | 입력 순서 유지가 필요할 때   |
         * | TreeSet        | 정렬순  | O(log n)     | 정렬이 필요할 때            |
         */
        System.out.println("[실무 결론]");
        System.out.println("기본: HashSet (가장 빠름)");
        System.out.println("순서 필요: LinkedHashSet");
        System.out.println("정렬 필요: TreeSet");
    }
}
