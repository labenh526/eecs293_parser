import java.util.Map;
import java.util.function.Function;

public final class Cache<T, V> {

    private Map<T, V> cache;

    private Cache(Map<T, V> cache) {
        this.cache = null;
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        try  {
            return this.getCache().computeIfAbsent(key, k -> constructor.apply(key));
        }
        catch (NullPointerException e) {
            System.out.println("Either T or constructor is null");
            return null;
        }
    }

    public Map<T, V> getCache() {
        return cache;
    }

}
