package toybox.functional.throwing;

import java.util.Objects;
import java.util.function.Function;

/**
 * N non-throwing (regular) lambda type
 * T throwing version (extension) of N
 * C chainer type for chained return methods
 * 
 * @author Luca Romito
 *
 */
public abstract class AbstractChainer<N, T extends N, C extends AbstractChainer<N,T,C>> {

	protected final T function;

	public AbstractChainer(T function) {
		this.function = Objects.requireNonNull(function, "The lambda must never be null");
	}

	public abstract C orTryWith(T other);
	
	public abstract C fallbackTo(N other);
	
	public abstract C elseThrow(Function<Throwable, ? extends Throwable> constructor);
	
}
