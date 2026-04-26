import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ## 섹션 6. 컬렉션 프레임워크 - List
 *
 * [학습 내용]
 * 1. 리스트 추상화 - 인터페이스 도입 (다형성 활용)
 * 2. 의존관계 주입 (DI: Dependency Injection)
 * 3. 컴파일 타임 vs 런타임 의존관계
 * 4. 자바가 제공하는 List와 성능 비교
 */
public class Section06_List {
    public static void main(String[] args) {

        // 1. [강의: 리스트 추상화 - 인터페이스 도입]
        /*
         * ArrayList와 LinkedList는 내부 구현은 다르지만 "리스트"라는 공통 기능을 가집니다.
         * 자바의 List 인터페이스를 통해 다형성을 활용하면,
         * 구현체를 바꿔도 나머지 코드는 변경할 필요가 없습니다.
         */

        // 2. [강의: 의존관계 주입 (DI)]
        /*
         * 사용하는 곳에서는 List 인터페이스 타입으로 선언하고,
         * 생성(new) 부분만 원하는 구현체로 교체하면 됩니다.
         * 이것이 "의존관계 주입"의 핵심 원리입니다.
         */
        List<String> list;

        // ArrayList 사용
        list = new ArrayList<>();
        list.add("사과");
        list.add("바나나");
        list.add("체리");
        printList("ArrayList", list);

        // LinkedList로 교체 - 나머지 코드는 동일!
        list = new LinkedList<>();
        list.add("포도");
        list.add("수박");
        list.add("딸기");
        printList("LinkedList", list);

        System.out.println("=========================================");

        // 3. [강의: 컴파일 타임 vs 런타임 의존관계]
        /*
         * 컴파일 타임 의존관계: 코드에 적힌 타입 → List (인터페이스)
         * 런타임 의존관계: 실제 실행 시 사용되는 객체 → ArrayList 또는 LinkedList (구현체)
         *
         * 이렇게 분리하면 구현체를 변경해도 컴파일 에러 없이 바로 교체 가능합니다.
         * → OCP (개방-폐쇄 원칙): 확장에는 열려 있고, 변경에는 닫혀 있다.
         */
        System.out.println("[의존관계]");
        System.out.println("컴파일 타임: List 인터페이스에 의존");
        System.out.println("런타임: 실제로는 ArrayList 또는 LinkedList 객체 사용");

        System.out.println("=========================================");

        // 4. [강의: 자바 리스트의 성능 비교]
        /*
         * | 기능           | ArrayList | LinkedList |
         * |---------------|-----------|------------|
         * | 앞에 추가      | O(n)      | O(1)       |
         * | 끝에 추가      | O(1)      | O(1)*      |
         * | 인덱스 조회    | O(1)      | O(n)       |
         * | 검색(값으로)   | O(n)      | O(n)       |
         *
         * 실무에서는 거의 항상 ArrayList를 사용합니다.
         * 이유: 현대 CPU의 캐시 구조상 연속 메모리(배열)가 훨씬 유리하기 때문입니다.
         * (이전 CS 강의에서 배운 "공간적 지역성(Spatial Locality)"과 동일한 원리!)
         */
        System.out.println("[실무 결론]");
        System.out.println("대부분의 경우 ArrayList를 사용하세요.");
        System.out.println("이유: CPU 캐시의 공간적 지역성(Spatial Locality) 덕분에 ArrayList가 훨씬 빠릅니다.");
    }

    static void printList(String name, List<String> list) {
        System.out.println(name + " 내용:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("  [" + i + "] " + list.get(i));
        }
    }
}
