package com.devgoo.commons.interfaces;

import com.devgoo.commons.util.FileFormats;

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
	boolean validate(String content, FileFormats format);
}
