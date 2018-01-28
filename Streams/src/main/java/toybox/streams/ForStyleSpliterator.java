package toybox.streams;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

class ForStyleSpliterator<T> implements Spliterator<T> {
	private T position;
	private final Predicate<? super T> condition;
	private final UnaryOperator<T> nextValue;
	
	public static <E> ForStyleSpliterator<E> of(E seed, Predicate<? super E> condition, UnaryOperator<E> nextValue) {
		Objects.requireNonNull(condition, "Condition lambda must not be null");
		Objects.requireNonNull(nextValue, "Next value lambda must not be null");
		return new ForStyleSpliterator<>(seed, condition, nextValue);
	}
	
	public static <E> ForStyleSpliterator<E> of(Supplier<? extends E> seeder, Predicate<? super E> condition, UnaryOperator<E> nextValue) {
		Objects.requireNonNull(condition, "Supplier lambda must not be null");
		return ForStyleSpliterator.of(seeder.get(), condition, nextValue);
	}

	private ForStyleSpliterator(T seed, Predicate<? super T> condition, UnaryOperator<T> nextValue) {
		position = seed;
		this.condition = condition;
		this.nextValue = nextValue;
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		if (condition.test(position)) {
			action.accept(position);
			position = nextValue.apply(position);
			return true;
		}
		return false;
	}
	
	@Override
	public long estimateSize() {
		// We cannot estimate the size of the range (yet).
		return Long.MAX_VALUE;
	}
	
	@Override
	public Spliterator<T> trySplit() {
		// Parallel traversal is not implemented
		return null;
	}

	@Override
	public int characteristics() {
		return Spliterator.IMMUTABLE & Spliterator.ORDERED;
	}

}
