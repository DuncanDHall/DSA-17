import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

public class JedisMaker {

	/**
	 * Make a Jedis object and authenticate it.
	 *
	 * @return
	 * @throws IOException
	 */
	public static Jedis make() throws IOException {
		// assemble the directory name
		String slash = File.separator;
		String filename = System.getProperty("user.dir") + slash +
				"resources" + slash + "redis_url.txt";

		System.out.println(filename);

	    StringBuilder sb = new StringBuilder();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e1) {
			System.out.println("File not found: " + filename);
			printInstructions();
			return null;
		}

		while (true) {
			String line = br.readLine();
			if (line == null) break;
			sb.append(line);
		}
		br.close();

		URI uri;
		try {
			uri = new URI(sb.toString());
		} catch (URISyntaxException e) {
			System.out.println("Reading file: " + filename);
			System.out.println("It looks like this file does not contain a valid URI.");
			printInstructions();
			return null;
		}
		String host = uri.getHost();
		int port = uri.getPort();

		String[] array = uri.getAuthority().split("[:@]");
		String auth = array[1];

		Jedis jedis = new Jedis(host, port);

		try {
			jedis.auth(auth);
		} catch (Exception e) {
			System.out.println("Trying to connect to " + host);
			System.out.println("on port " + port);
			System.out.println("with authcode " + auth);
			System.out.println("Got exception " + e);
			printInstructions();
			return null;
		}
		return jedis;
	}

	/**
	 * Prints usage instructions
	 */
	private static void printInstructions() {
		System.out.println("");
		System.out.println("To connect to RedisToGo, you have to provide a file called");
		System.out.println("redis_url.txt that contains the URL of your Redis server.");
		System.out.println("If you select an instance on the RedisToGo web page,");
		System.out.println("you should see a URL that contains the information you need:");
		System.out.println("redis://redistogo:AUTH@HOST:PORT");
		System.out.println("Create a file called redis_url.txt in the src/resources");
		System.out.println("directory, and paste in the URL.");
	}

    public void clearAll(Jedis jedis) {
        Set<String> keys = jedis.keys("*");
        Transaction t = jedis.multi();
        for (String key: keys) {
            t.del(key);
        }
        t.exec();
    }

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
        // TODO

		Jedis jedis = make();

        // clearAll(jedis);
		jedis.flushAll();

		// String
        /*
        - Create a new key-value pair
        - Retrieve that value
        - Print it
         */
        jedis.set("greeting", "hello! butt");
        System.out.println(jedis.get("greeting"));

		// Set
        /*
        - Create a new jedis set named "sorts" with the values
        "quick", "merge", "heap"
        - Print whether "merge" is a member of that set
        - Print whether "insertion" is a member of that set
         */
        jedis.sadd("sorts", "quick", "merge", "heap");
        System.out.println(jedis.smembers("sorts"));

		// List
        /*
        - Create a new list named "lineards" with the elements
        "stacks", "queues", "lists"
        - Print the elements at the 0th and 2nd indices of that list
         */
        jedis.lpush("lineards", "stacks", "queues", "lists");
        System.out.println(jedis.lindex("lineards", 0));
        System.out.println(jedis.lindex("lineards", 2));

		// Hash
		/*
		- Create a new hash named "myhash", mapping the key "word1" to
		the value "2", but do not use the string literal
		- Increment that value by 1 without calling hget
		- Increment the nonexistent key "word2" by 1
		- Retrieve and print the values at both keys in the hash "myhash"
		*/
		jedis.hincrBy("myhash", "word1", 2);  // set initial value (increment from zero)
        jedis.hincrBy("myhash", "word1", 1);
        jedis.hincrBy("myhash", "word2", 1);  // another initial put
        System.out.println(jedis.hget("myhash", "word1"));
        System.out.println(jedis.hget("myhash", "word2"));


	    jedis.close();
	}
}
