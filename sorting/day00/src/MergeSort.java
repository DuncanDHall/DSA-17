import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     *
     * Best-case runtime: O(n log(n))
     * Worst-case runtime: O(n log(n))
     * Average-case runtime: O(n log(n))
     *
     * Space-complexity: O(n log(n))
     */

    @Override
    public int[] sort(int[] array) {

        if (array.length <= 1) return array;
        else if (array.length == 2) {
            if (array[0] > array[1]) swap(array, 0, 1);
            return array;
        }
        else {
            int mid = array.length / 2;
            return merge(sort(Arrays.copyOfRange(array, 0, mid)), sort(Arrays.copyOfRange(array, mid, array.length)));
        }
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     */
    public int[] merge(int[] a, int[] b) {

        int[] res = new int[a.length + b.length];

        int i; // pointer in result array
        int j = 0; // pointer in a
        int k = 0; // pointer in b

        for (i = 0; i < res.length; i++) {
            if (j == a.length) {
                System.arraycopy(b, k, res, i, b.length-k);
                break;
            } else if (k == b.length) {
                System.arraycopy(a, j, res, i, a.length-j);
                break;
            }

            if (a[j] < b[k]) {
                res[i] = a[j];
                j++;
            } else {
                res[i] = b[k];
                k++;
            }
        }

        return res;
    }

}
