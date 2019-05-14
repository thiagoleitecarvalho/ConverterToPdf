package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.JpegConverter;
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

public class JpegConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.jpg";
	}

	@Test
	public void getFormat() {

		JpegConverter converterFor = (JpegConverter) converterManager.createFor(EFormat.JPEG);

		assertEquals(EFormat.JPEG, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		byte[] result = converterManager.createFor(EFormat.JPEG).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.JPEG).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file1.jpg");

		converterManager.createFor(EFormat.JPEG).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.JPEG).validade().convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		JpegConverter jpegConverter = (JpegConverter) converterManager.createFor(EFormat.JPEG);
		Assert.assertTrue(jpegConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		JpegConverter jpegConverter = (JpegConverter) converterManager.createFor(EFormat.JPEG);
		jpegConverter.setFile(source).setConfigurations(configuration).validade().convert();

		Assert.assertFalse(jpegConverter.getConfigurations().isPortrait());
	}

	@Test(expected = PdfConverterException.class)
	public void convertByBytesIOException() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile("fileCorrupted.jpg");

		converterManager.createFor(EFormat.JPEG).setFile(source).validade().convert();
	}
}
