package toybox.functional.throwing.comparators;

import java.util.Comparator;
import java.util.function.Function;
import  toybox.functional.throwing.AbstractChainer;

public class ComparatorChainer<T> extends AbstractChainer<Comparator<T>, ThrowingComparator<T>, ComparatorChainer<T>> implements ThrowingComparator<T> {

	public ComparatorChainer(ThrowingComparator<T> function) {
		super(function);
	}

	@Override
	public boolean equals(Object obj) {
		return function.equals(obj);
	}

	@Override
	public int doCompare(T o1, T o2) throws Throwable {
		return function.doCompare(o1, o2);
	}

	@Override
	public ComparatorChainer<T> orTryWith(ThrowingComparator<T> other) {
		final ThrowingComparator<T> f = (o1, o2) -> {
			try {
				return function.doCompare(o1, o2);
			} catch (Error | RuntimeException e) {
	            throw e;
	        } catch (Throwable ignored) {
	            return other.doCompare(o1, o2);
	        }
		};		
		
		return new ComparatorChainer<>(f);
	}

	@Override
	public ComparatorChainer<T> fallbackTo(Comparator<T> other) {
		final ThrowingComparator<T> f = (o1, o2) -> {
			try {
				return function.doCompare(o1, o2);
			} catch (Error | RuntimeException e) {
	            throw e;
	        } catch (Throwable throwable) {
	            return other.compare(o1, o2);
	        }
		};		
		
		return new ComparatorChainer<>(f);
	}

	@Override
	public ComparatorChainer<T> elseThrow(Function<Throwable, ? extends Throwable> constructor) {
		final ThrowingComparator<T> f = (o1, o2) -> {
			try {
				return function.doCompare(o1, o2);
			} catch (Error | RuntimeException e) {
	            throw e;
	        } catch (Throwable throwable) {
	            throw constructor.apply(throwable);
	        }
		};		
		
		return new ComparatorChainer<>(f);
	}
	
	public ComparatorChainer<T> orElseReturn(final int value) {
		final ThrowingComparator<T> f = (o1, o2) -> {
			try {
				return function.doCompare(o1, o2);
			} catch (Error | RuntimeException e) {
	            throw e;
	        } catch (Throwable throwable) {
	            return value;
	        }
		};		
		
		return new ComparatorChainer<>(f);
	}

    @Override
    public int hashCode() {
        return function.hashCode();
    }

}
