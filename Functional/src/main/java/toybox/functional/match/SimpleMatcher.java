package toybox.functional.match;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import toybox.functional.partial.PartialFunction;

class SimpleMatcher<T, R> implements Matcher<T, R> {
	
	private final List<PartialFunction<T, R>> cases;
	private final T value;
	
	SimpleMatcher(T value) {
		this.cases = new ArrayList<>();
		this.value = value;
	}
	public CaseAdder<T, R> Case(Predicate<T> condition, Function<T, R> action) {
		cases.add(PartialFunction.of(condition, action));
		return this;
	}

	@Override
	public MatchStarter<T, R> Default(Function<T, R> action) {
		cases.add(PartialFunction.ofFunction(action));
		return this;
	}

	@Override
	public R go() {
		for (PartialFunction<T, R> c : cases) {
			if (c.isDefinedAt(value)) {
				return c.apply(value);
			}
		}
		return null;
	}

}
