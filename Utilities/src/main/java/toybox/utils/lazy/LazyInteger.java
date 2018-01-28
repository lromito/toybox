package toybox.utils.lazy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.IntSupplier;

public class LazyInteger implements IntSupplier, Serializable {
	private static final long serialVersionUID = -4143819890771634047L;
	/**
	 * 
	 */
	private volatile int value;
	private transient volatile IntSupplier supplier;
	
	public static LazyInteger of(IntSupplier supplier) {
		Objects.requireNonNull(supplier, "Supplier must not be null");
		if (supplier instanceof LazyInteger) {
			return (LazyInteger) supplier;
		} else {
			return new LazyInteger(supplier);
		}
	}
	
	private LazyInteger(IntSupplier supplier) {
		this.supplier = supplier;
		value = 0;
	}
	
	public int get() {
		if (!isInitialized()) {
			synchronized (this) {
				if (!isInitialized()) {
					value = supplier.getAsInt();
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
		return "LazyInteger [" + (isInitialized() ?  get() : "not initialized") +  "]";
	}

	
	@Override
	public boolean equals(Object obj) {
		return (this == obj) || (obj instanceof LazyInteger && Integer.compare(get(), ((LazyInteger) obj).get()) == 0);
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(get());
	}

	@Override
	public int getAsInt() {
		return get();
	}
	
    private void writeObject(ObjectOutputStream s) throws IOException {
        get(); // forces value evaluation
        s.defaultWriteObject();
    }
}
