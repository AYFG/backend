import java.util.Scanner;

public class PhoneBook {
    public static void main(String[] args) {
        Contact[] contact = new Contact[8];
        Scanner scanner = new Scanner(System.in);
        
        while(true){
            if (!scanner.hasNext()) {
                break;
            }
            String str = scanner.next();
            input(str);
            if (str.equals("EXIT")) {
                break;
            }
        }
        scanner.close();
    }

    public static void input(String str){
        switch (str) {
            case "ADD":
                System.out.println("ADD");
                break;
            case "SEARCH":
                System.out.println("SEARCH");
                break;
            case "EXIT":
                System.out.println("EXIT");
                break;
            default:
                System.out.println("Not Found");
                break;
        }
    }
}
