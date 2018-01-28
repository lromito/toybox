package toybox.streams;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.LongConsumer;
import java.util.function.LongPredicate;
import java.util.function.LongSupplier;
import java.util.function.LongUnaryOperator;

public class LongForStyleSpliterator implements Spliterator.OfLong {
	private long position;
	private final LongPredicate condition;
	private final LongUnaryOperator nextValue;
	
	public static LongForStyleSpliterator of(long seed, LongPredicate condition, LongUnaryOperator nextValue) {
		Objects.requireNonNull(condition, "Condition lambda must not be null");
		Objects.requireNonNull(nextValue, "Next value lambda must not be null");
		return new LongForStyleSpliterator(seed, condition, nextValue);
	}	
	
	public static LongForStyleSpliterator of(LongSupplier seeder, LongPredicate condition, LongUnaryOperator nextValue) {
		Objects.requireNonNull(seeder, "Seeder lambda must not be null");
		return LongForStyleSpliterator.of(seeder.getAsLong(), condition, nextValue);
	}
	
	private LongForStyleSpliterator(long seed, LongPredicate condition, LongUnaryOperator nextValue) {
		this.position = seed;
		this.condition = condition;
		this.nextValue = nextValue;
	}

	@Override
	public int characteristics() {
		return Spliterator.IMMUTABLE & Spliterator.ORDERED;
	}

	@Override
	public long estimateSize() {
		return Long.MAX_VALUE;
	}

	@Override
	public boolean tryAdvance(LongConsumer action) {
		if (condition.test(position)) {
			action.accept(position);
			position = nextValue.applyAsLong(position);
			return true;
		}
		return false;
	}

	@Override
	public java.util.Spliterator.OfLong trySplit() {
		// Parallel traversal is not implemented
		return null;
	}

}
