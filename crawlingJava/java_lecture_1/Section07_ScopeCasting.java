/**
 * ## 섹션 7. 스코프, 형변환
 */
public class Section07_ScopeCasting {
    public static void main(String[] args) {

        // 1. [강의: 스코프1 - 지역 변수와 스코프]
        // 2. [강의: 스코프2 - 스코프 존재 이유]
        /* 변수는 선언된 Stack 프레임/블록을 벗어나면 사라집니다. */
        int m = 10;
        if (true) {
            int x = 20;
            System.out.println("m=" + m + ", x=" + x);
        }
        // System.out.println(x); // 컴파일 오류 (x 소멸)

        // 3. [강의: 형변환1 - 자동 형변환]
        /* 작은 것 -> 큰 것 (안전) */
        int intValue = 10;
        double doubleValue = intValue; // 10.0으로 자동 변환

        // 4. [강의: 형변환2 - 명시적 형변환]
        /* 큰 것 -> 작은 것 (위험, 캐스팅 필요) */
        double pi = 3.14;
        int convertedPi = (int) pi; // 3으로 소수점 버림

        // 5. [강의: 계산과 형변환]
        /* 서로 다른 타입 계산 시 큰 타입으로 자동 변환됨 */

        // 6. [강의: 정리]
    }
}
