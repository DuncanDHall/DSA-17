import static java.lang.Math.min;

public class BillsNeeded {

    public int billsNeeded(int N, int[] billDenominations) {
        
        int[] memo = new int[N + 1];
        memo[0] = 0;
        for (int i = 1; i < memo.length; i++) {
            memo[i] = -1;
        }

        return billsNeededHelper(N, billDenominations, memo);
        
    }

    private int billsNeededHelper(int N, int[] billDenominations, int[] memo) {

        if (N < 0) return Integer.MAX_VALUE - 1;  // to prevent overflow one level up
        if (memo[N] != -1) return memo[N];  // case N = 0 included

        int bestSoFar = Integer.MAX_VALUE;

        for (int i = 0; i < billDenominations.length; i++) {
            bestSoFar = min(
                    bestSoFar,
                    billsNeededHelper(
                        N - billDenominations[i],
                        billDenominations,
                        memo
                    ) + 1
            );
        }


        // TODO
        memo[N] = bestSoFar;
        return bestSoFar;
    }

    public static void main(String[] args) {
        BillsNeeded solver = new BillsNeeded();

        int[] bills = new int[]{1, 3, 5, 10};
        System.out.println(solver.billsNeeded(100, bills));
        System.out.println(solver.billsNeeded(99, bills));
    }

}
