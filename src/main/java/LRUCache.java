import java.util.*;
import java.util.stream.Stream;

public class LRUCache<K, V> implements ICache<K, V> {

    private class Node<T, E> {
        Node<T, E> next;
        Node<T, E> prev;
        T key;
        E value;

        Node(Node<T, E> prev, Node<T, E> next, T key, E value) {
            this.prev = prev;
            this.next = next;
            this.key = key;
            this.value = value;
        }

        Node() {
            next = prev = null;
            key = null;
            value = null;
        }
    }

    private int maxSize;
    private Node<K, V> mru;
    private Node<K, V> lru;
    private Map<K, Node<K, V>> cache;

    public LRUCache(int maxSize) {
        this.maxSize = maxSize;
        cache = new HashMap<>();
        lru = new Node<K, V>();
        mru = new Node<K, V>();
    }

    public K put(K key, V value) {
        if (cache.containsKey(key)) {
            get(key);
            return null;
        }

        Node<K, V> nd = new Node<>(mru, null, key, value);
        mru.next = nd;
        mru = nd;

        if (cache.size() == 0) {
            lru = mru;
        }

        cache.put(key, nd);

        if (cache.size() > maxSize) {
            cache.remove(lru.key);
            K res = lru.key;
            lru = lru.next;
            lru.prev = null;
            return res;
        }

        return null;
    }

    public V get(K key) {
        if (cache.get(key) == null) {
            return null;
        }

        Node<K, V> got = cache.get(key);
        if (got.key == mru.key) {
            return mru.value;
        }

        Node<K, V> nxt = got.next;
        Node<K, V> prv = got.prev;


        if (got.key == lru.key) {
            nxt.prev = null;
            lru = nxt;
        } else {
            prv.next = nxt;
            nxt.prev = prv;
        }


        got.prev = mru;
        mru.next = got;
        mru = got;
        mru.next = null;

        return got.value;
    }

    public Stream<K> keys() {
        return cache.keySet().stream();
    }

    public int size() {
        return cache.size();
    }
}
