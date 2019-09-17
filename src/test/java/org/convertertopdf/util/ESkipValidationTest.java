package org.convertertopdf.util;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class ESkipValidationTest {

	@Test
	public void fromStringDoc() {

		assertTrue(ESkipValidation.TRUE.getValue());
	}

	@Test
	public void fromStringDocx() {

		assertFalse(!ESkipValidation.TRUE.getValue());
	}
}
