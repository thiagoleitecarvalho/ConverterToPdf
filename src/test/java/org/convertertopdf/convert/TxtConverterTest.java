package org.convertertopdf.convert;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.convertertopdf.configuration.implementation.TxtImageConfiguration;
import org.convertertopdf.convert.implementation.TxtConverter;
import org.convertertopdf.exception.FileFormatException;
import org.convertertopdf.exception.FileNotInformedException;
import org.convertertopdf.exception.FileValidationException;
import org.convertertopdf.exception.PdfConverterException;
import org.convertertopdf.management.ConverterManager;
import org.convertertopdf.util.EFormat;
import org.convertertopdf.util.EOrientation;
import org.convertertopdf.util.EPageSize;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class TxtConverterTest extends BaseConverterTest {

	private String file;

	@Before
	public void init() {
		file = "file.txt";
	}

	@AfterClass
	public static void cleanFiles() {
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted1.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted2.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted3.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted4.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted5.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted6.pdf"));
		FileUtils.deleteQuietly(new File(SRC_TEST_RESOURCES + "fileTxtConverted7.pdf"));
	}
	
	@Test
	public void getFormat() {

		TxtConverter converterFor = (TxtConverter) ConverterManager.createConverterFor(EFormat.TXT);

		assertEquals(EFormat.TXT, converterFor.getFormat());
	}

	@Test
	public void convertByFiles()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {
		
		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.from(getSource(file), getSource(file))
			.to(createFileDestiny("fileTxtConverted7.pdf"))
			.setConfigurations(configuration)
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileTxtConverted7.pdf"));
	}
	
	@Test
	public void convertByFile()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {
		
		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.from(getSource(file))
			.to(createFileDestiny("fileTxtConverted1.pdf"))
			.setConfigurations(configuration)
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileTxtConverted1.pdf"));
	}

	@Test
	public void convertByBytes() throws PdfConverterException, FileValidationException, FileFormatException,
			FileNotInformedException, IOException {

		byte[] bytes = FileUtils.readFileToByteArray(getSource(file));
		
		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.from(bytes)
			.to(createFileDestiny("fileTxtConverted2.pdf"))
			.setConfigurations(configuration)
			.validade()
			.convert();

		Assert.assertFalse(fileCorrupted("fileTxtConverted2.pdf"));
	}

	@Test(expected = FileValidationException.class)
	public void convertFileValidationException()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.from(getSource(file))
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionDestiny()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.from(getSource(file))
			.validade()
			.convert();
	}

	@Test(expected = FileNotInformedException.class)
	public void convertFileNotInformedExceptionSource()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.to(createFileDestiny("fileTxtConverted3.pdf"))
			.validade()
			.convert();
	}
	
	@Test
	public void convertByFilePortrait()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException {

		TxtConverter txtConverter = (TxtConverter) ConverterManager.createConverterFor(EFormat.TXT);
		Assert.assertTrue(txtConverter.getConfigurations().isPortrait());
	}

	@Test
	public void convertByFileLandscape()
			throws PdfConverterException, FileValidationException, FileFormatException, FileNotInformedException, FileNotFoundException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setOrientation(EOrientation.LANDSCAPE);

		TxtConverter txtConverter = (TxtConverter) ConverterManager.createConverterFor(EFormat.TXT);
		txtConverter
			.from(getSource(file))
			.to(createFileDestiny("fileTxtConverted4.pdf"))
			.setConfigurations(configuration)
			.validade();

		Assert.assertFalse(txtConverter.getConfigurations().isPortrait());
		
		txtConverter.convert();
	}
	
	@Ignore
	@Test(expected = PdfConverterException.class)
	public void convertByBytesExceptionConverter() throws PdfConverterException, FileValidationException,
			FileFormatException, FileNotInformedException, IOException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		ConverterManager
			.createConverterFor(EFormat.TXT)
			.from(new byte[] {})
			.to(createFileDestiny("fileTxtConverted5.pdf"))
			.setConfigurations(configuration)
			.validade();
	}

	@Ignore	
	@Test
	public void convertByBytesPdfConverterException()
			throws FileValidationException, FileFormatException, FileNotInformedException, IOException {

		TxtImageConfiguration configuration = new TxtImageConfiguration();
		configuration.setPageSize(EPageSize.A4);

		try {
			ConverterManager
				.createConverterFor(EFormat.TXT)
				.from(new byte[] {})
				.to(createFileDestiny("fileTxtConverted5.pdf"))
				.setConfigurations(configuration)
				.validade()
				.convert();
		} catch (PdfConverterException e) {
			assertEquals(e.getMessage(), PdfConverterException.formatMessage(EFormat.TXT));
		}
	}
}
