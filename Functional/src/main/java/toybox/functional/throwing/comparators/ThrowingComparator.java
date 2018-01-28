package toybox.functional.throwing.comparators;

import java.util.Comparator;
import  toybox.functional.throwing.ThrownByLamdbaRuntimeException;

public interface ThrowingComparator<T> extends Comparator<T> {

    @Override
    default int compare(T o1, T o2) {
        try {
            return doCompare(o1, o2);
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (Throwable throwable) {
            throw new ThrownByLamdbaRuntimeException(throwable);
        }
    }

    public int doCompare(T o1, T o2) throws Throwable;

}
