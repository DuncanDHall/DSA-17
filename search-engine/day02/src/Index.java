import org.jsoup.select.Elements;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.*;

public class Index {

	// Index: map of words to URL and their counts
	private Map<String, Set<TermCounter>> index = new HashMap<String, Set<TermCounter>>();
    Jedis jedis;

    public Index() throws IOException {
        jedis = JedisMaker.make();
    }

    public void add(String term, TermCounter tc) {
		// if we're seeing a term for the first time, make a new Set
		// otherwise we can add the term to an existing Set
        Set<TermCounter> thing = get(term);
		if(thing != null){
		    thing.add(tc);
        }
        else{
		    Set<TermCounter> first = new HashSet<TermCounter>();
		    first.add(tc);
		    index.put(term, first);
        }
	}

	public Set<TermCounter> get(String term) {
		Set temp = index.get(term);
		return temp;
	}

	public void indexPage(String url, Elements paragraphs) {

	    Transaction t = jedis.multi();
	    String hashName = "TermCounter: " + url;

		// make a TermCounter and count the terms in the paragraphs
		TermCounter counter = new TermCounter(url);
		counter.processElements(paragraphs);

		// for each term in the TermCounter, add the TermCounter to the redis db
//        for(String term: counter.keySet()){
//            t.hset(hashName, term, counter.get(term).toString());
//            t.sadd("urlSet: " + term, url);
//        }
        for(String term: counter.keySet()){
            t.hset("TermCounter: " + url, term, counter.get(term).toString());
            t.sadd("urlSet: " + term, url);
        }

        t.exec();
	}

	public void printIndex() {
		// loop through the search terms
		for (String term: keySet()) {
			System.out.println(term);

			// for each term, print the pages where it appears
			Set<TermCounter> tcs = get(term);
			for (TermCounter tc: tcs) {
				Integer count = tc.get(term);
				System.out.println("    " + tc.getLabel() + " " + count);
			}
		}
	}

	public Set<String> keySet() {
		return index.keySet();
	}


    public Map<String,Integer> getCounts(String key) {
        Map<String, Integer> res = new HashMap<String, Integer>();

        Set<String> urls = jedis.smembers("urlSet: " + key);
        System.out.println();
        for (String url: urls) {
            res.put(url, Integer.parseInt(jedis.hget("TermCounter: " + url, key)));
        }
        return res;
    }

	public static void main(String[] args) throws IOException {

		WikiFetcher wf = new WikiFetcher();
		Index indexer = new Index();


        String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		Elements paragraphs = wf.fetchWikipedia(url);
		indexer.indexPage(url, paragraphs);

		url = "https://en.wikipedia.org/wiki/Programming_language";
		paragraphs = wf.fetchWikipedia(url);
		indexer.indexPage(url, paragraphs);

		indexer.printIndex();
	}
}
