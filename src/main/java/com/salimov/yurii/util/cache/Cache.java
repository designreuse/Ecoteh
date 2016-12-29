package com.salimov.yurii.util.cache;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * The class implements a set of methods for working with cache.
 *
 * @author Yurii Salimov (yurii.alex.salimov@gmail.com)
 * @version 1.0
 */
public final class Cache {

    /**
     * The time to delay first execution.
     */
    private final static long SCHEDULER_INITIAL_DELAY = 5L; // 5 hours

    /**
     * The period between successive executions.
     */
    private final static long SCHEDULER_PERIOD = 1L; // 1 hour

    /**
     * The default lifetime of objects (milliseconds).
     * 864000000 milliseconds = 10 days
     */
    private static final long DEFAULT_TIMEOUT = 864000000L;


    /**
     * The maximum size of objects which can be stored in the cache.
     */
    private static int maxSize = 100;

    /**
     * The map where can be stored some objects.
     */
    private static volatile Map<Key, Object> cacheMap =
            new ConcurrentHashMap<>();

    /**
     * Static constructor.
     * Creates and starts ScheduledExecutorService.
     */
    static {
        final ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor(
                        new ThreadDaemonFactory()
                );
        scheduler.scheduleAtFixedRate(
                new Command(),
                Cache.SCHEDULER_INITIAL_DELAY,
                Cache.SCHEDULER_PERIOD,
                TimeUnit.MINUTES
        );
    }

    /**
     * Private constructor.
     */
    private Cache() {
    }

    /**
     * Saves object in the cache.
     *
     * @param key          a object key in the cache.
     * @param object       a object to save.
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     * @return The saving object.
     */
    public static <T> Object put(
            final T key,
            final Object object,
            final long milliseconds
    ) {
        Object savingObject = object;
        if (key != null && object != null) {
            savingObject = cacheMap.put(
                    new Key<>(
                            key,
                            milliseconds
                    ),
                    object
            );
        }
        return savingObject != null ? savingObject : object;
    }

    /**
     * Saves object in the cache.
     *
     * @param key          a object key in the cache.
     * @param object       a object to save.
     * @param seconds      a lifetime of objects (seconds).
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     * @return The saving object.
     */
    public static <T> Object put(
            final T key,
            final Object object,
            final long seconds,
            final long milliseconds
    ) {
        return put(
                key,
                object,
                milliseconds + 1000 * seconds
        );
    }

    /**
     * Saves object in the cache.
     *
     * @param key          a object key in the cache.
     * @param object       a object to save.
     * @param minutes      a lifetime of objects (minutes).
     * @param seconds      a lifetime of objects (seconds).
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     * @return The saving object.
     */
    public static <T> Object put(
            final T key,
            final Object object,
            final long minutes,
            final long seconds,
            final long milliseconds
    ) {
        return put(
                key,
                object,
                seconds + 60 * minutes,
                milliseconds
        );
    }

    /**
     * Saves object in the cache.
     *
     * @param key          a object key in the cache.
     * @param object       a object to save.
     * @param hours        a lifetime of objects (hours).
     * @param minutes      a lifetime of objects (minutes).
     * @param seconds      a lifetime of objects (seconds).
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     * @return The saving object.
     */
    public static <T> Object put(
            final T key,
            final Object object,
            final long hours,
            final long minutes,
            final long seconds,
            final long milliseconds
    ) {
        return put(
                key,
                object,
                minutes + 60 * hours,
                seconds,
                milliseconds
        );
    }

    /**
     * Saves object in the cache with default lifetime.
     *
     * @param key    a object key in the cache.
     * @param object a object to save.
     * @param <T>    a type of key.
     * @return The saving object.
     */
    public static <T> Object put(final T key, final Object object) {
        final long milliseconds = -1;
        return put(
                key,
                object,
                milliseconds
        );
    }

    /**
     * Saves objects in the cache.
     * Saves objects if map is not empty.
     *
     * @param map          a map with objects to save.
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     */
    public static <T> void putAll(
            final Map<T, Object> map,
            final long milliseconds
    ) {
        if (map != null && !map.isEmpty()) {
            for (Map.Entry<T, Object> entry : map.entrySet()) {
                put(
                        entry.getKey(),
                        entry.getValue(),
                        milliseconds
                );
            }
        }
    }

    /**
     * Saves objects in the cache.
     *
     * @param map          a map with objects to save.
     * @param seconds      a lifetime of objects (seconds).
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     */
    public static <T> void putAll(
            final Map<T, Object> map,
            final long seconds,
            final long milliseconds
    ) {
        putAll(
                map,
                milliseconds + 1000 * seconds
        );
    }

    /**
     * Saves objects in the cache.
     *
     * @param map          a map with objects to save.
     * @param minutes      a lifetime of objects (minutes).
     * @param seconds      a lifetime of objects (seconds).
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     */
    public static <T> void putAll(
            final Map<T, Object> map,
            final long minutes,
            final long seconds,
            final long milliseconds
    ) {
        putAll(
                map,
                seconds + 60 * minutes,
                milliseconds
        );
    }

