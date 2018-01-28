package toybox.functional.partial;

import java.util.function.IntFunction;

public interface IntPartialFunction<R> extends IntFunction<R> {

	boolean isDefinedAt(int value);

	@Override
	R apply(int value);

}