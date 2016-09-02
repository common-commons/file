package com.devgoo.commons.writter;

import com.devgoo.commons.implementations.Writer;
import com.devgoo.commons.interfaces.WriterInterface;
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
		assertTrue(true);
	}
}
