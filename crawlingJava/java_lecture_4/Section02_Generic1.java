/**
 * ## 섹션 2. 제네릭 - Generic1
 *
 * [학습 내용]
 * 1. 제네릭이 필요한 이유 (타입별로 클래스를 중복해서 만들어야 하는 문제)
 * 2. 다형성(Object)을 통한 해결 시도와 한계 (다운캐스팅 필요, 타입 안전성 부족)
 * 3. 제네릭(Generic) 적용 (코드 재사용성 + 타입 안전성 동시 해결)
 */
public class Section02_Generic1 {
    public static void main(String[] args) {

        // 1. [다형성(Object)을 통한 해결 시도]
        // 어떤 타입이든 담을 수 있지만, 꺼낼 때 다운캐스팅이 필요하고 잘못된 타입을 넣어도 컴파일 에러가 나지 않습니다.
        ObjectBox objBox = new ObjectBox();
        objBox.set("문자열 데이터");

        // 다운캐스팅 필요 (Object -> String)
        String strResult = (String) objBox.get();
        System.out.println("ObjectBox 결과: " + strResult);

        // 위험성: 실수로 숫자를 넣고 문자로 꺼내려 하면 런타임 에러(ClassCastException) 발생!
        // objBox.set(10);
        // String errorResult = (String) objBox.get(); // 컴파일은 되지만 실행 시 뻗어버림

        System.out.println("=========================================");

        // 2. [제네릭(Generic) 적용]
        // 생성 시점에 타입을 결정하므로, 다운캐스팅이 필요 없고 타입 안전성이 보장됩니다.

        // 제네릭에 String 타입을 지정
        GenericBox<String> stringBox = new GenericBox<>();
        stringBox.set("제네릭 문자열");
        String genericStr = stringBox.get(); // 다운캐스팅 필요 없음!
        System.out.println("GenericBox (String) 결과: " + genericStr);

        // 제네릭에 Integer 타입을 지정
        GenericBox<Integer> integerBox = new GenericBox<>();
        integerBox.set(100);
        Integer genericInt = integerBox.get();
        System.out.println("GenericBox (Integer) 결과: " + genericInt);

        // 컴파일러가 타입을 체크해주므로, 다른 타입을 넣으려 하면 아예 컴파일 에러를 내뿜습니다. (안전함)
        // integerBox.set("문자열"); // <- 컴파일 에러!
    }
}

// --- 아래는 학습을 위한 보조 클래스들 ---

/**
 * Object를 사용한 다형성 박스
 * 장점: 모든 타입을 담을 수 있다.
 * 단점: 꺼낼 때 캐스팅이 필요하고, 타입 안전성이 떨어진다.
 */
class ObjectBox {
    private Object value;

    public void set(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }
}

/**
 * 제네릭(Generic)을 적용한 박스
 * <T>는 Type 매개변수로, 인스턴스를 생성할 때 타입이 결정됩니다.
 * 관례상 Type의 앞글자를 따서 T를 주로 사용합니다. (E: Element, K: Key, V: Value)
 */
class GenericBox<T> {
    private T value;

    public void set(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }
}
