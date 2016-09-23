package com.devgoo.commons.interfaces;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * This class describes the contract to be adhered to by the Parser class.
 *
 */
public interface ParserInterface {

	/**
	 * * Parses a given file on the file system.
	 *
	 * @param absoluteFilePath The absolute path to the file to be parsed.
	 * @param fileFormat The format of the file to be parsed.
	 *
	 * @return Returns a PhatFile instance of the file parsed.
	 *
	 * @throws IOException if the file cannot be read
	 * @throws InvalidFileFormatException file not Supported
	 * @throws UnknownFileFormatException file not Supported
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	PhatFile parseFile(String absoluteFilePath, FileFormats fileFormat) throws UnknownFileFormatException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException, JSONException;
}
