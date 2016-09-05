package com.devgoo.commons.implementations;

import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.util.SimpleErrorHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringBufferInputStream;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class Validators implements ValidatorInterface {

	@Override
	public boolean validate(String content, FileFormats format) {
		if(content == null) return false;
		switch (format) {
			case JSON:
				return validateJsonFormat(content);
			case CSV:
				return validateCsvFormat(content);
			case XML:
				return validateXmlFormat(content);
			default:
				return false;
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
			new JSONObject(content.trim());
		} catch (JSONException ex) {
			try {
				new JSONArray(content.trim());
			} catch (JSONException ex1) {
				return false;
			}
		}
		return true;
	}

	/**
	 *
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
	 *
	 * TODO - Complete implementation of this function.
	 *
	 * Validates that the given content is valid xml format.
	 *
	 * @param content The content to be validated.
	 *
	 * @return Returns a boolean value representing the validity
	 * of the file.
	 */
	private boolean validateXmlFormat(String content) {

		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setValidating(false);
			factory.setNamespaceAware(true);

			DocumentBuilder builder = factory.newDocumentBuilder();

			builder.setErrorHandler(new SimpleErrorHandler());
			Document document = builder.parse(new StringBufferInputStream(content));

			return true;
		} catch (Exception e){
			return false;
		}
	}
}
