package toybox.functional.throwing.consumers;

import java.util.function.BiConsumer;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

public interface ThrowingBiConsumer<T, U> extends BiConsumer<T, U> {

	public void doAccept(T t, U u) throws Throwable;	
	
	@Override
	public default void accept(T t, U u) {
		try {
			doAccept(t, u);
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
	}

	
}
