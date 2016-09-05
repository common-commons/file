package com.devgoo.commons.interfaces;

import com.devgoo.commons.exceptions.IllegalPhatFileWriting;
import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

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
	PhatFile writeToFile(FileFormats output, String content, String absoluteFilePath) throws InvalidFileFormatException, java.io.IOException, UnknownFileFormatException;

	/**
	 * Write a string to a {@link java.io.File}
	 *
	 * @param name the name of the file to be written to
	 * @param content the string which is to be written to the file
	 * @param output output format {e.g: csv, json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 */
	PhatFile writeToFile(String name, String content, FileFormats output) throws java.io.IOException, InvalidFileFormatException, UnknownFileFormatException;

	/**
	 * Write content of {@link org.json.JSONObject} to a {@link PhatFile} file
	 *
	 * @param name the name of the file to be written to
	 * @param content @param content the string which is to be written to the file
	 * @param output @param output output format {e.g: json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 */
	PhatFile writeToFile(String name, org.json.JSONObject content, FileFormats output) throws IllegalPhatFileWriting, java.io.IOException;

	/**
	 * Write content of {@link org.json.JSONArray} to a {@link PhatFile} file
	 *
	 * @param name the name of the file to be written to
	 * @param content @param content the string which is to be written to the file
	 * @param output @param output output format {e.g: json, txt}
	 * @return {@link PhatFile} which extends {@link java.io.File}
	 */
	PhatFile writeToFile(String name, org.json.JSONArray content, FileFormats output) throws java.io.IOException, IllegalPhatFileWriting;
}
