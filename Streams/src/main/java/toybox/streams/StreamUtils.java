package toybox.streams;

import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.function.LongPredicate;
import java.util.function.LongUnaryOperator;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import java.util.stream.Collector;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Some methods to be used when dealing with streams.
 * Some of them are taken from the protonpack library on github.
 * 
 * 
 * @author Luca Romito
 *
 */
public final class StreamUtils {
	
	public static <T> Stream<T> unfold(T seed, Function<T, Optional<T>> generator) {
		return StreamSupport.stream(UnfoldSpliterator.over(seed, generator), false);
	}
	
	public static <L, R, O> Stream<O> zip(Stream<L> left, Stream<R> right, BiFunction<? super L, ? super R, ? extends O> combiner) {
		return StreamSupport.stream(ZippingSpliterator.zipping(left.spliterator(), right.spliterator(), combiner), false);
	}
	
	/**
	 * Note: replaced by a new stream method in Java 9.
	 * 
     * @param <T>
	 * @param source
	 * @param condition
	 * @return
	 */
	public static <T> Stream<T> takeWhile(Stream<T> source, Predicate<? super T> condition) {
		return StreamSupport.stream(TakeWhileSpliterator.over(source.spliterator(), condition), false);
	}
	
	public static <T> Stream<T> takeUntil(Stream<T> source, Predicate<? super T> condition) {
		return StreamSupport.stream(TakeWhileSpliterator.over(source.spliterator(), condition.negate()), false);
	}
	
	/**
	 *  Note: replaced by a new stream method in Java 9.
	 *  
	 *  This effectively gives you the ability to replicate the standard for loop syntax as a stream, 
	 *  e.g. StreamUtils.iterate(0, i -> i < 5, i -> i + 1) will give you a stream of Integers from 0 to 4.
	 *  
     * @param <T>
	 * @param initializer
	 * @param condition
	 * @param valueProvider
	 * @return
	 */
	public static <T> Stream<T> iterate(Supplier<? extends T> initializer, Predicate<? super T> condition, UnaryOperator<T> valueProvider) {
		return StreamSupport.stream(ForStyleSpliterator.of(initializer, condition, valueProvider), false);
	}
	
//	public static <T> Stream<T> iterate(T seed, Predicate<? super T> condition, UnaryOperator<T> valueProvider) {
//		return StreamSupport.stream(ForStyleSpliterator.of(seed, condition, valueProvider), false);
//	}
	
	public static <T> IntStream iterateInts(int seed, IntPredicate condition, IntUnaryOperator valueProvider) {
		return StreamSupport.intStream(IntForStyleSpliterator.of(seed, condition, valueProvider), false);
	}
	
	public static <T> LongStream iterateLongs(int seed, LongPredicate condition, LongUnaryOperator valueProvider) {
		return StreamSupport.longStream(LongForStyleSpliterator.of(seed, condition, valueProvider), false);
	}
	
	public static <T> Stream<T> reject(Stream<T> source, Predicate<? super T> condition) {
		return source.filter(condition.negate());
	}
	
	public static <T> Stream<T> iterableToStream(Iterable <T> iterable) {
		return StreamSupport.stream(iterable.spliterator(), false);
	}
	
	public static <T> Stream<T> iteratorToStream(Iterator<T> iterator) {
		return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
	}
	
	public static Collector<Object, StringBuilder, String> stringCollector() {
		return Collector.of(StringBuilder::new, StringBuilder::append, StringBuilder::append, StringBuilder::toString);
	}
	
	private StreamUtils() {
		//This class is not meant to be instantiated.
	}
}
