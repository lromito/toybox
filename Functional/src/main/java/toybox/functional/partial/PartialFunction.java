package toybox.functional.partial;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public interface PartialFunction<T, R> extends Function<T, R> {

    public static <T, R> PartialFunction<T, R> of(Predicate<T> condition, Function<T, R> func) {
        return new AdaptedPartialFunction<>(condition, func);
    }

    public static <T, R> PartialFunction<T, R> ofFunction(Function<T, R> func) {
        return new AdaptedPartialFunction<>(ignored -> true, func);
    }

    public static <T, R> PartialFunction<T, R> ofMap(Map<T, R> map) {
        return new PartialFunctionFromMap<>(map);
    }

    public static <R> IntPartialFunction<R> ofList(List<R> arg) {
        return new PartialFunctionFromList<>(arg);
    }

    public static <R> IntPartialFunction<R> ofArray(R[] arg) {
        return new PartialFunctionFromArray<>(arg);
    }

    /**
     *
     * @param value - argument to the partial function
     * @return true if the function returns a value for the specified argument, false otherwise.
     */
    public boolean isDefinedAt(T value);

    /**
     * The function is NOT required to return a value for an argument if isDefined() for the same argument is false.
     *
     * @param argument
     */
    @Override
    public R apply(T argument);

    /**
     * Lift converts a PartialFunction into a function that returns an Optional&lt;T&gt; when the PartialFunction is
     * defined and Optional.Empty when it is not.
     *
     * @return
     */
    default public Function<T, Optional<R>> lift() {
        return (T t) -> {
            return isDefinedAt(t) ? Optional.of(apply(t)) : Optional.empty();
        };
    }

}
