package java_lecture_2;

/**
 * ## 섹션 2. 클래스와 데이터
 */
public class Section02_ClassData {
    public static void main(String[] args) {

        // 1. [강의: 클래스가 필요한 이유]
        /*
         * 학생이 1명일 때는 변수로 관리해도 되지만,
         * 학생이 10명, 100명이 되면 변수 이름이 너무 많아지고 관리가 어려워집니다.
         */
        String student1Name = "학생1";
        int student1Age = 15;

        // 2. [강의: 클래스 도입]
        /*
         * Student 클래스는 '학생'이라는 개념을 담는 설계도입니다.
         * 같은 구조를 가진 학생 데이터를 여러 개 만들 수 있습니다.
         */
        Student student1 = new Student(); // 객체(인스턴스) 생성
        student1.name = "학생1";
        student1.age = 15;

        // 3. [강의: 객체 사용]
        System.out.println("이름: " + student1.name + ", 나이: " + student1.age);

        // 4. [강의: 클래스, 객체, 인스턴스 정리]
        /*
         * 클래스: 설계도 (Student)
         * 객체: 설계도로 만든 실제 물건
         * 인스턴스: 특정 클래스로부터 생성된 객체
         */

        // 5, 6. [강의: 배열 도입 - 시작, 리펙토링]
        Student student2 = new Student();
        student2.name = "학생2";
        student2.age = 16;

        Student[] students = new Student[] { student1, student2 };
        // 학생 목록을 배열로 묶어서 반복문으로 출력하면 코드가 훨씬 깔끔해집니다.
        for (int i = 0; i < students.length; i++) {
            System.out.println("학생 " + (i + 1) + ": " + students[i].name + ", " + students[i].age);
        }

        // 7. [강의: 문제와 풀이 / 정리]
    }
}

class Student {
    String name;
    int age;
}
