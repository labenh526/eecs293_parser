import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import static java.util.Objects.requireNonNull;

public final class Cache<T, V> {

    private Map<T, V> cache = new HashMap<>();


    V get(T key, Function<? super T, ? extends V> constructor) {
        return this.getCache().computeIfAbsent(requireNonNull(key, "Key cannot be null."),
                requireNonNull(constructor, "Constructor cannot be null."));
    }

    private Map<T, V> getCache() {
        return cache;
    }

}
