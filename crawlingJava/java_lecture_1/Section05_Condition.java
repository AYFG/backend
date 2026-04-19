/**
 * ## 섹션 5. 조건문
 */
public class Section05_Condition {
    public static void main(String[] args) {

        // 1. [강의: if문1 - if, else]
        int age = 15;
        if (age >= 20) {
            System.out.println("성인");
        } else {
            System.out.println("미성년자");
        }

        // 2. [강의: if문2 - else if]
        // 3. [강의: if문3 - if문과 else if문]
        /* if를 여러 개 쓰는 것과 else if를 쓰는 것의 차이점 학습 */
        if (age <= 7) {
            System.out.println("미취학");
        } else if (age <= 13) {
            System.out.println("초등학생");
        } else {
            System.out.println("학생/성인");
        }

        // 4. [강의: switch문]
        int grade = 2;
        switch (grade) {
            case 1 -> System.out.println("학점 A");
            case 2 -> System.out.println("학점 B");
            default -> System.out.println("학점 C");
        }

        // 5. [강의: 삼항 연산자]
        String result = (age >= 18) ? "OK" : "NO";
        System.out.println(result);

        // 6, 7. [강의: 문제와 풀이1, 2]
        /* 학점 계산기, 할인 서비스 등 강의 문제 수록 */
    }
}
