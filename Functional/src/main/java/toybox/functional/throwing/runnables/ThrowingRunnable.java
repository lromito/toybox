package toybox.functional.throwing.runnables;

import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

@FunctionalInterface
public interface ThrowingRunnable extends Runnable {

	public void doRun() throws Throwable;
	
	@Override
	public default void run() {
		try {
			doRun();
		} catch (Error | RuntimeException e) {
			throw e;
		} catch (Throwable e) {
			throw new ThrownByLamdbaRuntimeException(e);
		}
		
	}

}
