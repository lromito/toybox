package toybox.functional.match;

import java.util.function.Function;
import java.util.function.Predicate;

public interface CaseAdder<T, R> extends MatchStarter<T, R> {
	CaseAdder<T, R> Case(Predicate<T> condition, Function<T, R> action);
}
