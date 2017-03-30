import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static List<char[][]> nQueensSolutions(int n) {

        ArrayList<char[][]> solutions = new ArrayList<>();
        char[][] board = getBlankBoard(n);

//        nQueensHelper(board, 0, 0, n, solutions);


        // second run at a solution:
        boolean[] leftLeaningDiagonalsCovered = new boolean[2 * n - 1];  // (x, y) corresponds to lld y - x + 3 (many:1)
        boolean[] rightLeaningDiagonalsCovered = new boolean[2 * n - 1];  // (x, y) corresponds to rld x + y (many:1)
        boolean[] columnsCovered = new boolean[n];

        // rows need not be tracked (once a queen is placed, we skip the rest of the row
        nQueensHelper2(board, 0, n, columnsCovered, leftLeaningDiagonalsCovered, rightLeaningDiagonalsCovered, solutions);

        return solutions;
    }

    private static void nQueensHelper2(
            char[][] board, int row, int unplacedQueens,
            boolean[] columnsCovered, boolean[] leftLeaningDiagonalsCovered, boolean[] rightLeaningDiagonalsCovered,
            ArrayList<char[][]> solutions
    ) {
        // base case when all queens placed
        if (unplacedQueens == 0) {
            solutions.add(copyOf(board));
            return;
        }

        int y = row;
        for (int x = 0; x < board[0].length; x++) {
            if (!(columnsCovered[x] || leftLeaningDiagonalsCovered[y - x + (board.length - 1)] || rightLeaningDiagonalsCovered[x + y])) {
                // update board
                board[y][x] = 'Q';

                // update covered spaces
                columnsCovered[x] = leftLeaningDiagonalsCovered[y - x + (board.length - 1)] = rightLeaningDiagonalsCovered[x + y]
                        = true;

                // recursive call
                nQueensHelper2(
                        board, y + 1, unplacedQueens - 1,
                        columnsCovered, leftLeaningDiagonalsCovered, rightLeaningDiagonalsCovered,
                        solutions
                );

                // backtrack
                columnsCovered[x] = leftLeaningDiagonalsCovered[y - x + (board.length - 1)] = rightLeaningDiagonalsCovered[x + y]
                        = false;
                board[y][x] = '.';
            }
        }
    }

    private static boolean placementIsValid2(int x, int y, int n, boolean[] columnsCovered, boolean[] leftLeaningDiagonalsCovered, boolean[] rightLeaningDiagonalsCovered) {
        return !(columnsCovered[x] || leftLeaningDiagonalsCovered[y - x + (n - 1)] || rightLeaningDiagonalsCovered[x + y]);
    }

    private static char[][] getBlankBoard(int size) {
        char[][] emptyBoard = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                emptyBoard[i][j] = '.';
            }
        }

        return emptyBoard;
    }

//    private static void nQueensHelper(char[][] board, int start_i, int start_j, int unplaced, ArrayList<char[][]> solutions) {
//
//        if (unplaced == 0) {
//            solutions.add(copyOf(board));
//            return;
//        }
//
//        int i, j;
//        for (i = start_i; i < board.length; i++) {
//            for (j = start_j; j < board.length; j++) {
//                if (placementIsValid(i, j, board)) {
//                    board[i][j] = 'Q';
//                    nQueensHelper(board, i, j + 1, unplaced - 1, solutions);
//                    board[i][j] = '.';
//                }
//            }
//            start_j = 0;
//        }
//    }
//
//    private static boolean placementIsValid(int i, int j, char[][] board) {
//         // moving 'cursor' variables
//        int _i, _j;
//
//        // verify legal orthogonally
//        for (_i = 0; _i < board.length; _i++) {
//            if (board[_i][j] == 'Q') return false;
//        }
//        for (_j = 0; _j < board.length; _j++) {
//            if (board[i][_j] == 'Q') return false;
//        }
//
//        // verify legal diagonally
//        //      check NW
//        _i = i; _j = j;
//        while (_i >= 0 && _j >= 0) {
//            if (board[_i][_j] == 'Q') return false;
//            _i--; _j--;
//        }
//        //      check SE
//        _i = i; _j = j;
//        while (_i < board.length && _j < board[0].length) {
//            if (board[_i][_j] == 'Q') return false;
//            _i++; _j++;
//        }
//        //      check NE
//        _i = i; _j = j;
//        while (_i >= 0 && _j < board[0].length) {
//            if (board[_i][_j] == 'Q') return false;
//            _i--; _j++;
//        }
//        //      check SW
//        _i = i; _j = j;
//        while (_i < board.length && _j >= 0) {
//            if (board[_i][_j] == 'Q') return false;
//            _i++; _j--;
//        }
//
//        return true;
//    }


}
