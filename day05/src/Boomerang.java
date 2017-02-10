public class Boomerang {


    public static int numberOfBoomerangs(int[][] points) {
        int count = 0;
        MyHashMap<Integer, Integer> m;  // distance to number of points

        for (int[] center : points) {
            m = new MyHashMap<Integer, Integer>();
            for (int[] other : points) {
                int sqdist = getSquaredDistance(center, other);
                m.put(sqdist, m.getOrDefault(sqdist, 0) + 1);
            }
            for (Integer n : m.values()) {
                count += n * (n - 1);
            }
        }
        return count;
    }

    private static int getSquaredDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];

        return dx*dx + dy*dy;
    }
}
