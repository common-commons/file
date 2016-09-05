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
public class GenerateXmlFileTest {
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
	public void writeCorrectXmlFile() {
		try {
			String content =  "<note>\n" +
												"    <to>Alice</to>\n" +
												"    <from>Bob</from>\n" +
												"    <heading>Hello</heading>\n" +
												"    <body>I know what you did last summer...smh!</body>\n" +
												"</note>";

			PhatFile file = writer.writeToFile("name", content, FileFormats.XML);

			assertTrue(file.exists());

			assertEquals("name.xml", file.getName());
			assertEquals(FileFormats.XML, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (IOException | InvalidFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}

	@Test
	public void writeInCorrectXmlFile() {
		try {
			String content =  "<note>\n" +
												"    <to>Alice</to>\n" +
												"    <from>Bob</from>\n" +
												"    <heading>Hello</heading>\n" +
												"    <body I know what you did last summer...smh!</body>\n" +
												"</note>";
			writer.writeToFile("output", content, FileFormats.XML);

			fail("Not supposed to pass, XML is incorrect.");
		}catch (IOException e) {
			fail(e.getLocalizedMessage());
		} catch ( InvalidFileFormatException e) {
			assertEquals("The XML content is not valid.", e.getLocalizedMessage());
		}
	}

	@Test
	public void writeCorrectXmlWithFilePath() {
		try {
			URL url = classLoader.getResource("files/writer/file3.xml");

			assertNotNull(url);

			String path = url.getPath();
			String content =  "<note>\n" +
												"    <to>Alice</to>\n" +
												"    <from>Bob</from>\n" +
												"    <heading>Hello</heading>\n" +
												"    <body>I know what you did last summer...smh!</body>\n" +
												"</note>";

			PhatFile file = writer.writeToFile(FileFormats.XML, content, path);

			assertTrue(file.exists());

			assertEquals("file3.xml", file.getName());
			assertEquals(FileFormats.XML, file.getFormat());
			assertEquals(content, file.getContentAsString());
		} catch (UnknownFileFormatException | IOException | InvalidFileFormatException e) {
			fail(e.getLocalizedMessage());
		}
	}

}
