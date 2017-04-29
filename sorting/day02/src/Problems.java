import java.util.HashMap;
import java.util.LinkedList;

public class Problems {

    static void sortNumsBetween100s(int[] A) {
        CountingSort.countingSort(A);
    }

    /**
     * @param n the character number, 0 is the rightmost character
     * @return
     */
    private static int getNthCharacter(String s, int n) {
        return s.charAt(s.length() - 1 - n) - 'a';
    }


    /**
     * Use counting sort to sort the String array according to a character
     *
     * @param n The digit number (where 0 is the least significant digit)
     */
    static void countingSortByCharacter(String[] A, int n) {
        HashMap<Character, LinkedList<String>> L = new HashMap<>();
        char[] alph = new char[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        for (char c : alph)
            L.put(c, new LinkedList<>());
        for (String s : A) {
            char key = s.charAt(n);
            L.get(key).add(s);
        }
        int j = 0; // index in A to place numbers
        for (char c : alph) {
            LinkedList<String> list = L.get(c);
            while (list.size() > 0) {
                A[j++] = list.pop();
            }
        }
    }

    /**
     * @param stringLength The length of each of the strings in S
     */
    static void sortStrings(String[] S, int stringLength) {
        // Calculate the upper-bound for numbers in A
        int k = S[0].length() + 1;
        for (int i = 1; i < S.length; i++)
            k = (S[i].length() + 1 > k) ? S[i].length() + 1 : k;
        for (int n = stringLength - 1; n >= 0; n--) {
            countingSortByCharacter(S, n);
        }
    }
}
