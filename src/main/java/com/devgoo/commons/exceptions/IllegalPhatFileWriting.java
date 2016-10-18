package com.devgoo.commons.exceptions;

/**
 * Thrown in the event that writing content to a file fails.
 * This could be due to the file not existing, or the content being written
 * not being of the correct form.
 */
public class IllegalPhatFileWriting extends Exception {

	/**
	 * Raises an IllegalPhatFileWriting exception.
	 * @param message The message to be displayed when the exception is thrown.
	 */
	public IllegalPhatFileWriting(String message) {
		super(message);
	}
}
