package toybox.functional.partial;

import java.util.List;
import java.util.Objects;

class PartialFunctionFromList<R> implements IntPartialFunction<R> {
	
	private final List<R> list;
	
	public PartialFunctionFromList(List<R> list) {
		this.list = Objects.requireNonNull(list, "List cannot be null");
	}

	/* (non-Javadoc)
	 * @see toybox.utils.functional.IntPartialFunction#isDefinedAt(int)
	 */
	@Override
	public boolean isDefinedAt(int value) {
		try {
			return list.get(value) != null;
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see toybox.utils.functional.IntPartialFunction#apply(int)
	 */
	@Override
	public R apply(int value) {
		return list.get(value);
	}

}
