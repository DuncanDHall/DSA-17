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
        return -1;
    }

    /**
     * Calculated using a top-down dynamic programming approach.
     */
    public int minCutTD(String s) {
        return -1;
    }

    public static void main(String[] args) {
        System.out.println((new MinimumCutsPalindrome()).minCutBU("aabcacb")); // should return 1 "aa,bcacb"
        System.out.println((new MinimumCutsPalindrome()).minCutTD("aabcacb")); // should return 1 "aa,bcacb"
        System.out.println((new MinimumCutsPalindrome()).minCutBU("aabacacb")); // should return 3 "a,aba,cac,b"
        System.out.println((new MinimumCutsPalindrome()).minCutTD("aabacacb")); // should return 3 "a,aba,cac,b"
    }


}
