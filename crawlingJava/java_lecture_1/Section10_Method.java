/**
 * ## 섹션 10. 메서드
 */
public class Section10_Method {
    public static void main(String[] args) {

        // 1, 2. [강의: 메서드 시작, 사용]
        int result = add(10, 20);
        System.out.println("결과: " + result);

        // 3. [강의: 메서드 정의]
        // 4. [강의: 반환 타입]
        /* return 값의 유무와 타입 일치 학습 */

        // 5, 6. [강의: 메서드 호출과 값 전달1, 2]
        /* 자바는 항상 '값에 의한 전달(Pass by Value)'임을 확실히 이해해야 합니다. */

        // 7. [강의: 메서드와 형변환]
        printNumber((int) 1.5); // 직접 명시적 형변환 후 전달

        // 8. [강의: 메서드 오버로딩]
        /* 이름은 같지만 매개변수가 다른 메서드들 */
        System.out.println(add(1.5, 2.5));

        // 9 ~ 10. [강의: 문제와 풀이1, 2]
    }

    public static int add(int a, int b) {
        return a + b;
    }

    // 오버로딩 예시
    public static double add(double a, double b) {
        return a + b;
    }

    public static void printNumber(int n) {
        System.out.println("숫자 호출: " + n);
    }
}
