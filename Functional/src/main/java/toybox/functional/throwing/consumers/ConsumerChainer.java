package toybox.functional.throwing.consumers;

import java.util.function.Consumer;
import java.util.function.Function;

import  toybox.functional.throwing.AbstractChainer;

public class ConsumerChainer<T> extends AbstractChainer<Consumer<T>, ThrowingConsumer<T>, ConsumerChainer<T>> implements ThrowingConsumer<T> {

	public ConsumerChainer(ThrowingConsumer<T> function) {
		super(function);
	}

	@Override
	public void doAccept(T value) throws Throwable {
		function.doAccept(value);

	}

	@Override
	public ConsumerChainer<T> orTryWith(ThrowingConsumer<T> other) {
		final ThrowingConsumer<T> f = t -> {
			try {
				function.doAccept(t);
			}  catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.doAccept(t);
			}
			
		};
		return new ConsumerChainer<>(f);
	}

	@Override
	public ConsumerChainer<T> fallbackTo(Consumer<T> other) {
		final ThrowingConsumer<T> f = t -> {
			try {
				function.doAccept(t);
			}  catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.accept(t);
			}
			
		};
		return new ConsumerChainer<>(f);
	}
	
	public ConsumerChainer<T> elseDoNothing() {
		final ThrowingConsumer<T> f = t -> {
			try {
				function.doAccept(t);
			}  catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				//DO nothing
			}
			
		};
		return new ConsumerChainer<>(f);
	}
	
	public ConsumerChainer<T> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingConsumer<T> f = t -> {
			try {
				function.doAccept(t);
			}  catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
			
		};
		return new ConsumerChainer<>(f);
	}

}
