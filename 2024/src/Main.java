import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("December 1:");
            Dec1.main(args);

            System.out.println("\nDecember 2:");
            Dec2.main(args);

            System.out.println("\nDecember 3:");
            Dec3.main(args);

            System.out.println("\nDecember 4:");
            Dec4.main(args);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }
}
