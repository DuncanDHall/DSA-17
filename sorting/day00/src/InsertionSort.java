
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        int i;
        int j;

        // original implementation is bad!
//        for (i = 0; i < array.length; i++) {
//            j = 0;
//            while (j < i && array[j] < array[i]) j++;
//            while (j < i) {
//                swap(array, i, j);
//                j++;
//            }
//        }

        for (i = 0; i < array.length; i++) {
            j = i;
            while (j > 0 && array[j] < array[j-1]){
                swap(array, j, j-1);
                j--;
            }
        }
        return array;
    }
}
