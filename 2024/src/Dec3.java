import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.stream.Stream;

public class Dec3 {
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<Integer> allResults = new ArrayList<>();
        Scanner scanner = new Scanner(new File("2024/resources/dec3.txt"));
        Stream<MatchResult> results = scanner.findAll("mul\\([0-9]{1,3},[0-9]{1,3}\\)");
        results.forEach(result -> {
            String[] operation = result.group().split("\\(|,|\\)");
            int a = Integer.parseInt(operation[1]);
            int b = Integer.parseInt(operation[2]);
            allResults.add(a * b);
        });

        int total = 0;
        for (Integer i: allResults) {
            total += i;
        }

        System.out.println("Total: " + total);
    }
}
