import java.util.stream.Stream;

public interface ICache<K, V> {
    K put(K key, V value);

    V get(K key);

    Stream<K> keys();
}
