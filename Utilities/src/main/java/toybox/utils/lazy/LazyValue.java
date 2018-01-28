package toybox.utils.lazy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Supplier;

public class LazyValue<T> implements Supplier<T>, Serializable {
    private static final long serialVersionUID = -6409998429111074960L;
    /**
     * 
     */
    private volatile T value;
    private transient volatile Supplier<? extends T> supplier;

    @SuppressWarnings("unchecked")
    public static <T> LazyValue<T> of(Supplier<? extends T> supplier) {
            Objects.requireNonNull(supplier, "Supplier must not be null");
            if (supplier instanceof LazyValue) {
                    return (LazyValue<T>) supplier;
            } else {
                    return new LazyValue<>(supplier);
            }
    }

    private LazyValue(Supplier<? extends T> supplier) {
            this.supplier = supplier;
            value = null;
    }

    /**
     * Uses double checked locking which was broken before Java 5.0
     * 
     * @return
     */
    @Override
    public T get() {
            if (!isInitialized()) {
                    synchronized (this) {
                            if (!isInitialized()) {
                                    value = supplier.get();
                                    supplier = null;
                            }
                    }
            } 
            return value;
    }

    public boolean isInitialized() {
            return supplier == null;				
    }

    public <U> LazyValue<U> map(Function<? super T, ? extends U> mapper) {
            Objects.requireNonNull(mapper, "Mapper must not be null");
            return LazyValue.of(() -> mapper.apply(get()));
    }

    @Override
    public String toString() {
            return "LazyValue [" + (isInitialized() ?  get() : "not initialized") +  "]";
    }

    @Override
    public boolean equals(Object obj) {
            return (this == obj) || (obj instanceof LazyValue<?> && Objects.equals(get(), ((LazyValue<?>) obj).get()));
    }

    @Override
    public int hashCode() {
            return Objects.hash(get());
    }
	
    private void writeObject(ObjectOutputStream s) throws IOException {
        get(); // forces value evaluation
        s.defaultWriteObject();
    }
}
