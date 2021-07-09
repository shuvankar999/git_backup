package com.tip.inspection.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.tip.inspection.model.BaseModel;
import com.tip.inspection.model.ResponseObject;

@Component
public class CiaReportUtil {
	
	/*@Autowired
	HeaderTableUtil headerTableUtil;*/
	
	/*@Value("${spring.profiles.active}")
    private String env;*/
	
	@Autowired
	BodyUtil bodyUtil;
	
	static final Logger logger = LoggerFactory.getLogger(CiaReportUtil.class);

	StyleUtil styleUtil = new StyleUtil();

	private String errorString = "An error occurred while generating inspection report";


	private void setHeaderTitle(PdfWriter writer, String tipLogoPath) throws DocumentException, IOException {
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
				URL imageURL = CiaReportUtil.class.getResource(tipLogoPath);
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
			header.addCell(new Phrase("www.tipeurope.com"));
		}

		// add text
		PdfPCell text = new PdfPCell();
		text.setPaddingBottom(15);
		text.setPaddingLeft(100);
		text.setBorder(Rectangle.UNDEFINED);

		text.addElement(new Phrase("Inspection Report", styleUtil.getFontBold()));
		header.addCell(text);

		header.writeSelectedRows(0, -1, 24, 825, writer.getDirectContent());
		//return header;
	}

	public ResponseObject createPdf(Map<String,Map<String,String>> map,String inspType ,String inspId) {
		ResponseObject responseObj = new ResponseObject();		
		try {
			Document document = new Document(PageSize.A4, 0, 0, 80, 20);
			
			File file = new File("CIAR_"+inspType+"_"+inspId+".pdf");
			
			//File file = new File("CIA_Report.pdf");
			
				
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			InputStream inputStream = new FileInputStream(file);
			PdfWriter writer = PdfWriter.getInstance(document, fileOutputStream);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);
			document.open();
			setHeaderTitle(writer,null);

			/*Map<String, String> bmMap = new HashMap();
			bmMap.put*/
			document.add(bodyUtil.getBodyTable(map));
			/*PdfPTable hTable = headerTableUtil.getHeaderTable(bm);
			document.add(hTable);*/
			
			//document.add(getBodyTable(bm,labelMap,isSummary));

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
