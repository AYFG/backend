/**
 * ## 섹션 9. 배열
 */
public class Section09_Array {
    public static void main(String[] args) {

        // 1. [강의: 배열 시작]
        // 2, 3. [강의: 배열의 선언과 생성, 사용]
        int[] students = new int[]{90, 80, 70, 60, 50};

        // 4. [강의: 배열 리펙토링]
        for (int i = 0; i < students.length; i++) {
            System.out.println("학생 " + (i + 1) + " 점수: " + students[i]);
        }

        // 5 ~ 7. [강의: 2차원 배열 - 시작, 리팩토링1, 2]
        int[][] matrix = new int[2][3]; // 2행 3열

        // 8. [강의: 향상된 for문]
        for (int s : students) {
            System.out.println("점수(Enhanced for): " + s);
        }

        // 9 ~ 11. [강의: 문제와 풀이1, 2, 3]
    }
}
