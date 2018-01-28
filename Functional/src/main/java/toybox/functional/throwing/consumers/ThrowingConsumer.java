package toybox.functional.throwing.consumers;

import java.util.function.Consumer;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {

	public void doAccept(T value) throws Throwable;

	@Override
	public default void accept(T t) {
		try {
			doAccept(t);
		}  catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
		
	}
	
	
}