    /**
     * Saves objects in the cache.
     *
     * @param map          a map with objects to save.
     * @param hours        a lifetime of objects (hours).
     * @param minutes      a lifetime of objects (minutes).
     * @param seconds      a lifetime of objects (seconds).
     * @param milliseconds a lifetime of objects (milliseconds).
     * @param <T>          a type of key.
     */
    public static <T> void putAll(
            final Map<T, Object> map,
            final long hours,
            final long minutes,
            final long seconds,
            final long milliseconds
    ) {
        putAll(
                map,
                minutes + 60 * hours,
                seconds,
                milliseconds
        );
    }

    /**
     * Saves objects in the cache with default lifetime..
     *
     * @param map a map with objects to save.
     * @param <T> a type of key.
     */
    public static <T> void putAll(final Map<T, Object> map) {
        long milliseconds = -1;
        putAll(map, milliseconds);
    }

    /**
     * Returns object from cache with key.
     * Returns {@code null} if key is {@code null}.
     *
     * @param key a object key in the cache.
     * @param <T> a type of key.
     * @return The object with key or {@code null}.
     */
    public static <T> Object get(final T key) {
        Object object = null;
        if (key != null) {
            object = cacheMap.get(
                    new Key<>(key)
            );
        }
        return object;
    }

    /**
     * Returns all objects from cache with subKey.
     *
     * @param subKey a object key in the cache.
     * @return The objects with key or empty list.
     */
    public static Collection<Object> getAll(final String subKey) {
        return cacheMap.keySet()
                .stream()
                .filter(
                        key -> key.getKey()
                                .toString()
                                .contains(subKey)
                ).map(key -> cacheMap.get(key))
                .collect(Collectors.toList());
    }

    /**
     * Removes object from cache with key.
     * Removes object if key is not {@code null}.
     *
     * @param key a object key in the cache.
     * @param <T> a type of key.
     */
    public static <T> void remove(final T key) {
        if (key != null) {
            cacheMap.remove(
                    new Key<>(key)
            );
        }
    }

    /**
     * Removes all objects from the cache
     * if they key contains the string subKey.
     * Removes objects if subKey is not blank.
     * The objects are removed in a parallel thread.
     *
     * @param subKey a key string.
     */
    public static void removeAll(final String... subKey) {
        if (subKey != null && subKey.length > 0) {
            new Thread(() -> {
                final Collection<Key> keys = new ArrayList<>(cacheMap.keySet());
                for (String _key : subKey) {
                    if (isNotBlank(_key)) {
                        keys.stream()
                                .filter(
                                        key -> key.getKey()
                                                .toString()
                                                .contains(_key)
                                ).forEach(key -> cacheMap.remove(key));
                    }
                }
            }).start();
        }
    }

