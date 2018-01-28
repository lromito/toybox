package toybox.utils;

import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;

public final class Pair<T, U> {
	private final T first;
	private final U second;
	

	/**
	 * Use this static method to create a Pair.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static <T, U> Pair<T, U> of(T first, U second) {
		return new Pair<>(first, second);
	}

	/**
	 * Made constructor private. Use static method {@link Pair#of(Object, Object)} instead.
	 * @param first
	 * @param second
	 */
	private Pair(final T first, final U second) {
		this.first = first;
		this.second = second;
	}

	public T getFirst() {
		return first;
	}

	public U getSecond() {
		return second;
	}
	
	public <T2> Pair<T2, U> withFirst(T2 newFirst) {
		return new Pair<>(newFirst, this.second);
	}
	
	public <U2> Pair<T, U2> withSecond(U2 newSecond) {
		return new Pair<>(this.first, newSecond);
	}
        
        public <R> Pair<R, U> mapFirst(Function<? super T, ? extends R> mapper) {
		return Pair.of(mapper.apply(first), second);
	}
	
	public <R> Pair<T, R> mapSecond(Function<? super U, ? extends R> mapper) {
		return Pair.of(first, mapper.apply(second));
	}

	public <R1, R2> Pair<R1, R2> map(Function<? super T, ? extends R1> mapFirst, Function<? super U, ? extends R2> mapSecond) {
		return Pair.of(mapFirst.apply(first), mapSecond.apply(second));
	}
	
	
	/**
	 * Con questa possiamo allegramente andare in monade.
	 * 
	 * @param mapper
	 * @return
	 */
	public <R1, R2> Pair<R1, R2> flatMap(BiFunction<? super T, ? super U, Pair<R1, R2>> mapper) {
		return mapper.apply(first, second);
	}
	
	public <R> R combine(BiFunction<? super T, ? super U, ? extends R> combiner) {
		return combiner.apply(first, second);
	}
	
	@Override
	public String toString() {
		return "(" + Objects.toString(getFirst(), "[null]") + ", " + Objects.toString(getSecond(), "[null]") + ")";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getFirst(), getSecond());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj instanceof Pair<?, ?>) {
			Pair<?, ?> other = (Pair<?, ?>) obj;
			return Objects.equals(first, other.first) && Objects.equals(second, other.second);
		}
		return false;
	}

}

