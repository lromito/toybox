package toybox.functional.throwing.suppliers;

import java.util.function.Function;
import java.util.function.Supplier;

import  toybox.functional.throwing.AbstractChainer;

public class SupplierChainer<T> extends AbstractChainer<Supplier<T>, ThrowingSupplier<T>, SupplierChainer<T>> implements ThrowingSupplier<T> {

	public SupplierChainer(ThrowingSupplier<T> function) {
		super(function);
	}

	@Override
	public SupplierChainer<T> orTryWith(ThrowingSupplier<T> other) {
		final ThrowingSupplier<T> f = () -> {
			try {
				return function.doGet();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.doGet();
			}
		};
		
		return new SupplierChainer<>(f);
	}
	
	@Override
	public SupplierChainer<T> fallbackTo(Supplier<T> other) {
		final ThrowingSupplier<T> f = () -> {
			try {
				return function.doGet();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return other.get();
			}
		};
		
		return new SupplierChainer<>(f);
	}
	
	public SupplierChainer<T> orElseReturn(T value) {
		final ThrowingSupplier<T> f = () -> {
			try {
				return function.doGet();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				return value;
			}
		};
		
		return new SupplierChainer<>(f);
	}

	@Override
	public T doGet() throws Throwable {
		return function.doGet();
	}

	@Override
	public SupplierChainer<T> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingSupplier<T> f = () -> {
			try {
				return function.doGet();
			} catch (Error | RuntimeException e) {
				throw e;
			} catch (Throwable e) {
				throw constructor.apply(e);
			}
		};
		
		return new SupplierChainer<>(f);
	}
	
	

}
