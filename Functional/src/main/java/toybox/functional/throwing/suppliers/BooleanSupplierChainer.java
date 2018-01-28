package toybox.functional.throwing.suppliers;

import java.util.function.BooleanSupplier;
import java.util.function.Function;

import  toybox.functional.throwing.AbstractChainer;

public class BooleanSupplierChainer extends AbstractChainer<BooleanSupplier, ThrowingBooleanSupplier, BooleanSupplierChainer> implements ThrowingBooleanSupplier {
	
	public BooleanSupplierChainer(ThrowingBooleanSupplier function) {
		super(function);
	}
	
	@Override
	public boolean doGetAsBoolean() throws Throwable {
		return function.doGetAsBoolean();
	}
	
	@Override
	public BooleanSupplierChainer orTryWith(ThrowingBooleanSupplier other) {
		final ThrowingBooleanSupplier f = () -> {
			try {
				return function.doGetAsBoolean();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doGetAsBoolean();
			}
		};
		
		return new BooleanSupplierChainer(f);
	}

	@Override
	public BooleanSupplierChainer fallbackTo(BooleanSupplier other) {
		final ThrowingBooleanSupplier f = () -> {
			try {
				return function.doGetAsBoolean();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.getAsBoolean();
			}
		};
		
		return new BooleanSupplierChainer(f);
	}
	
	public BooleanSupplierChainer orReturnTrue() {
		final ThrowingBooleanSupplier f = () -> {
			try {
				return function.doGetAsBoolean();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return true;
			}
		};
		
		return new BooleanSupplierChainer(f);
	}

	@Override
	public BooleanSupplierChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingBooleanSupplier f = () -> {
			try {
				return function.doGetAsBoolean();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		
		return new BooleanSupplierChainer(f);
	}
	
	
}
