package com.tip.report.util;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.pdfbox.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	static final Logger logger = LoggerFactory.getLogger(HeaderFooterPageEvent.class);

	private PdfTemplate t;
	private Image total;
	private BaseFont base;
	private String tipLogoPath;
	private String custLogoPath;
	private String reportHeading;

	HeaderFooterPageEvent(String tipLogoPath, String custLogoPath, String reportHeading) {
		if (tipLogoPath != null)
			this.tipLogoPath = tipLogoPath.trim();
		if (custLogoPath != null)
			this.custLogoPath = custLogoPath.trim();
		this.reportHeading = reportHeading;
	}

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
					.getResourceAsStream("trebuchet-ms-bold-596f552632220.ttf"));
			base = BaseFont.createFont("trebuchet-ms-bold-596f552632220.ttf", BaseFont.WINANSI, BaseFont.EMBEDDED,
					false, bytes, null);

			addHeader(writer);
			addFooter(writer);
		} catch (DocumentException | IOException e) {
			logger.error("An error occurred while generating inspection report: " + e);
		}
	}

	private void addHeader(PdfWriter writer) throws DocumentException, IOException {
		Font font = new Font(base, 13f, Font.BOLD);
		PdfPTable header = new PdfPTable(3);
		try {
			// set defaults
			header.setWidths(new int[] { 24, 34, 14 });
			header.setTotalWidth(PageSize.A4.getWidth());
			header.setLockedWidth(true);
			header.getDefaultCell().setFixedHeight(10);
			header.getDefaultCell().setBorder(Rectangle.BOTTOM);
			header.getDefaultCell().setBorderColor(Color.LIGHT_GRAY);

			if (tipLogoPath != null && !tipLogoPath.isEmpty()) {
				addTipLogo(header);
			} else {
				header.addCell(new Phrase(""));
			}

			// add text
			PdfPCell text = new PdfPCell();
			text.setPaddingBottom(15);
			text.setPaddingLeft(10);
			text.setBorder(Rectangle.BOTTOM);
			text.setBorderColor(Color.LIGHT_GRAY);

			if (reportHeading != null && !reportHeading.isEmpty())
				text.addElement(new Phrase(reportHeading, font));
			else
				text.addElement(new Phrase("", font));
			header.addCell(text);

			if (custLogoPath != null && !custLogoPath.isEmpty()) {
				addCustomerLogo(header);
			} else {
				header.addCell(new Phrase(""));
			}

			header.writeSelectedRows(0, -1, 24, 825, writer.getDirectContent());
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}

	private void addFooter(PdfWriter writer) throws DocumentException, IOException {
		Font font = new Font(base, 6f, Font.NORMAL);
		PdfPTable footer = new PdfPTable(3);
		try {
			// set defaults
			footer.setWidths(new int[] { 2, 1, 24 });
			footer.setTotalWidth(527);
			footer.setLockedWidth(true);
			footer.getDefaultCell().setFixedHeight(40);
			footer.getDefaultCell().setBorder(Rectangle.TOP);
			footer.getDefaultCell().setBorderColor(Color.LIGHT_GRAY);

			// add copyright
			footer.addCell(new Phrase(String.format("Page %d of", writer.getPageNumber()), font));

			// add placeholder for total page count
			total.scaleAbsolute(30f, 13f);
			PdfPCell totalPageCount = new PdfPCell(total);
			totalPageCount.setBorder(Rectangle.TOP);
			totalPageCount.setBorderColor(Color.LIGHT_GRAY);
			footer.addCell(totalPageCount);

			// add current page count
			footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
			footer.addCell(new Phrase("Brought to you by TIP Trailer Services - https://www.tipeurope.com", font));

			//footer.writeSelectedRows(0, -1, 24, 825, writer.getDirectContent());
			footer.writeSelectedRows(0, -1, 30, 15, writer.getDirectContent());
			// write page
			/*PdfContentByte canvas = writer.getDirectContent();
			canvas.beginMarkedContentSequence(PdfName.ACTUALTEXT);
			canvas.beginMarkedContentSequence(PdfName.ARTIFACT);
			footer.writeSelectedRows(0, -1, 34, 15, canvas);
			canvas.endMarkedContentSequence();*/
		} catch (DocumentException de) {
			throw new ExceptionConverter(de);
		}
	}
	
	private void addTipLogo(PdfPTable header){
		Image logo;
		if (tipLogoPath.contains("http")) {
			try {
				URL url = new URL(tipLogoPath);
				logo = Image.getInstance(url);
				header.addCell(logo);
			} catch (Exception e) {
				header.addCell(new Phrase(""));
				logger.info("Logo path is not url", e);
			}
		} else {
			File f = new File(tipLogoPath);
			if (f.exists()) {
				try {
					logo = Image.getInstance(tipLogoPath);
					header.addCell(logo);
				} catch (BadElementException | IOException e) {
					logger.info("Error while fetching Logo ", e);
				}				
			} else {
				header.addCell(new Phrase(""));
			}
		}
	}
	
	private void addCustomerLogo(PdfPTable header){
		Image logo1;
		if (custLogoPath.contains("http")) {
			try {
				URL url = new URL(custLogoPath);
				logo1 = Image.getInstance(url);
				header.addCell(logo1);
			} catch (Exception e) {
				header.addCell(new Phrase(""));
				logger.info("Customer Logo path is not url", e);
			}
		} else {
			File f = new File(custLogoPath);
			if (f.exists()) {
				try {
					logo1 = Image.getInstance(custLogoPath);
					header.addCell(logo1);
				} catch (BadElementException | IOException e) {
					logger.info("Error while fetching Customer Logo ", e);
				}				
			} else {
				header.addCell(new Phrase(""));
			}
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
