package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BmpConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file1.bmp";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted6.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileBMPConverted7.pdf"));
	}
	
	@Test
	public void getFormat() {

		BmpConverter converterFor = (BmpConverter) ConverterManager.createConverterFor(EFormat.BMP);

		assertEquals(EFormat.BMP, converterFor.getFormat());
	}

	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(getSource("file2.bmp"))
			.to(createFileDestiny("fileBMPConverted1.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileBMPConverted1.pdf"));
	}
	
	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(getSource("file2.bmp"), getSource(file))
			.to(createFileDestiny("fileBMPConverted6.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileBMPConverted6.pdf"));
	}	

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(bytes)
			.to(createFileDestiny("fileBMPConverted2.pdf"))
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileBMPConverted2.pdf"));
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatExceptionByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(getSource("file.txt"))
			.to(createFileDestiny("fileBMPConverted3.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileFormatException.class)
	public void convertFileFormatExceptionByBytes() throws PdfConverterException, FileValidationException,
			FileFormatException, FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource("file.txt"));

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(bytes)
			.to(createFileDestiny("fileBMPConverted4.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(getSource(file))
			.to(createFileDestiny("fileBMPConverted5.pdf"))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.to(createFileDestiny("fileBMPConverted6.pdf"))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.BMP)
			.from(getSource("file2.bmp"))
			.validade()
			.convert();
	}
	
	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		BmpConverter bmpConverter = (BmpConverter) ConverterManager.createConverterFor(EFormat.BMP);
		Assert.assertTrue(bmpConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		BmpConverter bmpConverter = (BmpConverter) ConverterManager.createConverterFor(EFormat.BMP);
		bmpConverter
			.from(getSource(file))
			.to(createFileDestiny("fileBMPConverted7.pdf"))
			.setConfigurations(configuration)
			.validade();

		Assert.assertFalse(bmpConverter.getConfigurations().isPortrait());
		
		bmpConverter.convert();
	}
}
