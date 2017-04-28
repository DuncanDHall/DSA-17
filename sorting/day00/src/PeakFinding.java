
public class PeakFinding {

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peak(int i, int[] nums) {
        if (i>0 && nums[i] < nums[i-1])
            return -1;
        if (i<nums.length-1 && nums[i] < nums[i+1])
            return 1;
        return 0;
    }

    // Return -1 is left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x>0 && nums[y][x] < nums[y][x-1])
            return -1;
        if (x<nums[0].length-1 && nums[y][x] < nums[y][x+1])
            return 1;
        return 0;
    }

    // Return -1 is up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y>0 && nums[y][x] < nums[y-1][x])
            return -1;
        if (y<nums.length-1 && nums[y][x] < nums[y+1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }
    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }



    public static int findOneDPeak(int[] nums){
        int d = 2;
        int i = nums.length / d;

        while (true) {
            d = d * 2;
            int delta = Math.max(1, nums.length / d);
            switch (peak(i, nums)){
                case 0:
                    return i;
                case -1:
                    i -= delta;
                    break;
                case 1:
                    i += delta;
                    break;
            }
        }
    }

    public static int[] findTwoDPeak(int[][] nums){
        int w = nums.length;
        int h = nums[0].length;

        int x = 0;
        int y = 0;

        while (true) {
            switch (peakX(x, y, nums)) {
                case -1:
                    x--;
                    break;
                case 1:
                    x++;
                    break;
                case 0:
                    switch (peakY(x, y, nums)) {
                        case -1:
                            y--;
                            break;
                        case 1:
                            y++;
                            break;
                        case 0:
                            return new int[] {x, y};
                    }
                    break;
            }
        }
    }

}
