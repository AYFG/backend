/**
 * ## 섹션 3. 제네릭 - Generic2
 *
 * [학습 내용]
 * 1. 타입 매개변수 제한 (extends 키워드로 특정 타입만 허용)
 * 2. 제네릭 메서드 (클래스가 아닌 메서드 단위에서 타입 파라미터 선언)
 * 3. 와일드카드 (?) (제네릭 타입의 유연한 사용)
 * 4. 타입 이레이저 (컴파일 후 제네릭 타입 정보가 지워지는 원리)
 */
public class Section03_Generic2 {
    public static void main(String[] args) {

        // 1. [강의: 타입 매개변수 제한]
        /*
         * <T extends Number>를 사용하면 Number의 자식 타입(Integer, Double 등)만 허용됩니다.
         * 아무 타입이나 넣는 것을 방지하고, 특정 기능(예: intValue())을 안전하게 사용할 수 있습니다.
         */
        NumberBox<Integer> intBox = new NumberBox<>();
        intBox.set(100);
        System.out.println("Integer 값: " + intBox.get());
        System.out.println("int 변환: " + intBox.intValue());

        NumberBox<Double> doubleBox = new NumberBox<>();
        doubleBox.set(3.14);
        System.out.println("Double 값: " + doubleBox.get());
        System.out.println("int 변환: " + doubleBox.intValue());

        // NumberBox<String> strBox = new NumberBox<>(); // 컴파일 에러! String은 Number의 자식이 아님

        System.out.println("=========================================");

        // 2. [강의: 제네릭 메서드]
        /*
         * 클래스 전체가 아닌, 특정 메서드에만 제네릭을 적용하는 방법입니다.
         * 호출 시점에 타입이 결정되며, 타입 추론이 가능합니다.
         */
        String strResult = GenericUtil.convert("안녕하세요");
        Integer intResult = GenericUtil.convert(42);
        System.out.println("제네릭 메서드 (String): " + strResult);
        System.out.println("제네릭 메서드 (Integer): " + intResult);

        System.out.println("=========================================");

        // 3. [강의: 와일드카드]
        /*
         * <?> : 모든 타입 허용 (비한정 와일드카드)
         * <? extends Number> : Number 또는 그 하위 타입만 허용 (상한 와일드카드)
         * <? super Integer> : Integer 또는 그 상위 타입만 허용 (하한 와일드카드)
         *
         * 와일드카드는 이미 만들어진 제네릭 타입을 "사용"할 때 유연성을 주는 것이고,
         * 제네릭 메서드는 타입을 "정의"할 때 사용하는 것입니다.
         */
        NumberBox<Integer> wildcardBox = new NumberBox<>();
        wildcardBox.set(999);
        printNumber(wildcardBox);

        System.out.println("=========================================");

        // 4. [강의: 타입 이레이저]
        /*
         * 자바의 제네릭은 컴파일 시점에만 타입을 체크하고,
         * 실행(런타임) 시에는 제네릭 타입 정보가 지워집니다 (Object로 변환).
         * 이를 "타입 이레이저(Type Erasure)"라고 합니다.
         *
         * 이 때문에 런타임에 제네릭 타입을 알 수 없어서,
         * instanceof나 new T() 같은 코드를 사용할 수 없습니다.
         */
        System.out.println("타입 이레이저: 컴파일 후 제네릭 정보는 사라지고 Object로 처리됩니다.");
    }

    // 와일드카드를 사용한 메서드: Number의 하위 타입이면 무엇이든 받을 수 있음
    static void printNumber(NumberBox<? extends Number> box) {
        System.out.println("와일드카드 출력: " + box.get());
    }
}

// --- 보조 클래스 ---

/**
 * 타입 매개변수를 Number로 제한한 박스
 * T는 반드시 Number의 자식(Integer, Double, Long 등)이어야 합니다.
 */
class NumberBox<T extends Number> {
    private T value;

    public void set(T value) { this.value = value; }
    public T get() { return value; }

    // T가 Number의 자식임이 보장되므로 Number의 메서드를 안전하게 호출 가능
    public int intValue() { return value.intValue(); }
}

/**
 * 제네릭 메서드를 가진 유틸리티 클래스
 */
class GenericUtil {
    // <T>를 메서드 앞에 선언하면, 이 메서드만 독립적으로 제네릭을 사용합니다.
    public static <T> T convert(T value) {
        System.out.println("  입력 타입: " + value.getClass().getSimpleName());
        return value;
    }
}
