package toybox.functional.throwing.suppliers;

import java.util.function.Function;
import java.util.function.IntSupplier;

import  toybox.functional.throwing.AbstractChainer;

public class IntSupplierChainer extends AbstractChainer<IntSupplier, ThrowingIntSupplier, IntSupplierChainer> implements ThrowingIntSupplier {

	public IntSupplierChainer(ThrowingIntSupplier function) {
		super(function);
	}

	@Override
	public int doGetAsInt() throws Throwable {
		return function.doGetAsInt();
	}

	@Override
	public IntSupplierChainer orTryWith(ThrowingIntSupplier other) {
		final ThrowingIntSupplier f = () -> {
			try {
				return  function.doGetAsInt();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doGetAsInt();
			}
		};		
		
		return new IntSupplierChainer(f);
	}

	@Override
	public IntSupplierChainer fallbackTo(IntSupplier other) {
		final ThrowingIntSupplier f = () -> {
			try {
				return  function.doGetAsInt();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.getAsInt();
			}
		};		
		
		return new IntSupplierChainer(f);
	}

	@Override
	public IntSupplierChainer elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingIntSupplier f = () -> {
			try {
				return  function.doGetAsInt();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};		
		
		return new IntSupplierChainer(f);
	}

	public IntSupplierChainer orElseReturn(int value) {
		final ThrowingIntSupplier f = () -> {
			try {
				return  function.doGetAsInt();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return value;
			}
		};		
		
		return new IntSupplierChainer(f);
	}
	
}
