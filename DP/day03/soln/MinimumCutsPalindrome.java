public class MinimumCutsPalindrome {

    // Return true if s[lo:hi] is a palindrome (inclusive, exclusive)
    private boolean isPalindrome(String s, int lo, int hi) {
        assert lo <= hi;
        while (lo < hi) {
            if (s.charAt(lo) != s.charAt(hi-1))
                return false;
            lo++; hi--;
        }
        return true;
    }

    /**
     * the minimum number of cuts needed to partition s into palindromes. For example: "aabcacb" -> "aa","bcacb".
     * Calculated using a bottom-up dynamic programming approach.
     */
    public int minCutBU(String s) {
        int N = s.length();
        int[] DP = new int[N + 1]; // Step 1
        DP[N] = 0; // Step 2
        for (int i = N-1; i >= 0; i--) { // Step 3
            // Step 4
            int cuts = Integer.MAX_VALUE;
            for (int j = i+1; j <= N; j++) {
                if (isPalindrome(s, i, j)) {
                    int newCuts = DP[j];
                    if (j < N)
                        newCuts++;
                    if (newCuts < cuts)
                        cuts = newCuts;
                }
            }
            DP[i] = cuts; // Step 5
        }
        return DP[0]; // Step 6
    }

    /**
     * Step 3
     * a recursive function that returns DP[i], calculating it if necessary.
     */
    private int minCutRecursive(String s, int i, int[] DP) {
        // Step 4
        if (DP[i] != -1)
            return DP[i];
        // Step 5
        int N = s.length();
        int cuts = Integer.MAX_VALUE;
        for (int j = i+1; j <= N; j++) {
            if (isPalindrome(s, i, j)) {
                int newCuts = minCutRecursive(s, j, DP);
                if (j < N)
                    newCuts++;
                if (newCuts < cuts)
                    cuts = newCuts;
            }
        }
        // Step 6
        DP[i] = cuts;
        return DP[i];
    }

    /**
     * Calculated using a top-down dynamic programming approach.
     */
    public int minCutTD(String s) {
        // Step 1 - Memo initialization
        int N = s.length();
        int[] DP = new int[N + 1];
        for (int i = 0; i < DP.length; i++)
            DP[i] = -1;
        // Step 2
        DP[N] = 0;
        // Step 7
        return minCutRecursive(s, 0, DP);
    }

    public static void main(String[] args) {
        System.out.println((new MinimumCutsPalindrome()).minCutBU("aabcacb")); // should return 1 "aa,bcacb"
        System.out.println((new MinimumCutsPalindrome()).minCutTD("aabcacb")); // should return 1 "aa,bcacb"
        System.out.println((new MinimumCutsPalindrome()).minCutBU("aabacacb")); // should return 3 "a,aba,cac,b"
        System.out.println((new MinimumCutsPalindrome()).minCutTD("aabacacb")); // should return 3 "a,aba,cac,b"
    }


}
