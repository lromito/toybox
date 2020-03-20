package toybox.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

@SuppressWarnings({"OptionalIsPresent", "OptionalUsedAsFieldOrParameterType"})
public final class OptionalConverters {
	
	public static Optional<Integer> primitiveToBoxed(OptionalInt original) {
		Objects.requireNonNull(original);
		return original.isPresent() ? Optional.of(original.getAsInt()) : Optional.empty();
	}
	
	public static Optional<Long> primitiveToBoxed(OptionalLong original) {
		Objects.requireNonNull(original);
		return original.isPresent() ? Optional.of(original.getAsLong()) : Optional.empty();
	}
	
	public static Optional<Double> primitiveToBoxed(OptionalDouble original) {
		Objects.requireNonNull(original);
		return original.isPresent() ? Optional.of(original.getAsDouble()) : Optional.empty();
	}
	
	public static OptionalInt boxedToOptionalInt(Optional<Integer> original) {
		Objects.requireNonNull(original);
		return original.isPresent() ? OptionalInt.of(original.get()) : OptionalInt.empty();
	}
	
	public static OptionalLong boxedToOptionalLong(Optional<Long> original) {
		Objects.requireNonNull(original);
		//noinspection OptionalIsPresent
		return original.isPresent() ? OptionalLong.of(original.get()) : OptionalLong.empty();
	}
	
	public static OptionalDouble boxedToOptionalDouble(Optional<Double> original) {
		Objects.requireNonNull(original);
		return original.isPresent() ? OptionalDouble.of(original.get().longValue()) : OptionalDouble.empty();
	}

	public static <T> Optional<T> castIntoOptional(Object original, Class<T> target) {
		Objects.requireNonNull(original);
		Objects.requireNonNull(target);                
		return target.isInstance(original) ? Optional.of(target.cast(original)) : Optional.empty();
	}	
	
	private OptionalConverters() {
		// This class is not meant to be instantiated.
	}

}
