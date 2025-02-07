import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Dec5 {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("2024/resources/dec5.txt"));
        Map<String, OrderingRules> orderingRules = new HashMap<>();

        while (scanner.hasNextLine()) {
            String pair = scanner.nextLine();
            if(pair.isEmpty()) {
                break;
            }

            String[] splittedPair = pair.split("\\|");
            if(!orderingRules.containsKey(splittedPair[0])) {
                orderingRules.putIfAbsent(splittedPair[0], new OrderingRules());
            }

            if(!orderingRules.containsKey(splittedPair[1])) {
                orderingRules.putIfAbsent(splittedPair[1], new OrderingRules());
            }
            orderingRules.get(splittedPair[0]).addBefore(splittedPair[1]);
            orderingRules.get(splittedPair[1]).addAfter(splittedPair[0]);
        }

        ArrayList<ArrayList<String>> orderings = new ArrayList<>();
        while (scanner.hasNextLine()) {
            Scanner orderingScanner = new Scanner(scanner.nextLine());
            orderingScanner.useDelimiter(",");

            ArrayList<String> currentOrdering = new ArrayList<>();

            while (orderingScanner.hasNext()) {
                currentOrdering.add(orderingScanner.next());
            }

            orderings.add(currentOrdering);
        }

        int middlePages = getMiddlePage(orderingRules, orderings).left();
        System.out.println("Total middle page numbers of correct orderings: " + middlePages);

        int middlePagesWrong = getMiddlePage(orderingRules, orderings).right();
        System.out.println("Total middle page numbers of wrong orderings: " + middlePagesWrong);
    }

    private static Pair<Integer, Integer> getMiddlePage(Map<String, OrderingRules> orderingRules,
                                                        ArrayList<ArrayList<String>> orderings) {
        int normalTotal = 0;
        int correctionTotal = 0;

        for(ArrayList<String> ordering: orderings) {
            boolean correctOrdering = true;
            for(int i = 0; i < ordering.size() - 1; i++) {
                if(!orderingRules.get(ordering.get(i)).before.contains(ordering.get(i + 1))) {
                    correctOrdering = false;
                    break;
                }
            }

            if (correctOrdering) {
                // Part 1
                int middle = ordering.size() / 2;
                normalTotal += Integer.parseInt(ordering.get(middle));
            }

            else {
                // Part 2
                Map<String, Integer> score = new HashMap<>();
                for (String currentNumber : ordering) {
                    score.putIfAbsent(currentNumber, 0);
                    for (String newNumber : ordering) {
                        if(currentNumber.equals(newNumber)) {
                            continue;
                        }

                        if(orderingRules.get(currentNumber).after.contains(newNumber)) {
                            int newScore = score.get(currentNumber) + 1;
                            score.replace(currentNumber, newScore);
                        }
                    }
                }

                int newMiddle = (ordering.size() - 1) / 2;

                final int[] add = {0};
                score.forEach((key, value) -> {
                    if(value == newMiddle) {
                        add[0] = Integer.parseInt(key);
                    }
                });
                correctionTotal += add[0];
            }
        }

        return new Pair<>(normalTotal, correctionTotal);
    }

    private record Pair<L, R>(L left, R right) {
    }

    private static class OrderingRules {
        private final ArrayList<String> after;
        private final ArrayList<String> before;

        public OrderingRules() {
            after = new ArrayList<>();
            before = new ArrayList<>();
        }

        public void addBefore(String number) {
            before.add(number);
        }

        public void addAfter(String number) {
            after.add(number);
        }
    }
}
