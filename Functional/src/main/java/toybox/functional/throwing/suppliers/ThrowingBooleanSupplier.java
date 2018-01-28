package toybox.functional.throwing.suppliers;

import java.util.function.BooleanSupplier;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

/**
 * Version of ({@code BooleanSupplier} that throws {@code Throwable}.
 * It is meant to used as a test condition where an exception usually means a failure.
 * 
 * @author Luca Romito
 * @since 1.8
 */

@FunctionalInterface
public interface ThrowingBooleanSupplier extends BooleanSupplier {
	
	public boolean doGetAsBoolean() throws Throwable;

	@Override
	default boolean getAsBoolean() {
		try {
			return doGetAsBoolean();
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}
	
}
