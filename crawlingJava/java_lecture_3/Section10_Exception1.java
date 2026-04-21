package java_lecture_3;

/**
 * ## 섹션 10. 예외 처리1 - 이론
 */
public class Section10_Exception1 {
    public static void main(String[] args) {
        // 1. [강의: 체크 예외]
        /*
         * 반드시 잡거나(catch) 던져야(throws) 합니다. 안 하면 컴파일 에러.
         */
        try {
            callChecked();
        } catch (MyCheckedException e) {
            System.out.println("체크 예외 처리: " + e.getMessage());
        }

        // 2. [강의: 언체크 예외 (런타임 예외)]
        /*
         * 명시적으로 잡지 않아도 됩니다. (생략 가능)
         */
        try {
            callUnchecked();
        } catch (MyUncheckedException e) {
            System.out.println("언체크 예외 처리: " + e.getMessage());
        }
    }

    static void callChecked() throws MyCheckedException {
        throw new MyCheckedException("체크 예외 발생");
    }

    static void callUnchecked() {
        throw new MyUncheckedException("언체크 예외 발생");
    }
}

class MyCheckedException extends Exception {
    public MyCheckedException(String message) { super(message); }
}

class MyUncheckedException extends RuntimeException {
    public MyUncheckedException(String message) { super(message); }
}
