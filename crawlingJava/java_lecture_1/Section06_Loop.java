/**
 * ## 섹션 6. 반복문
 */
public class Section06_Loop {
    public static void main(String[] args) {

        // 1. [강의: 반복문 시작]
        // 2, 3. [강의: while문1, 2]
        int count = 0;
        while (count < 3) {
            count++;
            System.out.println("while count: " + count);
        }

        // 4. [강의: do-while문]
        /* 조건이 안 맞아도 최소 1번은 실행됨 */
        int i = 10;
        do {
            System.out.println("현재 i(do-while): " + i);
        } while (i < 5);

        // 5. [강의: break, continue]
        /* 반복문을 제어하는 핵심 키워드 */

        // 6, 7. [강의: for문1, 2]
        for (int j = 0; j < 3; j++) {
            System.out.println("for j: " + j);
        }

        // 8. [강의: 중첩 반복문]
        /* 구구단 등의 로직을 공부합니다. */
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 2; col++) {
                System.out.println("row: " + row + ", col: " + col);
            }
        }

        // 9, 10. [강의: 문제와 풀이1, 2]
    }
}
