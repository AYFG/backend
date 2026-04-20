package java_lecture_2;

/**
 * ## 섹션 9. final
 */
public class Section09_Final {
    public static void main(String[] args) {

        // 1. [강의: final 변수와 상수1, 2]
        /*
         * final 변수는 초기화 후 값을 바꿀 수 없습니다.
         * 그래서 변경되지 않아야 하는 값에 사용합니다.
         */
        final int a = 10;
        // a = 20; // 컴파일 에러!

        /*
         * 상수는 보통 static final로 선언하고,
         * 이름을 모두 대문자로 작성하는 것이 관례입니다.
         */
        System.out.println("최대 접속 수: " + Constant.MAX_USERS);

        // 2. [강의: final 변수와 참조]
        /*
         * 참조형에 final이 붙으면 변수에 저장된 주소값을 바꿀 수 없습니다.
         * 하지만, 그 주소가 가리키는 객체 내부의 상태는 변경할 수 있습니다.
         */
        final FinalData data = new FinalData();
        data.value = 100; // 내부 값 변경 가능
        // data = new FinalData(); // 주소값 변경은 불가 에러!
    }
}

class Constant {
    public static final int MAX_USERS = 1000;
}

class FinalData {
    int value;
}
