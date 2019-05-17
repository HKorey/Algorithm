package com.hquery.algorithm.dataStructure;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author hquery.huang
 * 2018/9/23 17:21
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private int cacheSize;

    public LRUCache(int cacheSize) {
        super(16, 0.75f, true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > cacheSize;
    }

    public static void main(String[] args) {
        LRUCache<String, String> lruCache = new LRUCache(3);
        lruCache.put("key", "value");
        System.out.println(lruCache.size());

        lruCache.put("key2", "value");
        lruCache.put("key3", "value");
        lruCache.put("key4", "value");
        lruCache.put("key5", "value");
        System.out.println(lruCache.size());
    }
}
