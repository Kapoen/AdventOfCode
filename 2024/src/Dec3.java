import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class Dec3 {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Integer> allResults = new ArrayList<>();
        Scanner scanner = new Scanner(new File("2024/resources/dec3.txt"));
        Object[] results = scanner.findAll("mul\\([0-9]{1,3},[0-9]{1,3}\\)|do\\(\\)|don't\\(\\)").toArray();

        int total = getValue(results, false);
        System.out.println("Total: " + total);

        int total2 = getValue(results, true);
        System.out.println("Total with activations: " + total2);
    }

    private static int getValue(Object[] results, boolean activation) {
        int total = 0;
        boolean activated = true;

        for(Object o: results) {
            MatchResult matchResult = (MatchResult) o;
            String[] operation = matchResult.group().split("\\(|,|\\)");
            if(!(operation.length == 3)) {
                activated = operation[0].equals("do");
                continue;
            }
            int a = Integer.parseInt(operation[1]);
            int b = Integer.parseInt(operation[2]);
            if(!activation || activated) {
                total += a * b;
            }
        }
        return total;
    }
}
