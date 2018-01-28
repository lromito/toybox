package toybox.utils.text;

import java.util.Iterator;
import java.util.Objects;

/**
 * This class acts as a replacement for the String#split method.
 * 
 * However, it splits the string along a single character instead of a regular expression 
 * and returns an iterator, instead of an array
 * 
 * @author Luca Romito
 *
 */
class StringSplitIterator implements Iterator<String> {
	
	private final String str;
	private final char delimiter;
	private int startPosition; //startPosition = -1 means we have reached the end
	private int endPosition;
	
	StringSplitIterator(String str, char delimiter) {
		this.str = Objects.requireNonNull(str, "The string cannot be null");
		this.delimiter = delimiter;
		// if the string is already empty than there is nothing to do.
		startPosition = (str.length() != 0) ? 0 : -1;
		endPosition = 0;
	}

	@Override
	public boolean hasNext() {
		return startPosition != -1;
	}

	@Override
	public String next() {
		endPosition = str.indexOf(delimiter, startPosition);
		if (endPosition == -1) {
			endPosition = str.length();
		}
		if (hasNext()) {
			String value = str.substring(startPosition, endPosition);
			
			final int nextCandidate = endPosition + 1;
			if (nextCandidate < str.length()) {
				startPosition = nextCandidate;
			} else {
				startPosition = -1; //we can stop
			};
			return value;
		} else {
			return null;
		}
	}

}
