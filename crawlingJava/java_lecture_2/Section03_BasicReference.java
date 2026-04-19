package java_lecture_2;

/**
 * ## 섹션 3. 기본형과 참조형
 */
public class Section03_BasicReference {
    public static void main(String[] args) {

        // 1. [강의: 기본형 vs 참조형1 - 시작]
        /*
         * 기본형(Primitive): 변수에 값을 직접 저장 (int, double, boolean...)
         * 참조형(Reference): 메모리 주소를 저장 (클래스, 배열...)
         */
        int a = 10;
        Data data1 = new Data();
        data1.value = 20;

        // 2. [강의: 기본형 vs 참조형2 - 변수 대입]
        /* 대입 시 기본형은 값을 복사하지만, 참조형은 '주소값'을 복사합니다. (중요!) */
        Data data2 = data1; // 주소가 복사됨. 같은 인스턴스를 가리킴.

        // 3, 4. [강의: 메서드 호출 학습]
        /* 자바는 항상 Pass by Value지만, 참조형은 '주소값'이 넘어가므로 내부 값 변경이 가능합니다. */
        changeValue(data1);
        System.out.println("data1.value after method: " + data1.value); // 100

        // 5. [강의: 변수와 초기화]
        /* 지역 변수는 수동 초기화 필수, 클래스 필드는 자동 초기화(0, null 등)됩니다. */

        // 6, 7. [강의: null / NullPointerException]
        /* 참조형 변수가 가리키는 대상이 없을 때 null, 없는 대상을 참조하면 에러 발생! */
        Data data3 = null;
        // System.out.println(data3.value); // NullPointerException 발생!

        // 8. [강의: 문제와 풀이 / 정리]
    }

    public static void changeValue(Data data) {
        data.value = 100;
    }
}

class Data {
    int value;
}
