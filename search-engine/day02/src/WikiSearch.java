import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class WikiSearch {

    // map from URLs that contain the term(s) to relevance score
    private Map<String, Double> map;
    private final int docCount;

    public WikiSearch(Map<String, Integer> freqMap, int docCount) {
        this.map = calcTFIDF(freqMap, docCount);
        this.docCount = docCount;
    }

    public WikiSearch(int docCount, Map<String, Double> map) {
        this.map = map;
        this.docCount = docCount;
    }

    private Map<String,Double> calcTFIDF(Map<String, Integer> freqMap, int docCount) {
        Map<String, Double> map = new HashMap<String, Double>();
        for (Entry<String, Integer> e: freqMap.entrySet()) {
            map.put(e.getKey(), freqMap.get(e.getKey()) * Math.log(((double) docCount) / freqMap.size()));
        }
        return map;
    }

    public Double getRelevance(String url) {
        Double relevance = map.get(url);
        if(relevance == null){
            return 0.0;
        }
        return relevance;
    }

    // Prints the contents in order of term frequency.
    private  void print() {
        List<Entry<String, Double>> entries = sort();
        for (Entry<String, Double> entry: entries) {
            System.out.println(entry);
        }
    }

    // Computes the union of two search results.
    public WikiSearch or(WikiSearch that) {
        Map<String, Double> orMap = new HashMap<String, Double>();
        Map<String, Double> m = that.map;
        for(String key: m.keySet()){
            if(!map.containsKey(key)){
                orMap.put(key, m.get(key));
            }
            else{
                orMap.put(key, totalRelevance(m.get(key), map.get(key)));
            }
        }
        for(String key: map.keySet()){
            if(!m.containsKey(key)){
                orMap.put(key, map.get(key));
            }
        }



        return new WikiSearch(docCount, orMap);
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Map<String, Double> andMap = new HashMap<String, Double>();
        Map<String, Double> m = that.map;
        for(String key: m.keySet()){
            if(map.containsKey(key)) {
                andMap.put(key, totalRelevance(m.get(key), map.get(key)));
            }
        }

        return new WikiSearch(docCount, andMap);
    }

    // Computes the difference of two search results.
    public WikiSearch minus(WikiSearch that) {
        Map<String, Double> minusMap = new HashMap<String, Double>();
        Map<String, Double> m = that.map;
        for(String key: map.keySet()){
            if(!m.containsKey(key)) {
                minusMap.put(key, map.get(key));
            }
        }

        return new WikiSearch(docCount, minusMap);
    }

    // Computes the relevance of a search with multiple terms.
    protected Double totalRelevance(Double rel1, Double rel2) {

        return rel1 + rel2;
    }


    // Sort the results by relevance.
    public List<Entry<String, Double>> sort() {
        Set<Entry<String, Double>> entries = map.entrySet();
        List<Entry<String, Double>> listOfEntries = new ArrayList<>(entries);
        Collections.sort(listOfEntries, Comparator.comparing(Map.Entry::getValue));
        return listOfEntries;
    }

    // Performs a search and makes a WikiSearch object.
    public static WikiSearch search(String term, Index index, int docCount) {
        Map<String, Integer> map = index.getCounts(term);

        // Store the map locally in the WikiSearch
        return new WikiSearch(map, docCount);
    }


    public static void main(String[] args) throws IOException {

        // make a Index
        Index index = new Index(); // You might need to change this, depending on how your constructor works.

        // search for the first term
        String term1 = "java";
        System.out.println("Query: " + term1);
        WikiSearch search1 = search(term1, index, 10); // 10 documents is completely arbitrary
        search1.print();

        // search for the second term
        String term2 = "programming";
        System.out.println("Query: " + term2);
        WikiSearch search2 = search(term2, index, 10);
        search2.print();

        // compute the intersection of the searches
        System.out.println("Query: " + term1 + " AND " + term2);
        WikiSearch intersection = search1.and(search2);
        intersection.print();
    }
}