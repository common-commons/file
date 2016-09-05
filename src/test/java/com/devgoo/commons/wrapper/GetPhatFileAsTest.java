package com.devgoo.commons.wrapper;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
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
	public void testGetJsonObjectInstanceReturnsSuccessfullyForValidContent() throws URISyntaxException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException {
		URL url = classLoader.getResource(PATH_TO_VALID_JSON_FILE);
		assertNotNull(url);
		File tmp = new File(url.toURI());
		assertTrue(tmp.exists());

		PhatFile phatFile = new PhatFile(url.toURI());

		//Create an instance of the org.w3c.dom.Document
		org.json.JSONObject JsonObject = new org.json.JSONObject(url.toURI());

		assertTrue(phatFile.getAsJsonObject() instanceof org.json.JSONObject);
		assertEquals(JsonObject.toString(), phatFile.getAsJsonObject().toString());
	}

	@Test
	public void testGetDocumentInstanceReturnsSuccessfullyForValidContent() throws URISyntaxException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException {
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
}
