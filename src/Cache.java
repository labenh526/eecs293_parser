import java.util.Map;
import java.util.function.Function;

public final class Cache<T, V> {

    private Map<T, V> cache;

    private Cache(Map<T, V> cache) {
        this.cache = null;
    }

    V get(T key, Function<? super T, ? extends V> constructor) {
        System.out.println("Function is called");
        if(key == null){
            throw new NullPointerException("Key must not be null");
        }
        else if (constructor == null){
            throw new NullPointerException("Constructor must not be null");
        }
        if (cache.containsValue(key)){
            System.out.println("Key was inside cache");
            return cache.get(key);
        }
        else{
            System.out.println("new item needs to be generated");
            cache.put(key,constructor.apply(key));
        }
    throw new NullPointerException("Incorrect use of get function");
    }

}
