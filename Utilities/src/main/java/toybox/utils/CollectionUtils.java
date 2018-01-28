package toybox.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public final class CollectionUtils {

    static class TemporaryMapEntry<K, V> implements Map.Entry<K, V> {

        private final K key;
        private final V value;

        TemporaryMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            throw new UnsupportedOperationException("This class should only be used as a temporary");
        }

    }

    public static <T> boolean hasValue(final T[] elements, final T element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (T e: elements) {
            if (Objects.equals(element, e)) {
                return true;
            }
        }
        return false;
    }
    
    public static <T> boolean hasValue(final char[] elements, final char element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (char e: elements) {
            if (e == element) {
                return true;
            }
        }
        return false;
    }
    
    public static <T> boolean hasValue(final short[] elements, final short element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (short e: elements) {
            if (e == element) {
                return true;
            }
        }
        return false;
    }
    
    public static <T> boolean hasValue(final int[] elements, final int element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (int e: elements) {
            if (e == element) {
                return true;
            }
        }
        return false;
    }
    
    public static <T> boolean hasValue(final long[] elements, final long element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (long e: elements) {
            if (e == element) {
                return true;
            }
        }
        return false; 
    }
    
    public static <T> boolean hasValue(final float[] elements, final float element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (float e: elements) {
            if (Float.compare(e, element) == 0) {
                return true;
            }
        }
        return false; 
    }
    
    public static <T> boolean hasValue(final double[] elements, final double element) {
        Objects.requireNonNull(elements, "Array to search in cannot be null");
        for (double e: elements) {
            if (Double.compare(e, element) == 0) {
                return true;
            }
        }
        return false; 
    }
    
    public static boolean isNullOrEmpty(final Collection<?> arg) {
        return arg == null || arg.isEmpty();
    }

    public static <T> List<T> nullToEmpty(List<T> arg) {
        return (arg == null) ? Collections.emptyList() : arg;
    }

    public static <T> Set<T> nullToEmpty(Set<T> arg) {
        return (arg == null) ? Collections.emptySet() : arg;
    }

    public static <K, V> Map<K, V> nullToEmpty(Map<K, V> arg) {
        return (arg == null) ? Collections.emptyMap() : arg;
    }

    @SafeVarargs
    public static <T> T[] rejectNullElements(T... args) {
        Objects.requireNonNull(args, "The array argument cannot be null");
        for (T arg : args) {
            if (arg == null) {
                throw new NullPointerException();
            }
        }
        return args;
    }

    public static <T> Set<T> setOf(T arg1) {
        final Set<T> result = new HashSet<>(1);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        return result;
    }

    public static <T> Set<T> setOf(T arg1, T arg2) {
        final Set<T> result = new HashSet<>(2);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg2, "Element cannot be null"));
        return result;
    }

    @SafeVarargs
    public static <T> Set<T> setOf(T arg1, T arg2, T... args) {
        final Set<T> result = new HashSet<>(args.length + 2);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg2, "Element cannot be null"));
        for (T arg : args) {
            result.add(Objects.requireNonNull(arg, "Element cannot be null"));
        }
        return result;
    }

    public static <T> List<T> listOf(T arg1) {
        final List<T> result = new ArrayList<>(1);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        return result;
    }

    public static <T> List<T> listOf(T arg1, T arg2) {
        final List<T> result = new ArrayList<>(2);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg2, "Element cannot be null"));
        return result;
    }

    public static <T> List<T> listOf(T arg1, T arg2, T arg3) {
        final List<T> result = new ArrayList<>(3);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg2, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg3, "Element cannot be null"));
        return result;
    }

    @SafeVarargs
    public static <T> List<T> listOf(T arg1, T arg2, T arg3, T... args) {
        Objects.requireNonNull(args);
        final List<T> result = new ArrayList<>(args.length + 3);
        result.add(Objects.requireNonNull(arg1, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg2, "Element cannot be null"));
        result.add(Objects.requireNonNull(arg3, "Element cannot be null"));
        for (T arg : args) {
            result.add(Objects.requireNonNull(arg, "Element cannot be null"));
        }
        return result;
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1) {
        final Map<K, V> map = new HashMap<>();
        map.put(Objects.requireNonNull(key1, "Key cannot be null"), Objects.requireNonNull(value1, "Value cannot be null"));
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2) {
        final Map<K, V> map = mapOf(key1, value1);
        map.put(Objects.requireNonNull(key2, "Key cannot be null"), Objects.requireNonNull(value2, "Value cannot be null"));
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2, K key3, V value3) {
        final Map<K, V> map = mapOf(key1, value1, key2, value2);
        map.put(Objects.requireNonNull(key3, "Key cannot be null"), Objects.requireNonNull(value3, "Value cannot be null"));
        return map;
    }

    public static <K, V> Map<K, V> mapOf(K key1, V value1, K key2, V value2, K key3, V value3, K key4, V value4) {
        final Map<K, V> map = mapOf(key1, value1, key2, value2, key3, value3);
        map.put(Objects.requireNonNull(key4, "Key cannot be null"), Objects.requireNonNull(value4, "Value cannot be null"));
        return map;
    }

    public static <K, V> Map.Entry<K, V> entry(K key, V value) {
        return new TemporaryMapEntry<>(Objects.requireNonNull(key, "Key cannot be null"), Objects.requireNonNull(value, "Value cannot be null"));
    }

    @SafeVarargs
    public static <K, V> HashMap<K, V> mapOfEntries(Map.Entry<K, V>... entries) {
        Objects.requireNonNull(entries, "Entries cannot be null");
        final HashMap<K, V> map = new HashMap<>();
        for (Map.Entry<K, V> entry : entries) {
            Objects.requireNonNull(entry, "An entry cannot be null");
            map.put(entry.getKey(), entry.getValue());
        }
        return map;
    }

    private CollectionUtils() {
//		This class is not meant to be instantiated.
    }
}
