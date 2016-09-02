package com.devgoo.commons.parser;

import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by madimetja on 2016/09/02.
 */
public class ParserTest {

	private ClassLoader classLoader;

	private Parser parser;

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
	}

	/**
	 * Ensure that a plain text file on the file system can successfully be parsed.
	 */
	@Test
	public void testParseTextFile(){
		try{

			PhatFile plainTextFile = parser.parseFile("files/parser/textFile.txt", FileFormats.TXT);

			assertNotNull(plainTextFile);

			assertTrue(plainTextFile.getContentAsString().equals("This is a random TeXt FilE WitH RanDoM Data.\n" +
				"It should be parsed well if the logic to parse\n" +
				"plain text files works properly...."));

		} catch (Exception e){
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
