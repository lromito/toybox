package toybox.streams;

import java.util.Spliterator;
import java.util.function.BiFunction;
import java.util.function.Consumer;

class ZippingSpliterator<L, R, O> implements Spliterator<O> {
	private final Spliterator<L> left;
	private final Spliterator<R> right;
	private final BiFunction<? super L, ? super R, ? extends O> combiner;
	private boolean rightHadNext;
	
	public static <L, R, O> ZippingSpliterator<L, R, O> zipping(Spliterator<L> left, Spliterator<R> right, BiFunction<? super L, ? super R, ? extends O> combiner) {
		return new ZippingSpliterator<>(left, right, combiner);
	}
	
	private ZippingSpliterator(Spliterator<L> left, Spliterator<R> right, BiFunction<? super L, ? super R, ? extends O> combiner) {
		this.left = left;
		this.right = right;
		this.combiner = combiner;
		rightHadNext= false;
	}

	@Override
	public int characteristics() {
		return left.characteristics() & right.characteristics() & ~(Spliterator.DISTINCT | Spliterator.SORTED);
	}

	@Override
	public long estimateSize() {
		return Math.min(left.estimateSize(), right.estimateSize());
	}


	@Override
	public Spliterator<O> trySplit() {
		return null;
	}

	@Override
	public boolean tryAdvance(Consumer<? super O> action) {
		rightHadNext = false;
		boolean leftHadNext = left.tryAdvance(l -> 
			right.tryAdvance(r -> {
				rightHadNext = true;
				action.accept(combiner.apply(l, r));
		}));
		return leftHadNext && rightHadNext;
	}

}
