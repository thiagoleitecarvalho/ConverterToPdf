package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.GifConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.EOrientation;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GifConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.gif";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileGifConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileGifConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileGifConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileGifConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileGifConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileGifConverted6.pdf"));
	}
	
	@Test
	public void getFormat() {

		GifConverter converterFor = (GifConverter) ConverterManager.createConverterFor(EFormat.GIF);

		assertEquals(EFormat.GIF, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.from(getSource(file))
			.to(createFileDestiny("fileGifConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileGifConverted1.pdf"));
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.from(getSource(file), getSource(file))
			.to(createFileDestiny("fileGifConverted6.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileGifConverted6.pdf"));
	}
	
	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.from(bytes)
			.to(createFileDestiny("fileGifConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileGifConverted2.pdf"));
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.from(getSource("file1.gif"))
			.to(createFileDestiny("fileGifConverted3.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.validade()
			.convert();
	}
	
	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.to(createFileDestiny("fileGifConverted4.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.GIF)
			.from(getSource(file))
			.validade()
			.convert();
	}
	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		GifConverter gifConverter = (GifConverter) ConverterManager.createConverterFor(EFormat.GIF);
		Assert.assertTrue(gifConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		GifConverter gifConverter = (GifConverter) ConverterManager.createConverterFor(EFormat.GIF);
		gifConverter
			.from(getSource(file))
			.to(createFileDestiny("fileGifConverted5.pdf"))
			.setConfigurations(configuration)
			.validade();

		Assert.assertFalse(gifConverter.getConfigurations().isPortrait());
		
		gifConverter.convert();
		
	}

}
