package toybox.functional;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

public final class Predicates {

	public static <T> Predicate<T> alwaysTrue() {
		return ignored -> true;
	}
	
	public static <T> Predicate<T> alwaysFalse() {
		return ignored -> false;
	}
	
	public static <T> Predicate<T> in(Collection<? extends T> collection) {
		Objects.requireNonNull(collection, "The collection cannot be null");
		return element -> collection.contains(element);
	}
	
	public static Predicate<Object> isInstanceOf(Class<?> clazz) {
		Objects.requireNonNull(clazz, "The class cannot be null");
		return obj -> clazz.isInstance(obj);
	}
	
	public static Predicate<Class<?>> isAssignableFrom(Class<?> clazz) {
		Objects.requireNonNull(clazz, "The class cannot be null");
		return cls -> clazz.isAssignableFrom(cls);
	}
	
	private Predicates() {
		// This class is not meant to be instantiated.
	}
}
