package toybox.functional.holder;

import java.util.Objects;

class NullableHolder<T> implements Holder<T> {
	private T value;
	
	NullableHolder(T value) {
		this.value = value;
	}

	@Override
	public T get() {
		return value;
	}

	@Override
	public void set(T newValue) {
		this.value = newValue;		
	}
	
	@Override
	public String toString() {
		return "NullableHolder of [" + Objects.toString(value) + "]";
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
		if (this == obj)
			return true;
		if (obj instanceof NullableHolder<?>) {
			NullableHolder<?> other = (NullableHolder<?>) obj;
			return Objects.equals(value, other.value);
		}
		return false;
	}
	
}
