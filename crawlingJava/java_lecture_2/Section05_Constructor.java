
/**
 * ## 섹션 5. 생성자
 */
public class Section05_Constructor {
    public static void main(String[] args) {

        // 1. [강의: 생성자 - 필요한 이유]
        /*
         * 객체를 만들고 나면, 필요한 값이 모두 들어가 있는 상태가 되도록 초기화해야 합니다.
         * 이 때 생성자가 유용합니다.
         */

        // 2. [강의: this]
        /*
         * 멤버 변수와 생성자 매개변수 이름이 같을 때,
         * this를 사용하면 멤버 변수를 명확히 참조할 수 있습니다.
         */

        // 3. [강의: 생성자 - 도입]
        MemberInit member = new MemberInit("user1", 20);

        // 4. [강의: 기본 생성자]
        /*
         * 클래스에 생성자를 하나도 정의하지 않으면,
         * 자바가 자동으로 기본 생성자(매개변수 없는 생성자)를 만들어 줍니다.
         */

        // 5. [강의: 생성자 - 오버로딩과 this()]
        /*
         * 생성자는 여러 개를 정의할 수 있으며,
         * this()로 다른 생성자를 호출해 중복 코드를 줄일 수 있습니다.
         */
        MemberInit member2 = new MemberInit("user2"); // 나이는 기본값 15로 설정됨
    }
}

class MemberInit {
    String name;
    int age;

    // 생성자 오버로딩
    MemberInit(String name) {
        this(name, 15); // 자기 자신의 다른 생성자 호출 (반드시 첫 줄에!)
    }

    MemberInit(String name, int age) {
        this.name = name; // this.name 은 멤버 변수, name 은 매개변수
        this.age = age;
        System.out.println("생성자 호출: " + name + ", " + age);
    }

}
