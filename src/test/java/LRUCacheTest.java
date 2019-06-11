import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LRUCacheTest {
    private LRUCache<Integer, Integer> cache;

    public LRUCacheTest() {
        cache = new LRUCache<>(4);
    }

    @Test
    public void GetFromEmptyCacheTest() {
        assertNull(cache.get(0));
    }

    @Test
    public void SampleTest() {
        assertNull(cache.put(1, 1));
        assertNull(cache.put(2, 2));
        assertNull(cache.put(3, 3));
        assertNull(cache.put(4, 4));
        assertEquals(Integer.valueOf(1), cache.get(1));
        assertEquals(Integer.valueOf(2), cache.get(2));
        assertEquals(Integer.valueOf(3), cache.get(3));
        assertEquals(Integer.valueOf(4), cache.get(4));
    }

    @Test
    public void LRUGetTest() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);

        assertEquals(Integer.valueOf(1), cache.put(5, 5));
        assertEquals(Integer.valueOf(2), cache.put(6, 6));
        assertEquals(Integer.valueOf(3), cache.put(7, 7));
        assertEquals(Integer.valueOf(4), cache.put(8, 8));
    }

    @Test
    public void LRUPutTest() {
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.put(1, 1);

        assertEquals(Integer.valueOf(2), cache.put(5, 5));

        cache.put(3, 3);

        assertEquals(Integer.valueOf(4), cache.put(6, 6));
    }

}
