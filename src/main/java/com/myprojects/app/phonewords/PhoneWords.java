package com.myprojects.app.phonewords;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;



public class PhoneWords {

	private Set<String> words;
	private static final String PHONE_NO_REGEX = "^[0-9]{10}";

	private final String[][] digits = new String[][] {

	new String[] {}, new String[] {}, new String[] { "a", "b", "c" },
			new String[] { "d", "e", "f" }, new String[] { "g", "h", "i" },
			new String[] { "j", "k", "l" }, new String[] { "m", "n", "o" },
			new String[] { "p", "q", "r", "s" },
			new String[] { "t", "u", "v" }, new String[] { "w", "x", "y", "z" } };


	public Set<String> readWords(String fileName) throws FileNotFoundException {

		words = new HashSet<String>();
		URL dict = PhoneWords.class.getClassLoader().getResource(fileName);
		if (dict == null)
			throw new FileNotFoundException("");

		Scanner input = new Scanner(new File(dict.getPath()));
		while (input.hasNext()) {
			String word = input.next();
			if (word.length() > 1 && StringUtils.isAlpha(word)) {
				words.add(word.toLowerCase());
			}
		}

		return words;
	}

	public Set<String> printWordsMatching(String phoneNumber) throws IllegalArgumentException{

		//TODO: replace this with log, test only
		System.out.println("Printing words matching " + phoneNumber);
		
		Pattern phoneNummberPattern = Pattern.compile(PHONE_NO_REGEX);
		Matcher pnMacthed = phoneNummberPattern.matcher(phoneNumber);
		
		if(StringUtils.isEmpty(phoneNumber) || phoneNumber.length() != 10 || !pnMacthed.matches()){
			//TODO: replace this with log, test only
			System.out.println("Invalid phone Number, Should be 10 Digits");
			throw new IllegalArgumentException("Invalid phone Number, Should be 10 Digits");
		}

		Set<String> candidates = new HashSet<String>();
		Set<String> wordList = new HashSet<String>();
		
		for (String digit : phoneNumber.split("")) {

			if (digit.equals("")) {
				continue;
			}
			String[] letters = digits[Integer.parseInt(digit)];
			if (letters.length == 0) {
				continue;
			}

			for (String letter : letters) {

				for (String candidate : candidates) {

					String newCandidate = candidate + letter;
					String matchedWord = wordsExistStartingWith(newCandidate);
					
					if (StringUtils.isNotEmpty(matchedWord)) {
						wordList.add(matchedWord);
					}
				}
			}
			if (candidates.isEmpty()) {
				candidates.addAll(Arrays.asList(letters));
			}
		}
		
		//TODO: replace this with log, test only
		System.out.println(wordList);
		return wordList;
	}

	private String wordsExistStartingWith(String startOfWord) {

		for (String word : words) {
			if (word.startsWith(startOfWord)) {
				return word;
			}
		}
		return "";
	}
}
