package toybox.streams;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class ForStyleIterator<E> implements Iterator<E> {
	
	private E position;
	private final Predicate<? super E> condition;
	private final UnaryOperator<E> valueProvider;
	
	
	public static <T> ForStyleIterator<T> of(Supplier<? extends T> initializer, Predicate<? super T> condition, UnaryOperator<T> valueProvider) {
		Objects.requireNonNull(initializer, "Initializer lambda must not be null");
		
		return ForStyleIterator.of(initializer.get(), condition, valueProvider);
	}
	
	public static <T> ForStyleIterator<T> of(T initial, Predicate<? super T> condition, UnaryOperator<T> valueProvider) {
		Objects.requireNonNull(condition, "Condition lambda must not be null");
		Objects.requireNonNull(valueProvider, "Value lambda must not be null");
		
		return new ForStyleIterator<>(initial, condition, valueProvider);
	}

	private ForStyleIterator(E initialValue, Predicate<? super E> condition, UnaryOperator<E> valueProvider) {
		this.position = initialValue;
		this.condition = condition;
		this.valueProvider = valueProvider;
	}
	
	@Override
	public boolean hasNext() {
		return condition.test(position);
	}

	@Override
	public E next() {
		position = valueProvider.apply(position);
		return position;
	}

}
