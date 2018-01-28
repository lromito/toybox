package toybox.functional;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public interface Try<T> {

    public static <V> Try<V> of(Supplier< ? extends V> function) {
        Objects.requireNonNull(function, "The function cannot be null");
        try {
            return new Success<>(function.get());
        } catch (Throwable e) {
            return new Failure<>(e);
        }
    }

    public <U> Try<U> map(Function<? super T, ? extends U> mapper);

    public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper);

    public T orElseGet(Supplier<? extends T> supplier);

    public T orElse(T returnValue);

    public Try<T> consume(Consumer<? super T> consumer);

    public <E extends Throwable> Try<T> recover(Class<E> exClass, Function<? super E, ? extends T> f);

    public boolean isSuccess();

    public boolean isFailure();

    final class Success<T> implements Try<T> {

        final T value;

        Success(T value) {
            this.value = value;
        }

        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> mapper) {
            return new Success<>(mapper.apply(value));
        }

        @Override
        public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
            return mapper.apply(value);
        }

        @Override
        public T orElseGet(Supplier<? extends T> supplier) {
            return value;
        }

        @Override
        public T orElse(T returnValue) {
            return this.value;
        }

        @Override
        public Try<T> consume(Consumer<? super T> consumer) {
            consumer.accept(value);
            return this;
        }

        @Override
        public <E extends Throwable> Try<T> recover(Class<E> exClass, Function<? super E, ? extends T> f) {
            return this;
        }

        @Override
        public boolean isSuccess() {
            return true;
        }

        @Override
        public boolean isFailure() {
            return false;
        }

    }

    final class Failure<T> implements Try<T> {

        private final Throwable exception;

        Failure(Throwable exception) {
            this.exception = exception;
        }

        @Override
        public <U> Try<U> map(Function<? super T, ? extends U> mapper) {
            return new Failure<>(exception);
        }

        @Override
        public <U> Try<U> flatMap(Function<? super T, Try<U>> mapper) {
            return new Failure<>(exception);
        }

        @Override
        public T orElseGet(Supplier<? extends T> supplier) {
            return supplier.get();
        }

        @Override
        public T orElse(T returnValue) {
            return returnValue;
        }

        @Override
        public Try<T> consume(Consumer<? super T> consumer) {
            return this;
        }

        @Override
        public <E extends Throwable> Try<T> recover(Class<E> exClass, Function<? super E, ? extends T> f) {
            if (exClass.isAssignableFrom(exception.getClass())) {
                final E assigned = exClass.cast(exception);
                return new Success<>(f.apply(assigned));
            } else {
                return this;
            }
        }

        @Override
        public boolean isSuccess() {
            return false;
        }

        @Override
        public boolean isFailure() {
            return true;
        }

    }
}
