package org.convertertopdf.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EFormatTest {

	@Test(expected = IllegalArgumentException.class)
	public void fromMineTypeNull() {

		EFormat.fromMineType(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fromMineTypeWrongMimeType() {

		EFormat.fromMineType("wrongMimeType");
	}

	@Test(expected = IllegalArgumentException.class)
	public void fromStringNull() {

		EFormat.fromString(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void fromStringWrongString() {

		EFormat.fromString("wrongString");
	}

	@Test
	public void fromStringTxt() {

		assertEquals(EFormat.TXT, EFormat.fromString("tXt"));
	}

	@Test
	public void fromStringBmp() {

		assertEquals(EFormat.BMP, EFormat.fromString("bmp"));
	}

	@Test
	public void fromStringDoc() {

		assertEquals(EFormat.DOC, EFormat.fromString("DOC"));
	}

	@Test
	public void fromStringDocx() {

		assertEquals(EFormat.DOCX, EFormat.fromString("DocX"));
	}

	@Test
	public void fromStringGif() {

		assertEquals(EFormat.GIF, EFormat.fromString("giF"));
	}

	@Test
	public void fromStringHtml() {

		assertEquals(EFormat.HTML, EFormat.fromString("HtMl"));
	}

	@Test
	public void fromStringJpeg() {

		assertEquals(EFormat.JPEG, EFormat.fromString("JPeg"));
	}

	@Test
	public void fromStringOdt() {

		assertEquals(EFormat.ODT, EFormat.fromString("Odt"));
	}

	@Test
	public void fromStringPng() {

		assertEquals(EFormat.PNG, EFormat.fromString("pNG"));
	}

	@Test
	public void fromStringRtf() {

		assertEquals(EFormat.RTF, EFormat.fromString("rTf"));
	}

	@Test
	public void fromStringTiff() {

		assertEquals(EFormat.TIFF, EFormat.fromString("tIFf"));
	}

	@Test
	public void imagesFormats() {

		
		List<EFormat> imageFormats = new ArrayList<EFormat>();
		imageFormats.add(EFormat.GIF);
		imageFormats.add(EFormat.BMP);
		imageFormats.add(EFormat.PNG);
		imageFormats.add(EFormat.JPEG);
		imageFormats.add(EFormat.TIFF);
		
		assertArrayEquals(EFormat.getImagesType().toArray(), imageFormats.toArray());
	}
	
	@Test
	public void officesFormats() {

		
		List<EFormat> officeFormats = new ArrayList<EFormat>();
		officeFormats.add(EFormat.DOC);
		officeFormats.add(EFormat.DOCX);
		officeFormats.add(EFormat.XLS);
		officeFormats.add(EFormat.XLSX);
		officeFormats.add(EFormat.PPT);
		officeFormats.add(EFormat.PPTX);
		officeFormats.add(EFormat.ODS);
		officeFormats.add(EFormat.ODP);
		officeFormats.add(EFormat.SXW);
		officeFormats.add(EFormat.SXC);
		officeFormats.add(EFormat.SXI);
		officeFormats.add(EFormat.RTF);
		
		assertArrayEquals(EFormat.getOfficesType().toArray(), officeFormats.toArray());
	}	
}
