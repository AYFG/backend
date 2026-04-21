package java_lecture_3;

/**
 * ## 섹션 3. 불변 객체
 */
public class Section03_ImmutableObject {
    public static void main(String[] args) {
        // 1. [강의: 공유 참조와 사이드 이펙트]
        /*
         * 여러 변수가 하나의 객체를 공유할 때, 한쪽에서 값을 변경하면 다른 쪽도 영향을 받습니다.
         */
        Address a = new Address("서울");
        Address b = a; 
        b.setValue("부산"); 

        // 2. [강의: 불변 객체 도입]
        /*
         * 불변 객체는 생성 이후 값을 변경할 수 없는 객체입니다.
         * 필드를 final로 선언하고 Setter를 만들지 않습니다.
         */
        ImmutableAddress c = new ImmutableAddress("서울");

        // 3. [강의: 불변 객체 - 값 변경]
        /*
         * 값을 변경해야 할 때는 기존 객체를 수정하는 대신, 새로운 객체를 생성해서 반환합니다.
         */
        ImmutableAddress newAddress = c.withValue("부산");
        System.out.println("c = " + c.getValue()); 
        System.out.println("newAddress = " + newAddress.getValue()); 
    }
}

class Address {
    private String value;
    public Address(String value) { this.value = value; }
    public void setValue(String value) { this.value = value; }
    public String getValue() { return value; }
}

class ImmutableAddress {
    private final String value; 
    public ImmutableAddress(String value) { this.value = value; }
    public String getValue() { return value; }

    public ImmutableAddress withValue(String newValue) {
        return new ImmutableAddress(newValue);
    }
}
