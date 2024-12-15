import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dec4 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("2024/resources/dec4.txt"));
        scanner.useDelimiter("\n");

        ArrayList<ArrayList<String>> board = getBoard(scanner);

        int totalXmas = getTotalXmas(board);
        System.out.println("Total times XMAS: " + totalXmas);

        int totalMas = getTotalMas(board);
        System.out.println("Total times X-MAS: " + totalMas);
    }

    private static int getTotalMas(ArrayList<ArrayList<String>> board) {
        int count = 0;

        for(int rowNumber = 0; rowNumber < board.size(); rowNumber++) {
            ArrayList<String> row = board.get(rowNumber);
            for (int columnNumber = 0; columnNumber < row.size(); columnNumber++) {
                String letter = row.get(columnNumber);

                if(letter.equals("A")) {
                    if(rowNumber >= 1 && rowNumber <= board.size() - 2
                            && columnNumber >= 1 && columnNumber <= row.size() - 2
                            && board.get(rowNumber - 1).get(columnNumber - 1).equals("M")
                            && board.get(rowNumber + 1).get(columnNumber + 1).equals("S")
                            && board.get(rowNumber - 1).get(columnNumber + 1).equals("M")
                            && board.get(rowNumber + 1).get(columnNumber - 1).equals("S")) {
                        count += 1;
                    }

                    if(rowNumber >= 1 && rowNumber <= board.size() - 2
                            && columnNumber >= 1 && columnNumber <= row.size() - 2
                            && board.get(rowNumber - 1).get(columnNumber - 1).equals("S")
                            && board.get(rowNumber + 1).get(columnNumber + 1).equals("M")
                            && board.get(rowNumber - 1).get(columnNumber + 1).equals("S")
                            && board.get(rowNumber + 1).get(columnNumber - 1).equals("M")) {
                        count += 1;
                    }

                    if(rowNumber >= 1 && rowNumber <= board.size() - 2
                            && columnNumber >= 1 && columnNumber <= row.size() - 2
                            && board.get(rowNumber - 1).get(columnNumber - 1).equals("M")
                            && board.get(rowNumber + 1).get(columnNumber + 1).equals("S")
                            && board.get(rowNumber - 1).get(columnNumber + 1).equals("S")
                            && board.get(rowNumber + 1).get(columnNumber - 1).equals("M")) {
                        count += 1;
                    }

                    if(rowNumber >= 1 && rowNumber <= board.size() - 2
                            && columnNumber >= 1 && columnNumber <= row.size() - 2
                            && board.get(rowNumber - 1).get(columnNumber - 1).equals("S")
                            && board.get(rowNumber + 1).get(columnNumber + 1).equals("M")
                            && board.get(rowNumber - 1).get(columnNumber + 1).equals("M")
                            && board.get(rowNumber + 1).get(columnNumber - 1).equals("S")) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    private static int getTotalXmas(ArrayList<ArrayList<String>> board) {
        int count = 0;

        for(int rowNumber = 0; rowNumber < board.size(); rowNumber++) {
            ArrayList<String> row = board.get(rowNumber);
            for(int columnNumber = 0; columnNumber < row.size(); columnNumber++) {
                String letter = row.get(columnNumber);

                if(letter.equals("X")) {
                    // Check horizontal
                    if(columnNumber >= 3
                            && row.get(columnNumber - 1).equals("M")
                            && row.get(columnNumber - 2).equals("A")
                            && row.get(columnNumber - 3).equals("S")) {
                        count += 1;
                    }

                    if(columnNumber <= row.size() - 4
                            && row.get(columnNumber + 1).equals("M")
                            && row.get(columnNumber + 2).equals("A")
                            && row.get(columnNumber + 3).equals("S")) {
                        count += 1;
                    }

                    //Check vertical
                    if(rowNumber >= 3
                            && board.get(rowNumber - 1).get(columnNumber).equals("M")
                            && board.get(rowNumber - 2).get(columnNumber).equals("A")
                            && board.get(rowNumber - 3).get(columnNumber).equals("S")) {
                        count += 1;
                    }

                    if(rowNumber <= board.size() - 4
                            && board.get(rowNumber + 1).get(columnNumber).equals("M")
                            && board.get(rowNumber + 2).get(columnNumber).equals("A")
                            && board.get(rowNumber + 3).get(columnNumber).equals("S")) {
                        count += 1;
                    }

                    //Check diagonal
                    if(rowNumber >= 3 && columnNumber >= 3
                            && board.get(rowNumber - 1).get(columnNumber - 1).equals("M")
                            && board.get(rowNumber - 2).get(columnNumber - 2).equals("A")
                            && board.get(rowNumber - 3).get(columnNumber - 3).equals("S")) {
                        count += 1;
                    }

                    if(rowNumber <= board.size() - 4 && columnNumber >= 3
                            && board.get(rowNumber + 1).get(columnNumber - 1).equals("M")
                            && board.get(rowNumber + 2).get(columnNumber - 2).equals("A")
                            && board.get(rowNumber + 3).get(columnNumber - 3).equals("S")) {
                        count += 1;
                    }

                    if(rowNumber >= 3 && columnNumber <= row.size() - 4
                            && board.get(rowNumber - 1).get(columnNumber + 1).equals("M")
                            && board.get(rowNumber - 2).get(columnNumber + 2).equals("A")
                            && board.get(rowNumber - 3).get(columnNumber + 3).equals("S")) {
                        count += 1;
                    }

                    if(rowNumber <= board.size() - 4 && columnNumber <= row.size() - 4
                            && board.get(rowNumber + 1).get(columnNumber + 1).equals("M")
                            && board.get(rowNumber + 2).get(columnNumber + 2).equals("A")
                            && board.get(rowNumber + 3).get(columnNumber + 3).equals("S")) {
                        count += 1;
                    }
                }
            }
        }
        return count;
    }

    private static ArrayList<ArrayList<String>> getBoard(Scanner boardScanner) {
        ArrayList<ArrayList<String>> board = new ArrayList<>();
        while(boardScanner.hasNext()) {
            ArrayList<String> row = new ArrayList<>();

            Scanner rowScanner = new Scanner(boardScanner.next());
            rowScanner.useDelimiter("");

            while(rowScanner.hasNext()) {
                row.add(rowScanner.next());
            }

            board.add(row);
        }
        return board;
    }
}
