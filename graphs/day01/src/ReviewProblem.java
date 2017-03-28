import static java.lang.Math.min;

public class ReviewProblem {

    public static int minimumLengthSubArray(int[] A, int desiredSum) {
        int fast = 0;
        int slow = 0;

        int sum = 0;
        int minLength = Integer.MAX_VALUE;

        while (true) {
            // phase 1: add items to sub-array
            while (sum < desiredSum) {

                if (fast == A.length) {
                    // we're done
                    return minLength == Integer.MAX_VALUE ? 0 : minLength;
                }

                sum += A[fast];
                fast++;
            }

            // phase 2: remove items from array, updating minimum as we go
            while (sum >= desiredSum) {
                minLength = min(minLength, fast - slow);
                sum -= A[slow];
                slow++;
            }
        }
    }

}
