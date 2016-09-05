package com.devgoo.commons.implementations;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.interfaces.WriterInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;

import java.io.IOException;

import static com.devgoo.commons.util.FileFormats.JSON;

;

/**
 * Created by chrismipi on 2016/09/02.
 */
public class Writer implements WriterInterface {

	@Override
	public PhatFile writeToFile(FileFormats output, String content, String absoluteFilePath) throws InvalidFileFormatException, java.io.IOException, UnknownFileFormatException {
		java.io.File f;

		switch(output) {
			case TXT:
				f = new java.io.File(absoluteFilePath);
				break;
			case JSON:
				validateJson(content);
				f = new java.io.File(absoluteFilePath);

				break;
			default:
				throw new InvalidFileFormatException("File format is not Supported.");
		}

		if(!f.exists())
			if(!f.createNewFile())
				throw new UnknownFileFormatException("File or Directory does not exist.");
		return createFile(f, content, null,output);
	}

	private void validateJson(String content) throws InvalidFileFormatException, UnknownFileFormatException {
		final ValidatorInterface v = new Validators();
		if(!v.validate(content.trim(), JSON))
			throw new InvalidFileFormatException("The JSON content is not valid.");
	}

	@Override
	public PhatFile writeToFile(String name, String content, FileFormats output) throws java.io.IOException, InvalidFileFormatException, UnknownFileFormatException {
		java.io.File f;
		String properName;

		switch(output) {
			case TXT:
				f = java.io.File.createTempFile(name, FileFormats.TXT.getExtension());
				properName = name + FileFormats.TXT.getExtension();
				break;
			case JSON:
				validateJson(content);
				f = java.io.File.createTempFile(name, output.getExtension());
				properName = name + output.getExtension();
				break;
			default:
				throw new InvalidFileFormatException("File format is not Supported.");
		}
		return createFile(f, content, properName, output);
	}

	private PhatFile createFile(java.io.File f, String content, String properName, FileFormats output) throws IOException {
		java.io.FileWriter fw = new java.io.FileWriter(f.getAbsoluteFile());
		java.io.BufferedWriter bw = new java.io.BufferedWriter(fw);
		bw.write(content);
		bw.close();

		PhatFile file = new PhatFile(f.toURI());
		file.setFormat(output);

		if(properName != null && !properName.isEmpty())
			file.setName(properName);

		return file;
	}
}
