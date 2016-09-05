package com.devgoo.commons.validator;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.implementations.Validators;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by madimetja on 2016/09/02.
 */
public class XmlValidatorTest {

	private ClassLoader classLoader;

	private ValidatorInterface validator;

	private final String PATH_TO_VALID_XML_FILE = "files/xml/validXmlFile.xml";
	private final String PATH_TO_VALID_XML_FILE_WITH_ATTRIBUTES = "files/xml/validXmlFileWithAttributes.xml";
	private final String PATH_TO_EMPTY_XML_FILE = "files/xml/emptyXmlFile.xml";
	private final String PATH_TO_INVALID_XML_FILE = "files/xml/invalidXmlFile.xml";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		validator = new Validators();
	}

	@Test
	public void testThatValidXmlIsReadCorrectly() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_VALID_XML_FILE).getPath());
		assertTrue(validator.validate(testFile.getContentAsString(), FileFormats.XML));
	}

	@Test
	public void testThatValidXmlWithAttributesIsReadCorrectly() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_VALID_XML_FILE_WITH_ATTRIBUTES).getPath());
		assertTrue(validator.validate(testFile.getContentAsString(), FileFormats.XML));
	}

	@Test
	public void testThatAnEmptyFileIsRejected() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_EMPTY_XML_FILE).getPath());
		assertFalse(validator.validate(testFile.getContentAsString(), FileFormats.XML));
	}

	@Test
	public void testThatInvalidXmlIsRejected() throws IOException, UnknownFileFormatException {
		PhatFile testFile = new PhatFile(classLoader.getResource(PATH_TO_INVALID_XML_FILE).getPath());
		assertFalse(validator.validate(testFile.getContentAsString(), FileFormats.XML));
	}
}
