/**
 * ## 섹션 9. final
 */
public class Section09_Final {
    public static void main(String[] args) {

        // 1. [강의: final 변수와 상수1, 2]
        /* final이 붙으면 값이 할당된 후 변경이 불가능합니다. */
        final int a = 10;
        // a = 20; // 컴파일 에러!

        /* 상수는 static final을 사용하며 대문자로 표기하는 관례가 있습니다. */
        System.out.println("최대 접속 수: " + Constant.MAX_USERS);

        // 2. [강의: final 변수와 참조]
        /* 참조형에 final이 붙으면 '주소값'은 못 바꾸지만, '객체 내부의 값'은 바꿀 수 있습니다. */
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
