package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class JpegConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.jpg";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted6.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileJpegConverted7.pdf"));
	}
	
	@Test
	public void getFormat() {

		JpegConverter converterFor = (JpegConverter) ConverterManager.createConverterFor(EFormat.JPEG);

		assertEquals(EFormat.JPEG, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.from(getSource(file))
			.to(createFileDestiny("fileJpegConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileJpegConverted1.pdf"));
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.from(getSource(file), getSource(file))
			.to(createFileDestiny("fileJpegConverted7.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileJpegConverted7.pdf"));
	}
	
	@Test
	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.from(bytes)
			.to(createFileDestiny("fileJpegConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileJpegConverted2.pdf"));
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.from(getSource("file1.jpg"))
			.to(createFileDestiny("fileJpegConverted3.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {
		
		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.to(createFileDestiny("fileJpegConverted4.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.from(getSource(file))
			.validade()
			.convert();
	}
	
	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		JpegConverter jpegConverter = (JpegConverter) ConverterManager.createConverterFor(EFormat.JPEG);
		Assert.assertTrue(jpegConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		JpegConverter jpegConverter = (JpegConverter) ConverterManager.createConverterFor(EFormat.JPEG);
		jpegConverter
			.from(getSource(file))
			.to(createFileDestiny("fileJpegConverted5.pdf"))
			.setConfigurations(configuration)
			.validade();

		Assert.assertFalse(jpegConverter.getConfigurations().isPortrait());
		
		jpegConverter.convert();
	}

	@Ignore
	@Test(expected = PdfConverterException.class)
	public void convertByBytesIOException() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		ConverterManager
			.createConverterFor(EFormat.JPEG)
			.from(getSource("fileCorrupted.jpg"))
			.to(createFileDestiny("fileJpegConverted6.pdf"))
			.validade()
			.convert();
	}
}
