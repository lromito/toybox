package toybox.functional.partial;

import java.util.Objects;

class PartialFunctionFromArray<T> implements IntPartialFunction<T> {

	private final T[] values;
	
	public PartialFunctionFromArray(T[] values) {
		this.values = Objects.requireNonNull(values, "Array cannot be null");
	}

	@Override
	public boolean isDefinedAt(int value) {
		try {
			return values[value] != null;
		} catch (ArrayIndexOutOfBoundsException e) {
			return false;
		}
	}

	@Override
	public T apply(int value) {
		return values[value];
	}

}
