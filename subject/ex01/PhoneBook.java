import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        Contact[] contact = new Contact[8];

        Scanner scanner = new Scanner(System.in);
	String str= scanner.next();
        if (str.equals("EXIT")) {
		System.out.println("eexxiitt");
        }else{
		System.out.println(str);
	}
    }
}
