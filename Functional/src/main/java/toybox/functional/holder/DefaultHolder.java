package toybox.functional.holder;

import java.util.Objects;

/**
 * Holder class to be used to avoid using only final or effectively final values.
 * 
 * @author Luca Romito
 *
 */
class DefaultHolder<T> implements Holder<T> {
	private T value;

	DefaultHolder(T value) {
		this.value = value;
	}
	
	/* (non-Javadoc)
	 * @see toybox.utils.functional.Holder#get()
	 */
	@Override
	public T get() {
		return value;
	}
	
	/* (non-Javadoc)
	 * @see toybox.utils.functional.Holder#set(T)
	 */
	@Override
	public void set(T newValue) {
		this.value = Objects.requireNonNull(newValue, "New value cannot be null");
	}
	
	@Override
	public String toString() {
		return "Holder of [" + Objects.toString(value) + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}

	/**
	 * Two holders are equal if their objects are equal.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
                    return true;
                }
		if (obj instanceof DefaultHolder<?>) {
			DefaultHolder<?> other = (DefaultHolder<?>) obj;
			return Objects.equals(value, other.value);
		}
		return false;
	}
	
	
}
