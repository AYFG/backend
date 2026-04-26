/**
 * ## 섹션 7. 컬렉션 프레임워크 - 해시(Hash)
 *
 * [학습 내용]
 * 1. List vs Set (중복 허용 여부)
 * 2. 해시 알고리즘의 원리 (인덱스, 나머지 연산, 해시 충돌)
 * 3. 직접 구현하는 해시 기반 Set
 */
public class Section07_Hash {
    public static void main(String[] args) {

        // 1. [강의: 리스트(List) vs 세트(Set)]
        /*
         * List: 순서가 있고, 중복을 허용합니다. [1, 2, 2, 3]
         * Set : 순서가 없고, 중복을 허용하지 않습니다. {1, 2, 3}
         *
         * Set에서 값이 있는지 찾으려면 데이터를 하나하나 비교해야 할까요? -> O(n)
         * 해시 알고리즘을 사용하면 O(1)에 찾을 수 있습니다!
         */
        System.out.println("[List vs Set]");
        System.out.println("List: 순서 O, 중복 O → [1, 2, 2, 3]");
        System.out.println("Set : 순서 X, 중복 X → {1, 2, 3}");

        System.out.println("=========================================");

        // 2. [강의: 해시 알고리즘 - 나머지 연산]
        /*
         * 핵심 아이디어: 데이터를 배열의 인덱스로 변환하면 O(1)에 접근 가능!
         *
         * 방법: 데이터 % 배열크기 = 인덱스
         * 예시: capacity = 10인 배열에서
         *   - 값 14 → 14 % 10 = 4번 인덱스에 저장
         *   - 값 99 → 99 % 10 = 9번 인덱스에 저장
         *   - 값 9  → 9 % 10  = 9번 인덱스 → 충돌!
         */
        int capacity = 10;
        int[] hashTable = new int[capacity];

        int value1 = 14;
        int value2 = 99;
        int index1 = value1 % capacity;
        int index2 = value2 % capacity;

        hashTable[index1] = value1;
        hashTable[index2] = value2;

        System.out.println("[해시 알고리즘 - 나머지 연산]");
        System.out.println(value1 + " → 인덱스 " + index1);
        System.out.println(value2 + " → 인덱스 " + index2);

        System.out.println("=========================================");

        // 3. [강의: 해시 충돌]
        /*
         * 서로 다른 값이 같은 인덱스에 배정되는 상황을 "해시 충돌"이라고 합니다.
         * 예: 9 % 10 = 9, 99 % 10 = 9 → 둘 다 인덱스 9!
         *
         * 해결 방법: 같은 인덱스에 여러 데이터를 연결 리스트(LinkedList)로 저장합니다.
         * 이를 "체이닝(Chaining)"이라고 합니다.
         *
         * [0] -> []
         * [1] -> []
         * ...
         * [4] -> [14]
         * ...
         * [9] -> [99] -> [9]  ← 체이닝으로 연결!
         */
        System.out.println("[해시 충돌 예시]");
        System.out.println("9 % 10 = " + (9 % capacity) + " (충돌!)");
        System.out.println("99 % 10 = " + (99 % capacity) + " (충돌!)");
        System.out.println("→ 같은 인덱스에 체이닝(LinkedList)으로 연결하여 해결");

        System.out.println("=========================================");

        // 4. [직접 구현한 Set으로 확인]
        MyHashSetV0 set = new MyHashSetV0(10);
        set.add(1);
        set.add(5);
        set.add(11); // 11 % 10 = 1 → 1과 충돌!
        set.add(5);  // 중복이므로 추가 안됨

        System.out.println("[MyHashSetV0 테스트]");
        System.out.println("1 포함? " + set.contains(1));  // true
        System.out.println("5 포함? " + set.contains(5));  // true
        System.out.println("11 포함? " + set.contains(11)); // true
        System.out.println("99 포함? " + set.contains(99)); // false
    }
}

// --- 직접 구현한 해시 기반 Set (기초 버전) ---

/**
 * 해시 알고리즘의 원리를 이해하기 위한 가장 단순한 Set 구현
 * - 내부에 LinkedList 배열을 사용하여 해시 충돌을 체이닝으로 해결
 */
class MyHashSetV0 {
    private java.util.LinkedList<Integer>[] buckets;
    private int capacity;

    @SuppressWarnings("unchecked")
    MyHashSetV0(int capacity) {
        this.capacity = capacity;
        buckets = new java.util.LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new java.util.LinkedList<>();
        }
    }

    // 해시 함수: 나머지 연산으로 인덱스 결정
    private int hash(int value) {
        return value % capacity;
    }

    // 추가 (중복 방지)
    public void add(int value) {
        int index = hash(value);
        if (!buckets[index].contains(value)) {
            buckets[index].add(value);
        }
    }

    // 포함 여부 확인 O(1) (충돌이 없을 때)
    public boolean contains(int value) {
        int index = hash(value);
        return buckets[index].contains(value);
    }
}
