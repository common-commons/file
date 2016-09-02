package com.devgoo.commons.wrapper;

import java.io.File;
import java.net.URI;

/**
 * Created by madimetja on 2016/09/02.
 */
public class PhatFile extends File {


	public PhatFile(String pathname) {
		super(pathname);
	}

	public PhatFile(String parent, String child) {
		super(parent, child);
	}

	public PhatFile(File parent, String child) {
		super(parent, child);
	}

	public PhatFile(URI uri) {
		super(uri);
	}


}
