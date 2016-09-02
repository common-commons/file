package com.devgoo.commons.implementations;

import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class Writer implements WriterInterface {
	@Override
	public PhatFile writeToFile(String fileContent, String absoluteFilePath, FileFormats outputFileFormat) {
		return null;
	}

	@Override
	public PhatFile writeToFile(String content, FileFormats output) {
		return null;
	}
}
