package toybox.utils.text;

/**
 * All sorts of little utilities that are missing from the String class (and that you need lots of times).
 *
 * @author Luca Romito
 *
 */
public final class StringUtils {

    public static boolean isNullOrEmpty(final String arg) {
        return arg == null || arg.isEmpty();
    }

    public static String nullToEmpty(final String arg) {
        return (arg == null) ? "" : arg;
    }

    public static String normalize(final String arg) {
        return nullToEmpty(arg).replaceAll("\\s+", " ").trim();
    }

    public static Iterable<Character> makeIterable(final String arg) {
        return () -> StringIterator.of(StringUtils.nullToEmpty(arg));
    }
    
    private StringUtils() {
        //This class is not meant to be instantiated.
    }
}
