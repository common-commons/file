package com.devgoo.commons.interfaces;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

import java.io.IOException;

/**
 * This class describes the contract to be adhered to by the ValidatorInterface class.
 *
 */
public interface ValidatorInterface {
	/**
	 * Validates the content if its the right format {@link FileFormats}
	 *
	 * @param content the string content
	 * @param format the format of the content {@link FileFormats}
	 * @return returns either true of false
	 * @throws UnknownFileFormatException if the file is not Supported
	 */
	boolean validate(String content, FileFormats format) throws UnknownFileFormatException;

	/**
	 * Determines the file type for the given file.
	 *
	 * @param file The PhatFile object.
	 *
	 * @return Returns the FileFormats format of the file.
	 * @throws IOException if the file cannot be read
	 */
	FileFormats determineFileType(PhatFile file) throws IOException;
}
