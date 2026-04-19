package java_lecture_2;

/**
 * ## 섹션 2. 클래스와 데이터
 */
public class Section02_ClassData {
    public static void main(String[] args) {

        // 1. [강의: 클래스가 필요한 이유]
        /* 학생 데이터를 각각 변수로 관리하면 학생이 늘어날수록 코드가 복잡해집니다. */
        String student1Name = "학생1";
        int student1Age = 15;

        // 2. [강의: 클래스 도입]
        /* 클래스를 사용해 학생이라는 개념을 하나의 설계도로 만듭니다. */
        Student student1 = new Student(); // 객체(인스턴스) 생성
        student1.name = "학생1";
        student1.age = 15;

        // 3. [강의: 객체 사용]
        System.out.println("이름: " + student1.name + ", 나이: " + student1.age);

        // 4. [강의: 클래스, 객체, 인스턴스 정리]
        /*
         * 클래스: 설계도 (Student)
         * 객체: 모든 실체 (student1)
         * 인스턴스: 특정 클래스로부터 생성된 객체 (student1은 Student의 인스턴스)
         */

        // 5, 6. [강의: 배열 도입 - 시작, 리펙토링]
        Student[] students = new Student[2];
        students[0] = student1;
        // ... 배열을 통해 관리 효율성 증대

        // 7. [강의: 문제와 풀이 / 정리]
    }
}

class Student {
    String name;
    int age;
}
