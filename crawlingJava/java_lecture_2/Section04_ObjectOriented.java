/**
 * ## 섹션 4. 객체 지향 프로그래밍
 */
public class Section04_ObjectOriented {
    public static void main(String[] args) {

        // 1~3. [강의: 절차 지향 프로그래밍]
        /* 데이터와 기능이 분리된 방식의 한계를 학습합니다. */

        // 4. [강의: 클래스와 메서드]
        /* 데이터와 그 데이터를 사용하는 기능을 하나로 묶습니다. */
        ValueObject vo = new ValueObject();
        vo.add();
        vo.add();
        System.out.println("최종 값: " + vo.value);

        // 5. [강의: 객체 지향 프로그래밍]
        /* 
         * 객체는 속성(데이터)과 기능(메서드)을 가집니다.
         * 코드를 객체 중심으로 설계하는 것이 객체 지향의 핵심입니다.
         */
    }
}

class ValueObject {
    int value;
    void add() {
        value++;
    }
}
