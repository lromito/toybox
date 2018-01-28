package toybox.functional.throwing.predicates;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;
import java.util.function.Predicate;

@FunctionalInterface
public interface ThrowingPredicate<T> extends Predicate<T> {

	@Override
	public default boolean test(T t) {
		try {
			return doTest(t);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

	public boolean doTest(T t) throws Throwable;
	
	
}
