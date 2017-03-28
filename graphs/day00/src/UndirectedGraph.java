import java.util.*;

public class UndirectedGraph implements Graph {

    private ArrayList<Integer> nodes;
    private HashMap<Integer, HashSet<Integer>> edges;

    private int edgeCount;

    public UndirectedGraph(int n) {
        // O(n)
        edgeCount = 0;
        nodes = new ArrayList<>();
        edges = new HashMap<>();
        for (int i = 0; i < n; i++) {
            nodes.add(i);
            edges.put(i, new HashSet<>());
        }
    }

    @Override
    public void addEdge(int v, int w) {
        // O(1)
        edges.get(v).add(w);
        edges.get(w).add(v);
        edgeCount++;
    }

    @Override
    public List<Integer> vertices() {
        // O(1);
        return nodes;
    }

    @Override
    public int numVertices() {
        // O(1)
        return vertices().size();
    }

    @Override
    public int numEdges() {
        // O(1)
        return edgeCount;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
        // O(1)
        return edges.get(v);
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
        // O(1)
        return edges.get(v).contains(w);
    }

}
