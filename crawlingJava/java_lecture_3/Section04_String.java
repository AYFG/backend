package java_lecture_3;

/**
 * ## 섹션 4. String 클래스
 */
public class Section04_String {
    public static void main(String[] args) {
        // 1. [강의: String 클래스 - 비교]
        String str1 = "hello";
        String str2 = "hello";
        String str3 = new String("hello");

        System.out.println("str1 == str2: " + (str1 == str2)); 
        System.out.println("str1 == str3: " + (str1 == str3)); 
        System.out.println("str1.equals(str3): " + str1.equals(str3)); 

        // 2. [강의: StringBuilder - 가변 String]
        StringBuilder sb = new StringBuilder();
        sb.append("A").append("B").append("C");
        System.out.println("result = " + sb.toString());

        // 3. [강의: 메서드 체이닝]
        ChainAdder adder = new ChainAdder();
        int finalValue = adder.add(1).add(2).add(3).getValue();
        System.out.println("finalValue = " + finalValue);
    }
}

class ChainAdder {
    private int value = 0;
    public ChainAdder add(int n) {
        this.value += n;
        return this;
    }
    public int getValue() { return value; }
}
