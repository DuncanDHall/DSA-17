public class Knapsack {

    public static int maxValue(int knapsackSize, int[] S, int[] V) {

        int[][] memo = new int[S.length][knapsackSize + 1];
        for (int i = 0; i < S.length; i++) {
            for (int j = 1; j < knapsackSize + 1; j++) {  // max value is zero for knapsackSize = 0
                memo[i][j] = -1;
            }
        }

        return maxValueRecurse(knapsackSize, 0, S, V, memo);

    }

    private static int maxValueRecurse(int knapsackSize, int i, int[] S, int[] V, int[][]memo) {

        // base
        if (i == S.length) return 0;

        // memo checking
        if (memo[i][knapsackSize] != -1) return memo[i][knapsackSize];

        // recursive relation + memoize
        if (S[i] > knapsackSize) {
            memo[i][knapsackSize] = maxValueRecurse(knapsackSize, i + 1, S, V, memo);
        } else {
            int addItem = maxValueRecurse(knapsackSize - S[i], i + 1, S, V, memo) + V[i];
            int noAddItem = maxValueRecurse(knapsackSize, i + 1, S, V, memo);
            memo[i][knapsackSize] = addItem > noAddItem ? addItem : noAddItem;
        }

        // return
        return memo[i][knapsackSize];

    }

}
