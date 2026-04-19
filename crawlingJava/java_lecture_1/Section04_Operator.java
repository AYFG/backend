/**
 * ## 섹션 4. 연산자
 */
public class Section04_Operator {
    public static void main(String[] args) {

        // 1. [강의: 산술 연산자]
        int a = 5, b = 2;
        System.out.println(a + b);
        System.out.println(a / b); // 2 (정수 나눗셈은 소수점 버림)
        System.out.println(a % b); // 1 (나머지 연산자)

        // 2. [강의: 문자열 더하기]
        String str = "java" + 11; // "java11"
        System.out.println(str);

        // 3. [강의: 연산자 우선순위]
        int sum = 10 + 20 * 3; // 곱셈이 먼저 (70)
        System.out.println(sum);

        // 4. [강의: 증감 연산자]
        int i = 0;
        i++; // 전위/후위 타입은 강의에서 상세히 다룸
        System.out.println(i);

        // 5. [강의: 비교 연산자]
        System.out.println(10 == 10); // true
        System.out.println(10 != 5);  // true

        // 6. [강의: 논리 연산자]
        System.out.println(true && false); // false (AND)
        System.out.println(true || false); // true  (OR)
        System.out.println(!true);         // false (NOT)

        // 7. [강의: 대입 연산자]
        int x = 10;
        x += 5; // x = x + 5 (15)

        // 8. [강의: 문제와 풀이]
        // 평균 구하기 문제 등 강의 예제 재현

        // 9. [강의: 정리]
    }
}
