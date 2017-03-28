import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class ShortestPath {

    public static List<Integer> shortestPath(Graph g, int v, int w) {
        HashSet<Integer> visited = new HashSet<>();

        HashMap<Integer, Integer> distances = new HashMap<>();
        HashMap<Integer, Integer> previouses = new HashMap<>();

        for (int s: g.vertices()) {
            distances.put(s, Integer.MAX_VALUE);
        }

        distances.put(v, 0);

        int next_dist;
        int next = -1;

        int d;

        boolean done = false;
        int current = v; // current node while walking

        while (!done) {
            done = true;
            next_dist = Integer.MAX_VALUE;
            visited.add(current);

            for (int n: g.getNeighbors(current)) {
                if (!visited.contains(n)) {
                    done = false;

                    if (distances.get(n) < distances.get(current) + 1) {
                        d = distances.get(n);
                    } else {
                        d = distances.get(current) + 1;
                        distances.put(n, d);
                        previouses.put(n, current);
                    }

                    if (d < next_dist) {
                        next = n;
                        next_dist = d;
                    }
                }
            }

            current = next;
        }

        return getPath(previouses, v, w);
    }

    private static List<Integer> getPath(HashMap<Integer, Integer> previouses, int v, int w) {
        ArrayList<Integer> res = new ArrayList<>();

        int current = w;
        res.add(current);

        while (current != v) {
            current = previouses.get(current);
            res.add(0, current);
        }

        return res;
    }

    public static int distanceBetween(Graph g, int v, int w) {

        // ALL THE SAME AS ABOVE...

        HashSet<Integer> visited = new HashSet<>();
        HashSet<Integer> unvisited = new HashSet<>(g.vertices());

        HashMap<Integer, Integer> distances = new HashMap<>();
        HashMap<Integer, Integer> previouses = new HashMap<>();

        for (int s: g.vertices()) {
            distances.put(s, Integer.MAX_VALUE);
        }

        distances.put(v, 0);

        int next_dist;
        int next = -1;

        int d;

        boolean done = false;
        int current = v; // current node while walking

        while (!done) {
            done = true;
            next_dist = Integer.MAX_VALUE;
            visited.add(current);

            for (int n: g.getNeighbors(current)) {
                if (!visited.contains(n)) {
                    done = false;

                    if (distances.get(n) < distances.get(current) + 1) {
                        d = distances.get(n);
                    } else {
                        d = distances.get(current) + 1;
                        distances.put(n, d);
                        previouses.put(n, current);
                    }

                    if (d < next_dist) {
                        next = n;
                        next_dist = d;
                    }
                }
            }

            current = next;
        }

        // ...TO HERE

        return distances.get(w) == Integer.MAX_VALUE? -1: distances.get(w);
    }
}