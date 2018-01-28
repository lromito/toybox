package toybox.utils;

import java.util.function.BooleanSupplier;
import java.util.function.DoublePredicate;
import java.util.function.IntPredicate;
import java.util.function.LongPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

public final class Validations {
	
	private static final String ERROR_MESSAGE = "Validation check failed";
	
	public static boolean requires(Supplier<String> lazyMessage, BooleanSupplier condition) {
		if (condition.getAsBoolean()) {
			return true;
		} else {
			throw new IllegalArgumentException(lazyMessage.get());
		}
	}
	
	public static boolean requires(String message, BooleanSupplier condition) {
		return requires(() -> message, condition);
	}
	
	public static boolean requires(BooleanSupplier condition) {
		return requires(ERROR_MESSAGE, condition);
	}

	public static <T> T requiresValue(final T argument, Predicate<? super T> condition) {
		if (condition.test(argument)) {
			return argument;
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}
	
	public static int requiresInt(final int argument, IntPredicate condition) {
		if (condition.test(argument)) {
			return argument;
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}
	
	public static long requiresLong(final long argument, LongPredicate condition) {
		if (condition.test(argument)) {
			return argument;
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}
	
	public static double requiresDouble(final double argument, DoublePredicate condition) {
		if (condition.test(argument)) {
			return argument;
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}

	private Validations() {
		// This class is not meant to be instantiated.
	}
}
