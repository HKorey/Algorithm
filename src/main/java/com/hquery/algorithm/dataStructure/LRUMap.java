package com.hquery.algorithm.dataStructure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hquery.huang
 * 2018/9/25 15:17
 */
public class LRUMap<K, V> {

    /**
     * 存储数据
     */
    private final Map<K, Node<K, V>> nodes;

    /**
     * 最大缓存大小
     */
    private int cacheSize;

    /**
     * 当前缓存大小
     */
    private int currentSize;

    /**
     * 头节点
     */
    private Node<K, V> header;

    /**
     * 尾节点
     */
    private Node<K, V> tailer;

    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        this.currentSize = 0;
        this.nodes = new HashMap<>(cacheSize);
    }

    public void put(K key, V val) {
        Node<K, V> node = nodes.get(key);
        if (node == null) {
            // 判断是否已经超过容量
            if (currentSize >= cacheSize) {
                // 将最后的节点删掉
                if (tailer != null) {
                    nodes.remove(tailer.getKey());
                    removeLast();
                }
            } else {
                currentSize++;
            }
            node = new Node<>(key, val);
        }
        // 刷新原缓存
        nodes.put(key, node);
        // 将该缓存节点挪至header
        move2Head(node);
    }

    public V get(K key) {
        Node<K, V> node = nodes.get(key);
        if (node == null)
            return null;
        move2Head(node);
        return node.value;
    }

    /**
     * 移动至链表头
     *
     * @param node
     * @return void
     * @author hquery
     * 2019/4/26 15:13:39
     */
    private void move2Head(Node<K, V> node) {
        if (node == header)
            return;
        // 如果node节点是非初始化的，则链接node的pre节点的next至node的next节点
        if (node.pre != null)
            node.pre.next = node.next;
        // 如果node节点是非初始化的，则链接node的next节点的pre至node的pre节点
        if (node.next != null)
            node.next.pre = node.pre;
        // 如果node节点在最后
        if (tailer == node)
            tailer = node.pre;
        // header节点存在的情况
        if (header != null) {
            header.pre = node;
            node.next = header;
        }
        header = node;
        node.pre = null;
        if (tailer == null) {
            tailer = header;
        }
    }

    private void removeLast() {
        tailer = tailer.pre;
        tailer.next = header;
    }


    class Node<K, V> {

        private K key;

        private V value;

        Node<K, V> pre;

        Node<K, V> next;

        public Node(K key, V val) {
            this.key = key;
            this.value = val;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        LRUMap<String, String> map = new LRUMap(3);
        map.put("1", "11");
        map.put("2", "22");
        map.put("3", "33");
        map.put("4", "44");
        System.out.println(map.get("3"));
    }

}
