package toybox.functional.match;

import java.util.function.Function;

public interface MatchStarter<T, R> {
	MatchStarter<T, R> Default(Function<T, R> action);
	R go();
}
