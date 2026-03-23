// package variable;

public class Var {
    int a = 1;

    //    System.out.println(a);
    public static void main(String[] args) {
//        int a = 2;
//        int b;
//        System.out.println(b); // java: variable b might not have been initialized
//        int c = 2, d = 3;

        int a = 100;
        double b = 10.5;
        boolean c = true;
        char d = 'a';
        String e = "test";

        //정수
        byte f = 127; // -128 ~ 127
        short g = 32767; // -32,768 ~ 32767
        int h = 2147483647; // -2,147,483,648 ~ 2,147,483,647
        // -9,223,372,036,854,775,808 ~ 9,223,372,036,854,775,807
        long i = 9223372036854775807L;

        // 실수
        float j = 10.0f;
        double k = 10.0;

        int num1 = 10;
        int num2 = 20;
        int num3 = num1 + num2;
        System.out.println(num3);

        
    }
}
