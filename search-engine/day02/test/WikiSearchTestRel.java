import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author downey
 *
 */
public class WikiSearchTestRel {

    private WikiSearch search1;
    private WikiSearch search2;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        Map<String, Integer> map1 = new HashMap<String, Integer>();
        map1.put("Page1", 1);
        map1.put("Page2", 2);
        map1.put("Page3", 3);
        search1 = new WikiSearch(map1, 4);

//        Map<String, Integer> map2 = new HashMap<String, Integer>();
//        map2.put("Page2", 4);
//        map2.put("Page3", 5);
//        map2.put("Page4", 7);
//        search2 = new WikiSearch(map2, 4);
    }

    @Test
    public void testRel() {
//        assertThat(search1.getRelevance("Page1"), is(1.3862943611198906));
//        assertThat(search1.getRelevance("Page2"), is(1.3862943611198906));
//        assertThat(search1.getRelevance("Page3"), is(1.3862943611198906));

    }
}
