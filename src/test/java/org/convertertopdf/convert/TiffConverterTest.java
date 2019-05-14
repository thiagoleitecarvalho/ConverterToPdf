package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.TiffConverter;
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

public class TiffConverterTest extends BaseConverterTest {

	private ConverterManager converterManager;

	private String file;

	@Before
	public void init() {
		converterManager = new ConverterManager();
		file = "file.tif";
	}

	@Test
	public void getFormat() {

		TiffConverter converterFor = (TiffConverter) converterManager.createFor(EFormat.TIFF);

		assertEquals(EFormat.TIFF, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		byte[] result = converterManager.createFor(EFormat.TIFF).setFile(source).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test
	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile(file);

		byte[] bytes = FileUtils.readFileToByteArray(source);

		byte[] result = converterManager.createFor(EFormat.TIFF).setFile(bytes).validade().convert();

		Assert.assertNotNull(result);
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile("file1.tif");

		converterManager.createFor(EFormat.TIFF).setFile(source).convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		converterManager.createFor(EFormat.TIFF).validade().convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		TiffConverter tiffConverter = (TiffConverter) converterManager.createFor(EFormat.TIFF);
		Assert.assertTrue(tiffConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		File source = getResourceAsFile(file);

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		TiffConverter tiffConverter = (TiffConverter) converterManager.createFor(EFormat.TIFF);
		tiffConverter.setFile(source).setConfigurations(configuration).validade().convert();

		Assert.assertFalse(tiffConverter.getConfigurations().isPortrait());
	}

	@Test(expected = PdfConverterException.class)
	public void convertByBytesIOException() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		File source = getResourceAsFile("fileCorrupted.tif");

		converterManager.createFor(EFormat.TIFF).setFile(source).validade().convert();
	}
}
