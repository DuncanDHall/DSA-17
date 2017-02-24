public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * Best-case runtime: O(n log(n))
     * Worst-case runtime: O(n^2) (amended)
     * Average-case runtime: O(n log(n))
     *
     * Space-complexity: O(log(n)) (worst is O(n))
     */
    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int low, int high) {
        if (low >= high) return;
        else {
            int p = partition(a, low, high);
            quickSort(a, low, p - 1);
            quickSort(a, p + 1, high);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param low The beginning index of the subarray being considered (inclusive)
     * @param high The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int low, int high) {

        // select pivot and move to the first element
        movePivot(array, low, high);

        // do the partition
        int i;
        int j;

        for (i = low + 1; i <= high; i++) {
            if (array[i] > array[low]) {
                for (j = i + 1; j < high + 1 && array[j] >= array[low]; j++);
                if (j == high + 1) break;  // no more elements lower than pivot
                swap(array, i, j);
            }
        }

        swap(array, low, i - 1);

        return i - 1;
    }

    private void movePivot(int[] a, int low, int high) {
        int mid = (high + low) / 2;
        if ((a[low] - a[mid]) * (a[high] - a[low]) >= 0) {
            return;
        } else if ((a[mid] - a[low]) * (a[high] - a[mid]) >= 0) {
            swap(a, mid, low);
        } else {
            swap(a, high, low);
        }
    }

}
