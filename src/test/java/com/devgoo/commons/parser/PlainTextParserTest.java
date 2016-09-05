package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by madimetja on 2016/09/02.
 */
public class PlainTextParserTest {

	private ClassLoader classLoader;

	private Parser parser;

	private final String PATH_TO_TEXT_FILE = "files/parser/textFile.txt";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
		parser = new Parser();
	}

	/**
	 * Ensure that a plain text file on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 */
	@Test
	public void testParseTextFile() throws UnknownFileFormatException, IOException, InvalidFileFormatException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_TEXT_FILE).getPath(), FileFormats.TXT);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "This is a random TeXt FilE WitH RanDoM Data.\n" +
			"It should be parsed well if the logic to parse\n" +
			"plain text files works properly....");
	}
}
