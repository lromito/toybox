package toybox.functional.throwing.bifunctions;

import java.util.function.IntBinaryOperator;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

public interface ThrowingIntBinaryOperator extends IntBinaryOperator {
	
	public int doApplyAsInt(int a, int b) throws Throwable;

	@Override
	public default int applyAsInt(int a, int b) {
		try {
			return doApplyAsInt(a, b);
		}  catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

}