    /**
     * Sets map with objects.
     *
     * @param map a map with objects.
     * @param <T> a type of key.
     */
    public static <T> void setAll(final Map<T, Object> map) {
        cacheMap = new ConcurrentHashMap<>();
        for (Map.Entry<T, Object> entry : map.entrySet()) {
            put(
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }

    /**
     * Clears the cache.
     */
    public static void clear() {
        cacheMap.clear();
    }

    /**
     * Clears objects by class.
     *
     * @param object a objects class to remove.
     */
    public static void clear(final Class object) {
        cacheMap.entrySet()
                .stream()
                .filter(
                        entry -> entry.getValue()
                                .getClass()
                                .equals(object)
                )
                .forEach(
                        entry -> cacheMap.remove(
                                entry.getKey()
                        )
                );
    }

    /**
     * Checks if exist object with the key in the cache.
     *
     * @param key a object key in the cache.
     * @param <T> a type of key.
     * @return {@code true} if object is exist, {@code false} otherwise.
     */
    public static <T> boolean exist(final T key) {
        return cacheMap.containsKey(new Key<>(key));
    }

    /**
     * Returns information about objects in cache.
     * Information about cache saved in cache too.
     *
     * @return The maps with entries.
     */
    public static Map<String, String> getEntriesToString() {
        final String key = "Cache information";
        Map<String, String> result = (Map<String, String>) get(key);
        if ((result == null) || (result.size() != cacheMap.size())) {
            result = getNewEntriesToString(key);
            put(key, result);
        }
        return result;
    }

    /**
     * Returns information about objects in cache.
     *
     * @param key a object key in the cache.
     * @return The maps with entries.
     */
    private static Map<String, String> getNewEntriesToString(final String key) {
        Map<String, String> result = new HashMap<>();
        for (Map.Entry<Key, Object> entry : cacheMap.entrySet()) {
            result.put(
                    entry.getKey()
                            .getKey()
                            .toString(),
                    entry.getValue()
                            .getClass()
                            .getName()
            );
        }
        result.put(
                key,
                Map.class.getName()
        );
        return result;
    }

    /**
     * Gets size of objects which storing in the cache.
     *
     * @return The size of objects which storing in the cache.
     */
    public static int getSize() {
        return cacheMap.size();
    }

    /**
     * Gets maximum size of objects which
     * can be stored in the cache.
     *
     * @return The maximum size of objects
     * which can be stored in the cache.
     */
    public static int getMaxSize() {
        return maxSize;
    }

    /**
     * Sets maximum size of objects which
     * can be stored in the cache.
     *
     * @param maxMapSize a maximum size of objects which
     *                   can be stored in the cache.
     */
    public static void setMaxSize(final int maxMapSize) {
        maxSize = maxMapSize > 0 ? maxMapSize : 1;
    }

    /**
     * The class implements a set of methods
     * for working with Key object in the cache.
     *
     * @param <T> a type of key.
     */
    private static class Key<T> implements Comparable {

        /**
         * Value of the Key.
         */
        private final T key;

        /**
         * The lifetime of objects.
         */
        private final long timeout;

        /**
         * Constructor.
         *
         * @param key          a object key in the cache.
         * @param milliseconds a lifetime of objects (milliseconds).
         */
        private Key(final T key, final long milliseconds) {
            this.key = key;
            this.timeout = System.currentTimeMillis()
                    + (milliseconds > 0 ? milliseconds : DEFAULT_TIMEOUT);
        }

        /**
         * Constructor.
         *
         * @param key a object key in the cache.
         */
        private Key(final T key) {
            this(key, DEFAULT_TIMEOUT);
        }

        /**
         * Gets key value.
         *
         * @return The key.
         */
        private T getKey() {
            return this.key;
        }

        /**
         * Checks whether the object is dead.
         *
         * @return Returns true if object is dead, otherwise returns false.
         */
        private boolean isDead() {
            return System.currentTimeMillis() > this.timeout;
        }

        /**
         * Checks whether the object is alive.
         *
         * @return Returns true if object is alive, otherwise returns false.
         */
        private boolean isLive() {
            return !isDead();
        }

        /**
         * Indicates whether some other object is "equal to" this one.
         *
         * @param object a reference object with which to compare.
         * @return Returns true if this object is the same
         * as the object argument, otherwise returns false.
         */
        @Override
        public boolean equals(final Object object) {
            boolean result = false;
            if (object != null) {
                if (this == object) {
                    result = true;
                } else if (this.getClass() == object.getClass()) {
                    final Key other = (Key) object;
                    result = this.key != null ?
                            this.key.equals(other.key)
                            : other.key == null;
                }
            }
            return result;
        }

        /**
         * Returns a string representation of the object.
         *
         * @return A string representation of the object.
         */
        @Override
        public String toString() {
            return this.key.toString() + "\nTimeout = " + this.timeout;
        }

        /**
         * Returns a hash code value for the object.
         *
         * @return The hash code value for this object.
         */
        @Override
        public int hashCode() {
            return this.key != null ? this.key.hashCode() : 0;
        }

        /**
         * Compares this object with the specified object for order.
         *
         * @param object a object to be compared.
         * @return A negative integer, zero, or a positive
         * integer as this object is less than, equal to,
         * or greater than the specified object.
         */
        @Override
        public int compareTo(final Object object) {
            int result = 0;
            if (object == null) {
                result = -1;
            } else {
                final Key other = (Key) object;
                if (this.timeout < other.timeout) {
                    result = 1;
                } else if (this.timeout > other.timeout) {
                    result = -1;
                }
            }
            return result;
        }
    }

    /**
     * An object that creates new threads on demand.
     */
    private static class ThreadDaemonFactory implements ThreadFactory {

        /**
         * Constructs a new Thread.
         *
         * @param runnable a runnable to be executed by new thread instance.
         * @return constructed thread, or null if the request
         * to create a thread is rejected.
         */
        @Override
        public Thread newThread(final Runnable runnable) {
            final Thread thread = new Thread(runnable);
            thread.setDaemon(true);
            return thread;
        }
    }

    /**
     * The class implements a set of methods for checking cache at old objects.
     */
    private static class Command implements Runnable {

        /**
         * Used to create a SenderImpl thread, starting
         * the thread causes the object's run method
         * to be called in that separately executing thread.
         */
        @Override
        public void run() {
            removeDeadObject();
            cleanCacheMapSize();
        }

        /**
         * Removes dead objects from cache.
         */
        private void removeDeadObject() {
            cacheMap.keySet()
                    .stream()
                    .filter(Key::isDead)
                    .forEach(key -> cacheMap.remove(key));
        }

        /**
         * Cleans cache when cacheMap.size() great Cache.maxSize.
         */
        private void cleanCacheMapSize() {
            if (cacheMap.size() > Cache.maxSize) {
                final List<Key> keys = new ArrayList<>(cacheMap.keySet());
                Collections.sort(keys);
                cleanToNormalSize(keys);
            }
        }

        /**
         * Cleans cache. Leaves last Cache.maxSize / 2 objects.
         *
         * @param keys a keys list.
         */
        private static void cleanToNormalSize(final List<Key> keys) {
            for (Key key : keys) {
                cacheMap.remove(key);
                if (cacheMap.size() <= maxSize / 2) {
                    break;
                }
            }
        }
    }
}
