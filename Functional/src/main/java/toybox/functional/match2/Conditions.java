package toybox.functional.match2;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import toybox.functional.partial.PartialFunction;

public interface Conditions<T, R> {
	
	public static <T> Predicate<T> is(T value) {
		return (t) -> Objects.equals(t, value);
	}
	
	public static <T, R> Function<T, R> thenReturn(R value) {
		return (ignored) -> value;
	}
	
	public static <T, R> PartialFunction<T, R> when(Predicate<T> condition, Function<T, R> action) {
		return PartialFunction.of(condition, action);
	}
	
	public static <T, R> PartialFunction<T, R> when(Predicate<T> condition, R returnValue) {
		return PartialFunction.of(condition, (ignored) -> returnValue);
	}
	
	public static <T, R> PartialFunction<T, R> when(Optional<? extends R> optional) {
		Objects.requireNonNull(optional, "Optional cannot be null");
		return PartialFunction.of((ignored) -> optional.isPresent(), (ignored) -> optional.get());
	}
	
	public static <T, R> PartialFunction<T, R> otherwise(Function<T, R> action) {
		return PartialFunction.of(ignored -> true, action);
	}
	
	public static <T, R> PartialFunction<T, R> otherwise(Supplier<? extends R>  action) {
		Objects.requireNonNull(action, "Action cannot be null");
		return PartialFunction.ofFunction(ignored -> action.get());
	}
	
	public static <T, R> PartialFunction<T, R> otherwise(R value) {
		return PartialFunction.ofFunction(ignored -> value);
	}
}
