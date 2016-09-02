package com.devgoo.commons.interfaces;

import com.devgoo.commons.util.FileFormats;

import java.io.File;

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
	 * @return Returns a Java IO File instance of the file parsed.
	 */
	File parseFile(String absoluteFilePath, FileFormats fileFormat);

}
