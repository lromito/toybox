package toybox.functional.throwing.predicates;

import  toybox.functional.throwing.AbstractChainer;
import java.util.function.Function;
import java.util.function.Predicate;

public class PredicateChainer<T> extends AbstractChainer<Predicate<T>, ThrowingPredicate<T>, PredicateChainer<T>> implements ThrowingPredicate<T> {

	public PredicateChainer(ThrowingPredicate<T> function) {
		super(function);
	}

	@Override
	public boolean doTest(T t) throws Throwable {
		return function.doTest(t);
	}

	@Override
	public PredicateChainer<T> orTryWith(ThrowingPredicate<T> other) {
		final ThrowingPredicate<T> f = (t) -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doTest(t);
			}
		};
		return new PredicateChainer<>(f);
	}

	@Override
	public PredicateChainer<T> fallbackTo(Predicate<T> other) {
		final ThrowingPredicate<T> f = (t) -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.test(t);
			}
		};
		return new PredicateChainer<>(f);
	}

	public PredicateChainer<T> orReturnTrue() {
		final ThrowingPredicate<T> f = (t) -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return true;
			}
		};
		return new PredicateChainer<>(f);
	}
	
	public PredicateChainer<T> orReturnFalse() {
		final ThrowingPredicate<T> f = (t) -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return false;
			}
		};
		return new PredicateChainer<>(f);
	}

	public PredicateChainer<T> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingPredicate<T> f = (t) -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		return new PredicateChainer<>(f);
		
	}
}
