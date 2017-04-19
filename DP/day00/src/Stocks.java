public class Stocks {

    /** MEMO */


    //CONSTANTS


    //PUBLIC FUNCTION
    public static int maxProfit(int[] prices){

        return maxProfitWithK(prices, 1);

    }

    private static int maxProfitRecursive(
            int p, int[] prices, int[][][] memo, int k, boolean invested
    ){

        // BASE CASES
        if (p == prices.length - 1) return 0;
        if (k == 0) return 0;

        /** HAS THIS BEEN MEMOIZED? */
        if (memo[p][k][invested? 1 : 0] != -1) return memo[p][k][invested? 1 : 0];


        // RECURRENCE RELATION

        int invest = prices[p + 1] - prices[p] + maxProfitRecursive(p + 1, prices, memo, k, true);
        int divest = maxProfitRecursive(p + 1, prices, memo, invested ? k - 1 : k, false);

        int best = invest > divest ? invest : divest;


        /** UPDATE THE MEMO */
        memo[p][k][invested? 1 : 0] = best;
//        System.out.println("p: " + p + " k: " + k + " best: " + best);

        // RETURN THE ANSWER
        return best;

    }

    public static int maxProfitWithK(int[] prices, int k) {

        // memo dimensions: day, remaining sells, currently invested (binary)
        int[][][] memo = new int[prices.length][k + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int j = 0; j < k + 1; j++) {
                memo[i][j][0] = -1;
                memo[i][j][1] = -1;
            }
        }

        return maxProfitRecursive(0, prices, memo, k, false);

    }

    public static void main(String[] args) {
        Stocks solver = new Stocks();
        int[] prices;

        prices = new int[]{2, 3, 1, 3, 2, 3};
//        System.out.println(solver.maxProfitWithK(prices, 1));
        System.out.println(solver.maxProfitWithK(prices, 2));
    }
}
