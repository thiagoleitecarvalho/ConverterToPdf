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
import org.convertertopdf.convert.implementation.PngConverter;
import org.convertertopdf.convert.implementation.RtfConverter;
import org.convertertopdf.convert.implementation.TiffConverter;
import org.convertertopdf.convert.implementation.TxtConverter;
import org.convertertopdf.util.EFormat;
import org.junit.Before;
import org.junit.Test;

public class ConverterManagerTest {

	private ConverterManager converterManager;

	@Before
	public void init() {
		converterManager = new ConverterManager();
	}

	@Test
	public void getAvaliableFormats() {

		assertEquals(Arrays.asList(EFormat.values()), converterManager.getAvaliableFormats());
	}

	@Test(expected = IllegalArgumentException.class)
	public void getConverterIllegalArgumentException() {

		converterManager.createFor(EFormat.PPT);
	}

	@Test
	public void getConverterForBMP() {

		converterManager.createFor(EFormat.BMP);

		assertTrue(converterManager.getConverter() instanceof BmpConverter);
	}

	@Test
	public void getConverterForDOC() {

		converterManager.createFor(EFormat.DOC);

		assertTrue(converterManager.getConverter() instanceof DocConverter);
	}

	@Test
	public void getConverterForDOCX() {

		converterManager.createFor(EFormat.DOCX);

		assertTrue(converterManager.getConverter() instanceof DocxConverter);
	}

	@Test
	public void getConverterForGIF() {

		converterManager.createFor(EFormat.GIF);

		assertTrue(converterManager.getConverter() instanceof GifConverter);
	}

	@Test
	public void getConverterForHTML() {

		converterManager.createFor(EFormat.HTML);

		assertTrue(converterManager.getConverter() instanceof HtmlConverter);
	}

	@Test
	public void getConverterForJPEG() {

		converterManager.createFor(EFormat.JPEG);

		assertTrue(converterManager.getConverter() instanceof JpegConverter);
	}

	@Test
	public void getConverterForODT() {

		converterManager.createFor(EFormat.ODT);

		assertTrue(converterManager.getConverter() instanceof OdtConverter);
	}

	@Test
	public void getConverterForPng() {

		converterManager.createFor(EFormat.PNG);

		assertTrue(converterManager.getConverter() instanceof PngConverter);
	}

	@Test
	public void getConverterForRTF() {

		converterManager.createFor(EFormat.RTF);

		assertTrue(converterManager.getConverter() instanceof RtfConverter);
	}

	@Test
	public void getConverterForTIFF() {

		converterManager.createFor(EFormat.TIFF);

		assertTrue(converterManager.getConverter() instanceof TiffConverter);
	}

	@Test
	public void getConverterForTXT() {

		converterManager.createFor(EFormat.TXT);

		assertTrue(converterManager.getConverter() instanceof TxtConverter);
	}

	@Test
	public void getConverterForBmpString() {

		converterManager.createFor("BMP");

		assertTrue(converterManager.getConverter() instanceof BmpConverter);
	}

	@Test
	public void getConverterForDocString() {

		converterManager.createFor("DOC");

		assertTrue(converterManager.getConverter() instanceof DocConverter);
	}

	@Test
	public void getConverterForDocxString() {

		AbstractConverter converterFor = converterManager.createFor("DOCX");

		assertTrue(converterFor instanceof DocxConverter);
	}

	@Test
	public void getConverterForGifString() {

		converterManager.createFor("GIF");

		assertTrue(converterManager.getConverter() instanceof GifConverter);
	}

	@Test
	public void getConverterForHtmlString() {

		converterManager.createFor("HTML");

		assertTrue(converterManager.getConverter() instanceof HtmlConverter);
	}

	@Test
	public void getConverterForJpegString() {

		converterManager.createFor("JPEG");

		assertTrue(converterManager.getConverter() instanceof JpegConverter);
	}

	@Test
	public void getConverterForOdtString() {

		converterManager.createFor("ODT");

		assertTrue(converterManager.getConverter() instanceof OdtConverter);
	}

	@Test
	public void getConverterForPngString() {

		converterManager.createFor("PNG");

		assertTrue(converterManager.getConverter() instanceof PngConverter);
	}

	@Test
	public void getConverterForRtfString() {

		converterManager.createFor("RTF");

		assertTrue(converterManager.getConverter() instanceof RtfConverter);
	}

	@Test
	public void getConverterForTiffString() {

		converterManager.createFor("TIFF");

		assertTrue(converterManager.getConverter() instanceof TiffConverter);
	}

	@Test
	public void getConverterForTxtString() {

		converterManager.createFor("TXT");

		assertTrue(converterManager.getConverter() instanceof TxtConverter);
	}

}
