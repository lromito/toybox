package toybox.functional.match2;

import java.util.Optional;
import toybox.functional.partial.PartialFunction;

public final class Matcher<T> {

	private final T comparison;

	private Matcher(T value) {
		this.comparison = value;
	}
	
	public static <T> Matcher<T> match(final T value) {
		return new Matcher<>(value);
	}
	
	@SafeVarargs
	public final <R> Optional<R> of(PartialFunction<T, R>...  conditions) {
		for (PartialFunction<T, R> function : conditions) {
			if (function.isDefinedAt(comparison)) {
				final R apply = function.apply(comparison);
				return Optional.of(apply);
			}
		}
		return Optional.empty();
	}
}
