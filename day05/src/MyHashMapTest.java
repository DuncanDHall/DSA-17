import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author downey
 *
 */
public class MyHashMapTest {

    protected Map<String, Integer> map;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        map = new MyHashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        map.put("Three", 3);
        map.put(null, 0);
    }

    /**
     * Test method for {@link MyLinearMap#clear()}.
     */
    @Test
    public void testClear() {
        map.clear();
        assertThat(map.size(), is(0));
    }

    /**
     * Test method for {@link MyLinearMap#containsKey(java.lang.Object)}.
     */
    @Test
    public void testContainsKey() {
        assertThat(map.containsKey("Three"), is(true));
        assertThat(map.containsKey(null), is(true));
        assertThat(map.containsKey("Four"), is(false));
    }

    /**
     * Test method for {@link MyLinearMap#containsValue(java.lang.Object)}.
     */
    @Test
    public void testContainsValue() {
        assertThat(map.containsValue(3), is(true));
        assertThat(map.containsValue(0), is(true));
        assertThat(map.containsValue(4), is(false));
    }

    /**
     * Test method for {@link MyLinearMap#get(java.lang.Object)}.
     */
    @Test
    public void testGet() {
        assertThat(map.get("Three"), is(3));
        assertThat(map.get(null), is(0));
        assertThat(map.get("Four"), nullValue());
    }

    /**
     * Test method for {@link MyLinearMap#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertThat(map.isEmpty(), is(false));
        map.clear();
        assertThat(map.isEmpty(), is(true));
    }

    /**
     * Test method for {@link MyLinearMap#keySet()}.
     */
    @Test
    public void testKeySet() {
        Set<String> keySet = map.keySet();
        assertThat(keySet.size(), is(4));
        assertThat(keySet.contains("Three"), is(true));
        assertThat(keySet.contains(null), is(true));
        assertThat(keySet.contains("Four"), is(false));
    }

    /**
     * Test method for {@link MyLinearMap#put(java.lang.Object, java.lang.Object)}.
     */
    @Test
    public void testPut() {
        map.put("One", 11);
        assertThat(map.size(), is(4));
        assertThat(map.get("One"), is(11));

        map.put("Five", 5);
        assertThat(map.size(), is(5));
        assertThat(map.get("Five"), is(5));
    }

    /**
     * Test method for resizing the HashMap.
     */
    @Test
    public void testResize() {
        for (int i = 0; i < 10000; i++)
            map.put(Integer.toString(i), i);
        MyHashMap temp = (MyHashMap) map;
        assertThat(temp.maps.size(), is(16384));
        for (int i = 0; i < 6000; i++)
            map.remove(Integer.toString(i));
        assertThat(temp.maps.size(), is(8192));
        for (int i = 6000; i < 9960; i++)
            map.remove(Integer.toString(i));
        assertThat(temp.maps.size(), is(128));
        for (int i = 9960; i < 9990; i++)
            map.remove(Integer.toString(i));
        assertThat(temp.maps.size(), is(32));
        for (int i = 9990; i < 9998; i++)
            map.remove(Integer.toString(i));
        assertThat(temp.maps.size(), is(16));
    }

    /**
     * Test method for {@link MyLinearMap#putAll(java.util.Map)}.
     */
    @Test
    public void testPutAll() {
        Map<String, Integer> m = new HashMap<>();
        m.put("Six", 6);
        m.put("Seven", 7);
        m.put("Eight", 8);
        map.putAll(m);
        assertThat(map.size(), is(7));
    }

    /**
     * Test method for {@link MyLinearMap#remove(java.lang.Object)}.
     */
    @Test
    public void testRemove() {
        map.remove("One");
        assertThat(map.size(), is(3));
        assertThat(map.get("One"), nullValue());
    }

    /**
     * Test method for {@link MyLinearMap#size()}.
     */
    @Test
    public void testSize() {
        assertThat(map.size(), is(4));
    }

    /**
     * Test method for {@link MyLinearMap#values()}.
     */
    @Test
    public void testValues() {
        Collection<Integer> keySet = map.values();
        assertThat(keySet.size(), is(4));
        assertThat(keySet.contains(3), is(true));
        assertThat(keySet.contains(0), is(true));
        assertThat(keySet.contains(4), is(false));
    }
}