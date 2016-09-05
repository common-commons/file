package com.devgoo.commons.writer;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.implementations.Writer;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by chrismipi on 2016/09/05.
 */
public class GenerateJsonFileTest {
	private WriterInterface writer;
	private ClassLoader classLoader;

	@Before
	public void setUp() {
		writer = new Writer();
		assertNotNull(writer);
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
	}

	@Test
	public void writeCorrectJsonFile() {
		try {
			String content = "{\"content\":\"Hello World, how are you World ?\"}";

			PhatFile file = writer.writeToFile("name", content, FileFormats.JSON);

			assertTrue(file.exists());

			assertEquals("name.json", file.getName());
			assertEquals(FileFormats.JSON, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (IOException | InvalidFileFormatException | UnknownFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void writeInCorrectJSONFile() {
		try {
			String content = "{\"message\":Hello World, how are you World ?\"}";

			writer.writeToFile("output", content, FileFormats.JSON);

			fail("Not supposed to pass, JSON is incorrect.");
		}catch (IOException | UnknownFileFormatException e) {
			fail(e.getLocalizedMessage());
		} catch ( InvalidFileFormatException e) {
			assertEquals("The JSON content is not valid.", e.getLocalizedMessage());
		}
	}

	@Test
	public void writeCorrectJsonWithFilePath() {
		try {
			URL url = classLoader.getResource("files/writer/file2.json");

			assertNotNull(url);

			String path = url.getPath();
			String content = "{\"name\": \"Hello World, how are you World ?\"}";

			PhatFile file = writer.writeToFile(FileFormats.JSON, content, path);

			assertTrue(file.exists());

			assertEquals("file2.json", file.getName());
			assertEquals(FileFormats.JSON, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (UnknownFileFormatException | IOException | InvalidFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}
}
