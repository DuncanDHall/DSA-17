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

        nQueensHelper(board, 0, 0, n, solutions);

        return solutions;
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

    private static void nQueensHelper(char[][] board, int start_i, int start_j, int unplaced, ArrayList<char[][]> solutions) {

        if (unplaced == 0) {
            solutions.add(copyOf(board));
            return;
        }

        int i, j;
        for (i = start_i; i < board.length; i++) {
            for (j = start_j; j < board.length; j++) {
                if (placementIsValid(i, j, board)) {
                    board[i][j] = 'Q';
                    nQueensHelper(board, i, j + 1, unplaced - 1, solutions);
                    board[i][j] = '.';
                }
            }
            start_j = 0;
        }
    }

    private static boolean placementIsValid(int i, int j, char[][] board) {
         // moving 'cursor' variables
        int _i, _j;

        // verify legal orthogonally
        for (_i = 0; _i < board.length; _i++) {
            if (board[_i][j] == 'Q') return false;
        }
        for (_j = 0; _j < board.length; _j++) {
            if (board[i][_j] == 'Q') return false;
        }

        // verify legal diagonally
        //      check NW
        _i = i; _j = j;
        while (_i >= 0 && _j >= 0) {
            if (board[_i][_j] == 'Q') return false;
            _i--; _j--;
        }
        //      check SE
        _i = i; _j = j;
        while (_i < board.length && _j < board[0].length) {
            if (board[_i][_j] == 'Q') return false;
            _i++; _j++;
        }
        //      check NE
        _i = i; _j = j;
        while (_i >= 0 && _j < board[0].length) {
            if (board[_i][_j] == 'Q') return false;
            _i--; _j++;
        }
        //      check SW
        _i = i; _j = j;
        while (_i < board.length && _j >= 0) {
            if (board[_i][_j] == 'Q') return false;
            _i++; _j--;
        }

        return true;
    }


}
