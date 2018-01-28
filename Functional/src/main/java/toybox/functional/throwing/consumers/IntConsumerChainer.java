package toybox.functional.throwing.consumers;

import java.util.function.Function;
import java.util.function.IntConsumer;

import  toybox.functional.throwing.AbstractChainer;

public class IntConsumerChainer extends AbstractChainer<IntConsumer, ThrowingIntConsumer, IntConsumerChainer> implements ThrowingIntConsumer {

	public IntConsumerChainer(ThrowingIntConsumer function) {
		super(function);
	}

	@Override
	public void doAccept(int arg) throws Throwable {
		function.doAccept(arg);
	}

	@Override
	public IntConsumerChainer orTryWith(ThrowingIntConsumer other) {
		final ThrowingIntConsumer f = arg -> {
			try {
				function.doAccept(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.doAccept(arg);
			}
		};		
		
		return new IntConsumerChainer(f);
	}

	@Override
	public IntConsumerChainer fallbackTo(IntConsumer other) {
		final ThrowingIntConsumer f = arg -> {
			try {
				function.doAccept(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.accept(arg);
			}
		};		
		
		return new IntConsumerChainer(f);
	}

	@Override
	public IntConsumerChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingIntConsumer f = arg -> {
			try {
				function.doAccept(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				constructor.apply(e);
			}
		};		
		
		return new IntConsumerChainer(f);
	}

	
	public IntConsumerChainer elseDoNothing() {
		final ThrowingIntConsumer f = arg -> {
			try {
				function.doAccept(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				//Do nothing
			}
		};		
		
		return new IntConsumerChainer(f);
	}
}
