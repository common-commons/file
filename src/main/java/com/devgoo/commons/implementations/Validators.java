package com.devgoo.commons.implementations;

import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
}
