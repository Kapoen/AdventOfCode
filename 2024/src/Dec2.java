import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Dec2 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("2024/resources/dec2.txt"));
        scanner.useDelimiter(" ");

        int safeReports = getSafeReports(scanner, false);
        System.out.println("Safe reports: " + safeReports);

        // Need new scanner to start at the beginning again
        Scanner newScanner = new Scanner(new File("2024/resources/dec2.txt"));
        newScanner.useDelimiter(" ");

        int safeReportsWithDampener = getSafeReports(newScanner, true);
        System.out.println("Safe reports with dampener: " + safeReportsWithDampener);
    }

    private static int getSafeReports(Scanner allReports, boolean dampener) {
        int safeReports = 0;

        while (allReports.hasNextLine()) {
            Scanner reportScanner = new Scanner(allReports.nextLine());
            ArrayList<Integer> report = getReport(reportScanner);
            Boolean goodReport = checkReport(report, dampener);
            if (goodReport) {
                safeReports += 1;
            }
        }
        return safeReports;
    }

    private static ArrayList<Integer> getReport(Scanner reportScanner) {
        ArrayList<Integer> report = new ArrayList<>();
        while (reportScanner.hasNextInt()) {
            report.add(reportScanner.nextInt());
        }
        return report;
    }

    private static Boolean checkReport(ArrayList<Integer> report, boolean dampener) {
        if(!dampener) {
            return checkSorted(report);
        }

        for (int i = 0; i < report.size(); i++) {
            ArrayList<Integer> clone = new ArrayList<>(report);
            clone.remove(i);
            if(checkSorted(clone)) {
                return true;
            }
        }
        return false;
    }

    private static Boolean checkSorted(ArrayList<Integer> report) {
        ArrayList<Integer> sorted = new ArrayList<>(report);
        sorted.sort(Comparator.naturalOrder());

        if(report.equals(sorted)) {
            return checkDistance(report);
        }

        sorted.sort((x, y) -> {return -1 * x.compareTo(y);});
        if(report.equals(sorted)) {
            return checkDistance(report);
        }

        return false;
    }

    private static Boolean checkDistance(ArrayList<Integer> report) {
        for (int i = 1; i < report.size(); i++) {
            int distance = Math.abs(report.get(i) - report.get(i - 1));
            if(distance > 3 || distance < 1) {
                return false;
            }
        }
        return true;
    }
}
