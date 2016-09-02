package com.devgoo.commons.parser;

import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by madimetja on 2016/09/02.
 */
public class ParserTest {

	private ClassLoader classLoader;

	private Parser parser;

	private final String PATH_TO_TEXT_FILE = "files/parser/textFile.txt";
	private final String PATH_TO_JSON_FILE = "files/parser/jsonFile.json";

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
	public void testParseTextFile(){
		try{
			PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_TEXT_FILE).getPath(), FileFormats.TXT);

			assertNotNull(plainTextFile);

			assertEquals(plainTextFile.getContentAsString(), "This is a random TeXt FilE WitH RanDoM Data.\n" +
				"It should be parsed well if the logic to parse\n" +
				"plain text files works properly....");

		} catch (Exception e){
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

	/**
	 * Ensure that a plain text file on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 */
	@Test
	public void testParseJsonFile(){
		try{

			PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_TEXT_FILE).getPath(), FileFormats.JSON);

			assertNotNull(plainTextFile);

			assertEquals(plainTextFile.getContentAsString(), "{\n" +
				"  \"title\": \"Barney Is A Dinaa-whaaa??\",\n" +
				"  \"description\": \"Incredibly random description...\",\n" +
				"  \"listNode\": [\n" +
				"    \"Value Within List\",\n" +
				"    \"Another Value Within List\",\n" +
				"    \"A Final Value Within The List\"\n" +
				"  ],\n" +
				"  \"nestedObject\": {\n" +
				"    \"nestedKey\": \"Nested Key Value\"\n" +
				"  }\n" +
				"}");

		} catch (Exception e){
			e.printStackTrace();
			fail(e.getLocalizedMessage());
		}
	}

}
