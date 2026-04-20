package java_lecture_2;

/**
 * ## 섹션 4. 객체 지향 프로그래밍
 */
public class Section04_ObjectOriented {
    public static void main(String[] args) {

        // 1~3. [강의: 절차 지향 프로그래밍]
        /* 데이터와 기능이 분리된 방식의 한계를 학습합니다. */

        // 4. [강의: 클래스와 메서드]
        /*
         * 데이터를 다루는 코드와 그 데이터를 처리하는 코드를 함께 묶으면,
         * 이해하기 쉬운 객체 지향 코드가 됩니다.
         */
        ValueObject vo = new ValueObject();
        vo.add();
        vo.add();
        System.out.println("최종 값: " + vo.value);
        processValueObject(vo); // 객체를 전달하면 메서드가 객체의 내부 상태를 바꿀 수 있습니다.
        System.out.println("processValueObject 후 값: " + vo.value);

        // 5. [강의: 객체 지향 프로그래밍]
        /*
         * 객체는 속성(데이터)와 기능(메서드)을 가집니다.
         * 코드를 객체 중심으로 설계하는 것이 객체 지향의 핵심입니다.
         */
    }

    public static void processValueObject(ValueObject vo) {
        vo.add();
    }
}

class ValueObject {
    int value;

    void add() {
        value++;
    }
}
