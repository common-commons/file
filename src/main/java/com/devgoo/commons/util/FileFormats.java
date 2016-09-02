package com.devgoo.commons.util;

/**
 * Created by madimetja on 2016/09/02.
 */
public enum FileFormats {
	TXT (".txt"),
	CSV (".csv"),
	JSON (".json"),
	XML (".xml");

	private String fileFormat;

	private FileFormats(String fileFormat){
		this.fileFormat = fileFormat;
	}

	public String getExtension(){
		return this.fileFormat;
	}

	public String getName(){
		return this.name().toLowerCase();
	}
}
