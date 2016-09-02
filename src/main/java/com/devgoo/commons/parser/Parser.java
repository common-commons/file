package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.interfaces.ParserInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

/**
 * Concrete Parser Class. This class implemets the contract specified by
 * the Parser Interface.
 *
 * Created by madimetja on 2016/09/02.
 */
public class Parser implements ParserInterface {

	public PhatFile parseFile(String absoluteFilePath, FileFormats fileFormat) throws UnknownFileFormatException {

		switch (fileFormat) {
			case TXT:
				return parseTextFile(absoluteFilePath);
			case JSON:
				return parseJsonFile(absoluteFilePath);
			case CSV:
				return parseCsvFile(absoluteFilePath);
		}

		throw new UnknownFileFormatException("");
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
	private PhatFile parseTextFile(String absoluteFilePath) {
		return new PhatFile(absoluteFilePath);
	}

	/**
	 * Parses a given .json file.
	 *
	 * @param absoluteFilePath The absolute path to the .json file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseJsonFile(String absoluteFilePath) {
		return null;
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
}
