package com.devgoo.commons.exceptions;

/**
 * Thrown in the event that the file format in question is unknown or not supported
 * by the library.
 */
public class UnknownFileFormatException extends Exception {

	/**
	 * Raises an UnknownFileFormatException exception.
	 * @param message The message to be displayed when the exception is thrown.
	 */
	public UnknownFileFormatException(String message){super(message);}
}
