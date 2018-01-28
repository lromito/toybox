package toybox.functional.throwing.consumers;

import java.util.function.BiConsumer;
import java.util.function.Function;

import  toybox.functional.throwing.AbstractChainer;

public class BiConsumerChainer<T, U> extends AbstractChainer<BiConsumer<T, U>, ThrowingBiConsumer<T, U>, BiConsumerChainer<T, U>> implements ThrowingBiConsumer<T, U> {

	public BiConsumerChainer(ThrowingBiConsumer<T, U> function) {
		super(function);
	}

	@Override
	public BiConsumerChainer<T, U> orTryWith(ThrowingBiConsumer<T, U> other) {
		final ThrowingBiConsumer<T, U> f = (t, u) -> {
			try {
				function.doAccept(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.doAccept(t, u);
			}
		};
		
		return new BiConsumerChainer<>(f);
	}

	@Override
	public BiConsumerChainer<T, U> fallbackTo(BiConsumer<T, U> other) {
		final ThrowingBiConsumer<T, U> f = (t, u) -> {
			try {
				function.doAccept(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				other.accept(t, u);
			}
		};
		
		return new BiConsumerChainer<>(f);
	}
	
	public BiConsumerChainer<T, U> elseDoNothing() {
		final ThrowingBiConsumer<T, U> f = (t, u) -> {
			try {
				function.doAccept(t, u);
			}  catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				//DO nothing
			}
			
		};
		return new BiConsumerChainer<>(f);
	}
	
	public BiConsumerChainer<T, U> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingBiConsumer<T, U> f = (t, u) -> {
			try {
				function.doAccept(t, u);
			}  catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
			
		};
		return new BiConsumerChainer<>(f);
	}

	@Override
	public void doAccept(T t, U u) throws Throwable {
		function.doAccept(t, u);
		
	}

}
