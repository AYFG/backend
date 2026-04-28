public class Megaphone {
    public static void main(String[] args) {
        if (args.length >= 1) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i].toUpperCase() + " ");
            }
            System.out.println();
        } else {
            System.out.println("* LOUD AND UNBEARABLE FEEDBACK NOISE *");
        }

    }
}
