package toybox.functional.throwing.bifunctions;

import java.util.function.BiFunction;
import java.util.function.Function;

import  toybox.functional.throwing.AbstractChainer;

public class BiFunctionChainer<T, U, R> extends AbstractChainer<BiFunction<T, U, R>, ThrowingBiFunction<T, U, R>, BiFunctionChainer<T, U, R>> implements ThrowingBiFunction<T, U, R> {
	
	
	public BiFunctionChainer(ThrowingBiFunction<T, U, R> function) {
		super(function);
	}

	@Override
	public R doApply(T arg0, U arg1) throws Throwable {
		return function.doApply(arg0, arg1);
	}

	@Override
	public BiFunctionChainer<T, U, R> orTryWith(ThrowingBiFunction<T, U, R> other) {
		final ThrowingBiFunction<T, U, R> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doApply(t, u);
			}
		};
		
		return new BiFunctionChainer<>(f);
	}

	public BiFunctionChainer<T, U, R> elseReturn(R value) {
		final ThrowingBiFunction<T, U, R> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return value;
			}
		};
		
		return new BiFunctionChainer<>(f);
	}
	
	@Override
	public BiFunctionChainer<T, U, R> fallbackTo(BiFunction<T, U, R> other) {
		final ThrowingBiFunction<T, U, R> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.apply(t, u);
			}
		};
		
		return new BiFunctionChainer<>(f);
	}
	
	@Override
	public BiFunctionChainer<T, U, R> elseThrow(Function<Throwable, ? extends Throwable> generator) {
		final ThrowingBiFunction<T, U, R> func = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw generator.apply(e);
			}
		};
		
		return new BiFunctionChainer<>(func);
	}
	
}
