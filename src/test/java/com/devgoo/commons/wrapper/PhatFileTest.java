package com.devgoo.commons.wrapper;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class PhatFileTest {

	private ClassLoader classLoader;

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
	}

	@Test
	public void getPhatFileContent() {
		try {
			URL url = classLoader.getResource("files/text/file.txt");

			assertNotNull(url);

			File tmp = new File(url.toURI());

			assertTrue(tmp.exists());

			PhatFile file = new PhatFile(tmp.toURI());
			assertEquals("hello world\nhow are you world.", file.getContentAsString());
		} catch (URISyntaxException | IOException e) {
			fail(e.getLocalizedMessage());
		}
	}
}
