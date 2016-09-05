package com.devgoo.commons.wrapper;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.util.FileFormats;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created by madimetja on 2016/09/02.
 */
public class PhatFile extends java.io.File {
	private final java.io.File file;
	private FileFormats format;
	private String name;

	/**
	 * Default constructor for {@link java.io.File}
	 * @param pathname path to the file
	 */
	public PhatFile(String pathname) {
		super(pathname);
		file = super.getAbsoluteFile();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param parent {@link String}
	 * @param child {@link String}
	 */
	public PhatFile(String parent, String child) {
		super(parent, child);
		file = super.getAbsoluteFile();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param parent {@link java.io.File}
	 * @param child {@link java.io.File}
	 */
	public PhatFile(java.io.File parent, String child) {
		super(parent, child);
		file = super.getAbsoluteFile();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param uri path to the file
	 */
	public PhatFile(java.net.URI uri) {
		super(uri);
		file = super.getAbsoluteFile();
	}

	/**
	 * Converts the {@link java.io.File} contents to a {@link String}
	 *
	 * @return string {@link String}
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
	 * @return org.json.JSONObject.
	 * @throws InvalidFileFormatException
	 */
	public org.json.JSONObject getAsJsonObject() throws InvalidFileFormatException {

		if(this.format.equals(FileFormats.JSON)){
			return new org.json.JSONObject(this);
		}else{
			throw new InvalidFileFormatException("The given file of type " + this.format + " cannot be returned as org.json.JSONObject.");
		}
	}

	/**
	 * Returns the file object as an org.w3c.dom.Document.
	 *
	 * @return org.w3c.dom.Document.
	 *
	 * @throws InvalidFileFormatException
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public org.w3c.dom.Document getAsDocument() throws InvalidFileFormatException, ParserConfigurationException, IOException, SAXException {

		if (this.format.equals(FileFormats.XML)) {
			javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);
			javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();
			return builder.parse(this);
		} else {
			throw new InvalidFileFormatException("The given file of type " + this.format + " cannot be returned as org.w3c.dom.Document.");
		}
	}
}
