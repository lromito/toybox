package toybox.streams;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Predicate;

class TakeWhileSpliterator<T> implements Spliterator<T> {
	
	private final Spliterator<T> source;
	private final Predicate<? super T> pred;
	private boolean condition;
	
	public static <T> TakeWhileSpliterator<T> over(Spliterator<T> source, Predicate<? super T> predicate) {
		return new TakeWhileSpliterator<>(source, predicate);
	}

	private TakeWhileSpliterator(Spliterator<T> source, Predicate<? super T> pred) {
		this.source = source;
		this.pred = pred;
		condition = true;
	}

	@Override
	public int characteristics() {
		return source.characteristics() & ~Spliterator.SIZED;
	}

	@Override
	public long estimateSize() {
		return condition ? source.estimateSize() : 0;
	}

	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		return condition && source.tryAdvance(e -> {
			condition = pred.test(e);
			if (condition) {
				action.accept(e);
			}
		});
	}

	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

}
