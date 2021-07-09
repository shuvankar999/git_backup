package com.tip.estimationreport.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tip.estimationreport.model.BaseModel;
import com.tip.estimationreport.model.ResponseObject;

@Component
public class EstimationReportPdf {
	
	@Autowired
	HeaderTableUtil headerTableUtil;
	
	@Value("${spring.profiles.active}")
    private String env;
	
	static final Logger logger = LoggerFactory.getLogger(EstimationReportPdf.class);

	StyleUtil styleUtil = new StyleUtil();

	private String errorString = "An error occurred while generating inspection report";

	public PdfPTable getBodyTable(BaseModel bm, Map<String, String> labelMap, boolean isSummary) {
		PdfPTable mainBTable = new PdfPTable(1);
		BodyTableUtil bodyUtil;

		if (isSummary) {
			if (bm.getCountryCd() != null && bm.getCountryCd().equalsIgnoreCase("FR"))
				bodyUtil = new BodyTableSummaryUtil(bm.getCurrency(), true);
			else
				bodyUtil = new BodyTableSummaryUtil(bm.getCurrency(), false);
		}else {
			if (bm.getCountryCd() != null && bm.getCountryCd().equalsIgnoreCase("FR"))
				bodyUtil = new BodyTableUtil(bm.getCurrency(), true);
			else
				bodyUtil = new BodyTableUtil(bm.getCurrency(), false);
		}
		if (null != bm.getComments())
			setBodyMainCell(mainBTable, bodyUtil.getCommentsNotesTab(bm.getComments(), labelMap.get("hComments")));
		setBodyMainCell(mainBTable, bodyUtil.getLabourTable(bm.getListOfLabour(), labelMap));
		if (bm.getCustomJob() != null)
			setBodyMainCell(mainBTable, bodyUtil.getCustomJobTab(bm.getCustomJob(), labelMap));
		if (bm.getAdditionalParts().getListOfUnMatchedParts().size() > 0)
			setBodyMainCell(mainBTable, bodyUtil.getUnmatchedPartsTab(bm.getAdditionalParts(), labelMap));
		if (null != bm.getOtherFees())
			setBodyMainCell(mainBTable, bodyUtil.getOtherFeesTab(bm.getOtherFees(), labelMap));
		bodyUtil.getBodyTotalCell(mainBTable, bm.getTotalCharges(), labelMap);
		/*if (null != bm.getNotes())
			setBodyMainCell(mainBTable, bodyUtil.getCommentsNotesTab(bm.getNotes(), labelMap.get("hNotes")));*/
		if (bm.getListOfImages().size() > 0&&!isSummary) {
			setBodyMainCell(mainBTable, bodyUtil.getPhotosTab(bm.getListOfImages()));
		}
		return mainBTable;
	}

	private void setBodyMainCell(PdfPTable mainBTable, PdfPTable table) {
		PdfPCell mainBodyCell = new PdfPCell(table);
		mainBodyCell.setPaddingLeft(20);
		mainBodyCell.setPaddingRight(20);
		mainBodyCell.setPaddingTop(10);
		mainBodyCell.setBorder(Rectangle.NO_BORDER);
		mainBTable.setSplitLate(false);
		mainBTable.addCell(mainBodyCell);
		mainBTable.setWidthPercentage(100);
	}

	private void setHeaderTitle(PdfWriter writer, String tipLogoPath, boolean isSummary) throws DocumentException, IOException {
		PdfPTable header = new PdfPTable(2);
		header.setTotalWidth(555);
		header.getDefaultCell().setFixedHeight(25f);
		header.getDefaultCell().setBorder(Rectangle.NO_BORDER);

		if (tipLogoPath != null && !tipLogoPath.isEmpty()) {
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
				URL imageURL = EstimationReportPdf.class.getResource(tipLogoPath);
				File f = new File(imageURL.toString());
				//if (f.exists()) {
					try {
						System.out.println();
						logo = Image.getInstance(imageURL);
						header.addCell(logo);
					} catch (BadElementException | IOException e) {
						logger.info("Error while fetching Logo ", e);
					}
			}
		} else {
			header.addCell(new Phrase(""));
		}

		// add text
		PdfPCell text = new PdfPCell();
		text.setPaddingBottom(15);
		text.setPaddingLeft(isSummary?120:100);
		text.setBorder(Rectangle.UNDEFINED);
		//text.setBorderColor(Color.LIGHT_GRAY);

		text.addElement(new Phrase(isSummary ? "Estimation Summary" : "Full Estimate Report", styleUtil.getPdfTitleFont()));
		
		//text.setHorizontalAlignment(Element.ALIGN_CENTER);
		header.addCell(text);

		header.writeSelectedRows(0, -1, 24, 825, writer.getDirectContent());
		//return header;
	}

	public ResponseObject createPdf(BaseModel bm, Map<String, String> labelMap, boolean isSummary) {
		ResponseObject responseObj = new ResponseObject();
		String version = (bm.getVersion()==null||bm.getVersion()==0) ? "_1" : "_"+String.valueOf(bm.getVersion());
		String supplementary = bm.getSupplementary()==null||bm.getSupplementary()==0 ? "_0" : "_"+String.valueOf(bm.getSupplementary());
		
		try {
			Document document = new Document(PageSize.A4, 0, 0, 80, 20);
			File file;
			if (env.equalsIgnoreCase("LOCAL"))
				file = new File("Estimation_Preview" + version + supplementary + ".pdf");
			else
				file = new File(bm.getReportLoc() + version + supplementary + ".pdf");
				
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			InputStream inputStream = new FileInputStream(file);
			PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);
			document.open();
			setHeaderTitle(writer, bm.getLogoUrl(),isSummary);

			PdfPTable hTable = headerTableUtil.getHeaderTable(bm,labelMap);
			document.add(hTable);
			document.add(getBodyTable(bm,labelMap,isSummary));

			document.close();
			responseObj.setFile(file);
			responseObj.setInputStream(inputStream);
			responseObj.setOutputStream(fileOutputStream);
		} catch (IOException | DocumentException e) {
			logger.error(errorString, e);
		}
		return responseObj;
	}
}
