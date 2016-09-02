package com.devgoo.commons.implementations;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class Writer implements WriterInterface {
	private static final String TXT = ".txt";
	private static final String CSV = ".csv";
	private static final String JSON = ".json";

	@Override
	public PhatFile writeToFile(String name, String fileContent, String absoluteFilePath, FileFormats outputFileFormat) {
		return null;
	}

	@Override
	public PhatFile writeToFile(String name, String content, FileFormats output) throws java.io.IOException, InvalidFileFormatException {
		java.io.File f;
		String properName;

		switch(output) {
			case TXT:
				f = java.io.File.createTempFile(name, TXT);
				properName = name + TXT;
				break;
			case JSON:
				ValidatorInterface v = new Validators();
				if(v.validate(content.trim(), output)) {
					f = java.io.File.createTempFile(name, JSON);
					properName = name + JSON;
				}
				else throw new InvalidFileFormatException("The JSON content is not valid.");
				break;
			default:
				throw new InvalidFileFormatException("File format is not Supported.");
		}

		java.io.FileWriter fw = new java.io.FileWriter(f.getAbsoluteFile());
		java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
		bw.write(content);
		bw.close();

		PhatFile file = new PhatFile(f.toURI());
		file.setFormat(output);
		file.setName(properName);
		return file;
	}
}
