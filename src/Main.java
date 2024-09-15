import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MultiSet[] multiSets = new MultiSet[] {
                new ArrayListMultiSet<>(),
                new LinkedListMultiSet<>(),
                new TreeMultiSet<>()
        };

        for (MultiSet multiSet : multiSets) {
            for (int n : new int[] {500, 1000, 2000, 4000}) {
                profileMultiSet(multiSet, n);
            }
        }
    }

    public static void profileMultiSet(MultiSet<Integer> multiSet, int n) {
        Random random = new Random();
        ArrayList<Integer> items = new ArrayList<>();

        // Add items
        for (int i = 0; i < n; i++) {
            int x = random.nextInt(100);
            multiSet.add(x);
            items.add(x);
        }

        // Ensure all items are added
        assert multiSet.size() == n;

        long start = System.nanoTime();

        // Remove all items
        for (int x : items) {
            multiSet.remove(x);
        }

        long end = System.nanoTime();

        // Ensure all items are removed
        assert multiSet.isEmpty();

        System.out.printf("%5d %37s %12.6f\n", n, multiSet.getClass().getSimpleName(), (end - start) / 1e9);
    }
}

