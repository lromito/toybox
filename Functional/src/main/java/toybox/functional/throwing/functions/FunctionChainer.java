package toybox.functional.throwing.functions;

import  toybox.functional.throwing.AbstractChainer;
import java.util.function.Function;

public class FunctionChainer<T, R> extends AbstractChainer<Function<T, R>, ThrowingFunction<T, R>, FunctionChainer<T, R>> implements ThrowingFunction<T, R> {

	
	public FunctionChainer(ThrowingFunction<T, R> function) {
		super(function);
	}


	@Override
	public R doApply(T t) throws Throwable {
		return function.doApply(t);
	}
	
	@Override
	public FunctionChainer<T, R> orTryWith(ThrowingFunction<T, R> other) {
		final ThrowingFunction<T, R> f = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doApply(t);
			}
		};
		
		return new FunctionChainer<>(f);
	}
	
	public FunctionChainer<T, R> orElseReturn(R value) {
		final ThrowingFunction<T, R> func = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return value;
			}
		};
		
		return new FunctionChainer<>(func);
	}
	
	public FunctionChainer<T, R> elseThrow(Function<Throwable, ? extends Throwable> generator) {
		final ThrowingFunction<T, R> func = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw generator.apply(e);
			}
		};
		
		return new FunctionChainer<>(func);
	}
	
	@Override
	public FunctionChainer<T, R> fallbackTo(Function<T, R> other) {
		final ThrowingFunction<T, R> func = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.apply(t);
			}
		};
		
		return new FunctionChainer<>(func);
	}
	
}
