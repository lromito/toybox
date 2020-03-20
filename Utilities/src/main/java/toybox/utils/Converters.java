package toybox.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

public final class Converters {

    public static OptionalInt stringToInt(String s) {
        try {
            return OptionalInt.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return OptionalInt.empty();
        }
    }

    public static Optional<Integer> stringToBoxedInt(String s) {
        try {
            return Optional.of(Integer.valueOf(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static OptionalLong stringToLong(String s) {
        try {
            return OptionalLong.of(Long.parseLong(s));
        } catch (NumberFormatException e) {
            return OptionalLong.empty();
        }
    }

    public static Optional<Long> stringToBoxedLong(String s) {
        try {
            return Optional.of(Long.valueOf(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static OptionalDouble stringToDouble(String s) {
        try {
            return OptionalDouble.of(Double.parseDouble(s));
        } catch (NumberFormatException e) {
            return OptionalDouble.empty();
        }
    }

    public static Optional<Double> stringToBoxedDouble(String s) {
        try {
            return Optional.of(Double.valueOf(s));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    public static OptionalInt longToOptionalInt(long value) {
        if (value > Integer.MAX_VALUE || value < Integer.MIN_VALUE) {
            return OptionalInt.empty();
        } else {
            return OptionalInt.of((int) value);
        }
    }

    private Converters() {
        // This class is not meant to be instantiated.
    }

}
