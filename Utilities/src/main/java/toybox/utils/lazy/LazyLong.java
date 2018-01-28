package toybox.utils.lazy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.LongSupplier;

public class LazyLong implements LongSupplier, Serializable {
	private static final long serialVersionUID = 5542733396901032543L;
	/**
	 * 
	 */
	private volatile long value;
	private transient volatile LongSupplier supplier;
	
	public static LazyLong of(LongSupplier supplier) {
		Objects.requireNonNull(supplier, "Supplier must not be null");
		if (supplier instanceof LazyLong) {
			return (LazyLong) supplier;
		}  else {
			return new LazyLong(supplier);
		}
	}
	
	private LazyLong(LongSupplier supplier) {
		this.supplier = supplier;
		value = 0L;
	}
	
	public long get() {
		if (!isInitialized()) {
			synchronized (this) {
				if (!isInitialized()) {
					value = supplier.getAsLong();
					supplier = null;
				}
			}
		} 
		return value;
	}
	
	public boolean isInitialized() {
		return supplier == null;
	}
	
	@Override
	public String toString() {
		return "LazyInitializationLong [" + (isInitialized() ?  get() : "not initialized") +  "]";
	}

	@Override
	public boolean equals(Object obj) {
		return (this == obj) || (obj instanceof LazyLong && get() == ((LazyLong) obj).get());
	}
	

	@Override
	public int hashCode() {
		return Long.hashCode(get());
	}

	@Override
	public long getAsLong() {
		return get();
	}
	
    private void writeObject(ObjectOutputStream s) throws IOException {
        get(); // forces value evaluation
        s.defaultWriteObject();
    }

}
