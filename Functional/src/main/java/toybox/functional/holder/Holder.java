package toybox.functional.holder;

import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Holder<T> {

	public static <U> Holder<U> of(U value) {
		Objects.requireNonNull(value, "Value cannot be null");
		return new DefaultHolder<>(value);
	}
	
	public static <U> Holder<U> from(Supplier<? extends U> supplier) {
		Objects.requireNonNull(supplier, "Supplier cannot be null");
		return new DefaultHolder<>(supplier.get());
	}
	
	public static <U> NullableHolder<U> ofNull() {
		return new NullableHolder<>(null);
	}

	default public <R> Holder<R> map(Function<? super T,? extends R> mapper) {
		return Holder.of(mapper.apply(get()));
	}
	
	default public <R> Holder<R> flatMap(Function<? super T, Holder<R>> mapper) {
		return mapper.apply(get());
	}

	default boolean isNull() {
		return get() == null;
	}

	T get();

	void set(T newValue);

}