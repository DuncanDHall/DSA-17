import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.Comparator;
import java.util.Collections;

import redis.clients.jedis.Jedis;

public class WikiSearch {

    // map from URLs that contain the term(s) to relevance score
    private Map<String, Integer> map;

    public WikiSearch(Map<String, Integer> map) {
        this.map = map;
    }

    public Integer getRelevance(String url) {
        Integer relevance = map.get(url);
        if(relevance == null){
            return 0;
        }
        return relevance;
    }

    // Prints the contents in order of term frequency.
    private  void print() {
        List<Entry<String, Integer>> entries = sort();
        for (Entry<String, Integer> entry: entries) {
            System.out.println(entry);
        }
    }

    // Computes the union of two search results.
    public WikiSearch or(WikiSearch that) {
        Map<String, Integer> orMap = new HashMap<String, Integer>();
        Map<String, Integer> m = that.map;
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



        return new WikiSearch(orMap);
    }

    // Computes the intersection of two search results.
    public WikiSearch and(WikiSearch that) {
        Map<String, Integer> andMap = new HashMap<String, Integer>();
        Map<String, Integer> m = that.map;
        for(String key: m.keySet()){
            if(map.containsKey(key)) {
                andMap.put(key, totalRelevance(m.get(key), map.get(key)));
            }
        }

        return new WikiSearch(andMap);
    }

    // Computes the difference of two search results.
    public WikiSearch minus(WikiSearch that) {
        Map<String, Integer> minusMap = new HashMap<String, Integer>();
        Map<String, Integer> m = that.map;
        for(String key: map.keySet()){
            if(!m.containsKey(key)) {
                minusMap.put(key, map.get(key));
            }
        }

        return new WikiSearch(minusMap);
    }

    // Computes the relevance of a search with multiple terms.
    protected int totalRelevance(Integer rel1, Integer rel2) {

        return rel1 + rel2;
    }


    // Sort the results by relevance.
    public List<Entry<String, Integer>> sort() {
        Set<Entry<String, Integer>> entries = map.entrySet();
        List<Entry<String, Integer>> listOfEntries = new ArrayList<>(entries);
        Collections.sort(listOfEntries, Comparator.comparing(Map.Entry::getValue));
        return listOfEntries;
    }

    // Performs a search and makes a WikiSearch object.
    public static WikiSearch search(String term, Index index) {
        Map<String, Integer> map = index.getCounts(term);

        // Store the map locally in the WikiSearch
        return new WikiSearch(map);
    }


    public static void main(String[] args) throws IOException {

        // make a Index
        Index index = new Index(); // You might need to change this, depending on how your constructor works.

        // search for the first term
        String term1 = "java";
        System.out.println("Query: " + term1);
        WikiSearch search1 = search(term1, index);
        search1.print();

        // search for the second term
        String term2 = "programming";
        System.out.println("Query: " + term2);
        WikiSearch search2 = search(term2, index);
        search2.print();

        // compute the intersection of the searches
        System.out.println("Query: " + term1 + " AND " + term2);
        WikiSearch intersection = search1.and(search2);
        intersection.print();
    }
}