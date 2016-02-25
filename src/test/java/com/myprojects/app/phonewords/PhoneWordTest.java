package com.myprojects.app.phonewords;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;


public class PhoneWordTest {

	private Set<String> words;
	private PhoneWords phoneWords;
	private static final String fileName = "wordsEn.txt";

	@Before
	public void setUp() throws Exception {
		phoneWords = new PhoneWords();
		words = phoneWords.readWords(fileName);
	}

	@Test
	public void testValidPhoneNumber() {
		String phoneNumber = "1234567890";
		assertEquals(51, phoneWords.printWordsMatching(phoneNumber).size());
	}
	
	@Test
	public void testWordsFound() {
		String phoneNumber = "1234567890";
		assertTrue(phoneWords.printWordsMatching(phoneNumber).contains("crocks"));
	}
	
	@Test
	public void testInvalidPhoneNumber() {
		String phoneNumber = "1234zgdfg5678";
		try {
			phoneWords.printWordsMatching(phoneNumber);
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid phone Number, Should be 10 Digits", e.getMessage());
		}
	}
}
