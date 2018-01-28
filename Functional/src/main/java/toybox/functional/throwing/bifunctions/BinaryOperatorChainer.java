package toybox.functional.throwing.bifunctions;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import  toybox.functional.throwing.AbstractChainer;

public class BinaryOperatorChainer<T> extends AbstractChainer<BinaryOperator<T>, ThrowingBinaryOperator<T>, BinaryOperatorChainer<T>> implements ThrowingBinaryOperator<T> {

	public BinaryOperatorChainer(ThrowingBinaryOperator<T> function) {
		super(function);
	}

	@Override
	public T doApply(T t, T u) throws Throwable {
		return function.doApply(t, u);
	}

	@Override
	public BinaryOperatorChainer<T> orTryWith(ThrowingBinaryOperator<T> other) {
		ThrowingBinaryOperator<T> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doApply(t, u);
			}
		};
		return new BinaryOperatorChainer<>(f);
	}

	@Override
	public BinaryOperatorChainer<T> fallbackTo(BinaryOperator<T> other) {
		ThrowingBinaryOperator<T> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.apply(t, u);
			}
		};
		return new BinaryOperatorChainer<>(f);
	}

	public BinaryOperatorChainer<T> orReturnLeftArgument() {
		ThrowingBinaryOperator<T> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return t;
			}
		};
		return new BinaryOperatorChainer<>(f);
	}
	
	public BinaryOperatorChainer<T> orReturnRightArgument() {
		ThrowingBinaryOperator<T> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return u;
			}
		};
		return new BinaryOperatorChainer<>(f);
	}

	@Override
	public BinaryOperatorChainer<T> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		ThrowingBinaryOperator<T> f = (t, u) -> {
			try {
				return function.doApply(t, u);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		return new BinaryOperatorChainer<>(f);
	}
	
	
	
}
