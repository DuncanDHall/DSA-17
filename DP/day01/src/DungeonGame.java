public class DungeonGame {

    public static int minInitialHealth(int[][] map) {

        // memo (min health required to enter and finish room (i, j) )
        int[][] memo = new int[map.length][map[0].length];


        // fill in right column and bottom row of memo (only one option)
        int h;     // health
        int i, j;  // row, column

        // right
        h = 1;
        j = map[0].length - 1;
        for (i = map.length - 1; i >= 0; i--) {
            h = Math.max(1, h - map[i][j]);
            memo[i][j] = h;
        }

        // bottom
        h = 1;
        i = map.length - 1;
        for (j = map[0].length - 1; j >= 0; j--) {
            h = Math.max(1, h - map[i][j]);
            memo[i][j] = h;
        }


        // fill in the rest

        for (i = map.length - 2; i >= 0; i--) {
            for (j = map[0].length - 2; j >= 0; j--) {
                int minEntry = Math.max(1, Math.min(memo[i + 1][j], memo[i][j + 1]) - map[i][j]);
                memo[i][j] = minEntry;
            }
        }

        // TODO: Your code here
        return memo[0][0];
    }

    public static void main(String[] args) {
        main(new String[0]);
    }
}
