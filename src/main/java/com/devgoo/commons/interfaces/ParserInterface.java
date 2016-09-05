package com.devgoo.commons.interfaces;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

import java.io.IOException;

/**
 * This class describes the contract to be adhered to by the Parser class.
 *
 * Created by madimetja on 2016/09/02.
 */
public interface ParserInterface {

	/**
	 * Parses a given file on the file system.
	 *
	 * @param absoluteFilePath The absolute path to the file to be parsed.
	 * @param fileFormat The format of the file to be parsed.
	 *
	 * @return Returns a PhatFile instance of the file parsed.
	 */
	PhatFile parseFile(String absoluteFilePath, FileFormats fileFormat) throws UnknownFileFormatException, IOException, InvalidFileFormatException;
}
