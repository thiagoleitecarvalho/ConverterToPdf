package org.convertertopdf.util;

import com.lowagie.text.Rectangle;

/**
 * Enum responsible for encapsulate the pages sizes.
 * 
 * @author Thiago Leite e-mail: thiagoleiteecarvalho@gmail.com
 */
public enum EPageSize {

	/** This is the letter format. */
	LETTER(com.lowagie.text.PageSize.LETTER),

	/** This is the note format. */
	NOTE(com.lowagie.text.PageSize.NOTE),

	/** This is the legal format. */
	LEGAL(com.lowagie.text.PageSize.LEGAL),

	/** This is the tabloid format. */
	TABLOID(com.lowagie.text.PageSize.TABLOID),

	/** This is the executive format. */
	EXECUTIVE(com.lowagie.text.PageSize.EXECUTIVE),

	/** This is the postcard format. */
	POSTCARD(com.lowagie.text.PageSize.POSTCARD),

	/** This is the a0 format. */
	A0(com.lowagie.text.PageSize.A0),

	/** This is the a1 format. */
	A1(com.lowagie.text.PageSize.A1),

	/** This is the a2 format. */
	A2(com.lowagie.text.PageSize.A2),

	/** This is the a3 format. */
	A3(com.lowagie.text.PageSize.A3),

	/** This is the a4 format. Default value */
	A4(com.lowagie.text.PageSize.A4),

	/** This is the a5 format. */
	A5(com.lowagie.text.PageSize.A5),

	/** This is the a6 format. */
	A6(com.lowagie.text.PageSize.A6),

	/** This is the a7 format. */
	A7(com.lowagie.text.PageSize.A7),

	/** This is the a8 format. */
	A8(com.lowagie.text.PageSize.A8),

	/** This is the a9 format. */
	A9(com.lowagie.text.PageSize.A9),

	/** This is the a10 format. */
	A10(com.lowagie.text.PageSize.A10),

	/** This is the b0 format. */
	B0(com.lowagie.text.PageSize.B0),

	/** This is the b1 format. */
	B1(com.lowagie.text.PageSize.B1),

	/** This is the b2 format. */
	B2(com.lowagie.text.PageSize.B2),

	/** This is the b3 format. */
	B3(com.lowagie.text.PageSize.B3),

	/** This is the b4 format. */
	B4(com.lowagie.text.PageSize.B4),

	/** This is the b5 format. */
	B5(com.lowagie.text.PageSize.B5),

	/** This is the b6 format. */
	B6(com.lowagie.text.PageSize.B6),

	/** This is the b7 format. */
	B7(com.lowagie.text.PageSize.B7),

	/** This is the b8 format. */
	B8(com.lowagie.text.PageSize.B8),

	/** This is the b9 format. */
	B9(com.lowagie.text.PageSize.B9),

	/** This is the b10 format. */
	B10(com.lowagie.text.PageSize.B10),

	/** This is the archE format. */
	ARCH_E(com.lowagie.text.PageSize.ARCH_E),

	/** This is the archD format. */
	ARCH_D(com.lowagie.text.PageSize.ARCH_D),

	/** This is the archC format. */
	ARCH_C(com.lowagie.text.PageSize.ARCH_C),

	/** This is the archB format. */
	ARCH_B(com.lowagie.text.PageSize.ARCH_B),

	/** This is the archA format. */
	ARCH_A(com.lowagie.text.PageSize.ARCH_A),

	/** This is the American Foolscap format. */
	FLSA(com.lowagie.text.PageSize.FLSA),

	/** This is the European Foolscap format. */
	FLSE(com.lowagie.text.PageSize.FLSE),

	/** This is the halfletter format. */
	HALFLETTER(com.lowagie.text.PageSize.HALFLETTER),

	/** This is the 11x17 format. */
	_11X17(com.lowagie.text.PageSize._11X17),

	/**
	 * This is the ISO 7810 ID-1 format (85.60 x 53.98 mm or 3.370 x 2.125 inch).
	 */
	ID_1(com.lowagie.text.PageSize.ID_1),

	/** This is the ISO 7810 ID-2 format (A7 rotated). */
	ID_2(com.lowagie.text.PageSize.ID_2),

	/** This is the ISO 7810 ID-3 format (B7 rotated). */
	ID_3(com.lowagie.text.PageSize.ID_3),

	/** This is the ledger format. */
	LEDGER(com.lowagie.text.PageSize.LEDGER),

	/** This is the Crown Quarto format. */
	CROWN_QUARTO(com.lowagie.text.PageSize.CROWN_QUARTO),

	/** This is the Large Crown Quarto format. */
	LARGE_CROWN_QUARTO(com.lowagie.text.PageSize.LARGE_CROWN_QUARTO),

	/** This is the Demy Quarto format. */
	DEMY_QUARTO(com.lowagie.text.PageSize.DEMY_QUARTO),

	/** This is the Royal Quarto format. */
	ROYAL_QUARTO(com.lowagie.text.PageSize.ROYAL_QUARTO),

	/** This is the Crown Octavo format. */
	CROWN_OCTAVO(com.lowagie.text.PageSize.CROWN_OCTAVO),

	/** This is the Large Crown Octavo format. */
	LARGE_CROWN_OCTAVO(com.lowagie.text.PageSize.LARGE_CROWN_OCTAVO),

	/** This is the Demy Octavo format. */
	DEMY_OCTAVO(com.lowagie.text.PageSize.DEMY_OCTAVO),

	/** This is the Royal Octavo format. */
	ROYAL_OCTAVO(com.lowagie.text.PageSize.ROYAL_OCTAVO),

	/** This is the small paperback format. */
	SMALL_PAPERBACK(com.lowagie.text.PageSize.SMALL_PAPERBACK),

	/** This is the Pengiun small paperback format. */
	PENGUIN_SMALL_PAPERBACK(com.lowagie.text.PageSize.PENGUIN_SMALL_PAPERBACK),

	/** This is the Penguin large paperback format. */
	PENGUIN_LARGE_PAPERBACK(com.lowagie.text.PageSize.PENGUIN_LARGE_PAPERBACK);

	/**
	 * Constructor.
	 * 
	 * @param size The size of page
	 */
	private EPageSize(Rectangle size) {
		this.size = size;
	}

	/**
	 * Size of page.
	 */
	private Rectangle size;

	/**
	 * Represents the Rectangle of pege size.
	 * 
	 * @return Rectangle of pege size
	 */
	public Rectangle getSize() {
		return size;
	}
}
