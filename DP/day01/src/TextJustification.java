import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    public static List<Integer> justifyText(String[] w, int m) {

        // memo
        int[] memo = new int[w.length];
        for (int i = 0; i < memo.length; i++) memo[i] = -1;


        int[] parents = new int[w.length];

        recursive(w, m, 0, memo, parents);

        List<Integer> res = new LinkedList<>();
        int c = 0;
        res.add(c);
        System.out.println(Arrays.toString(parents));
        while (parents[c] < w.length) {
            c = parents[c];
            res.add(c);
        }

        return res;
    }


    private static int recursive(String[] w, int m, int first, int[] memo, int[] parents) {
        /*
        w : list of words
        m : line length
        start : index of first word of words to pack
        memo : the memo which stores subproblems
                    memo[first word index] --> cost of packing these words into full lines.
         */

        // base
        if (first == w.length) return 0;

        // already computed?
        if (memo[first] != -1) return memo[first];

        // recurrence relation
        int bestCost = Integer.MAX_VALUE;
        int bestBreak = -1;
        int last = first;
        int lineCost;
        int restCost;

        while (last < w.length) {

            // compute cost of the line
            lineCost = lineCost(w, first, last, m);
            // if line is too long
            if (lineCost == -1) {
                break;
            }

            // recursively compute the cost of the rest
            restCost = recursive(w, m, last + 1, memo, parents);

            if (bestCost > lineCost + restCost) {
                bestCost = lineCost + restCost;
                bestBreak = last + 1;
            }

            last++;
        }

        // find best option
        memo[first] = bestCost;
        parents[first] = bestBreak;
        return bestCost;

    }


    private static int lineCost(String[] w, int first, int last, int m) {

        // last line of a paragraph costs zero.
        if (last >= w.length) return 0;

        int len = 0;
        for (; first <= last; first++) {
            len += w[first].length() + 1;
        }
        len--;  // no trailing spaces

        if (len > m) return -1;
        else return simplePow(m - len, 3);

    }

    private static int simplePow(int base, int e) {
        if (e < 0) throw new ArithmeticException("simplePow() only works for positive integer exponents");

        int res = 1;
        for (; e > 0; e--) res *= base;
        return res;
    }


    public static void main(String[] args) {


        System.out.println(lineCost("this is a test".split(" "), 0, 3, 14));



        String text;
        String[] w;
        List<Integer> res;

        text = "This is a text justification test which needs a lot of words in order to work, so here I am typing them.";
        w = text.split(" ");
        System.out.println(Arrays.toString(w));
        System.out.println(w.length);
        res = justifyText(w, 21);
        for (int i : res) System.out.println(w[i]);

        text = "This is a text justification test which needs a lot of words in order to work, here I am typing them.";
        w = text.split(" ");
        System.out.println(Arrays.toString(w));
        System.out.println(w.length);
        res = justifyText(w, 21);
        for (int i : res) System.out.println(w[i]);

        text = "This is a text justification test which needs a lot of words in order to work, here am typing them.";
        w = text.split(" ");
        System.out.println(Arrays.toString(w));
        System.out.println(w.length);
        res = justifyText(w, 21);
        for (int i : res) System.out.println(w[i]);
    }

}