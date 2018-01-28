package toybox.utils.text;

import java.util.Iterator;
import java.util.Objects;

public class StringIterator implements Iterator<Character> {
	
	private final String str;
	private final int length;
	private int position;
	
	public static StringIterator of(String str, int start, int end) {
		Objects.requireNonNull(str);
		return new StringIterator(str, start, end);
	}
	
	public static StringIterator of(String str) {
		Objects.requireNonNull(str);
		return new StringIterator(str, 0, str.length());
	}
	
	private StringIterator(String str, int position, int length) {
		this.str = str;
		this.position = position;
		this.length = length;
	}

	@Override
	public boolean hasNext() {
		return position < length;
	}

	@Override
	public Character next() {
		return str.charAt(position++);
	}

}
