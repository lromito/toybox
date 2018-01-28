package toybox.functional.partial;

import java.util.Map;
import java.util.Objects;

class PartialFunctionFromMap<T, R> implements PartialFunction<T, R> {
	private final Map<T, R> map;

	public PartialFunctionFromMap(Map<T, R> map) {
		this.map = Objects.requireNonNull(map, "Map cannot be null");
	}

	@Override
	public boolean isDefinedAt(T value) {
		return map.containsKey(value);
	}

	@Override
	public R apply(T argument) {
		return map.get(argument);
	}
}