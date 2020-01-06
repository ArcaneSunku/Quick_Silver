package atomix.toolbox;

import java.util.Map;
import java.util.LinkedHashMap;

/**
 * A Simple LUR Cache that will handle holding
 * anything we want to store a lot of, but we're
 * unsure how much space we'll need.
 *
 * @author ArcaneSunku
 * @since 12/27/2019
 */
public class Cache<K, V> extends LinkedHashMap<K, V> {
    private int size;

    private Cache(int size) {
        this.size = size;
    }

    public static <K, V> Cache<K, V> newInstance(int size) {
        return new Cache<K, V>(size);
    }

    public void setMaxSize(int size) {
        this.size = size;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }

}
