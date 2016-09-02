package com.devgoo.commons.validator;

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
	public void validateCorrectJson() {
		String json = "{\"name\" : \"name\"}";
		assertTrue(validator.validate(json, FileFormats.JSON));
	}

	@Test
	public void validateIncorrectJson() {
		String json = "{name\":\"name}";
		assertFalse(validator.validate(json, FileFormats.JSON));
	}
}
