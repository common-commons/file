package com.devgoo.commons.implementations;

import com.devgoo.commons.exceptions.IllegalPhatFileWriting;
import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static com.devgoo.commons.util.FileFormats.JSON;
import static com.devgoo.commons.util.FileFormats.XML;

/**
 * Writer which implements {@link WriterInterface}
 *
 * The Supported file extensions are txt, csv, xml and json
 */
public class Writer implements WriterInterface {
	private org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(getClass().getName());

	@Override
	public PhatFile writeToFile(FileFormats output, String content, String absoluteFilePath) throws InvalidFileFormatException, java.io.IOException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
		java.io.File f;

		switch(output) {
			case TXT:
				f = new java.io.File(absoluteFilePath);
				break;
			case JSON:
				validateJson(content);
				f = new java.io.File(absoluteFilePath);
				break;
			case XML:
				validateXml(content);
				f = new java.io.File(absoluteFilePath);
				break;
			default:
				logger.error("File extension :> " + output.getExtension() + " is not Supported.");
				throw new InvalidFileFormatException("File format is not Supported.");
		}

		if(!f.exists())
			if(!f.createNewFile())
				throw new UnknownFileFormatException("File or Directory does not exist.");
		return createFile(f, content, null,output);
	}

	/**
	 * Validates json using the class {@link ValidatorInterface}
	 * @param content string which is validated
	 * @throws InvalidFileFormatException is the json is invalid
	 */
	private void validateJson(String content) throws InvalidFileFormatException, UnknownFileFormatException {
		final ValidatorInterface v = new Validators();
		if(!v.validate(content.trim(), JSON))
			throw new InvalidFileFormatException("The JSON content is not valid.");
	}

	@Override
	public PhatFile writeToFile(String name, String content, FileFormats output) throws java.io.IOException, InvalidFileFormatException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
		java.io.File f;
		String properName;

		switch(output) {
			case TXT:
				f = java.io.File.createTempFile(name, FileFormats.TXT.getExtension());
				properName = name + FileFormats.TXT.getExtension();
				break;
			case JSON:
				validateJson(content);
				f = java.io.File.createTempFile(name, output.getExtension());
				properName = name + output.getExtension();
				break;
			case XML:
				validateXml(content);
				f = java.io.File.createTempFile(name, output.getExtension());
				properName = name + output.getExtension();
				break;
			default:
				logger.error("File extension :> " + output.getExtension() + " is not Supported.");
				throw new InvalidFileFormatException("File format is not Supported.");
		}
		return createFile(f, content, properName, output);
	}

	@Override
	public PhatFile writeToFile(String name, JSONObject content, FileFormats output) throws IllegalPhatFileWriting, IOException, ParserConfigurationException, SAXException, JSONException {
		java.io.File f;
		String properName;

		switch (output) {
			case JSON:
			case TXT:
				f = java.io.File.createTempFile(name, output.getExtension());
				properName = name + output.getExtension();
				break;
			default:
				throw new IllegalPhatFileWriting("Cannot write the content to an extension " + output.getName());
		}

		return createFile(f, content.toString(), properName, output);
	}

	@Override
	public PhatFile writeToFile(String name, JSONArray content, FileFormats output) throws IOException, IllegalPhatFileWriting, ParserConfigurationException, SAXException, JSONException {
		java.io.File f;
		String properName;

		switch (output) {
			case JSON:
			case TXT:
				f = java.io.File.createTempFile(name, output.getExtension());
				properName = name + output.getExtension();
				break;

			default:
				throw new IllegalPhatFileWriting("Cannot write the content to an extension " + output.getName());
		}

		return createFile(f, content.toString(), properName, output);
	}

	/**
	 * Validates xml using the class {@link ValidatorInterface}
	 * @param content string which is validated
	 * @throws InvalidFileFormatException is the xml is invalid or {@link UnknownFileFormatException } is the file is not
	 * Supported
	 */
	private void validateXml(String content) throws InvalidFileFormatException, UnknownFileFormatException {
		final ValidatorInterface v = new Validators();
		if(!v.validate(content.trim(), XML))
			throw new InvalidFileFormatException("The XML content is not valid.");
	}

	private PhatFile createFile(java.io.File f, String content, String properName, FileFormats output) throws java.io.IOException, JSONException, SAXException, ParserConfigurationException {
		java.io.FileWriter fw = new java.io.FileWriter(f.getAbsoluteFile());
		java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
		bw.write(content);
		bw.close();

		PhatFile file = new PhatFile(f.toURI());
		file.setFormat(output);

		if(properName != null && !properName.isEmpty())
			file.setName(properName);

		return file;
	}
}