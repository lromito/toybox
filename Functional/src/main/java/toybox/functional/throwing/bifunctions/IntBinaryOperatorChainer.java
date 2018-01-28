package toybox.functional.throwing.bifunctions;

import java.util.function.Function;
import java.util.function.IntBinaryOperator;

import  toybox.functional.throwing.AbstractChainer;

public class IntBinaryOperatorChainer extends AbstractChainer<IntBinaryOperator, ThrowingIntBinaryOperator, IntBinaryOperatorChainer> implements ThrowingIntBinaryOperator {

	public IntBinaryOperatorChainer(ThrowingIntBinaryOperator function) {
		super(function);
	}

	@Override
	public int doApplyAsInt(int a, int b) throws Throwable {
		return function.doApplyAsInt(a, b);
	}

	@Override
	public IntBinaryOperatorChainer orTryWith(ThrowingIntBinaryOperator other) {
		ThrowingIntBinaryOperator f = (a, b) -> {
			try {
				return function.doApplyAsInt(a, b);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doApplyAsInt(a, b);
			}
		};
		
		return new IntBinaryOperatorChainer(f);
	}

	@Override
	public IntBinaryOperatorChainer fallbackTo(IntBinaryOperator other) {
		ThrowingIntBinaryOperator f = (a, b) -> {
			try {
				return function.doApplyAsInt(a, b);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.applyAsInt(a, b);
			}
		};
		
		return new IntBinaryOperatorChainer(f);
	}

	@Override
	public IntBinaryOperatorChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		ThrowingIntBinaryOperator f = (a, b) -> {
			try {
				return function.doApplyAsInt(a, b);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		
		return new IntBinaryOperatorChainer(f);
	}
	
	public IntBinaryOperatorChainer orReturnLeftArgument() {
		ThrowingIntBinaryOperator f = (a, b) -> {
			try {
				return function.doApplyAsInt(a, b);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return a;
			}
		};
		
		return new IntBinaryOperatorChainer(f);
	}
	
	public IntBinaryOperatorChainer orReturnRightArgument() {
		ThrowingIntBinaryOperator f = (a, b) -> {
			try {
				return function.doApplyAsInt(a, b);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return b;
			}
		};
		
		return new IntBinaryOperatorChainer(f);
	}
	
	public IntBinaryOperatorChainer orElseReturn(int value) {
		ThrowingIntBinaryOperator f = (a, b) -> {
			try {
				return function.doApplyAsInt(a, b);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return value;
			}
		};
		
		return new IntBinaryOperatorChainer(f);
	}

}
