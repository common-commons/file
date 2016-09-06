package com.devgoo.commons.wrapper;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class GetPhatFileAsTest {

	private ClassLoader classLoader;

	private final String PATH_TO_VALID_JSON_FILE = "files/json/jsonFile.json";
	private final String PATH_TO_INVALID_JSON_FILE = "files/json/jsonListFile.json";

	private final String PATH_TO_VALID_XML_FILE = "files/xml/validXmlFile.xml";
	private final String PATH_TO_INVALID_XML_FILE = "files/xml/invalidXmlFile.xml";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
	}

	@Test
	public void testGetJsonObjectInstanceReturnsSuccessfullyForValidContent() throws URISyntaxException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException, JSONException {
		URL url = classLoader.getResource(PATH_TO_VALID_JSON_FILE);
		assertNotNull(url);
		File tmp = new File(url.toURI());
		assertTrue(tmp.exists());

		PhatFile phatFile = new PhatFile(url.toURI());

		//Create an instance of the org.w3c.dom.Document
		org.json.JSONObject JsonObject = new org.json.JSONObject("{\n" +
			"  \"title\": \"Barney Is A Dinaa-whaaa??\",\n" +
			"  \"description\": \"Incredibly random description...\",\n" +
			"  \"listNode\": [\n" +
			"    \"Value Within List\",\n" +
			"    \"Another Value Within List\",\n" +
			"    \"A Final Value Within The List\"\n" +
			"  ],\n" +
			"  \"nestedObject\": {\n" +
			"    \"nestedKey\": \"Nested Key Value\"\n" +
			"  }\n" +
			"}");

		org.json.JSONObject phatFileObjectInstance = phatFile.getAsJsonObject();

		assertTrue(phatFile.getAsJsonObject() instanceof org.json.JSONObject);
		assertEquals(JsonObject.toString(), phatFile.getAsJsonObject().toString());
	}

	/**
	 * Ensure that an attempt to get a JSONObject from XML content throws an exception.
	 */
	@Test(expected = InvalidFileFormatException.class)
	public void testGetJsonObjectInstanceThrowsExceptionIfNotJsonContent() throws URISyntaxException, ParserConfigurationException, SAXException, JSONException, IOException, InvalidFileFormatException {

		URL url = classLoader.getResource(PATH_TO_VALID_XML_FILE);
		assertNotNull(url);
		File tmp = new File(url.toURI());
		assertTrue(tmp.exists());

		PhatFile phatFile = new PhatFile(url.toURI());

		assertNotNull(phatFile);

		phatFile.getAsJsonObject();
	}

	@Test
	public void testGetDocumentInstanceReturnsSuccessfullyForValidContent() throws URISyntaxException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException, JSONException {
		URL url = classLoader.getResource(PATH_TO_VALID_XML_FILE);
		assertNotNull(url);
		File tmp = new File(url.toURI());
		assertTrue(tmp.exists());

		PhatFile phatFile = new PhatFile(url.toURI());

		//Create an instance of the org.w3c.dom.Document
		javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
		org.w3c.dom.Document document = builder.parse(tmp);

		assertTrue(phatFile.getAsDocument() instanceof org.w3c.dom.Document);
		assertEquals(document.getTextContent(), phatFile.getAsDocument().getTextContent());
	}

	/**
	 * Ensure that an attempt to get an XMLDocument from JSON content throws an exception.
	 */
	@Test(expected = InvalidFileFormatException.class)
	public void testGetXmlDocumentInstanceThrowsExceptionIfNotXmlContent() throws ParserConfigurationException, InvalidFileFormatException, SAXException, IOException, URISyntaxException, JSONException {

		URL url = classLoader.getResource(PATH_TO_VALID_JSON_FILE);
		assertNotNull(url);
		File tmp = new File(url.toURI());
		assertTrue(tmp.exists());

		PhatFile phatFile = new PhatFile(url.toURI());

		assertNotNull(phatFile);

		phatFile.getAsDocument();
	}
}
