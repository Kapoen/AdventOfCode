import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dec2 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("2024/resources/dec2.txt"));
        scanner.useDelimiter(" ");

        int safeReports = getSafeReports(scanner);
        System.out.println("Safe reports: " + safeReports);
    }

    private static int getSafeReports(Scanner allReports) {
        int safeReports = 0;

        while (allReports.hasNextLine()) {
            Scanner reportScanner = new Scanner(allReports.nextLine());
            ArrayList<Integer> report = getReport(reportScanner);
            Boolean goodReport = checkReport(report);
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

    private static Boolean checkReport(ArrayList<Integer> report) {
        boolean increasing = true;

        if(report.getLast() - report.getFirst() < 0) {
            increasing = false;
        }

        for (int i = 1; i < report.size(); i++) {
            int distance = report.get(i) - report.get(i - 1);

            if(report.get(i).equals(report.get(i - 1))) {
                return false;
            }

            if ((increasing && (report.get(i) < report.get(i - 1)))
                    || (!increasing && report.get(i) > report.get(i - 1))) {
                return false;
            }

            if((increasing && distance > 3) || (!increasing && distance < -3)) {
                return false;
            }
        }

        return true;
    }
}
