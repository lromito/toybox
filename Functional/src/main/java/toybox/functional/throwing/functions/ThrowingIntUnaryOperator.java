package toybox.functional.throwing.functions;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;
import java.util.function.IntUnaryOperator;

public interface ThrowingIntUnaryOperator extends IntUnaryOperator {

	public int doApplyAsInt(int arg0) throws Throwable;
	
	@Override
	public default int applyAsInt(int arg0) {
		try {
			return doApplyAsInt(arg0);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

}
