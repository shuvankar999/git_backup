package com.tip.assetpdf.repository.impl;

import java.awt.Color;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.MultiColumnText;

import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;

import com.lowagie.text.pdf.PdfWriter;
import com.tip.assetpdf.model.CommonUtil;
import com.tip.assetpdf.model.FullSpecPrintData;
import com.tip.assetpdf.model.PDFElementITextHelper;
import com.tip.assetpdf.model.SpecItemData;
import com.tip.assetpdf.repository.unitDaoInf;



@Repository
public class unitDaoImpl extends PdfPageEventHelper implements unitDaoInf {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource ds) {
		jdbcTemplate = new JdbcTemplate(ds);
	}
	
	static final Logger logger = LoggerFactory.getLogger(unitDaoImpl.class);


	public ByteArrayOutputStream getNoPicture(String unitNr, String heading,
			String uri, boolean picture, String pictureLocation,byte[] data)
			{
		
	
		
		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
		PDFElementITextHelper pdfHelper = new PDFElementITextHelper();
		pdfHelper.setUnitNrFooter(unitNr);
		pdfHelper.setUri(uri);
		CommonUtil.uri = uri;
	
		StringBuffer query = new StringBuffer();
		SpecItemData specItemData=null;
		List<FullSpecPrintData> newData = new ArrayList<FullSpecPrintData>(0);
		try {
		
			query.append("SELECT DISTINCT specGroups.Specgr_Descr, ");
			query.append("specGroups.Specgr_Id ");
			query.append("FROM OPSeqsp..Spec_Groups specGroups, ");
			query.append("OPSeqsp..Unit_Specs unitSpecs, ");
			query.append("OPSeqsp..Spec_Itm_Usg specItmUsg, ");
			query.append("OPSeqsp..Units units ");
			query
					.append("WHERE ( specGroups.Specgr_Id = unitSpecs.Specgr_Id ) and ");
			query.append("( unitSpecs.Specgr_Id = specItmUsg.Specgr_Id ) and ");
			query
					.append("( unitSpecs.Specitm_Seq = specItmUsg.Specitm_Seq ) and ");
			query.append("( specItmUsg.Catgr_Id = units.Catgr_Id ) and ");
			query.append("( unitSpecs.Unit_Id = units.Unit_Nr ) and ");
			query.append("( ( unitSpecs.Unit_Id = ");
			query.append(unitNr);
			query.append(") AND ");
			query.append("( specItmUsg.Aud_id = 'SL' ) AND ");
			query.append("( unitSpecs.Unitsp_Value is not null ) AND ");
			query.append("( lower(unitSpecs.Unitsp_Value) <> 'no' ) AND ");
			query.append("( specGroups.Specgr_Id <> 186 ) ) ");
			query.append("GROUP BY specGroups.Specgr_Descr, ");
			query.append("specGroups.Specgr_Id ");
			query.append("ORDER BY specGroups.Specgr_Descr ASC "); // Akilesh
																	// Change
																	// for
																	// Description
																	// display
																	// order by
																	// in PDF

			
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(query.toString());
			ResultSet rs = stmt.executeQuery();
			
			List<SpecItemData> fullPrintData = new ArrayList<SpecItemData>();
			while (rs.next()) {
				
				specItemData = new SpecItemData();
				
				specItemData.setSpecitmDescr(CommonUtil
						.checkNullObject(rs.getString("Specgr_Descr")));
				specItemData.setSpecgrId(CommonUtil
						.checkNullObject(rs.getString("Specgr_Id")));
				
				fullPrintData.add(specItemData);
			}
			
	
			Iterator iter = fullPrintData.iterator();
			
			FullSpecPrintData fullSpecPrintData = null;
			StringBuffer innerQuery1 = new StringBuffer();
			StringBuffer innerQuery2 = new StringBuffer();
			StringBuffer innerQuery3 = new StringBuffer();

			

			innerQuery1.append("SELECT SI.Specitm_Shortn, ");
			innerQuery1.append("SI.Specitm_Descr, ");
			innerQuery1.append("US.Unitsp_Value, ");
			innerQuery1.append("SI.Specitm_Order ");
			innerQuery1.append("FROM OPSeqsp..Spec_Items SI, ");
			innerQuery1.append("OPSeqsp..Unit_Specs US, ");
			innerQuery1.append("OPSeqsp..Spec_Itm_Usg SIU, ");
			innerQuery1.append("OPSeqsp..Units U ");
			innerQuery1.append("WHERE ( SI.Specgr_Id = US.Specgr_Id ) and ");
			innerQuery1.append("( SI.Specitm_Seq = US.Specitm_Seq ) and ");
			innerQuery1.append("( US.Specgr_Id = SIU.Specgr_Id ) and ");
			innerQuery1.append("( US.Specitm_Seq = SIU.Specitm_Seq ) and ");
			innerQuery1.append("( SIU.Catgr_Id = U.Catgr_Id ) and ");
			innerQuery1.append("( US.Unit_Id = U.Unit_Nr ) and ");
			innerQuery1.append("( ( SI.Specgr_Id = ");

			innerQuery2.append(") AND ");
			innerQuery2.append("( US.Unit_Id = ");

			innerQuery3.append(") AND ");
			innerQuery3.append("( US.Unitsp_Value is not null ) AND ");
			innerQuery3.append("( lower(US.Unitsp_Value) <> 'no' ) AND ");
			innerQuery3.append("( SIU.Aud_id = 'SL' ) AND ");
			innerQuery3.append("( SIU.Usg_Used = 'Y' ) AND ");
			innerQuery3
					.append("( char_length(rtrim(US.Unitsp_Value)) > 0 )) ORDER BY SI.Specitm_Order ASC ");

			StringBuffer innerquery = null;
			//List innerList = null;
			Iterator innerIter = null;
			Object[] obj = null;
			SpecItemData specItemDataItr =null;
			List<SpecItemData> innerList = new ArrayList<SpecItemData>();
			
			while (iter.hasNext()) {

				specItemDataItr = (SpecItemData) iter.next();
				// System.out.println("$$>>>"+CommonUtil.checkNullObject(obj[1]));
				innerquery = new StringBuffer();
				innerquery.append(innerQuery1.toString());
			//	System.out.println("Spec group Id :"+specItemDataItr.getSpecgrId());
				innerquery.append(CommonUtil.checkNullObject(specItemDataItr.getSpecgrId()));
				innerquery.append(innerQuery2.toString());
			//	System.out.println("Unit Nr :"+unitNr);
				innerquery.append(unitNr);
				innerquery.append(innerQuery3.toString());
				
				
				PreparedStatement stmt2 = jdbcTemplate.getDataSource().getConnection().prepareStatement(innerquery.toString());
				ResultSet resultSet = stmt2.executeQuery();
				
			//	List<SpecItemData> fullPrintData = new ArrayList<SpecItemData>();
				while (resultSet.next()) {
					//	type = rs.getString("DOCUMENT_TYPE");
						//if (type.equalsIgnoreCase("SERVICES")) {
					fullSpecPrintData = new FullSpecPrintData();
				//	System.out.println("specgrDescr :"+specItemDataItr.getSpecitmDescr());
					fullSpecPrintData.setSpecgrDescr(CommonUtil
							.checkNullObject(specItemDataItr.getSpecitmDescr()));
				//	System.out.println("specItmDescr :"+resultSet.getString(2));
					fullSpecPrintData.setSpecitmDescr(CommonUtil
							.checkNullObject(resultSet.getString(2)));
				//	System.out.println("Unitsp Descr :"+resultSet.getString(3));
					fullSpecPrintData.setUnitspValue(CommonUtil
							.checkNullObject(resultSet.getString(3)));
				
					
					newData.add(fullSpecPrintData);
				}
				
				

			}
			pdfHelper.setCustomerSpecPrint(newData);
			pdfHelper.setHeadingCustomerPrint(CommonUtil
					.checkNullObject(heading));
			logger.info("newData.size() : " + newData.size());
			if (newData.size() != 0) {

				generateNopicturePrint(newData, baoStream, heading, uri,
						picture, pictureLocation, pdfHelper,data);

			}
			logger.info("baoStream : " + baoStream.size());

		} catch (Exception e) {

			e.printStackTrace();
			logger.error("Exception At unitDaoImpl.getChoosePicture"
					+ e.getMessage());
//			throw new DAOException(
//					"Exception At unitDaoImpl.getChoosePicture.."
//							+ e.getMessage());
		} finally {
			

		}
		return baoStream;
	}

	
	private String generateNopicturePrint(List<FullSpecPrintData> fullSpecs,
			ByteArrayOutputStream baoStream, String heading, String uri,
			boolean picture, String pictureLocation,
			PDFElementITextHelper pdfHelper,byte[] imageData){
		logger.info("unitDaoImpl.generateNopicturePrint....Start");
		// Document document = new Document(PageSize.A4, 50, 10, 50, 50);
		Document document = new Document(PageSize.A4, 50, 10, 30, 30);
		// document.setMargins(20, 20, 20, 20);
		document.setMargins(20, 20, 20, 20);
		try {
			PdfWriter writer = PdfWriter.getInstance(document, baoStream);
			FontFactory.register(uri + "/images/calibri.ttf");
			FontFactory.register(uri + "/images/calibrib.ttf");
			// Font colfont =
			// FontFactory.getFont("Calibri Bold",BaseFont.IDENTITY_H,
			// BaseFont.EMBEDDED, 7);
			Font colfont = FontFactory.getFont("Calibri Bold",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 6);
			// Font colfontBody =
			// FontFactory.getFont("Calibri",BaseFont.IDENTITY_H,
			// BaseFont.EMBEDDED, 7);
			Font colfontBody = FontFactory.getFont("Calibri",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 6);

			writer.setPageEvent(pdfHelper);
			/*
			 * HeaderFooter footer = new HeaderFooter(new
			 * Phrase("Page : ",colfont), true); footer.disableBorderSide(3);
			 * footer.setAlignment(Element.ALIGN_RIGHT); footer.setTop(100);
			 * document.setFooter(footer);
			 */
			document.open();

			PdfPTable emptyTable = new PdfPTable(1);
			emptyTable.getDefaultCell().setBorder(0);

			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			emptyTable.addCell("");
			// emptyTable.addCell("");
			document.add(emptyTable);

			if (picture) {
				try {
					PdfPTable imgTable = new PdfPTable(1);
					imgTable.getDefaultCell().setBorder(0);

					Image image2 = Image.getInstance(imageData);
					// imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");
					// imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");
					// imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");imgTable.addCell("");
					// imgTable.addCell("");
					imgTable.addCell(image2);
					imgTable.addCell("");
					document.add(imgTable);
				} catch (FileNotFoundException e) {
					logger.error("FileNotFoundException : " + pictureLocation);
					PdfPTable imgTable = new PdfPTable(2);
					imgTable.getDefaultCell().setBorder(0);
					imgTable.addCell("  ");
					imgTable.addCell("  ");

					imgTable.addCell("  ");
					imgTable.addCell("  ");

					document.add(imgTable);

				}

			}

			MultiColumnText mct = new MultiColumnText();
			mct.setColumnsRightToLeft(false);
			mct.addRegularColumns(document.left(), document.right(), 15f, 2);// changed
																				// on
																				// 15/04/2011

			/*
			 * PdfPTable headingTable1 = new PdfPTable(1);
			 * headingTable1.setWidthPercentage(100);
			 * headingTable1.getDefaultCell().setBorder(0);
			 * headingTable1.addCell(new Phrase("", colfont));
			 * 
			 * document.add(headingTable1);
			 */

			// PdfPTable main = null;
			PdfPTable mainTable = null;
			PdfPTable mainTable1 = null;
			String specDesc = "";
			int count = 1;
			int rowCount = 0;
			int maxRowCount = 0;
			if (picture)
				// maxRowCount=40;
				maxRowCount = 35;
			else
				// maxRowCount=50;
				maxRowCount = 40;

			for (FullSpecPrintData data : fullSpecs) {
				rowCount++;
				/*
				 * main = new PdfPTable(1); main.setWidthPercentage(100);
				 * main.getDefaultCell().setBorder(0);
				 */
			//	System.out.println("SpecgrDescr :"+data.getSpecgrDescr());
				if (!specDesc.equalsIgnoreCase(data.getSpecgrDescr())) { // checks
																			// data.getSpecgrDescr
																			// not
																			// null
					if (rowCount > maxRowCount) {
						/*
						 * PdfPTable emptyTable1 = new PdfPTable(1);
						 * emptyTable1.getDefaultCell().setBorder(0);
						 * 
						 * emptyTable1.addCell("");emptyTable1.addCell("");
						 * emptyTable1
						 * .addCell("");emptyTable1.addCell("");emptyTable1
						 * .addCell("");
						 * emptyTable1.addCell("");emptyTable1.addCell
						 * ("");emptyTable1
						 * .addCell("");emptyTable1.addCell("");emptyTable1
						 * .addCell("");
						 * emptyTable1.addCell("");emptyTable1.addCell
						 * ("");emptyTable1
						 * .addCell("");emptyTable1.addCell("");emptyTable1
						 * .addCell(""); emptyTable1.addCell("");
						 * document.add(emptyTable1);
						 */
						mainTable1 = new PdfPTable(2);
						int[] mainTableWidth = { 50, 50 };
						mainTable1.setWidths(mainTableWidth);
						mainTable1.setWidthPercentage(100);
						// Added on 11May2011
						// /mainTable.getDefaultCell().setFixedHeight(10.8f);
						// mainTable.getDefaultCell().setBorder(0);
						mainTable1.getDefaultCell().disableBorderSide(
								PdfPCell.TOP);
						mainTable1.getDefaultCell().disableBorderSide(
								PdfPCell.BOTTOM);
						// count=1;
						PdfPCell bottom1 = new PdfPCell(new Phrase(""));
						bottom1.disableBorderSide(PdfPCell.TOP);
						PdfPCell bottom2 = new PdfPCell(new Phrase(""));
						bottom2.disableBorderSide(PdfPCell.TOP);
						mainTable1.addCell(bottom1);
						mainTable1.addCell(bottom2);
						PdfPCell empty = new PdfPCell(new Phrase(""));
						empty.setColspan(2);
						empty.setBorder(0);
						mainTable1.addCell(empty);
						// mct.nextColumn();
						// rowCount=0;
						mct.addElement(mainTable1);
						document.add(mct);
					}
				}
				mainTable = new PdfPTable(2);
				int[] mainTableWidth = { 50, 50 };
				mainTable.setWidths(mainTableWidth);
				mainTable.setWidthPercentage(100);
				// Added on 11May2011
				// /mainTable.getDefaultCell().setFixedHeight(10.8f);
				// mainTable.getDefaultCell().setBorder(0);
				mainTable.getDefaultCell().disableBorderSide(PdfPCell.TOP);
				mainTable.getDefaultCell().disableBorderSide(PdfPCell.BOTTOM);
				// Akhliesh on 12/06/2011
				if (!specDesc.equalsIgnoreCase(data.getSpecgrDescr())) { // checks
																			// data.getSpecgrDescr
																			// not
																			// null
					if (rowCount > maxRowCount) {
						count = 1;
						mct.nextColumn();
						rowCount = 0;
					}
					if (count != 1) {
						PdfPCell bottom1 = new PdfPCell(new Phrase(""));
						bottom1.disableBorderSide(PdfPCell.TOP);
						PdfPCell bottom2 = new PdfPCell(new Phrase(""));
						bottom2.disableBorderSide(PdfPCell.TOP);
						mainTable.addCell(bottom1);
						mainTable.addCell(bottom2);
						PdfPCell empty = new PdfPCell(new Phrase(""));
						empty.setColspan(2);
						empty.setBorder(0);
						mainTable.addCell(empty);
					}
					count++;
					PdfPCell specDescData = new PdfPCell();

					specDescData.addElement(new Phrase(data.getSpecgrDescr(),
							colfont));

					// disableTopLeftRightBorders(specDescData);
					specDescData.setColspan(2);
					specDescData.setFixedHeight(20);// 22Chandra
					// specDescData.setVerticalAlignment(Element.ALIGN_TOP);

					specDescData.setHorizontalAlignment(Element.ALIGN_CENTER);
					specDescData.setBackgroundColor(Color.LIGHT_GRAY);

					mainTable.addCell(specDescData);
					specDesc = data.getSpecgrDescr();
				}

				// PdfPCell specItmDescData = new PdfPCell();
				// disableBorders(specItmDescData);
				// specItmDescData.addElement(new
				// Phrase(data.getSpecitmDescr(),colfont));
				// specItmDescData.setHorizontalAlignment(Element.ALIGN_CENTER);

				mainTable.getDefaultCell().disableBorderSide(PdfPCell.TOP);
				mainTable.getDefaultCell().disableBorderSide(PdfPCell.BOTTOM);
			//	System.out.println("SpecItm Desc :"+data.getSpecitmDescr());
				mainTable.addCell(new Phrase(data.getSpecitmDescr(),
						colfontBody));

				// PdfPCell unitspValueData = new PdfPCell();
				// disableBorders(unitspValueData);
				// unitspValueData.addElement(new
				// Phrase(data.getUnitspValue(),colfont));
				// unitspValueData.setHorizontalAlignment(Element.ALIGN_CENTER);
				mainTable.getDefaultCell().disableBorderSide(PdfPCell.TOP);
				mainTable.getDefaultCell().disableBorderSide(PdfPCell.BOTTOM);
			//	System.out.println("unitspvalue :"+data.getUnitspValue());
				mainTable
						.addCell(new Phrase(data.getUnitspValue(), colfontBody));
				// System.out.println(data.getSpecitmDescr()+ "  --------" +
				// data.getUnitspValue());
				// main.addCell(mainTable);
				// main.addCell(new Phrase("", colfont));
				mct.addElement(mainTable);
				document.add(mct);
			}
			// PdfPCell bottom1 = new PdfPCell(new Phrase(""));
			// bottom1.addElement(new Phrase("test data",colfontBody));
			// mainTable.addCell(bottom1);
			mainTable = new PdfPTable(2);
			int[] mainTableWidth = { 50, 50 };
			mainTable.setWidths(mainTableWidth);
			mainTable.setWidthPercentage(100);
			PdfPCell bottom = new PdfPCell(new Phrase(""));
			bottom.setColspan(2);
			bottom.disableBorderSide(PdfPCell.TOP);
			mainTable.addCell(bottom);
			mct.addElement(mainTable);
			document.add(mct);

			PdfPTable headingTable = new PdfPTable(1);
			headingTable.getDefaultCell().setBorder(0);

			document.close();

		} catch (Exception e) {

			e.printStackTrace();
//			throw new DAOException(
//					"Exception at unitDaoImpl.generateNopicturePrint...."
//							+ e.getMessage());

		} finally {
			logger.info("unitDaoImpl.generateNopicturePrint....End");
		}

		return "";

	}
	

	public ByteArrayOutputStream getUnitFullSpecPrintDetails(String unitNr,
			String uri)  {
		logger.info("unitDaoImpl.getUnitFullSpecPrintDetails....START");
		PDFElementITextHelper pdfHelper = new PDFElementITextHelper();
		pdfHelper.setUri(uri);

		CommonUtil.uri = uri;

		ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
	//	Session session = null;
		StringBuffer query = new StringBuffer();
		try {
			
			
			
			boolean saveFlag = true;
			Connection connection = null;
			PreparedStatement statement = null;
			List<FullSpecPrintData> fullSpecs = new ArrayList<FullSpecPrintData>();
			FullSpecPrintData fullSpecPrintData = null;
			String catDesc = "";
			try {
				connection = jdbcTemplate.getDataSource().getConnection();
				statement = connection.prepareStatement("{call " + "OPSeqsp..EQSP_print_FullSpecification" + "(?)}");
				connection.setAutoCommit(false);
				
					statement.setInt(1, Integer.parseInt(unitNr));
				
					ResultSet rs =	statement.executeQuery();
			
					while (rs.next()) {
						//	type = rs.getString("DOCUMENT_TYPE");
							//if (type.equalsIgnoreCase("SERVICES")) {
						fullSpecPrintData = new FullSpecPrintData();
						
						fullSpecPrintData.setSpecgrDescr(CommonUtil
								.checkNullObject(rs.getString("Specgr_Descr")));
						fullSpecPrintData.setSpecitmDescr(CommonUtil
								.checkNullObject(rs.getString("Specitm_Descr")));
						fullSpecPrintData.setUnitspValue(CommonUtil
								.checkNullObject(rs.getString("Unitsp_Value")));
						fullSpecPrintData.setUnitId(CommonUtil.checkNullObject(rs.getString("Unit_Id")));
						catDesc = CommonUtil.checkNullObject(rs.getString("Catgr_Desc"));
						fullSpecPrintData.setCatgrDesc(catDesc);
						fullSpecPrintData.setSpecitmOrder(CommonUtil
								.checkNullObject(rs.getString("Specitm_Order")));
						fullSpecs.add(fullSpecPrintData);
					}
				//int[] returnArr = statement.executeBatch();
				connection.commit();
				//logger.debug("Return array  :" + Arrays.toString(returnArr));
				
				statement.close();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				if(statement != null){
					try {
						statement.close();
					} catch (SQLException e1) {
						logger.error("An error occurred while saving customer fee: " , e1);
					}
				}
				saveFlag = false;
				logger.error("An error occurred while saving filter details: " + e);
			}	
			
			
			
			//session = HibernateUtil.getHibernateSessionOPSinv();

			
			long startDate = new Date().getTime();

			
			logger.info("Start of the procedure Specification details--->>"
					+ startDate);
			
			pdfHelper.setUnitNrFooter(unitNr);
			pdfHelper.setCatDesc(catDesc);
			pdfHelper.setFullSpecPrint(fullSpecs);
			if (fullSpecs.size() != 0) {
				generateFullSpecPrintPdf(fullSpecs, baoStream, catDesc, unitNr,
						uri, pdfHelper);
			}

		}  catch (Exception e) {

			logger.error("Exception At unitDaoImpl.getUnitFullSpecPrintDetails"
					+ e.getMessage());
//			throw new DAOException(
//					"Exception At unitDaoImpl.getUnitFullSpecPrintDetails.."
//							+ e.getMessage());
		} finally {
			

		}
		return baoStream;
	}

	private String generateFullSpecPrintPdf(List<FullSpecPrintData> fullSpecs,
			ByteArrayOutputStream baoStream, String catDesc, String unitNr,
			String uri, PDFElementITextHelper pdfHelper)  {
		logger.info("unitDaoImpl.generateFullSpecPrintPdf....START");
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);

		try {
			PdfWriter writer = PdfWriter.getInstance(document, baoStream);
			BaseFont bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "",
					false);

			FontFactory.register(uri + "/images/calibri.ttf");
			FontFactory.register(uri + "/images/calibrib.ttf");
			// Font colfont = FontFactory.getFont("Calibri Bold",
			// BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 7);
			Font colfont = FontFactory.getFont("Calibri Bold",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 7);
			Font colfontBody = FontFactory.getFont("Calibri",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 7);
			Font colfont1 = FontFactory.getFont("Calibri Bold",
					BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 14);

			writer.setPageEvent(pdfHelper);
			// HeaderFooter footer = new HeaderFooter(new Phrase("Page : ",
			// colfont), true);
			/*
			 * footer.disableBorderSide(3);
			 * footer.setAlignment(Element.ALIGN_RIGHT); footer.setTop(100);
			 * document.setFooter(footer);
			 */
			document.open();
			document.add(pdfHelper.getLineSpace(3));

			PdfPTable headingTable1 = new PdfPTable(1);
			headingTable1.setWidthPercentage(100);
			headingTable1.getDefaultCell().setBorder(0);
			headingTable1.addCell(new Phrase("", colfont));
			document.add(headingTable1);

			PdfPTable main = null;
			PdfPTable mainTable = null;
