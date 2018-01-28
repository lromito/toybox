package toybox.utils.lazy;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.function.DoubleSupplier;

public class LazyDouble implements DoubleSupplier, Serializable {
	private static final long serialVersionUID = 4061029651995314978L;
	/**
	 * 
	 */
	private volatile double value;
	private transient volatile DoubleSupplier supplier;
	
	public static LazyDouble of(DoubleSupplier supplier) {
		Objects.requireNonNull(supplier, "Supplier must not be null");
		if (supplier instanceof LazyDouble) {
			return (LazyDouble) supplier;
		} else {
			return new LazyDouble(supplier);
		}
	}
	
	private LazyDouble(DoubleSupplier supplier) {
		this.supplier = supplier;
		value = 0L;
	}
	
	public double get() {
		if (!isInitialized()) {
			synchronized (this) {
				if (!isInitialized()) {
					value = supplier.getAsDouble();
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
		return "LazyDouble [" + (isInitialized() ?  get() : "not initialized") +  "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return (this == obj) || (obj instanceof LazyDouble && Double.compare(get(), ((LazyDouble) obj).get()) == 0);
	}

	@Override
	public int hashCode() {
		return Double.hashCode(get());
	}

	@Override
	public double getAsDouble() {
		return get();
	}
	
    private void writeObject(ObjectOutputStream s) throws IOException {
        get(); // forces value evaluation
        s.defaultWriteObject();
    }
}
