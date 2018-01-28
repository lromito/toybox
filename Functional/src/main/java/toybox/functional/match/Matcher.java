package toybox.functional.match;

public interface Matcher<T, R> extends CaseAdder<T, R>, MatchStarter<T, R> {
	public static <U, V> Matcher<U, V> match(U value) {
		return new SimpleMatcher<>(value);
	}
}
