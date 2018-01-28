package toybox.functional.throwing.runnables;

import java.util.function.Function;

import  toybox.functional.throwing.AbstractChainer;

public class RunnableChainer extends AbstractChainer<Runnable, ThrowingRunnable, RunnableChainer> implements ThrowingRunnable {

	
	public RunnableChainer(ThrowingRunnable function) {
		super(function);
	}


	@Override
	public void doRun() throws Throwable {
		function.run();
	}
	
	@Override
	public RunnableChainer orTryWith(ThrowingRunnable other) {
		final ThrowingRunnable f = () -> {
			try {
				function.doRun();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.doRun();
			}
		};
		
		return new RunnableChainer(f);
	}
	
	@Override
	public RunnableChainer fallbackTo(Runnable other) {
		final ThrowingRunnable f = () -> {
			try {
				function.doRun();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.run();
			}
		};
		
		return new RunnableChainer(f);
	}
	
	public RunnableChainer orDoNothing(Runnable other) {
		final ThrowingRunnable f = () -> {
			try {
				function.doRun();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				//Ignored
			}
		};
		
		return new RunnableChainer(f);
	}


	@Override
	public RunnableChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingRunnable f = () -> {
			try {
				function.doRun();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		
		return new RunnableChainer(f);
	}
	
	

}
