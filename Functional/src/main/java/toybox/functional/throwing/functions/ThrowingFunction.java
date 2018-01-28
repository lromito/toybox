package toybox.functional.throwing.functions;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;
import java.util.function.Function;

@FunctionalInterface
public interface ThrowingFunction<T, R> extends Function<T, R> {
	
	public R doApply(T t) throws Throwable;

	@Override
	public default R apply(T t) {
		try {	
			return doApply(t);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}
	
}
