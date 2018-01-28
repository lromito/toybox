package toybox.utils;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Experimental function for Pair
 * @author Luca Romito
 * 
 * 
 * For union type support, see @see <a href="https://blog.jooq.org/2016/02/16/an-ingenious-workaround-to-emulate-sum-types-in-java/">This blog post.</a>
 *
 */
public final class Pairs {
	
	/**
	 * This function applies a single function to all pair members.
	 * 
     * @param <T>
     * @param <T1>
     * @param <T2>
	 * @param pair
	 * @param consumer
	 */
	public static <T, T1 extends T, T2 extends T> void forEach(Pair<T1, T2> pair, Consumer<? super T> consumer) {
		Objects.requireNonNull(pair);
		Objects.requireNonNull(consumer);
		consumer.accept(pair.getFirst());
		consumer.accept(pair.getSecond());
	}
	
	/**
	 * This function turns the pair into a Stream of the common type between the two values.
	 * 
     * @param <T>
     * @param <T1>
     * @param <T2>
	 * 	@param pair
     * @return 
	 */
	public static <T, T1 extends T, T2 extends T>  Stream<T> toStream(Pair<T1, T2> pair)  {
		Objects.requireNonNull(pair);
		return Stream.of(pair.getFirst(), pair.getSecond());
	}

	private Pairs() {
		// This class is not meant to be instantiated
	}
}
