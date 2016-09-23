package com.devgoo.commons.wrapper;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.implementations.Validators;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import org.json.JSONException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * PhatFile which wraps the following classes:
 * -{@link java.io.File}
 * -{@link org.json.JSONObject}
 * -{@link com.fasterxml.jackson.databind.JsonNode}
 * -{@link org.w3c.dom.Document}
 *
 * More functionality is added
 */
public class PhatFile extends java.io.File {
	private final java.io.File file;
	private org.json.JSONObject jsonObject;
	private com.fasterxml.jackson.databind.JsonNode jsonNode;
	private org.w3c.dom.Document xmlDocument;
	private FileFormats format;
	private ValidatorInterface validators;
	private String name;

	/**
	 * Default constructor for {@link java.io.File}
	 * @param pathname path to the file
	 *
	 * @throws IOException if the file cannot be read
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	public PhatFile(String pathname) throws IOException, ParserConfigurationException, SAXException, JSONException {
		super(pathname);
		file = super.getAbsoluteFile();
		validators = new Validators();
		this.format = validators.determineFileType(this);
		initialiseCompatibleFiles();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param parent {@link String}
	 * @param child {@link String}
	 *
	 * @throws IOException if the file cannot be read
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	public PhatFile(String parent, String child) throws IOException, ParserConfigurationException, SAXException, JSONException {
		super(parent, child);
		file = super.getAbsoluteFile();
		validators = new Validators();
		this.format = validators.determineFileType(this);
		initialiseCompatibleFiles();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param parent {@link java.io.File}
	 * @param child {@link java.io.File}
	 *
	 * @throws IOException if the file cannot be read
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	public PhatFile(java.io.File parent, String child) throws IOException, ParserConfigurationException, SAXException, JSONException {
		super(parent, child);
		file = super.getAbsoluteFile();
		validators = new Validators();
		this.format = validators.determineFileType(this);
		initialiseCompatibleFiles();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param uri path to the file
	 *
	 * @throws IOException if the file cannot be read
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	public PhatFile(java.net.URI uri) throws IOException, ParserConfigurationException, SAXException, JSONException {
		super(uri);
		file = super.getAbsoluteFile();
		validators = new Validators();
		this.format = validators.determineFileType(this);
		initialiseCompatibleFiles();
	}

	private void initialiseCompatibleFiles() throws IOException, SAXException, ParserConfigurationException, JSONException {
		this.jsonObject = null;
		this.xmlDocument = null;
	}

	/**
	 * Converts the {@link java.io.File} contents to a {@link String}
	 *
	 * @return string {@link String}
	 *
	 * @throws java.io.IOException if the file cannot be read
	 */
	public String getContentAsString() throws java.io.IOException {
		return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(this.file.getAbsolutePath())));
	}

	public FileFormats getFormat() {
		return format;
	}

	public void setFormat(FileFormats format) {
		this.format = format;
	}

	@Override
	public String getName() {
		if (name != null && !name.isEmpty()) return name;
		else return super.getName();
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the file object as an org.json.JSONObject.
	 *
	 * @return {@link org.json.JSONObject}
	 *
	 * @throws InvalidFileFormatException file format is not Supported
	 * @throws IOException if the file cannot be read
	 * @throws JSONException JSON file could not be read
	 */
	public org.json.JSONObject getAsJsonObject() throws InvalidFileFormatException, IOException, JSONException {

		if(this.format.equals(FileFormats.JSON)){
			if(this.jsonObject == null){
				this.jsonObject = new org.json.JSONObject(this.getContentAsString().trim());
			}

			return this.jsonObject;

		}else{
			throw new InvalidFileFormatException("The given file of type " + this.format + " cannot be returned as org.json.JSONObject.");
		}
	}

	/**
	 * Returns the file object as a {@link com.fasterxml.jackson.databind.JsonNode}.
	 *
	 * @return {@link com.fasterxml.jackson.databind.JsonNode}
	 *
	 * @throws InvalidFileFormatException file format is not Supported
	 * @throws IOException if the file cannot be read
	 * @throws JSONException JSON file could not be read
	 */
	public com.fasterxml.jackson.databind.JsonNode getAsJsonNode() throws InvalidFileFormatException, IOException, JSONException {

		if(this.format.equals(FileFormats.JSON)){
			if(this.jsonNode == null){

				com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
				this.jsonNode = mapper.readTree(this.getContentAsString());
			}

			return this.jsonNode;

		}else{
			throw new InvalidFileFormatException("The given file of type " + this.format + " cannot be returned as com.fasterxml.jackson.databind.JsonNode.");
		}
	}

	/**
	 * Returns the file object as an org.w3c.dom.Document.
	 *
	 * @return {@link org.w3c.dom.Document}
	 *
	 * @throws InvalidFileFormatException file format not Supported
	 * @throws ParserConfigurationException general Parser exception
	 * @throws IOException if the file cannot be read
	 * @throws SAXException XML file could not be read
	 */
	public org.w3c.dom.Document getAsDocument() throws InvalidFileFormatException, ParserConfigurationException, IOException, SAXException {

		if (this.format.equals(FileFormats.XML)) {

			if(this.xmlDocument == null){
				javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
				factory.setValidating(false);
				factory.setNamespaceAware(true);
				javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
				xmlDocument = builder.parse(new java.io.StringBufferInputStream(this.getContentAsString()));
				this.xmlDocument = builder.parse(this.file.getAbsolutePath());
			}

			return this.xmlDocument;

		} else {
			throw new InvalidFileFormatException("The given file of type " + this.format + " cannot be returned as org.w3c.dom.Document.");
		}
	}
}
