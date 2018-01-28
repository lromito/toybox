package toybox.utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayIterator<T> implements Iterator<T> {	
	private final T[] array;
	private final int begin;
	private final int end;
	private int current;
	
	public static <T> ArrayIterator<T> of(T[] array, int begin, int end) {
		return new ArrayIterator<>(array, begin, end);
	}
	
	public static <T> ArrayIterator<T> of(T[] array) {
		return new ArrayIterator<>(array, 0, array.length);
	}
	
	public static <T> Iterable<T> makeIterable(T[] arr) {
		return () -> ArrayIterator.of(arr);
	}
	
	private ArrayIterator(T[] array, int begin, int end) {
		this.array = Objects.requireNonNull(array, "Array cannot be null");
		this.begin = begin;
		this.end = end;
		this.current = this.begin;
	}

	@Override
	public boolean hasNext() {
		return current < end;
	}

	@Override
	public T next() {
		if (hasNext()) {
			return array[current++];
		} else {
			throw new NoSuchElementException("Attempting to get element [" + current + "] from an array with [" + array.length + "]");
		}
	}

}
