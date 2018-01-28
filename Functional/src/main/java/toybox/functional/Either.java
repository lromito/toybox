package toybox.functional;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Either<L, R> {
	
	public static <T, U> Either<T, U> leftFrom(T left) {
		return new Left<>(left);
	}
	
	public static <T, U> Either<T, U> rightFrom(U right) {
		return new Right<>(right);
	}
	
	public <T, U> Either<T, U> map(Function<? super L, ? extends T> ifLeft, Function<? super R, ? extends U> ifRight);
	
	public void from(Consumer<? super L> ifLeft, Consumer<? super R> ifRight);
	
	public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> mapper);
	
	public <U> Either<L, U> mapRight(Function<? super R, ? extends U> mapper);
	
	public void ifLeft(Consumer<? super L> consumer);
	
	public void ifRight(Consumer<? super R> consumer);
	
	public default Optional<L> toOptionalLeft() {
		return Optional.empty();
	}
	
	public default Optional<R> toOptionalRight() {
		return Optional.empty();
	}
		
	public default void orElseCall(Runnable otherwise) {
		otherwise.run();
	}
	
	public default boolean isLeft() {
		return false;
	}
	
	public default boolean isRight() {
		return false;
	}
	
	public Either<R, L> swap();
	
	public Object get();

	
	final class Left<L, R> implements Either<L, R> {
		private final L value;
		
		Left(L value) {
			this.value = value;
		}
		
		public L getLeft() {
			return value;
		}
		
		@Override
		public boolean isLeft() {
			return true;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public <T, U> Either<T, U> map(Function<? super L, ? extends T> ifLeft, Function<? super R, ? extends U> ifRight) {
			Objects.requireNonNull(ifLeft, "Left mapping function cannot be null");
			return Either.leftFrom(ifLeft.apply(getLeft()));
		}

		@Override
		public void from(Consumer<? super L> ifLeft, Consumer<? super R> ifRight) {
			Objects.requireNonNull(ifLeft, "Left mapping function cannot be null");
			ifLeft.accept(getLeft());
		}
		

		@Override
		public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> mapper) {
			Objects.requireNonNull(mapper, "Mapping function cannot be null");
			return Either.leftFrom(mapper.apply(getLeft()));
		}

		@Override
		public <U> Either<L, U> mapRight(Function<? super R, ? extends U> mapper) {
			Objects.requireNonNull(mapper, "Mapping function cannot be null");
			return Either.leftFrom(getLeft());
		}

		@Override
		public Either<R, L> swap() {
			return new Right<>(this.value);
		}
		
		@Override
		public Optional<L> toOptionalLeft() {
			return Optional.of(value);
		}

		@Override
		public Object get() {
			return getLeft();
		}
		
		@Override
		public boolean equals(Object obj) {
			return (this == obj) || (obj instanceof Left && Objects.equals(this.value, ((Left<?, ?>) obj).value));
		}
		
		@Override
		public String toString() {
			return "Either<Left>[" + get() + "]";
		}

		@Override
		public void ifLeft(Consumer<? super L> consumer) {
			consumer.accept(getLeft());			
		}

		@Override
		public void ifRight(Consumer<? super R> consumer) {
			// Does nothing by design			
		}
	}
	
	final class Right<L, R> implements Either<L, R> {
		private final R value;
		
		Right(R value) {
			this.value = value;
		}
		
		public R getRight() {
			return value;
		}
		
		@Override
		public boolean isRight() {
			return true;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(value);
		}

		@Override
		public <T, U> Either<T, U> map(Function<? super L, ? extends T> ifLeft, Function<? super R, ? extends U> ifRight) {
			Objects.requireNonNull(ifRight, "Right mapping function cannot be null");
			return Either.rightFrom(ifRight.apply(getRight()));
		}

		@Override
		public void from(Consumer<? super L> ifLeft, Consumer<? super R> ifRight) {
			Objects.requireNonNull(ifRight, "Right mapping function cannot be null");
			ifRight.accept(getRight());
		}
		
		

		@Override
		public <T> Either<T, R> mapLeft(Function<? super L, ? extends T> mapper) {
			Objects.requireNonNull(mapper, "Mapping function cannot be null");
			return Either.rightFrom(getRight());
		}

		@Override
		public <U> Either<L, U> mapRight(Function<? super R, ? extends U> mapper) {
			Objects.requireNonNull(mapper, "Mapping function cannot be null");
			return Either.rightFrom(mapper.apply(getRight()));
		}

		@Override
		public Either<R, L> swap() {
			return new Left<>(this.value);
		}
		
		

		@Override
		public Optional<R> toOptionalRight() {
			return Optional.of(value);
		}

		@Override
		public Object get() {
			return getRight();
		}
		
		@Override
		public boolean equals(Object obj) {
			return (this == obj) || (obj instanceof Right && Objects.equals(this.value, ((Right<?, ?>) obj).value));
		}
		
		@Override
		public String toString() {
			return "Either<Right>[" + get() + "]";
		}

		@Override
		public void ifLeft(Consumer<? super L> consumer) {
			// Does nothing by design
		}

		@Override
		public void ifRight(Consumer<? super R> consumer) {
			consumer.accept(getRight());			
		}
	}
	
}
