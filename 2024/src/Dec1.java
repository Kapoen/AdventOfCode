import javax.lang.model.element.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Dec1 {
    public static void main(String[] args) throws FileNotFoundException {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        Scanner scanner = new Scanner(new File("2024/resources/dec1.txt"));
        scanner.useDelimiter("   |\n");
        while (scanner.hasNext()) {
            list1.add(scanner.nextInt());
            list2.add(scanner.nextInt());
        }

        list1.sort(Comparator.naturalOrder());
        list2.sort(Comparator.naturalOrder());

        int distance = getDistance(list1, list2);

        System.out.println(distance);
    }

    private static int getDistance(List<Integer> list1, List<Integer> list2) {
        int distance = 0;

        for(int i = 0; i < list1.size(); i++) {
            int currentDistance = list1.get(i) - list2.get(i);
            if(currentDistance >= 0) {
                distance += currentDistance;
            }
            else {
                distance -= currentDistance;
            }
        }
        return distance;
    }
}