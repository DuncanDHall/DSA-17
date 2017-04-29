public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(rn) (r is the range)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int minSoFar = Integer.MAX_VALUE;
        int maxSoFar = Integer.MIN_VALUE;
        int range;
        int offset;

        for (int n : A) {
            maxSoFar = n > maxSoFar ? n : maxSoFar;
            minSoFar = n < minSoFar ? n : minSoFar;
        }

        range = maxSoFar - minSoFar;
        offset = minSoFar;

        int[] counts = new int[range + 1];  // initializes to zero
        for (int n : A) {
            counts[n - offset] += 1;
        }


        int j = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                counts[i] -= 1;
                A[j++] = i + offset;
            }
        }
    }

    public static void main(String[] args) {
        CountingSort.countingSort(new int[]{45,36,24,65,26,43});
    }

}
