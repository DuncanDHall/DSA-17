public class SplitCoins {

    /** MEMO */
    static int[][] memo;

    //CONSTANTS

    public static int splitCoins(int[] coins) {

        /** INITIALIZE THE MEMO */
        int sum = 0;
        for (int c : coins) sum += c;
        memo = new int[sum][coins.length];
        for (int i = 0; i < sum; i++) {
            for (int j = 0; j < coins.length; j++) {
                memo[i][j] = -1;
            }
        }

        // SET GLOBAL CONSTANTS
        int diff = 0;  // unsigned difference between piles A and B
        int p = 0;

        // CALL RECURSIVE FUNCTION ON PROBLEM THAT YOU WANT TO SOLVE
        return recursiveFunction(diff, p, coins, memo);

    }


    private static int recursiveFunction(int diff, int p, int[] coins, int[][] memo){

        // BASE CASES
        if (p == coins.length) return diff;

        /** HAS THIS BEEN MEMOIZED? */
        if (memo[diff][p] != -1) return memo[diff][p];

        // RECURRENCE RELATION
        int toA = recursiveFunction(Math.abs(diff + coins[p]),p + 1, coins, memo);
        int toB = recursiveFunction(Math.abs(diff - coins[p]), p + 1, coins, memo);
        int best = toA < toB ? toA : toB;

        /** UPDATE THE MEMO */
        memo[diff][p] = best;

        // RETURN THE ANSWER
        return best;

    }

    public static void main(String[] args) {
        int[] coins;

        coins = new int[] {3,1,1,2,5,7};
        System.out.println(SplitCoins.splitCoins(coins));

        coins = new int[] {10, 8, 5, 4, 3};
        System.out.println(SplitCoins.splitCoins(coins));
    }
}
