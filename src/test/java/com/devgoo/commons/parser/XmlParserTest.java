package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by madimetja on 2016/09/02.
 */
public class XmlParserTest {

	private ClassLoader classLoader;

	private Parser parser;

	private final String PATH_TO_VALID_XML_FILE = "files/xml/validXmlFile.xml";
	private final String PATH_TO_VALID_XML_FILE_WITH_ATTRIBUTES = "files/xml/validXmlFileWithAttributes.xml";
	private final String PATH_TO_INVALID_XML_FILE = "files/xml/invalidXmlFile.xml";
	private final String PATH_TO_EMPTY_XML_FILE = "files/xml/emptyXmlFile.xml";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
		parser = new Parser();
	}

	/**
	 * Ensure that a valid xml file on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 *
	 * More specifically, it ensures that a file with valid XML content.
	 */
	@Test
	public void testParseValidXmlFileIsSuccessful() throws UnknownFileFormatException, IOException, InvalidFileFormatException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_VALID_XML_FILE).getPath(), FileFormats.XML);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "<note>\n" +
			"    <to>Alice</to>\n" +
			"    <from>Bob</from>\n" +
			"    <heading>Hello</heading>\n" +
			"    <body>I know what you did last summer...smh!</body>\n" +
			"</note>");
	}

	/**
	 * Ensure that a valid xml file with attributes on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 *
	 * More specifically, it ensures that a file with valid XML content.
	 */
	@Test
	public void testParseValidXmlFileWithAttributesIsSuccessful() throws UnknownFileFormatException, IOException, InvalidFileFormatException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_VALID_XML_FILE_WITH_ATTRIBUTES).getPath(), FileFormats.XML);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "<note description=\"This is the root attribute\">\n" +
			"    <to>Alice</to>\n" +
			"    <from value=\"Bob\">Bob</from>\n" +
			"    <heading>Hello</heading>\n" +
			"    <body>I know what you did last summer...smh!</body>\n" +
			"</note>");
	}


	/**
	 * Ensure that an invalid xml file on the file system is unsuccessfully parsed.
	 */
	@Test(expected = InvalidFileFormatException.class)
	public void testParseInvalidXmlFileFails() throws UnknownFileFormatException, IOException, InvalidFileFormatException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_INVALID_XML_FILE).getPath(), FileFormats.XML);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "<note>\n" +
			"    <to>Missing Closing Tag\n" +
			"    <from>Bob</from>\n" +
			"    <heading>Hello</heading>\n" +
			"    <body>I know what you did last summer...smh!</body>\n" +
			"</note>");
	}

	/**
	 * Ensure that an empty xml file on the file system is unsuccessfully parsed.
	 */
	@Test(expected = InvalidFileFormatException.class)
	public void testParseEmptyXmlFileFails() throws UnknownFileFormatException, IOException, InvalidFileFormatException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_EMPTY_XML_FILE).getPath(), FileFormats.XML);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "");
	}
}
