import java.util.Map;

public final class Cache<T, V> {

    private Map<T, V> cache = null;

    private Cache(Map<T, V> cache) {
        this.cache = cache;
    }

    private V get(T key, Function<? super T, ? extends V> constructor) {
        return null;
    }

    private V add(T key, Function<? super T, ? extends V> constructor) {
        return null;
    }
}
