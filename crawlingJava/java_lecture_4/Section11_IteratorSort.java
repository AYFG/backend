import java.util.*;

/**
 * ## 섹션 11. 컬렉션 프레임워크 - 순회, 정렬, 전체 정리
 *
 * [학습 내용]
 * 1. Iterable / Iterator 인터페이스 (순회의 원리)
 * 2. 향상된 for문 (for-each)
 * 3. Comparable / Comparator (정렬 기준 정의)
 * 4. 컬렉션 유틸리티 (Collections 클래스)
 * 5. 컬렉션 프레임워크 전체 정리
 */
public class Section11_IteratorSort {
    public static void main(String[] args) {

        // 1. [강의: Iterable, Iterator]
        /*
         * Iterable: "나는 순회할 수 있는 객체야"라는 약속 (인터페이스)
         * Iterator: 실제로 순회하는 도구 (커서처럼 하나씩 이동)
         *
         * Iterable을 구현하면 향상된 for문(for-each)을 사용할 수 있습니다.
         *
         * 비유: Iterable = "이 책은 읽을 수 있다"는 표시
         *       Iterator = "책갈피" (어디까지 읽었는지 추적)
         */
        List<String> fruits = List.of("사과", "바나나", "체리");

        // Iterator를 직접 사용하는 방법
        System.out.println("[Iterator 직접 사용]");
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            String fruit = iterator.next();
            System.out.println("  " + fruit);
        }

        System.out.println("=========================================");

        // 2. [강의: 향상된 for문 (for-each)]
        /*
         * Iterable을 구현한 객체는 아래처럼 간결하게 순회 가능합니다.
         * 내부적으로 Iterator를 자동으로 사용합니다.
         */
        System.out.println("[향상된 for문]");
        for (String fruit : fruits) {
            System.out.println("  " + fruit);
        }

        System.out.println("=========================================");

        // 3. [강의: Comparable - 기본 정렬 기준 정의]
        /*
         * Comparable<T> 인터페이스를 구현하면 "자연 순서(Natural Order)"를 정의할 수 있습니다.
         * compareTo() 메서드에서:
         *   - 음수 반환: 현재 객체가 더 앞 (작다)
         *   - 0 반환: 같다
         *   - 양수 반환: 현재 객체가 더 뒤 (크다)
         */
        List<Student> students = new ArrayList<>();
        students.add(new Student("홍길동", 90));
        students.add(new Student("김철수", 75));
        students.add(new Student("이영희", 95));

        Collections.sort(students); // Comparable 기준으로 정렬
        System.out.println("[Comparable - 점수 오름차순 정렬]");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        System.out.println("=========================================");

        // 4. [강의: Comparator - 별도의 정렬 기준 정의]
        /*
         * Comparator는 Comparable과 달리 클래스 외부에서 정렬 기준을 정의합니다.
         * 하나의 클래스에 대해 여러 가지 정렬 기준을 만들 수 있습니다.
         */
        students.sort(Comparator.comparing(Student::getName)); // 이름순 정렬
        System.out.println("[Comparator - 이름 오름차순 정렬]");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        students.sort(Comparator.comparing(Student::getScore).reversed()); // 점수 내림차순
        System.out.println("[Comparator - 점수 내림차순 정렬]");
        for (Student s : students) {
            System.out.println("  " + s);
        }

        System.out.println("=========================================");

        // 5. [강의: 컬렉션 유틸리티 - Collections 클래스]
        /*
         * Collections 클래스는 컬렉션을 다루는 다양한 유틸리티 메서드를 제공합니다.
         */
        List<Integer> numbers = new ArrayList<>(List.of(3, 1, 4, 1, 5, 9));

        System.out.println("[Collections 유틸리티]");
        System.out.println("원본: " + numbers);

        Collections.sort(numbers);
        System.out.println("정렬: " + numbers);

        Collections.reverse(numbers);
        System.out.println("역순: " + numbers);

        Collections.shuffle(numbers);
        System.out.println("셔플: " + numbers);

        System.out.println("최솟값: " + Collections.min(numbers));
        System.out.println("최댓값: " + Collections.max(numbers));

        // 불변 리스트 (수정 불가능한 리스트)
        List<String> immutableList = Collections.unmodifiableList(List.of("A", "B", "C"));
        System.out.println("불변 리스트: " + immutableList);
        // immutableList.add("D"); // UnsupportedOperationException 발생!

        System.out.println("=========================================");

        // 6. [컬렉션 프레임워크 전체 정리]
        /*
         * [Collection 인터페이스]
         * ├── List (순서 O, 중복 O)
         * │   ├── ArrayList  ← 기본 선택! (대부분 이것)
         * │   └── LinkedList ← 앞쪽 삽입/삭제가 빈번할 때
         * │
         * ├── Set (순서 X, 중복 X)
         * │   ├── HashSet       ← 기본 선택!
         * │   ├── LinkedHashSet ← 입력 순서 유지
         * │   └── TreeSet       ← 정렬 필요 시
         * │
         * └── Queue / Deque
         *     ├── ArrayDeque ← Stack/Queue 용도 모두 사용
         *     └── LinkedList ← Queue 용도
         *
         * [Map 인터페이스] (Collection 상속 아님, 별도 계층)
         * ├── HashMap       ← 기본 선택!
         * ├── LinkedHashMap ← 입력 순서 유지
         * └── TreeMap       ← 키 정렬 필요 시
         */
        System.out.println("[컬렉션 프레임워크 전체 정리]");
        System.out.println("List → ArrayList (기본)");
        System.out.println("Set  → HashSet (기본)");
        System.out.println("Map  → HashMap (기본)");
        System.out.println("Stack/Queue → ArrayDeque (기본)");
    }
}

// --- 보조 클래스 ---

/**
 * Comparable을 구현한 학생 클래스
 * 점수 기준 오름차순이 "자연 순서"로 정의됨
 */
class Student implements Comparable<Student> {
    private String name;
    private int score;

    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() { return name; }
    public int getScore() { return score; }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.score, other.score); // 점수 오름차순
    }

    @Override
    public String toString() {
        return name + " (" + score + "점)";
    }
}
