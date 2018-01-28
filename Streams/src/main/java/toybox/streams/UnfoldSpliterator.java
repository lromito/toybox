package toybox.streams;

import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Unfold uses a generator function that returns an Optional.
 * If the Optional is empty the stream stops.
 * 
 * 
 * @author Luca Romito
 * @param <T> 
 */
class UnfoldSpliterator<T> implements Spliterator<T> {
	
	private Optional<T> current;
	private final Function<T, Optional<T>> generator;
	
	static <T> UnfoldSpliterator<T> over(T seed, Function<T, Optional<T>> generator) {
		return new UnfoldSpliterator<>(seed, generator);
	}
	
	private UnfoldSpliterator(T seed, Function<T, Optional<T>> generator) {
		current = Optional.of(seed);
		this.generator = generator;
	}

	@Override
	public int characteristics() {
		return Spliterator.IMMUTABLE & Spliterator.NONNULL & Spliterator.ORDERED;
	}

	@Override
	public long estimateSize() {
		return Long.MAX_VALUE;
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		current.ifPresent(action);
		current = current.flatMap(generator);
		return current.isPresent();
	}

	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

}
