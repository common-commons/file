package com.devgoo.commons.validator;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.implementations.Validators;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by madimetja on 2016/09/02.
 */
public class PlainTextValidatorTest {

	private ClassLoader classLoader;

	private ValidatorInterface validator;

	private final String PATH_TO_EMPTY_TEXT_FILE = "files/text/plainTextFileOne.txt";
	private final String PATH_TO_SIMPLE_TEXT_FILE = "files/text/plainTextFileThree";
	private final String PATH_TO_COMPLEX_TEXT_FILE = "files/text/plainTextFileTwo.txt";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		validator = new Validators();
	}

	/**
	 * Ensures that an empty text file can be parsed successfully.
	 *
	 * @throws IOException
	 * @throws UnknownFileFormatException
	 */
	@Test
	public void testThatEmptyPlainTextFileIsSeenAsValid() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_EMPTY_TEXT_FILE).getPath());
		assertTrue(validator.validate(testFile.getContentAsString(), FileFormats.TXT));
	}

	/**
	 * Ensures that a text file with a single line can be parsed successfully.
	 *
	 * @throws IOException
	 * @throws UnknownFileFormatException
	 */
	@Test
	public void testThatSimplePlainTextFileSeenAsValid() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_SIMPLE_TEXT_FILE).getPath());
		assertTrue(validator.validate(testFile.getContentAsString(), FileFormats.TXT));
	}

	/**
	 * Ensures that a text file with multiple paragraphs can be parsed successfully.
	 *
	 * @throws IOException
	 * @throws UnknownFileFormatException
	 */
	@Test
	public void testThatComplexPlainTextFileSeenAsValid() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_COMPLEX_TEXT_FILE).getPath());
		assertTrue(validator.validate(testFile.getContentAsString(), FileFormats.TXT));
	}
}
