import java.util.Arrays;

/**
 * ## 섹션 4. 컬렉션 프레임워크 - ArrayList
 *
 * [학습 내용]
 * 1. 배열의 특징 (인덱스, 고정 크기, 데이터 추가/삭제 비용)
 * 2. 빅오(O) 표기법 (알고리즘 성능 측정의 기본)
 * 3. 직접 구현하는 배열 리스트 (동적 배열, 제네릭 적용)
 */
public class Section04_ArrayList {
    public static void main(String[] args) {

        // 1. [강의: 배열의 특징 - 인덱스]
        /*
         * 배열은 메모리에 연속적으로 저장되며, 인덱스를 통해 O(1)로 빠르게 접근할 수 있습니다.
         * 하지만 크기가 고정되어 있어 한 번 생성하면 늘릴 수 없습니다.
         */
        int[] arr = new int[5];
        arr[0] = 10;
        arr[1] = 20;
        arr[2] = 30;
        System.out.println("배열 인덱스 접근: arr[1] = " + arr[1]); // O(1)
        System.out.println("배열 내용: " + Arrays.toString(arr));

        System.out.println("=========================================");

        // 2. [강의: 빅오(O) 표기법]
        /*
         * O(1)   : 상수 시간 - 데이터 양과 무관하게 항상 같은 시간 (예: 인덱스 접근)
         * O(n)   : 선형 시간 - 데이터 양에 비례 (예: 순차 검색)
         * O(log n): 로그 시간 - 절반씩 줄여나감 (예: 이진 탐색)
         * O(n²)  : 제곱 시간 - 이중 반복문 (예: 버블 정렬)
         *
         * 성능 순서: O(1) > O(log n) > O(n) > O(n log n) > O(n²)
         */
        System.out.println("[빅오 표기법 정리]");
        System.out.println("O(1)    : 배열 인덱스 접근");
        System.out.println("O(n)    : 배열 검색 (처음부터 끝까지 순회)");
        System.out.println("O(log n): 이진 탐색 (정렬된 데이터에서 절반씩 줄이기)");

        System.out.println("=========================================");

        // 3. [강의: 직접 구현하는 배열 리스트]
        /*
         * 자바의 ArrayList를 직접 구현하여 내부 동작 원리를 이해합니다.
         * - 데이터 추가 시 배열이 꽉 차면 기존의 1.5배~2배 크기로 새 배열을 만들어 복사합니다 (동적 배열).
         * - 중간 삽입/삭제 시 뒤의 요소들을 전부 이동해야 합니다 -> O(n).
         * - 끝에 추가하는 것은 O(1)로 빠릅니다.
         */
        MyArrayList<String> list = new MyArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("체리");
        System.out.println("MyArrayList 크기: " + list.size());
        System.out.println("인덱스 0: " + list.get(0));
        System.out.println("인덱스 1: " + list.get(1));
        System.out.println("인덱스 2: " + list.get(2));

        // 중간 삽입
        list.add(1, "포도");
        System.out.println("중간 삽입 후 인덱스 1: " + list.get(1)); // 포도

        // 삭제
        list.remove(0); // "사과" 삭제
        System.out.println("삭제 후 인덱스 0: " + list.get(0)); // 포도
        System.out.println("최종 크기: " + list.size());
    }
}

// --- 직접 구현한 배열 리스트 ---

/**
 * ArrayList 내부 동작 원리를 이해하기 위한 직접 구현 클래스
 *
 * [핵심 성능 정리]
 * - 인덱스 조회: O(1) - 배열이므로 바로 접근 가능
 * - 끝에 추가: O(1) - 배열 끝에 넣기만 하면 됨 (grow 제외)
 * - 중간 삽입: O(n) - 뒤의 요소들을 전부 한 칸씩 밀어야 함
 * - 중간 삭제: O(n) - 뒤의 요소들을 전부 한 칸씩 당겨야 함
 */
class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 5;
    private Object[] elementData;
    private int size = 0;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    // 끝에 추가 O(1)
    public void add(E e) {
        if (size == elementData.length) {
            grow(); // 배열이 꽉 차면 크기를 늘림
        }
        elementData[size] = e;
        size++;
    }

    // 중간 삽입 O(n)
    public void add(int index, E e) {
        if (size == elementData.length) {
            grow();
        }
        // index부터 뒤의 요소들을 한 칸씩 뒤로 이동
        for (int i = size; i > index; i--) {
            elementData[i] = elementData[i - 1];
        }
        elementData[index] = e;
        size++;
    }

    // 삭제 O(n)
    public E remove(int index) {
        @SuppressWarnings("unchecked")
        E oldValue = (E) elementData[index];
        // 삭제 후 뒤의 요소들을 한 칸씩 앞으로 이동
        for (int i = index; i < size - 1; i++) {
            elementData[i] = elementData[i + 1];
        }
        elementData[size - 1] = null;
        size--;
        return oldValue;
    }

    // 인덱스 조회 O(1)
    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E) elementData[index];
    }

    public int size() {
        return size;
    }

    // 배열 크기 증가 (동적 배열의 핵심)
    private void grow() {
        int newCapacity = elementData.length * 2; // 2배로 늘림
        elementData = Arrays.copyOf(elementData, newCapacity);
        System.out.println("  [grow] 배열 크기 증가: " + newCapacity);
    }
}
