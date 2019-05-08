package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.PngConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.EOrientation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PngConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.png";
	}

	@Test
	public void getFormat() {

		PngConverter converterFor = (PngConverter) converterManager.createFor(EFormat.PNG);

		assertEquals(EFormat.PNG, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		byte[] result = converterManager.createFor(EFormat.PNG).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.PNG).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file1.png");

		converterManager.createFor(EFormat.PNG).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.PNG).validade().convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		PngConverter pngConverter = (PngConverter) converterManager.createFor(EFormat.PNG);
		Assert.assertTrue(pngConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		PngConverter pngConverter = (PngConverter) converterManager.createFor(EFormat.PNG);
		pngConverter.setFile(source).setConfigurations(configuration).validade().convert();

		Assert.assertFalse(pngConverter.getConfigurations().isPortrait());
	}

}
