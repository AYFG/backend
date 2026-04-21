package java_lecture_3;

/**
 * ## 섹션 6. 열거형 - ENUM
 */
public class Section06_Enum {
    public static void main(String[] args) {
        // 1. [강의: 문자열 타입 안전성 문제]
        /*
         * 문자열로 상태를 관리하면 오타나 잘못된 값이 들어올 수 있습니다.
         */
        String grade = "GOLD"; 

        // 2. [강의: 열거형 - Enum Type]
        /*
         * Enum을 사용하면 정해진 상수 외에 다른 값이 들어오는 것을 막아줍니다. (타입 안전성)
         */
        Grade myGrade = Grade.GOLD;
        System.out.println("myGrade = " + myGrade.name());

        // 3. [강의: 열거형 - 리팩토링]
        /*
         * Enum에 메서드나 필드를 추가하여 데이터를 직접 관리할 수 있습니다.
         */
        int discount = Grade.DIAMOND.discount(10000);
        System.out.println("DIAMOND 등급 할인 금액: " + discount);
    }
}

enum Grade {
    BASIC(10), GOLD(20), DIAMOND(30);

    private final int discountPercent;

    Grade(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int discount(int price) {
        return price * discountPercent / 100;
    }
}
