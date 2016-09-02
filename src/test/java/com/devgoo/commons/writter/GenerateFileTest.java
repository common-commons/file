package com.devgoo.commons.writter;

import com.devgoo.commons.implementations.Writer;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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
		String content = "Hello World, how are you World ?";

		PhatFile file = writer.writeToFile(content, FileFormats.TXT);

		assertTrue(true);
	}
}
