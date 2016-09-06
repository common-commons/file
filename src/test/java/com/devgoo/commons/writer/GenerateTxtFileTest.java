package com.devgoo.commons.writer;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.implementations.Writer;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.*;


/**
 * Created by madimetja on 2016/09/02.
 */
public class GenerateTxtFileTest {
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
	public void writeTxtFile() throws UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
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
	public void writeTxtWithFilePath() throws ParserConfigurationException, SAXException, JSONException {
		try {
			URL url = classLoader.getResource("files/text/file1.txt");

			assertNotNull(url);

			String path = url.getPath();
			String content = "Hello World, how are you World ?";

			PhatFile file = writer.writeToFile(FileFormats.TXT, content, path);

			assertTrue(file.exists());

			assertEquals("file1.txt", file.getName());
			assertEquals(FileFormats.TXT, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (UnknownFileFormatException | IOException | InvalidFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void writeCorrectJSONFile() throws UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
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
	public void writeInCorrectJSONFile() throws UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
		try {
			String content = "{\"message\":Hello World, how are you World ?\"}";

			writer.writeToFile("output", content, FileFormats.JSON);

			fail("Not supposed to pass, JSON is incorrect.");
		} catch (IOException | InvalidFileFormatException e) {
			assertEquals("The JSON content is not valid.", e.getLocalizedMessage());
		}
	}
}
