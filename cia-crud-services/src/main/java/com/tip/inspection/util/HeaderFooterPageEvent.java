package com.tip.inspection.util;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	static final Logger logger = LoggerFactory.getLogger(HeaderFooterPageEvent.class);

	private PdfTemplate t;
	private Image total;
	private BaseFont base;
	
	StyleUtil styleUtil = new StyleUtil();

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		t = writer.getDirectContent().createTemplate(30, 17);
		try {
			total = Image.getInstance(t);
			//total.setRole(PdfName.ARTIFACT);
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		try {
			byte[] bytes = IOUtils.toByteArray(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("fonts/trebuchet_ms.ttf"));
			base = BaseFont.createFont("trebuchet_ms.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED,
					false, bytes, null);

			addFooter(writer);
		} catch (DocumentException | IOException e) {
			logger.error("An error occurred while generating inspection report: " + e);
		}
	}

	private void addFooter(PdfWriter writer) throws DocumentException, IOException {
		//Font font = new Font(base, 6f, Font.NORMAL);
		Font font = styleUtil.getPdfTitleFont(8f);
		PdfPTable footer = new PdfPTable(1);
		try {
			// set defaults
			footer.setWidths(new int[] { 1});
			footer.setTotalWidth(527);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(40);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(Color.LIGHT_GRAY);

			// add copyright
			footer.addCell(new Phrase(String.format("Page %d", writer.getPageNumber()), font));
			footer.writeSelectedRows(0, -1, 30, 15, writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	@Override
	public void onCloseDocument(PdfWriter writer, Document document) {
		int totalLength = String.valueOf(writer.getPageNumber()).length();
		int totalWidth = totalLength * 5;
		ColumnText.showTextAligned(t, Element.ALIGN_RIGHT,
				new Phrase(String.valueOf(writer.getPageNumber()-1), new Font(base, 8)), totalWidth,
				6, 0);
	}
}
