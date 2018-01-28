package toybox.functional.throwing.functions;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;
import java.util.function.UnaryOperator;

@FunctionalInterface
public interface ThrowingUnaryOperator<T> extends UnaryOperator<T> {
	
	public T doApply(T t) throws Throwable;

	@Override
	public default T apply(T t) {
		try {
			return doApply(t);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

}
