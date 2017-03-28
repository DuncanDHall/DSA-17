import java.util.*;

public class GraphProblems {

    public static boolean connected(Graph g, int v, int u) {
        // breadth first search through the graph

        HashSet<Integer> visited = new HashSet<>();

        Queue<Integer> bfsQueue = new LinkedList<>();
        bfsQueue.offer(v);

        while (!bfsQueue.isEmpty()) {
            Integer next = bfsQueue.poll();
            if (!visited.contains(next)) {
                for (int neighbor: g.getNeighbors(next)) {
                    if (neighbor == u) return true;
                    bfsQueue.offer(neighbor);
                }
                visited.add(next);
            }
        }

        return false;
    }

    public static List<Integer> topologicalOrder(Digraph g) {
        // assumes a connected, acyclic, directed graph
        // result is non-deterministic

        HashSet<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();

        for (int v: g.vertices()) {
            if (!visited.contains(v)) topologicalHelper(v, g, res, visited);
        }

        return res;
    }

    private static void topologicalHelper(int v, Digraph g, List<Integer> res, HashSet<Integer> visited) {
        visited.add(v);
        for (int n: g.getNeighbors(v)) {
            if (!visited.contains(n)) topologicalHelper(n, g, res, visited);
        }
        // after all children have been visited, add this node
        res.add(0, v);
    }

    public static boolean hasCycle(UndirectedGraph g) {

        HashSet<Integer> visited = new HashSet<>();

        for (int v: g.vertices()) {
        // needed for checking non-connected graphs
            if (!visited.contains(v)) {
                if (cycleHelper(g, v, -1, visited)) return true;
            }
        }

        return false;
    }

    private static boolean cycleHelper(UndirectedGraph g, int v, int p, HashSet<Integer> visited) {
        // v is current, p is previous
        if (visited.contains(v)) return true;
        visited.add(v);
        for (int n: g.getNeighbors(v)) {
            if (n != p) {
                if (cycleHelper(g, n, v, visited)) return true;
            }
        }
        return false;
    }


    public static void main(String[] args) {
        Digraph g = new Digraph(5);
        System.out.println(g.vertices());
        g.addEdge(1, 0);
        g.addEdge(2, 1);
        g.addEdge(3, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 0);
        System.out.println(topologicalOrder(g));
    }

}