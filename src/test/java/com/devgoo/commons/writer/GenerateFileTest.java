package com.devgoo.commons.writer;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.implementations.Writer;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;


/**
 * Created by madimetja on 2016/09/02.
 */
public class GenerateFileTest {
	private WriterInterface writer;

	@Before
	public void setUp() {
		writer = new Writer();
		assertNotNull(writer);
	}

	@Test
	public void writeTxtFile() {
		try {
			String content = "Hello World, how are you World ?";

			PhatFile file = writer.writeToFile("name", content, FileFormats.TXT);

			assertTrue(file.exists());

			assertEquals("name.txt", file.getName());
			assertEquals(FileFormats.TXT, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (IOException | InvalidFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void writeCorrectJSONFile() {
		try {
			String content = "{\"message\":\"Hello World, how are you World ?\"}";

			PhatFile file = writer.writeToFile("output", content, FileFormats.JSON);

			assertTrue(file.exists());

			assertEquals("output.json", file.getName());
			assertEquals(FileFormats.JSON, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (IOException | InvalidFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void writeInCorrectJSONFile() {
		try {
			String content = "{\"message\":Hello World, how are you World ?\"}";

			writer.writeToFile("output", content, FileFormats.JSON);

			fail("Not supposed to pass, JSON is incorrect.");
		} catch (IOException | InvalidFileFormatException e) {
			assertEquals("The JSON content is not valid.", e.getLocalizedMessage());
		}
	}
}
