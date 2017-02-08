import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Problems {

    public static Map<Integer, Integer> getCountMap(int[] arr) {
        MyLinearMap m = new MyLinearMap();
        for (int n : arr) {
            m.put(n, (int) m.getOrDefault(n, 0) + 1);
        }
        return m;
    }

    public static List<Integer> removeKDigits(int[] num, int k) {

        // append 0
        List<Integer> arr = new ArrayList<Integer>();
        for (int n : num) arr.add(n);
        arr.add(0);

        int i = 0;
        for (; k > 0; k--) {
            while (i < arr.size()-1 && arr.get(i) <= arr.get(i+1)) i++;
            arr.remove(i);
            if (i > 0) i--;
        }

        arr.remove(arr.size()-1);
        return (List<Integer>) arr;
    }

    public static int sumLists(Node<Integer> h1, Node<Integer> h2) {
        Node<Integer> r1 = reverseList(h1);
        Node<Integer> r2 = reverseList(h2);

        int m = 1;
        int res = 0;
        while (r1 != null) {
            res += r1.data * m;
            r1 = r1.next;
            m *= 10;
        }
        m = 1;
        while (r2 != null) {
            res += r2.data * m;
            r2 = r2.next;
            m *= 10;
        }

        return res;
    }

    private static Node<Integer> reverseList(Node<Integer> h) {
        Node p = null;
        Node c = h;
        Node n;

        while (c != null) {
            n = c.next;
            c.next = p;
            p = c;
            c = n;
        }

        return p;
    }

}
