package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.convert.implementation.HtmlConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HtmlConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.html";
	}

	@Test
	public void getFormat() {

		HtmlConverter converterFor = (HtmlConverter) converterManager.createFor(EFormat.HTML);

		assertEquals(EFormat.HTML, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		byte[] result = converterManager.createFor(EFormat.HTML).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.HTML).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		converterManager.createFor(EFormat.HTML).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.HTML).validade().convert();
	}
}
