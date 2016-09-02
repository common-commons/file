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
		switch (format) {
			case JSON:
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

			default:
				return false;
		}
	}
}
