package com.devgoo.commons.exceptions;

/**
 * Thrown in the event that the file format in question is not correct.
 */
public class InvalidFileFormatException extends Exception {

	/**
	 * Raises an InvalidFileFormatException exception.
	 * @param message The message to be displayed when the exception is thrown.
	 */
	public InvalidFileFormatException(String message){super(message);}
}
