package toybox.functional.throwing.suppliers;

import java.util.function.IntSupplier;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

public interface ThrowingIntSupplier extends IntSupplier {
	
	public int doGetAsInt() throws Throwable;

	@Override
	public default int getAsInt() {
		try {
			return doGetAsInt();
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

	
	
}
