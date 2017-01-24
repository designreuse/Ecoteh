package com.salimov.yurii.util;

import com.salimov.yurii.util.cache.Cache;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public final class CacheTest {

    @Test
    public void putTest() {
        final long time = 1000L;
        final String key = "key";
        final Object object = new Object();

        Cache.put(key, object, time);
        assertNotNull(Cache.get(key));
        Cache.remove(key);
        assertNull(Cache.get(key));

        Cache.put(key, object, time, time);
        assertNotNull(Cache.get(key));
        Cache.remove(key);
        assertNull(Cache.get(key));

        Cache.put(key, object, time, time, time);
        assertNotNull(Cache.get(key));
        Cache.remove(key);
        assertNull(Cache.get(key));

        Cache.put(key, object, time, time, time, time);
        assertNotNull(Cache.get(key));
        Cache.remove(key);
        assertNull(Cache.get(key));

        Cache.put(key, object);
        assertNotNull(Cache.get(key));
        Cache.clear();
        assertNull(Cache.get(key));
    }

    @Test
    public void putAndSetAllTest() {
        final long time = 1000L;
        final String key = "key";

        final Map<String, Object> map = new HashMap<>();
        for(int i = 0; i < 10; i++) {
            map.put(key + i, new Object());
        }

        Cache.putAll(map, time);
        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
        Cache.clear();

        Cache.putAll(map, time, time);
        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
        Cache.clear();

        Cache.putAll(map, time, time, time);
        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
        Cache.clear();

        Cache.putAll(map, time, time, time, time);
        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
        Cache.clear();

        Cache.putAll(map);
        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
        Cache.clear();

        Cache.setAll(map);
        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
        Cache.clear();
    }

    @Test
    public void getEntriesToString() {
        Cache.clear();
        assertNotNull(Cache.getEntriesToString());
        assertEquals(Cache.getEntriesToString().size(), 1);

        final String key = "key";
        final Map<String, Object> map = new HashMap<>();

        Cache.putAll(map);
        assertNotNull(Cache.getEntriesToString());
        assertEquals(Cache.getEntriesToString().size(), 1);

        for(int i = 0; i < 10; i++) {
            map.put(key + i, new Object());
            assertNull(Cache.get(key + i));
        }

        Cache.putAll(map);
        assertNotNull(Cache.getEntriesToString());
        assertTrue(!Cache.getEntriesToString().isEmpty());

        for(int i = 0; i < 10; i++) {
            assertNotNull(Cache.get(key + i));
        }
    }

    /*@Test
    public void maxSizeTest() {
        Cache.setMaxSize(0);
        assertTrue(Cache.getMaxSize() == 1);

        Cache.setMaxSize(-1);
        assertTrue(Cache.getMaxSize() == 1);

        Cache.setMaxSize(1);
        assertTrue(Cache.getMaxSize() == 1);
    }*/
}
