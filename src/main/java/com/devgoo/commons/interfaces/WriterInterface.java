package com.devgoo.commons.interfaces;

import com.devgoo.commons.util.FileFormats;

import java.io.File;

/**
 * Created by madimetja on 2016/09/02.
 */
public interface WriterInterface {
	/**
	 * Write a string to a {@link java.io.File}
	 *
	 * @param fileContent the string which is to be written to the file
	 * @param absoluteFilePath the path where the file will be saved
	 * @param outputFileFormat output format {e.g: csv, json}
	 * @return
	 */
	File writeToFile(String fileContent, String absoluteFilePath, FileFormats outputFileFormat);
}
