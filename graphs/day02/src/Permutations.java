import java.util.*;

public class Permutations {

    public static List<List<Integer>> permutations(List<Integer> A) {

        ArrayList<Integer> remaining = new ArrayList<>(A);
        ArrayList<List<Integer>> solutions = new ArrayList<>();

        permutationsHelper(new Stack<Integer>(), remaining, solutions);

        return solutions;
    }

    private static void permutationsHelper(
            Stack<Integer> soFar, ArrayList<Integer> remaining, ArrayList<List<Integer>> solutions
    ) {
        // base case
        if (remaining.isEmpty()) {
            // creating of a new list so soFar can be further modified
            solutions.add(new ArrayList<>(soFar));
            return;
        }

        // if nums remaining, add and recurse with each
        int n;
        for (int i = 0; i < remaining.size(); i++) {
            n = remaining.get(i);

            soFar.push(n);
            remaining.remove(new Integer(n));  // necessary to look for the object, not the index
            permutationsHelper(soFar, remaining, solutions);

            // backtrace
            remaining.add(i, n);
            soFar.pop();
        }
    }

}
