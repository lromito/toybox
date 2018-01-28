/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toybox.utils.text;

import java.util.Arrays;
import java.util.stream.Stream;
import static toybox.utils.text.StringUtils.isNullOrEmpty;

/**
 *
 * @author Luca Romito
 */
public final class StringIterators {
    
    public static Iterable<String> splitOnCharacter(String arg, char delimiter) {
        return () -> new StringSplitIterator(StringUtils.nullToEmpty(arg), delimiter);
    }

    public static Iterable<String> splitOnFixedLength(String arg, int length) {
        return () -> new FixedLengthStringSplitIterator(StringUtils.nullToEmpty(arg), length);
    }

    public static Stream<String> splitWithRegex(String original, String regex) {
        return isNullOrEmpty(original) ? Stream.empty() : Arrays.stream(original.split(regex));
    }

    public static Stream<String> splitStringStream(String original, char delimiter) {
        if (isNullOrEmpty(original)) {
            return Stream.empty();
        }

        final Stream.Builder<String> builder = Stream.<String>builder();
        final int length = original.length();

        int startPosition = 0;
        int endPosition = original.indexOf(delimiter, startPosition);
        if (endPosition == -1) {
            endPosition = length;
        }

        while (startPosition <= endPosition) {
            builder.add(original.substring(startPosition, endPosition));
            startPosition = endPosition + 1;
            endPosition = original.indexOf(delimiter, startPosition);

            if (endPosition == -1) {
                endPosition = length;
            }
        }

        return builder.build();
    }
    
    private StringIterators() {
        // This is class is not meant to be instantiated.
    }
}
