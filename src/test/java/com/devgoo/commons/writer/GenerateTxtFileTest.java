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
	public void writeTxtWithFilePath() {
		try {
			URL url = classLoader.getResource("files/writer/file1.txt");

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
}
