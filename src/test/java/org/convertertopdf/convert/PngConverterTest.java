package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PngConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.png";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted6.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "filePngConverted7.pdf"));
	}

	@Test
	public void getFormat() {

		PngConverter converterFor = (PngConverter) ConverterManager.createConverterFor(EFormat.PNG);

		assertEquals(EFormat.PNG, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.PNG)
			.from(getSource(file))
			.to(createFileDestiny("filePngConverted1.pdf"))
			.validade().convert();

		Assert.assertFalse(fileCorrupted("filePngConverted1.pdf"));
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.PNG)
			.from(getSource(file), getSource(file))
			.to(createFileDestiny("filePngConverted7.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("filePngConverted7.pdf"));
	}
	
	@Test
	public void convertByByte() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.PNG)
			.from(bytes)
			.to(createFileDestiny("filePngConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("filePngConverted2.pdf"));
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.PNG)
			.from(getSource("file1.png"))
			.to(createFileDestiny("filePngConverted3.pdf"))
			.convert();
	}
	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.PNG)
			.to(createFileDestiny("filePngConverted4.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.PNG)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		PngConverter pngConverter = (PngConverter) ConverterManager.createConverterFor(EFormat.PNG);
		Assert.assertTrue(pngConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		PngConverter pngConverter = (PngConverter) ConverterManager.createConverterFor(EFormat.PNG);
		pngConverter
			.from(getSource(file))
			.to(createFileDestiny("filePngConverted5.pdf"))
			.setConfigurations(configuration)
			.validade();

		Assert.assertFalse(pngConverter.getConfigurations().isPortrait());
		
		pngConverter.convert();
	}

}
