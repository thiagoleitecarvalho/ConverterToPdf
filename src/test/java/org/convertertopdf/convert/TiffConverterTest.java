package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TiffConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.tif";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted6.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTiffConverted7.pdf"));
	}

	@Test
	public void getFormat() {

		TiffConverter converterFor = (TiffConverter) ConverterManager.createConverterFor(EFormat.TIFF);

		assertEquals(EFormat.TIFF, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.from(getSource(file))
			.to(createFileDestiny("fileTiffConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileTiffConverted1.pdf"));
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.from(getSource(file), getSource(file))
			.to(createFileDestiny("fileTiffConverted7.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileTiffConverted7.pdf"));
	}
	
	@Test
	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.from(bytes)
			.to(createFileDestiny("fileTiffConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileTiffConverted2.pdf"));
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.from(getSource("file1.tif"))
			.to(createFileDestiny("fileTiffConverted3.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.to(createFileDestiny("fileTiffConverted4.pdf"))
			.validade()
			.convert();
	}
	
	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		TiffConverter tiffConverter = (TiffConverter) ConverterManager.createConverterFor(EFormat.TIFF);
		Assert.assertTrue(tiffConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		TiffConverter tiffConverter = (TiffConverter) ConverterManager.createConverterFor(EFormat.TIFF);
		tiffConverter
			.from(getSource(file))
			.to(createFileDestiny("fileTiffConverted5.pdf"))
			.setConfigurations(configuration)
			.validade();

		Assert.assertFalse(tiffConverter.getConfigurations().isPortrait());
		
		tiffConverter.convert();
	}

	@Test(expected = FileValidationException.class)
	public void convertByBytesIllegalArgumentException() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		ConverterManager
			.createConverterFor(EFormat.TIFF)
			.from(getSource("fileCorrupted2tif"))
			.to(createFileDestiny("fileTiffConverted6.pdf"))
			.validade();
	}
}
