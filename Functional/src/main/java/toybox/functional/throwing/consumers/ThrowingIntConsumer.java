package toybox.functional.throwing.consumers;

import java.util.function.IntConsumer;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

public interface ThrowingIntConsumer extends IntConsumer {

	@Override
	public default void accept(int arg) {
		try {
			doAccept(arg);
		} catch (Error | RuntimeException e) {
	        throw e;
	    } catch (Throwable throwable) {
	        throw new ThrownByLamdbaRuntimeException(throwable);
		}
	};

	public void doAccept(int arg) throws Throwable;

}
