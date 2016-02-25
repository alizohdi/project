package com.myprojects.app.reverse;

import java.util.StringTokenizer;

public class ReverseWords {
	
	private static final char CHAR_DELIMITER = ' ';
	private static final String STRING_DELIMITER = " ";

	public static void main(String[] args) throws Exception {
		ReverseWords rw = new ReverseWords();
		System.out.println(rw.reverseWords("This is a test"));

		char[] input = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 't', 'e', 's', 't' };
		rw.reverseWords(input);
		System.out.println(new String(input));
	}

	public String reverseWords(String input) {
		
		StringTokenizer st = new StringTokenizer(input, STRING_DELIMITER);

		String strReversedLine = "";

		while (st.hasMoreTokens()) {
			strReversedLine = st.nextToken() + STRING_DELIMITER + strReversedLine;
		}

		return strReversedLine;
	}

	public void reverseWords(char[] input) {
		reverseWord(input, 0, input.length);

		int start = -1;
		int end = -1;

		for (int i = 0; i < input.length; i++) {

			if (start == -1 && input[i] != CHAR_DELIMITER) {
				start = i;
			}
			if (end == -1 && input[i] == CHAR_DELIMITER) {
				end = i;
			}
			if (i == input.length - 1) {
				end = i + 1;
			}

			if (start != -1 && end != -1) {
				reverseWord(input, start, end);

				start = -1;
				end = -1;
			}
		}
	}

	public static void reverseWord(char[] input, int start, int length) {
		int mid = (length - start) / 2;
		length--;
		for (int i = 0; i < mid; i++, length--) {
			char t = input[start + i];
			input[start + i] = input[length];
			input[length] = t;
		}
	}
}
