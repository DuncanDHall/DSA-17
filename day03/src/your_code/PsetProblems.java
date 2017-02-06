package your_code;

import ADTs.StackADT;

import java.util.EmptyStackException;
import java.util.Stack;

public class PsetProblems {

    public static int longestValidSubstring(String s) {
        int i = 0;
        Stack<Integer> opens = new Stack<Integer>();
        char[] chars = s.toCharArray();
        int lStopHere;
        int maxSoFar = 0;

        for (char c : chars) {
            if (c == '(') opens.push(i);
            else if (c == ')') {
                try {
                    lStopHere = i - opens.pop() + 1;
                } catch (EmptyStackException _) {
                    i++;
                    continue;
                }
                if (lStopHere > maxSoFar) maxSoFar = lStopHere;
            }

            i++;
        }

        return maxSoFar;
    }

    public static StackADT<Integer> sortStackLimitedMemory(StackADT<Integer> s) {

        Stack<Integer> res = new Stack<Integer>();
        int h;

        while (!s.isEmpty()) {
            while (res.isEmpty() || s.peek() <= res.peek()) {
                res.push(s.pop());
                if (s.isEmpty()) return (StackADT<Integer>) res;
            }
            h = s.pop();
            while (!res.isEmpty() && res.peek() < h) {
                s.push(res.pop());
            }
            res.push(h);
        }

        return (StackADT<Integer>) res;
    }

}
