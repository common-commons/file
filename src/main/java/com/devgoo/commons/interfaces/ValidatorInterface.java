package com.devgoo.commons.interfaces;

import com.devgoo.commons.util.FileFormats;

/**
 * Created by madimetja on 2016/09/02.
 */
public interface ValidatorInterface {

	boolean validate(String content, FileFormats format);
}
