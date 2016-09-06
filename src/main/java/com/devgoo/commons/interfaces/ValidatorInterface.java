package com.devgoo.commons.interfaces;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

import java.io.IOException;

/**
 * Created by madimetja on 2016/09/02.
 */
public interface ValidatorInterface {
	/**
	 * Validates the content if its the right format {@link FileFormats}
	 *
	 * @param content the string content
	 * @param format the format of the content {@link FileFormats}
	 * @return returns either true of false
	 */
	boolean validate(String content, FileFormats format) throws UnknownFileFormatException;

	/**
	 * Determines the file type for the given file.
	 *
	 * @param file The PhatFile object.
	 *
	 * @return Returns the FileFormats format of the file.
	 */
	FileFormats determineFileType(PhatFile file) throws IOException;
}
