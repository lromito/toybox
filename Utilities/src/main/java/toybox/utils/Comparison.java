package toybox.utils;

import java.util.Comparator;

public enum Comparison implements Comparable<Comparison> {
    LESSER,
    EQUAL,
    GREATER;

    public static Comparison ofInt(int result) {
        if (result < 0) {
            return LESSER;
        }
        if (result > 0) {
            return GREATER;
        } else {
            return EQUAL;
        }
    }

    public static <T> Comparison compare(T a, T b, Comparator<T> comparator) {
        return Comparison.ofInt(comparator.compare(a, b));
    }

    public static <T extends Comparable<T>> Comparison compare(T a, T b) {
        return Comparison.ofInt(a.compareTo(b));
    }
}
