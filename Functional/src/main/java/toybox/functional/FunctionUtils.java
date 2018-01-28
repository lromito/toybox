package toybox.functional;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public final class FunctionUtils {

    public static <T, U, R> Function<T, Function<U, R>> curry(BiFunction<? super T, ? super U, ? extends R> func) {
        return (T t) -> (U u) -> func.apply(t, u);
    }

    public static <T, U, R> BiFunction<T, U, R> uncurry(Function<T, Function<U, R>> func) {
        return (T t, U u) -> func.apply(t).apply(u);
    }

    public static <T, U, R> Function<U, R> bindFirst(BiFunction<? super T, ? super U, ? extends R> func, final T t) {
        return (U u) -> func.apply(t, u);
    }

    public static <T, U, R> Function<T, R> bindSecond(BiFunction<? super T, ? super U, ? extends R> func, final U u) {
        return (T t) -> func.apply(t, u);
    }

    public static <T, U, R> Supplier<R> bind(Function<T, R> func, final T t) {
        return () -> func.apply(t);
    }

    public static <T, U, R> Supplier<R> bind(BiFunction<T, U, R> func, final T t, final U u) {
        return () -> func.apply(t, u);
    }

    public static <T, R> Function<T, Optional<R>> wrapException(Function<? super T, ? extends R> func) {
        return (a) -> {
            try {
                return Optional.of(func.apply(a));
            } catch (Throwable t) {
                return Optional.empty();
            }
        };
    }

    public static <T, U, R> BiFunction<T, U, Optional<R>> wrapException(BiFunction<? super T, ? super U, ? extends R> func) {
        return (a, b) -> {
            try {
                return Optional.of(func.apply(a, b));
            } catch (Throwable t) {
                return Optional.empty();
            }
        };
    }

    private FunctionUtils() {
        // This class is not meant to be instantiated.
    }
}
