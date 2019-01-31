import java.util.Map;
import java.util.function.Function;

public final class Cache<T, V> {

    private Map<T, V> cache;

    /* Changed constructor back to private as per the instructions.
     * However, currently the cache only works with a public constructor. */
    private Cache(Map<T, V> cache) {
        this.cache = cache;
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        try  {
            return this.getCache().computeIfAbsent(key, k -> constructor.apply(key));
        }
        catch (NullPointerException e) {
            System.out.println("T or constructor is null");
            return null;
        }
    }

    public Map<T, V> getCache() {
        return cache;
    }

}
