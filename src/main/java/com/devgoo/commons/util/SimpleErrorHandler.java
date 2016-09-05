package com.devgoo.commons.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Created by madimetja on 2016/09/04.
 */
public class SimpleErrorHandler implements ErrorHandler {

	private Logger logger = LoggerFactory.getLogger(getClass().getName());

	public void warning(SAXParseException e) throws SAXException {
		logger.info(e.getMessage());
	}

	public void error(SAXParseException e) throws SAXException {
		logger.error(e.getMessage());
	}

	public void fatalError(SAXParseException e) throws SAXException {
		logger.error(e.getMessage());
	}
}
