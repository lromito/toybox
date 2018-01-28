package toybox.functional.throwing.bifunctions;

import java.util.function.BinaryOperator;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

@FunctionalInterface
public interface ThrowingBinaryOperator<T> extends BinaryOperator<T> {

	@Override
	public default T apply(T t, T u) {
		try {
			return doApply(t, u);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

	public T doApply(T t, T u) throws Throwable;

}
