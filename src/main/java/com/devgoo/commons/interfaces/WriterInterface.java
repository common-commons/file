package com.devgoo.commons.interfaces;

import com.devgoo.commons.exceptions.IllegalPhatFileWriting;
import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * This class describes the contract to be adhered to by the WriterInterface interface.
 *
 */
public interface WriterInterface {
	/**
	 * Write a string to a {@link java.io.File}
	 *
	 * @param content the string which is to be written to the file
	 * @param absoluteFilePath the path where the file will be saved
	 * @param output output format {e.g: csv, json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 *
	 * @throws IOException if the file cannot be read
	 * @throws InvalidFileFormatException file not Supported
	 * @throws UnknownFileFormatException file not Supported
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	PhatFile writeToFile(FileFormats output, String content, String absoluteFilePath) throws InvalidFileFormatException, IOException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException;

	/**
	 * Write a string to a {@link java.io.File}
	 *
	 * @param name the name of the file to be written to
	 * @param content the string which is to be written to the file
	 * @param output output format {e.g: csv, json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 *
	 * @throws IOException if the file cannot be read
	 * @throws InvalidFileFormatException file not Supported
	 * @throws UnknownFileFormatException file not Supported
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	PhatFile writeToFile(String name, String content, FileFormats output) throws IOException, InvalidFileFormatException, UnknownFileFormatException, ParserConfigurationException, SAXException, JSONException;

	/**
	 * Write content of {@link org.json.JSONObject} to a {@link PhatFile} file
	 *
	 * @param name the name of the file to be written to
	 * @param content the string which is to be written to the file
	 * @param output output format {e.g: json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 *
	 * @throws java.io.IOException if the file cannot be read
	 * @throws IllegalPhatFileWriting illegal {@link PhatFile} writing
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	PhatFile writeToFile(String name, org.json.JSONObject content, FileFormats output) throws IllegalPhatFileWriting, java.io.IOException, ParserConfigurationException, SAXException, JSONException;

	/**
	 * Write content of {@link org.json.JSONArray} to a {@link PhatFile} file
	 *
	 * @param name the name of the file to be written to
	 * @param content content the string which is to be written to the file
	 * @param output output output format {e.g: json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 *
	 * @throws java.io.IOException if the file cannot be read
	 * @throws IllegalPhatFileWriting illegal {@link PhatFile} writing
	 * @throws ParserConfigurationException general Parser exception
	 * @throws SAXException XML file could not be read
	 * @throws JSONException JSON file could not be read
	 */
	PhatFile writeToFile(String name, org.json.JSONArray content, FileFormats output) throws java.io.IOException, IllegalPhatFileWriting, ParserConfigurationException, SAXException, JSONException;
}
