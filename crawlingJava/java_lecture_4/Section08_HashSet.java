import java.util.Objects;

/**
 * ## 섹션 8. 컬렉션 프레임워크 - HashSet
 *
 * [학습 내용]
 * 1. 문자열 해시 코드
 * 2. 자바의 hashCode()와 equals()의 관계
 * 3. 직접 만든 객체를 HashSet에 올바르게 보관하는 방법
 * 4. equals, hashCode를 함께 재정의해야 하는 이유
 */
public class Section08_HashSet {
    public static void main(String[] args) {

        // 1. [강의: 문자열 해시 코드]
        /*
         * 자바의 모든 객체는 hashCode() 메서드를 가지고 있습니다.
         * hashCode()는 객체를 정수(int) 값으로 변환하여
         * 해시 기반 자료구조에서 인덱스로 활용합니다.
         */
        System.out.println("[문자열 해시 코드]");
        System.out.println("\"hello\" hashCode: " + "hello".hashCode());
        System.out.println("\"java\"  hashCode: " + "java".hashCode());
        System.out.println("\"hello\" hashCode: " + "hello".hashCode()); // 같은 값이면 같은 해시코드

        System.out.println("=========================================");

        // 2. [강의: 자바의 hashCode()와 equals()]
        /*
         * [절대 규칙]
         * equals()가 true인 두 객체는 반드시 같은 hashCode()를 반환해야 합니다!
         *
         * 이유: HashSet/HashMap은 먼저 hashCode()로 빠르게 위치를 찾고,
         *       같은 위치에 여러 객체가 있으면 equals()로 정확히 비교합니다.
         *       hashCode가 다르면 아예 다른 위치를 보므로 equals 비교 자체를 안 합니다.
         *
         * 순서: hashCode() 비교 → 같으면 → equals() 비교
         */
        System.out.println("[hashCode와 equals 관계]");
        System.out.println("1단계: hashCode()로 빠르게 위치(버킷) 찾기");
        System.out.println("2단계: 같은 버킷 안에서 equals()로 정확한 비교");

        System.out.println("=========================================");

        // 3. [강의: 직접 만든 객체 보관]
        /*
         * 직접 만든 클래스(Member)를 HashSet에 넣으려면
         * 반드시 equals()와 hashCode()를 함께 재정의해야 합니다.
         * 그렇지 않으면 같은 내용의 객체도 "다른 객체"로 인식합니다.
         */
        java.util.HashSet<Member> set = new java.util.HashSet<>();
        Member m1 = new Member("user1");
        Member m2 = new Member("user1"); // m1과 같은 id

        set.add(m1);
        set.add(m2); // equals + hashCode를 재정의했으므로 중복으로 판단 → 추가 안됨

        System.out.println("[직접 만든 객체 HashSet 보관]");
        System.out.println("m1 == m2 (참조 비교): " + (m1 == m2));         // false (다른 객체)
        System.out.println("m1.equals(m2) (값 비교): " + m1.equals(m2));    // true (같은 id)
        System.out.println("Set 크기: " + set.size());                      // 1 (중복 제거됨)

        System.out.println("=========================================");

        // 4. [강의: equals, hashCode 재정의하지 않으면?]
        /*
         * equals, hashCode를 재정의하지 않으면:
         * - hashCode: 객체의 메모리 주소 기반 → m1과 m2는 다른 해시값
         * - equals: == 비교 (참조 비교) → 항상 false
         * - 결과: "같은 id의 회원"을 "다른 회원"으로 인식 → Set에 중복 저장됨!
         */
        java.util.HashSet<BadMember> badSet = new java.util.HashSet<>();
        badSet.add(new BadMember("user1"));
        badSet.add(new BadMember("user1")); // 재정의 안 했으므로 중복 저장됨!
        System.out.println("[equals/hashCode 미재정의 시]");
        System.out.println("BadMember Set 크기: " + badSet.size()); // 2 (중복 제거 실패!)
    }
}

// --- 보조 클래스 ---

/**
 * equals()와 hashCode()를 올바르게 재정의한 회원 클래스
 */
class Member {
    private String id;

    Member(String id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return Objects.equals(id, member.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() { return "Member{id='" + id + "'}"; }
}

/**
 * equals()와 hashCode()를 재정의하지 않은 회원 클래스 (잘못된 예시)
 */
class BadMember {
    private String id;
    BadMember(String id) { this.id = id; }
}
