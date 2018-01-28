package toybox.functional.throwing.bifunctions;

import java.util.function.BiFunction;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

@FunctionalInterface
public interface ThrowingBiFunction<T, U, R> extends BiFunction<T, U, R> {

	@Override
	public default R apply(T arg0, U arg1) {
		try {
			return doApply(arg0, arg1);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

	public R doApply(T arg0, U arg1) throws Throwable;

	
	
}
