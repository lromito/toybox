package toybox.functional.partial;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

class AdaptedPartialFunction<T, R> implements PartialFunction<T, R>  {
	
	private final Predicate<T> condition;
	private final Function<T, R> func;
	
	AdaptedPartialFunction(Predicate<T> condition, Function<T, R> func) {
		this.condition = Objects.requireNonNull(condition, "Condition cannot be null");
		this.func = Objects.requireNonNull(func, "Function cannot be null");
	}

	@Override
	public boolean isDefinedAt(T value) {
		return condition.test(value);
	}

	@Override
	public R apply(T argument) {
		return func.apply(argument);
	}
	
}
