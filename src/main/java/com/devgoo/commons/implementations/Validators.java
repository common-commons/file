package com.devgoo.commons.implementations;

import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
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
				try{
					new JSONObject(content);
					return true;
				} catch (JSONException e) {
					return false;
				}

			default:
				return false;
		}
	}
}
