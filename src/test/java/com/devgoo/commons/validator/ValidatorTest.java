package com.devgoo.commons.validator;

import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.implementations.Validators;
import com.devgoo.commons.interfaces.ValidatorInterface;
import com.devgoo.commons.util.FileFormats;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by madimetja on 2016/09/02.
 */
public class ValidatorTest {
	private ValidatorInterface validator;

	@Before
	public void setUp() {
		validator = new Validators();
	}

	@Test
	public void validateEmptyStringRegardlessOfType() throws UnknownFileFormatException {
		assertFalse(validator.validate("", FileFormats.JSON));
		assertTrue(validator.validate("", FileFormats.TXT));
		assertFalse(validator.validate("", FileFormats.CSV));
		assertFalse(validator.validate("", FileFormats.XML));
	}

	@Test
	public void validateCorrectJson() throws UnknownFileFormatException {
		String json = "{\"name\" : \"name\"}";
		assertTrue(validator.validate(json, FileFormats.JSON));
	}

	@Test
	public void validateIncorrectJson() throws UnknownFileFormatException {
		String json = "{name\":\"name}";
		assertFalse(validator.validate(json, FileFormats.JSON));
	}

	@Test
	public void validateCorrectJsonArrays() throws UnknownFileFormatException {
		String json = "{\"names\":[\"chris\", \"john\"], \"age\":[\"12\", \"32\"]}";
		assertTrue(validator.validate(json, FileFormats.JSON));
	}

	@Test
	public void validateInCorrectJsonArrays() throws UnknownFileFormatException {
		String json = "{\"names\":{\"chris\", \"john\"], \"age\":[\"12\", \"32\"}}";
		assertFalse(validator.validate(json, FileFormats.JSON));
	}

	@Test
	public void validateJsonObjectAndArray() throws UnknownFileFormatException {
		String json = "{\"names\":[\"chris\", \"john\"], \"age\":[\"12\", \"32\"], \"town\":\"Johannesburg\"}";
		assertTrue(validator.validate(json, FileFormats.JSON));
	}

	@Test
	public void validateNullRegardlessOfType() throws UnknownFileFormatException {
		assertFalse(validator.validate(null, FileFormats.JSON));
		assertFalse(validator.validate(null, FileFormats.TXT));
		assertFalse(validator.validate(null, FileFormats.CSV));
		assertFalse(validator.validate(null, FileFormats.XML));
	}
}
