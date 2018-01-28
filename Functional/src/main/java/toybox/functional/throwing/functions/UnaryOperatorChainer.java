package toybox.functional.throwing.functions;

import  toybox.functional.throwing.AbstractChainer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryOperatorChainer<T> extends AbstractChainer<UnaryOperator<T>, ThrowingUnaryOperator<T>, UnaryOperatorChainer<T>> 
implements ThrowingUnaryOperator<T> {

	public UnaryOperatorChainer(ThrowingUnaryOperator<T> function) {
		super(function);
	}

	@Override
	public T doApply(T t) throws Throwable {
		return function.doApply(t);
	}

	@Override
	public UnaryOperatorChainer<T> orTryWith(ThrowingUnaryOperator<T> other) {
		ThrowingUnaryOperator<T> f = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doApply(t);
			}
		};
		return new UnaryOperatorChainer<>(f );
	}

	@Override
	public UnaryOperatorChainer<T> fallbackTo(UnaryOperator<T> other) {
		ThrowingUnaryOperator<T> f = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.apply(t);
			}
		};
		return new UnaryOperatorChainer<>(f );
	}
	
	public UnaryOperatorChainer<T> orThrow(Function<Throwable, ? extends Throwable> constructor) {
		ThrowingUnaryOperator<T> f = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		return new UnaryOperatorChainer<>(f);
	}
	
	public UnaryOperatorChainer<T> orReturnArgument() {
		ThrowingUnaryOperator<T> f = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return t;
			}
		};
		return new UnaryOperatorChainer<>(f);
	}

	@Override
	public UnaryOperatorChainer<T> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		ThrowingUnaryOperator<T> f = t -> {
			try {
				return function.doApply(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		return new UnaryOperatorChainer<>(f);
	}
	
	

}
