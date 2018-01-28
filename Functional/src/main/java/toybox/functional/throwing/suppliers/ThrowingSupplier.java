package toybox.functional.throwing.suppliers;

import java.util.function.Supplier;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

@FunctionalInterface
public interface ThrowingSupplier<T> extends Supplier<T> {
	
	public T doGet() throws Throwable;

	@Override
	public default T get() {
		try {
			return doGet();
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

	
}
