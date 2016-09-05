package com.devgoo.commons.implementations;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.util.SimpleErrorHandler;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class Validators implements ValidatorInterface {

	@Override
	public boolean validate(String content, FileFormats format) throws UnknownFileFormatException {
		if(content == null) return false;
		switch (format) {
			case TXT:
				//Plain text files can have content of any form.
				return true;
			case JSON:
				return validateJsonFormat(content);
			case CSV:
				return validateCsvFormat(content);
			case XML:
				return validateXmlFormat(content);
			default:
				throw new UnknownFileFormatException("The file type provided is not supported.");
		}
	}

	/**
	 * Validates that the given content is valid JSON format.
	 *
	 * @param content The content to be validated.
	 *
	 * @return Returns a boolean value representing the validity
	 * of the file.
	 */
	private boolean validateJsonFormat(String content){
		try {
			new org.json.JSONObject(content.trim());
		} catch (org.json.JSONException ex) {
			try {
				new org.json.JSONArray(content.trim());
			} catch (org.json.JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * TODO - Complete implementation of this function.
	 *
	 * Validates that the given content is valid csv format.
	 *
	 * The function will attempt to determine the delimiter used.
	 * If it can't be picked up, the file will be invalidated,
	 *
	 * @param content The content to be validated.
	 *
	 * @return Returns a boolean value representing the validity
	 * of the file.
	 */
	private boolean validateCsvFormat(String content) {

		try{

			return false;

		} catch (Exception e){
			return false;
		}

	}

	/**
	 * Validates that the given content is valid xml format.
	 *
	 * @param content The content to be validated.
	 *
	 * @return Returns a boolean value representing the validity
	 * of the file.
	 */
	private boolean validateXmlFormat(String content) {

		try{
			javax.xml.parsers.DocumentBuilderFactory factory = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			javax.xml.parsers.DocumentBuilder builder = factory.newDocumentBuilder();

			builder.setErrorHandler(new SimpleErrorHandler());
			builder.parse(new java.io.StringBufferInputStream(content));

			return true;
		} catch (Exception e){
			return false;
		}
	}
}
