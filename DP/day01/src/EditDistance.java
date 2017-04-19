public class EditDistance {
    /*
    sub-problem:
        After making an edit, what is the distance?
    Recurrence relation:
        Which edit provides minimum distance?
     */

    public static int minEditDist(String a, String b) {

        int[][] memo = new int[a.length()][b.length()];
        for (int i = 0; i < a.length(); i++) {
            for (int j = 0; j < b.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return recursive(a.toCharArray(), b.toCharArray(), 0, 0, memo);


        // TODO: Your code here
    }

    private static int recursive(char[] a, char[] b, int i, int j, int[][] memo) {

        // base cases
        if (i == a.length) return b.length - j;
        if (j == b.length) return a.length - i;

        // previously solved?
        if (memo[i][j] != -1) return memo[i][j];

        // recurrence relation
        int best;
        if (a[i] == b[j]) {
            best = recursive(a, b, i + 1, j + 1, memo);
        }
        else {
            int opD = recursive(a, b, i + 1, j, memo);  // delete
            int opA = recursive(a, b, i, j + 1, memo);  // add
            int opS = recursive(a, b, i + 1, j + 1, memo); // substitute

            best = 1 + Math.min(opD, Math.min(opA, opS));
        }

        memo[i][j] = best;
        return best;

    }

}
