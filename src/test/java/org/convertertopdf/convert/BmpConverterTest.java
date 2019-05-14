package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.BmpConverter;
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

public class BmpConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file1.bmp";
	}

	@Test
	public void getFormat() {

		BmpConverter converterFor = (BmpConverter) converterManager.createFor(EFormat.BMP);

		assertEquals(EFormat.BMP, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file2.bmp");

		byte[] result = converterManager.createFor(EFormat.BMP).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.BMP).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatExceptionByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file.txt");

		converterManager.createFor(EFormat.BMP).setFile(source).validade().convert();
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatExceptionByBytes() throws PdfConverterException, FileValidationException,
			FileFormatException, FileNotInformedException, IOException {

		File source = getResourceAsFile("file.txt");

		byte[] bytes = FileUtils.readFileToByteArray(source);

		converterManager.createFor(EFormat.BMP).setFile(bytes).validade().convert();
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		converterManager.createFor(EFormat.BMP).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.BMP).validade().convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		BmpConverter bmpConverter = (BmpConverter) converterManager.createFor(EFormat.BMP);
		Assert.assertTrue(bmpConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		BmpConverter bmpConverter = (BmpConverter) converterManager.createFor(EFormat.BMP);
		bmpConverter.setFile(source).setConfigurations(configuration).validade().convert();

		Assert.assertFalse(bmpConverter.getConfigurations().isPortrait());
	}
}
