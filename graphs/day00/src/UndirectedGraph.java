import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class UndirectedGraph implements Graph {

    private ArrayList<Integer> nodes;
    private HashMap<Integer, List<Integer>> edges;

    public UndirectedGraph(int n) {
        for (int i = 0; i < n; i++) {
            nodes.add(i);
        }
        edges = new HashMap<>();
    }

    @Override
    public void addEdge(int v, int w) {
        if (!edges.containsKey(v)) edges.put(v, new LinkedList<>());
        edges.get(v).add(w);
        // TODO: Your code here
    }

    @Override
    public List<Integer> vertices() {
    	// TODO: Your code here
        return null;
    }

    @Override
    public int numVertices() {
    	// TODO: Your code here
        return 0;
    }

    @Override
    public int numEdges() {
    	// TODO: Your code here
        return 0;
    }

    @Override
    public Iterable<Integer> getNeighbors(int v) {
    	// TODO: Your code here
        return null;
    }

    @Override
    public boolean hasEdgeBetween(int v, int w) {
    	// TODO: Your code here
        return false;
    }

}
