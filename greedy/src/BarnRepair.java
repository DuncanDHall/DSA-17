import java.util.Arrays;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int S, int C, int[] occupied) {

        Arrays.sort(occupied);

        PriorityQueue<Integer> gaps = new PriorityQueue<>();
        int gap;

        for (int i = 1; i < occupied.length; i++) {
            gap = occupied[i] - occupied[i - 1] - 1;
            if (gap > 0) gaps.add(gap);
        }

        while (gaps.size() >= M) {
            C += gaps.poll();
        }

        return C;

    }

    public static void main(String[] args) {
        BarnRepair br = new BarnRepair();

        // [ 1 0 1 1 0 0 1 ]
        //   -   ---
        System.out.println(5 + " == " + br.solve(2, 7, 4, new int[]{1, 3, 4, 7}));

        // { 1, 3, 4, 7, 11 }
        // [ 1 0 1 1 0 0 1 0 0 0 1 ]
        //   -------     -       -
        System.out.println(6 + " == " + br.solve(3, 11, 5, new int[]{1, 3, 4, 7, 11}));

        // {2, 3, 4, 7, 11}
        // [ 0 1 1 1 0 0 1 0 0 0 1 ]
        System.out.println(5 + " == " + br.solve(3, 11, 5, new int[]{2, 3, 4, 7, 11}));

        // {2, 3, 4, 7, 11}
        // [ 0 1 1 1 0 0 1 0 0 0 1 0 ]
        System.out.println(5 + " == " + br.solve(3, 12, 5, new int[]{2, 3, 4, 7, 11}));

        // { 1, 100 }
        System.out.println(200 + " == " + br.solve(1, 200, 2, new int[]{1, 200}));

        System.out.println(21 + " == " + br.solve(4, 50, 17, new int[]{
                3, 4, 6, 8, 14, 15, 16, 17, 25, 26, 27, 30, 31, 40, 41, 42, 43
        }));

        System.out.println(9 + " == " + br.solve(1, 200, 2, new int[]{1, 200}));


        System.out.println("done");
    }
}