System.out.println("FullSpec details :"+fullSpecs.size());
			String specDesc = "";
			for (FullSpecPrintData data : fullSpecs) {

				main = new PdfPTable(1);
				// int[] width11 = {90,10};
				// main.setWidths(width11);
				main.setWidthPercentage(100);
				main.getDefaultCell().setBorder(0);
				mainTable = new PdfPTable(3);
				mainTable.setWidthPercentage(100);
				// int[] width = {30,40,30}; chandra test
				int[] width = { 30, 30, 40 };
				mainTable.setWidths(width);
				// test chandra
				// /mainTable.getDefaultCell().setFixedHeight(11f);

				mainTable.getDefaultCell().setBorder(0);

				/*
				 * if(!(specDesc.equalsIgnoreCase(data.getSpecgrDescr()))){
				 * 
				 * PdfPCell specDescData = new PdfPCell();
				 * specDescData.addElement(new
				 * Phrase(data.getSpecgrDescr(),colfontBody));
				 * specDescData.setBorderColorBottom(Color.BLACK);
				 * disableTopLeftRightBorders(specDescData);
				 * 
				 * specDescData.setHorizontalAlignment(Element.ALIGN_RIGHT);
				 * mainTable.addCell(specDescData); }
				 */

				if (!(specDesc.equalsIgnoreCase(data.getSpecgrDescr()))) {

					PdfPCell specDescData = new PdfPCell();
					specDescData.addElement(new Phrase(data.getSpecgrDescr(),
							colfontBody));
					disableTopLeftRightBorders(specDescData);
					specDescData.setHorizontalAlignment(Element.ALIGN_CENTER);
					mainTable.addCell(specDescData);

					PdfPCell empty1 = new PdfPCell();
					empty1.addElement(new Phrase("", colfontBody));
					disableBorders(empty1);
					empty1.setHorizontalAlignment(Element.ALIGN_CENTER);
					mainTable.addCell(empty1);

					PdfPCell empty2 = new PdfPCell();
					empty2.addElement(new Phrase("", colfontBody));
					disableBorders(empty2);
					empty2.setHorizontalAlignment(Element.ALIGN_CENTER);
					mainTable.addCell(empty2);
					specDesc = data.getSpecgrDescr();
				}

				PdfPCell empty3 = new PdfPCell();
				empty3.addElement(new Phrase("", colfontBody));
				disableBorders(empty3);
				empty3.setHorizontalAlignment(Element.ALIGN_CENTER);
				mainTable.addCell(empty3);

				PdfPCell specItmDescData = new PdfPCell();
				disableBorders(specItmDescData);
				specItmDescData.addElement(new Phrase(data.getSpecitmDescr(),
						colfontBody));
				specItmDescData.setHorizontalAlignment(Element.ALIGN_CENTER);
				mainTable.addCell(specItmDescData);

				PdfPCell unitspValueData = new PdfPCell();
				disableBorders(unitspValueData);
				unitspValueData.addElement(new Phrase(data.getUnitspValue(),
						colfontBody));
				unitspValueData.setHorizontalAlignment(Element.ALIGN_CENTER);
				mainTable.addCell(unitspValueData);

				main.addCell(mainTable);
				// / main.addCell(new Phrase("",colfontBody));
				document.add(main);
			}

			document.close();

		} catch (Exception e) {

			e.printStackTrace();
//			throw new DAOException(
//					"Exception at unitDaoImpl.generateFullSpecPrintPdf...."
//							+ e.getMessage());

		} finally {
			logger.info("unitDaoImpl.generateFullSpecPrintPdf....END");
		}

		return "";

	}

	
	
	private static void disableBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableTopLeftRightBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
		cell.disableBorderSide(PdfPCell.TOP);

	}

	private static void disableBottomRightBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}

	private static void disableLeftRightBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}

	private static void disableBottomLeftBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.LEFT);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableTopRightBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}

	private static void disableTopLeftBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.LEFT);
	}

	private static void disableBottomBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableTopBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);
	}

	private static void disableTopBottomBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
	}

	private static void disableTopBottomLeftBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.LEFT);
	}

	private static void disableTopBottomRightBorders(PdfPCell cell) {
		cell.disableBorderSide(PdfPCell.TOP);
		cell.disableBorderSide(PdfPCell.BOTTOM);
		cell.disableBorderSide(PdfPCell.RIGHT);
	}

	
	public String getAxle(String unitCatGroup) {
		
		String axleCatDescData = "";
		//Session session = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT Catgr_Code ");
		query.append("FROM OPSeqsp..Catgr_Codes ");
		query.append("WHERE charindex('axle', lower(Catgr_Descr)) > 0 ");
		query.append("AND rtrim(Catgr_Type) = SubString('");
		query.append(unitCatGroup);
		query.append("', 1, 2) ");
		query.append("AND charindex(rtrim(Catgr_Code), '");
		query.append(unitCatGroup);
		query.append("') = Catgr_Pos_From ");
		query.append("AND Catgr_Code Between '0' and '30'");
		
		boolean existFlag = false;
		int count = 0;
		try {
			PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(query.toString());
			ResultSet resultSet = stmt.executeQuery();
			if (resultSet.next()) {
				axleCatDescData = resultSet.getString("Catgr_Code");
			}
			
		} catch (Exception e) {
			logger.error("An error occurred while equipment photo count : ", e);
		}finally {
			

		}
		return axleCatDescData;

	}

	public String getCatDesc(String unitCatGroup) {
		logger.info("unitDaoImpl.getCatDesc....START");
		StringBuffer query1 = new StringBuffer();
		String catDesc = "";
	//	Session session = null;
		try {
	//		session = HibernateUtil.getHibernateSessionOPSinv();
			query1
					.append("SELECT convert(varchar(100),Catgr_Descr) as Catgr_Descr  ");
			query1.append("FROM OPSeqsp..Catgr_Codes ");
			query1.append("WHERE ( rtrim(Catgr_Type) = substring('");
			query1.append(unitCatGroup);
			query1.append("',1,2) ) AND ");
			query1.append("( Catgr_Pos_From = 1 )");

		
				PreparedStatement stmt = jdbcTemplate.getDataSource().getConnection().prepareStatement(query1.toString());
				ResultSet resultSet = stmt.executeQuery();
				if (resultSet.next()) {
					catDesc = resultSet.getString("Catgr_Descr");
				}
			
		} catch (Exception e) {
			logger.error("Exception At unitDaoImpl.getCatDesc" + e.getMessage());
//			throw new DAOException("Exception At unitDaoImpl.getCatDesc.."
//					+ e.getMessage());
		} finally {
			

		}
		return catDesc;
	}

	/** Written for new dat interface Field Crs **/

	
}
