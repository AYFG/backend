package java_lecture_2;

/**
 * ## 섹션 3. 기본형과 참조형
 */
public class Section03_BasicReference {
    public static void main(String[] args) {

        // 1. [강의: 기본형 vs 참조형1 - 시작]
        /*
         * 기본형(Primitive): 변수에 값을 그대로 저장합니다.
         * 참조형(Reference): 변수에는 객체가 위치한 메모리 주소가 저장됩니다.
         */
        int a = 10;
        Data data1 = new Data();
        data1.value = 20;

        // 2. [강의: 기본형 vs 참조형2 - 변수 대입]
        /*
         * 기본형을 복사하면 값이 복사되므로 서로 영향을 주지 않습니다.
         * 참조형을 복사하면 주소값이 복사되어 같은 객체를 가리키게 됩니다.
         */
        Data data2 = data1; // 두 변수 모두 같은 객체를 가리킴.
        System.out.println("data1 == data2? " + (data1 == data2));

        // 3, 4. [강의: 메서드 호출 학습]
        /*
         * 자바는 항상 값을 복사해서 전달합니다.
         * 참조형은 객체 주소가 복사되므로, 같은 객체를 수정할 수 있습니다.
         */
        changeValue(data1);
        System.out.println("data1.value after method: " + data1.value); // 100

        // 5. [강의: 변수와 초기화]
        /*
         * 로컬 변수는 반드시 초기화해야 합니다.
         * 클래스 필드는 자동으로 기본값(0, false, null 등)으로 초기화됩니다.
         */
        Data uninitData = new Data();
        System.out.println("Data.value 기본 초기값 = " + uninitData.value);
        // int localVar; // 컴파일 에러: 초기화되지 않은 지역 변수 사용 불가
        // System.out.println(localVar);

        // 6, 7. [강의: null / NullPointerException]
        /*
         * 참조형 변수가 아무것도 가리키지 않으면 null이 됩니다.
         * null 상태에서 객체를 사용하면 NullPointerException이 발생합니다.
         */
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
