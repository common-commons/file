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
 * Created by madimetja on 2016/09/02.
 */
public interface WriterInterface {
	/**
	 * Write a string to a {@link java.io.File}
	 *
	 * @param content the string which is to be written to the file
	 * @param absoluteFilePath the path where the file will be saved
	 * @param output output format {e.g: csv, json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 */
	PhatFile writeToFile(FileFormats output, String content, String absoluteFilePath) throws InvalidFileFormatException, IOException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException;

	/**
	 * Write a string to a {@link java.io.File}
	 *
	 * @param content the string which is to be written to the file
	 * @param output output format {e.g: csv, json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 */
	PhatFile writeToFile(String name, String content, FileFormats output) throws IOException, InvalidFileFormatException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException;
}
