package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.implementations.Validators;
import com.devgoo.commons.interfaces.ParserInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Concrete Parser Class. This class implements the contract specified by
 * the Parser Interface.
 *
 */
public class Parser implements ParserInterface {

	private Validators validators = new Validators();

	/**
	 * Parses a given file with a given file format and returns the file as
	 * a {@link PhatFile}.
	 *
	 * @param absoluteFilePath The absolute path to the file to be parsed.
	 * @param fileFormat The format of the file to be parsed.
	 *
	 * @return {@link PhatFile}
	 * @throws UnknownFileFormatException Thrown in the event that the given file format is unknown or unsupported.
	 * @throws IOException Thrown if there was some IO error while parsing the file.
	 * @throws InvalidFileFormatException Thrown in the event that the given file format is incorrect for the content in the file.
	 * @throws ParserConfigurationException Thrown in the event that an error occurred while constructing the {@link PhatFile}
	 * @throws SAXException Thrown if there was some error parsing the content for as an XML Document.
	 * @throws JSONException Thrown if there was some error parsing the content as JSON.
	 */
	public PhatFile parseFile(String absoluteFilePath, FileFormats fileFormat) throws UnknownFileFormatException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException, JSONException {

		switch (fileFormat) {
			case TXT:
				return parseTextFile(absoluteFilePath);
			case JSON:
				return parseJsonFile(absoluteFilePath);
			case CSV:
				return parseCsvFile(absoluteFilePath);
			case XML:
				return parseXmlFile(absoluteFilePath);
			default:
				throw new UnknownFileFormatException("Unsupported file version.");
		}
	}

	/**
	 * Parses a given .txt file.
	 *
	 * In essence, text files will just be spat out as they
	 * are found. No special formatting or rules to be applied
	 * as these files can follow any format.
	 *
	 * @param absoluteFilePath The absolute path to the .txt file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseTextFile(String absoluteFilePath) throws IOException, ParserConfigurationException, SAXException, JSONException {
		return new PhatFile(absoluteFilePath);
	}

	/**
	 * Parses a given .json file.
	 *
	 * The function will first validate that the given file is
	 * a valid Json file. If not, an exception will be thrown.
	 *
	 * @param absoluteFilePath The absolute path to the .json file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseJsonFile(String absoluteFilePath) throws IOException, InvalidFileFormatException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
		return validateAndParseFile(absoluteFilePath, FileFormats.JSON);
	}

	/**
	 * Parses a given .csv file. The logic will attempt to pick up the delimiter automatically.
	 *
	 * The supported delimiters are the following:
	 * 1. Pipe "|"
	 * 2. Comma ","
	 * 3. Space " "
	 * 4. Tilda "~"
	 *
	 * If an unsupported delimiter is found, then an exception will be thrown.
	 *
	 * @param absoluteFilePath The absolute path to the .csv file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseCsvFile(String absoluteFilePath) {
		return null;
	}

	/**
	 * Parses a given .xml file.
	 *
	 * @param absoluteFilePath The absolute path to the .xml file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseXmlFile(String absoluteFilePath) throws InvalidFileFormatException, IOException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
		return validateAndParseFile(absoluteFilePath, FileFormats.XML);
	}

	/**
	 * Validates that the content of the indicated file matches the given format.
	 *
	 * @param absoluteFilePath The absolute path to the file.
	 * @param fileFormat The file format to which the content should be parsed to.
	 * @return
	 * @throws IOException Thrown if there was some IO error while parsing the file.
	 * @throws InvalidFileFormatException Thrown in the event that the given file format is incorrect for the content in the file.
	 * @throws UnknownFileFormatException Thrown in the event that the given file format is unknown or unsupported.
	 * @throws ParserConfigurationException Thrown in the event that an error occurred while constructing the {@link PhatFile}
	 * @throws SAXException Thrown if there was some error parsing the content for as an XML Document.
	 * @throws JSONException Thrown if there was some error parsing the content as JSON.
	 */
	private PhatFile validateAndParseFile(String absoluteFilePath, FileFormats fileFormat) throws IOException, InvalidFileFormatException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException {
		PhatFile jsonFile = new PhatFile(absoluteFilePath);

		//validate the file content. If this line does not throw
		//an exception, then the content is valid JSON.
		if(validators.validate(jsonFile.getContentAsString(), fileFormat)){
			return jsonFile;
		}else{
			throw new InvalidFileFormatException("The given file is not valid " + fileFormat + " format.");
		}
	}
}
