package com.devgoo.commons.wrapper;

import com.devgoo.commons.util.FileFormats;

/**
 * Created by madimetja on 2016/09/02.
 */
public class PhatFile extends java.io.File {
	private final java.io.File file;
	private FileFormats format;

	/**
	 * Default constructor for {@link java.io.File}
	 * @param pathname path to the file
	 */
	public PhatFile(String pathname) {
		super(pathname);
		file = super.getAbsoluteFile();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param parent {@link String}
	 * @param child {@link String}
	 */
	public PhatFile(String parent, String child) {
		super(parent, child);
		file = super.getAbsoluteFile();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param parent {@link java.io.File}
	 * @param child {@link java.io.File}
	 */
	public PhatFile(java.io.File parent, String child) {
		super(parent, child);
		file = super.getAbsoluteFile();
	}

	/**
	 * Default constructor for {@link java.io.File}
	 * @param uri path to the file
	 */
	public PhatFile(java.net.URI uri) {
		super(uri);
		file = super.getAbsoluteFile();
	}

	/**
	 * Converts the {@link java.io.File} contents to a {@link String}
	 *
	 * @return string {@link String}
	 */
	public String getContentAsString() throws java.io.FileNotFoundException {
		return new java.util.Scanner(this.file).useDelimiter("\\Z").next();
	}

	public FileFormats getFormat() {
		return format;
	}
}
