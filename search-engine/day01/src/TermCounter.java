import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TermCounter {

	private Map<String, Integer> map;
	private String label;
	private HashSet<String> stopWords;

	public TermCounter(String label) {
		this.label = label;
		this.map = new HashMap<String, Integer>();

		stopWords = new HashSet<String>();


        try {
            BufferedReader fileReader = new BufferedReader(new FileReader("resources/stopwords.txt"));
            String word;
            while ((word = fileReader.readLine()) != null) {
                stopWords.add(word);
            }
        } catch (IOException e) {
            System.out.println("Issue with reading resources/stopwords.txt");
            e.printStackTrace();
        }

    }

	public String getLabel() {
		return label;
	}

	public int size() {
	    int totalwords = 0;
	    for(String term: map.keySet()){
	        totalwords += map.get(term);
        }
		return totalwords;
	}

	public void processElements(Elements paragraphs) {
		for (Node node: paragraphs) {
			processTree(node);
		}
	}

	public void processTree(Node root) {
		// NOTE: we could use select to find the TextNodes, but since
		// we already have a tree iterator, let's use it.
		for (Node node: new WikiNodeIterable(root)) {
			if (node instanceof TextNode) {
				processText(((TextNode) node).text());
			}
		}
	}

	public void processText(String text) {
		// replace punctuation with spaces, convert to lower case, and split on whitespace
		String[] array = text.replaceAll("\\pP", " ").toLowerCase().split("\\s+");

		for(String word: array){
		    if (!stopWords.contains(word)) incrementTermCount(word);
		}
		// increment the count for each term not included in stopwords
	}

	public void incrementTermCount(String term) {
        put(term, get(term)+1);
    }

	public void put(String term, int count) {
		map.put(term, count);
	}

	public Integer get(String term) {
		Integer freq = map.get(term);
		if(freq == null){
		    return 0;
        }
        else{
            return freq;

        }
	}

	public Set<String> keySet() {
		return map.keySet();
	}

	public void printCounts() {
		for (String key: keySet()) {
			Integer count = get(key);
			System.out.println(key + ", " + count);
		}
		System.out.println("Total of all counts = " + size());
	}

	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";

		WikiFetcher wf = new WikiFetcher();
		Elements paragraphs = wf.fetchWikipedia(url);

		TermCounter counter = new TermCounter(url.toString());
		counter.processElements(paragraphs);
		counter.printCounts();
	}
}
