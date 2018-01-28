package toybox.utils.text;

import java.util.Iterator;
import java.util.Objects;

class FixedLengthStringSplitIterator implements Iterator<String> {
	
	private final String str;
	private final int fixedLength;
	private int startPosition; 
	
	FixedLengthStringSplitIterator(String str, int fixedLength) {
		if (fixedLength <= 0) {
			throw new IllegalArgumentException("The length can only be greater than zero");
		}
		this.str = Objects.requireNonNull(str, "The string cannot be null");
		this.fixedLength = fixedLength;
		startPosition = 0;
	}

	@Override
	public boolean hasNext() {
		return startPosition < str.length();
	}

	@Override
	public String next() {
		String result = str.substring(startPosition, Math.min(startPosition + fixedLength, str.length()));
		startPosition += fixedLength;
		return result;
	}

}
