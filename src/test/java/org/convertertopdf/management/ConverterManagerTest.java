package org.convertertopdf.management;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.convertertopdf.convert.AbstractConverter;
import org.convertertopdf.convert.implementation.BmpConverter;
import org.convertertopdf.convert.implementation.DocConverter;
import org.convertertopdf.convert.implementation.DocxConverter;
import org.convertertopdf.convert.implementation.GifConverter;
import org.convertertopdf.convert.implementation.HtmlConverter;
import org.convertertopdf.convert.implementation.JpegConverter;
import org.convertertopdf.convert.implementation.OdtConverter;
import org.convertertopdf.convert.implementation.PdfConverter;
import org.convertertopdf.convert.implementation.PngConverter;
import org.convertertopdf.convert.implementation.RtfConverter;
import org.convertertopdf.convert.implementation.TiffConverter;
import org.convertertopdf.convert.implementation.TxtConverter;
import org.convertertopdf.util.EFormat;
import org.junit.Test;

public class ConverterManagerTest {

	@Test
	public void getAvaliableFormats() {

		assertEquals(Arrays.asList(EFormat.values()), ConverterManager.getAvaliableFormats());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getConverterIllegalArgumentException() {

		ConverterManager.createConverterFor(EFormat.PPT);
	}

	@Test
	public void getConverterForBMP() {

		assertTrue(ConverterManager.createConverterFor(EFormat.BMP) instanceof BmpConverter);
	}

	@Test
	public void getConverterForDOC() {

		assertTrue(ConverterManager.createConverterFor(EFormat.DOC) instanceof DocConverter);
	}

	@Test
	public void getConverterForDOCX() {

		assertTrue(ConverterManager.createConverterFor(EFormat.DOCX) instanceof DocxConverter);
	}

	@Test
	public void getConverterForGIF() {

		assertTrue(ConverterManager.createConverterFor(EFormat.GIF) instanceof GifConverter);
	}

	@Test
	public void getConverterForHTML() {

		assertTrue(ConverterManager.createConverterFor(EFormat.HTML) instanceof HtmlConverter);
	}

	@Test
	public void getConverterForJPEG() {

		assertTrue(ConverterManager.createConverterFor(EFormat.JPEG) instanceof JpegConverter);
	}

	@Test
	public void getConverterForODT() {

		assertTrue(ConverterManager.createConverterFor(EFormat.ODT) instanceof OdtConverter);
	}

	@Test
	public void getConverterForPng() {

		assertTrue(ConverterManager.createConverterFor(EFormat.PNG) instanceof PngConverter);
	}

	@Test
	public void getConverterForRTF() {

		assertTrue(ConverterManager.createConverterFor(EFormat.RTF) instanceof RtfConverter);
	}

	@Test
	public void getConverterForTIFF() {

		assertTrue(ConverterManager.createConverterFor(EFormat.TIFF) instanceof TiffConverter);
	}

	@Test
	public void getConverterForTXT() {

		assertTrue(ConverterManager.createConverterFor(EFormat.TXT) instanceof TxtConverter);
	}

	@Test
	public void getConverterForPdf() {

		assertTrue(ConverterManager.createConverterFor(EFormat.PDF) instanceof PdfConverter);
	}
	
	@Test
	public void getConverterForBmpString() {

		assertTrue(ConverterManager.createConverterFor("BMP") instanceof BmpConverter);
	}

	@Test
	public void getConverterForDocString() {

		assertTrue(ConverterManager.createConverterFor("DOC") instanceof DocConverter);
	}

	@Test
	public void getConverterForDocxString() {

		AbstractConverter converterFor = ConverterManager.createConverterFor("DOCX");

		assertTrue(converterFor instanceof DocxConverter);
	}

	@Test
	public void getConverterForGifString() {

		assertTrue(ConverterManager.createConverterFor("GIF") instanceof GifConverter);
	}

	@Test
	public void getConverterForHtmlString() {

		assertTrue(ConverterManager.createConverterFor("HTML") instanceof HtmlConverter);
	}

	@Test
	public void getConverterForJpegString() {

		assertTrue(ConverterManager.createConverterFor("JPEG") instanceof JpegConverter);
	}

	@Test
	public void getConverterForOdtString() {

		assertTrue(ConverterManager.createConverterFor("ODT") instanceof OdtConverter);
	}

	@Test
	public void getConverterForPngString() {

		assertTrue(ConverterManager.createConverterFor("PNG") instanceof PngConverter);
	}

	@Test
	public void getConverterForRtfString() {

		assertTrue(ConverterManager.createConverterFor("RTF") instanceof RtfConverter);
	}

	@Test
	public void getConverterForTiffString() {

		assertTrue(ConverterManager.createConverterFor("TIFF") instanceof TiffConverter);
	}

	@Test
	public void getConverterForTxtString() {

		assertTrue(ConverterManager.createConverterFor("TXT") instanceof TxtConverter);
	}

	@Test
	public void getConverterForPdfString() {

		assertTrue(ConverterManager.createConverterFor("PDF") instanceof PdfConverter);
	}
	
}
