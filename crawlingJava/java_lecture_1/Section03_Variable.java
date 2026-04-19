/**
 * ## 섹션 3. 변수
 */
public class Section03_Variable {
    public static void main(String[] args) {

        // 1. [강의: 변수 시작]
        int a; // 변수 a 선언 (정수 보관)
        a = 10;
        System.out.println(a);

        // 2. [강의: 변수 값 변경]
        a = 20; // 값 변경 (10 -> 20)
        System.out.println(a);

        // 3. [강의: 변수 선언과 초기화]
        int b = 30; // 선언과 동시 초기화
        int c, d;   // 여러 변수 동시 선언
        c = 40; d = 50;
        System.out.println(b);
        System.out.println(c);

        // 4. [강의: 변수 타입1]
        int i = 100;         // 정수
        double dd = 10.5;    // 실수
        boolean bb = true;   // 불리언 (true, false)
        char ch = 'A';       // 문자 하나 (작은 따옴표)
        String s = "Hello";  // 문자열 (큰 따옴표)

        // 5. [강의: 변수 타입2]
        /* 정수 타입: byte, short, int, long (메모리 크기 순) */
        long longVal = 10000000000L; // long은 끝에 L을 붙임 (int 범위를 넘을 때 필요)
        /* 실수 타입: float, double */
        float floatVal = 10.5f;      // float은 끝에 f를 붙임

        // 6. [강의: 변수 명명 규칙]
        /* 
         * 규칙: 숫자로 시작 불가, 공백 불가, 자바 예약어 불가.
         * 관례: camelCase (userName), 변수명은 의미 있게.
         */

        // 7. [강의: 문제와 풀이]
        /* 
         * 문제: num1에 4, num2에 3을 더해서 7을 출력하고, 
         * 두 수의 곱인 12를 출력하는 코드를 작성하세요.
         */
        int num1 = 4, num2 = 3;
        System.out.println(num1 + num2);
        System.out.println(num1 * num2);

        // 8. [강의: 정리]
        /* 변수는 데이터를 저장하는 이름이 붙은 메모리 공간입니다. */
    }
}
