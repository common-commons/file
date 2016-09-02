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

	private PhatFile parseTextFile(String absoluteFilePath) {
		return null;
	}

	private PhatFile parseJsonFile(String absoluteFilePath) {
		return null;
	}

	private PhatFile parseCsvFile(String absoluteFilePath) {
		return null;
	}

}
