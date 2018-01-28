package toybox.functional.throwing.functions;

import  toybox.functional.throwing.AbstractChainer;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

public class IntUnaryOperatorChainer extends AbstractChainer<IntUnaryOperator, ThrowingIntUnaryOperator, IntUnaryOperatorChainer> implements ThrowingIntUnaryOperator {

	public IntUnaryOperatorChainer(ThrowingIntUnaryOperator function) {
		super(function);
	}

	@Override
	public int doApplyAsInt(int arg0) throws Throwable {
		return function.doApplyAsInt(arg0);
	}

	@Override
	public IntUnaryOperatorChainer orTryWith(ThrowingIntUnaryOperator other) {
		final ThrowingIntUnaryOperator f = arg -> {
			try {
				return function.doApplyAsInt(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doApplyAsInt(arg);
			}
		};
		return new IntUnaryOperatorChainer(f);
	}

	@Override
	public IntUnaryOperatorChainer fallbackTo(IntUnaryOperator other) {
		final ThrowingIntUnaryOperator f = arg -> {
			try {
				return function.doApplyAsInt(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.applyAsInt(arg);
			}
		};
		return new IntUnaryOperatorChainer(f);
	}

	@Override
	public IntUnaryOperatorChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingIntUnaryOperator f = arg -> {
			try {
				return function.doApplyAsInt(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		return new IntUnaryOperatorChainer(f);
	}
	
	public IntUnaryOperatorChainer orReturnArgument() {
		final ThrowingIntUnaryOperator f = arg -> {
			try {
				return function.doApplyAsInt(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return arg;
			}
		};
		return new IntUnaryOperatorChainer(f);
	}

	public IntUnaryOperatorChainer orElseReturn(int value) {
		final ThrowingIntUnaryOperator f = arg -> {
			try {
				return function.doApplyAsInt(arg);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return value;
			}
		};
		return new IntUnaryOperatorChainer(f);
	}


}
