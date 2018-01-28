package toybox.functional.throwing.predicates;

import  toybox.functional.throwing.AbstractChainer;
import java.util.function.Function;
import java.util.function.IntPredicate;

public class IntPredicateChainer extends AbstractChainer<IntPredicate, ThrowingIntPredicate, IntPredicateChainer> implements ThrowingIntPredicate {

	public IntPredicateChainer(ThrowingIntPredicate function) {
		super(function);
	}

	@Override
	public boolean doTest(int t) throws Throwable {
		return function.doTest(t);
	}

	@Override
	public IntPredicateChainer orTryWith(ThrowingIntPredicate other) {
		final ThrowingIntPredicate f = t -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doTest(t);
			}
		};
		return new IntPredicateChainer(f);
	}

	@Override
	public IntPredicateChainer fallbackTo(IntPredicate other) {
		final ThrowingIntPredicate f = t -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.test(t);
			}
		};
		return new IntPredicateChainer(f);
	}

	@Override
	public IntPredicateChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingIntPredicate f = t -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		return new IntPredicateChainer(f);
	}
	
	public IntPredicateChainer elseReturnTrue() {
		final ThrowingIntPredicate f = t -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return true;
			}
		};
		return new IntPredicateChainer(f);
	}
	
	public IntPredicateChainer elseReturnFalse() {
		final ThrowingIntPredicate f = t -> {
			try {
				return function.doTest(t);
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return false;
			}
		};
		return new IntPredicateChainer(f);
	}

}
