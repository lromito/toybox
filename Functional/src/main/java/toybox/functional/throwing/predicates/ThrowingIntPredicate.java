package toybox.functional.throwing.predicates;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;
import java.util.function.IntPredicate;

public interface ThrowingIntPredicate extends IntPredicate {
	
	public boolean doTest(int t) throws Throwable;

	@Override
	public default boolean test(int t) {
		try {
			return doTest(t);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

}
