package toybox.streams;

import java.util.Objects;
import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.function.IntPredicate;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;

class IntForStyleSpliterator implements Spliterator.OfInt {
	private int position;
	private final IntPredicate condition;
	private final IntUnaryOperator nextValue;
	
	public static IntForStyleSpliterator of(IntSupplier seeder, IntPredicate condition, IntUnaryOperator nextValue) {
		Objects.requireNonNull(seeder, "Seeder lambda must not be null");
		return IntForStyleSpliterator.of(seeder.getAsInt(), condition, nextValue);
	}
	
	public static IntForStyleSpliterator of(int seed, IntPredicate condition, IntUnaryOperator nextValue) {
		Objects.requireNonNull(condition, "Condition lambda must not be null");
		Objects.requireNonNull(nextValue, "Next value lambda must not be null");
		return new IntForStyleSpliterator(seed, condition, nextValue);
	}
	
	private IntForStyleSpliterator(int seed, IntPredicate condition, IntUnaryOperator nextValue) {
		position = seed;
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
	public boolean tryAdvance(IntConsumer action) {
		if (condition.test(position)) {
			action.accept(position);
			position = nextValue.applyAsInt(position);
			return true;
		}
		return false;
	}

	@Override
	public java.util.Spliterator.OfInt trySplit() {
		// Parallel traversal is not implemented
		return null;
	}

}
