package com.tip.download.assetdetail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tip.asset.model.ArrayOfAssetData;
import com.tip.asset.model.AssetSearchResponse;
import com.tip.equipmentdetails.model.DownloadAssetDetailResponse;
import com.tip.equipmentdetails.model.DownloadAssetDetailsRequest;

@Component
public class DownloadAssetDetail {
	
	static final Logger logger = LoggerFactory.getLogger(DownloadAssetDetail.class);

	private Workbook getWorkbook(String excelFilePath)
	        throws IOException {
	    Workbook workbook;
	 
	    if (excelFilePath.endsWith("xlsx")) {
	        workbook = new XSSFWorkbook();
	    } else if (excelFilePath.endsWith("xls")) {
	        workbook = new HSSFWorkbook();
	    } else {
	        throw new IllegalArgumentException("The specified file is not Excel file");
	    }	 
	    return workbook;
	}
	
	public DownloadAssetDetailResponse writeassetDetailsToExcel(ArrayOfAssetData arrayOfAssetData, DownloadAssetDetailsRequest downloadAssetDetailsRequest){
		DownloadAssetDetailResponse responseObj = new DownloadAssetDetailResponse();
		try {
			String excelFilePath = "assetDet." + downloadAssetDetailsRequest.getFormat();
			File file = new File(excelFilePath);
		    FileOutputStream fileOutputStream = new FileOutputStream(file);
		    InputStream inputStream = new FileInputStream(file);
		    
	        Workbook workbook = getWorkbook(excelFilePath);
	        Sheet sheet = workbook.createSheet("AssetDetails");
	        if(downloadAssetDetailsRequest.getFetchFilterDetails() == null || downloadAssetDetailsRequest.getFieldNames().isEmpty()){
	        	List<String> headerList = addHeaders();
	        	createHeaderRow(sheet,headerList);
    	    }
    	    else{
    	    	createHeaderRow(sheet,downloadAssetDetailsRequest.getFieldNames());
    	    }
	        createBodyRows(sheet,arrayOfAssetData,downloadAssetDetailsRequest);
	        workbook.write(fileOutputStream);
	        workbook.close();
	        responseObj.setFile(file);
	        responseObj.setInputStream(inputStream);
	        responseObj.setOutputStream(fileOutputStream);
        } catch (IOException e) {
        	logger.error("An error occurred while generating excel for asset detail: " + e);
        }
		return responseObj;
    }
	
	private void createBodyRows(Sheet sheet, ArrayOfAssetData arrayOfAssetData, DownloadAssetDetailsRequest downloadAssetDetailsRequest) {
		int rowIndex = 1;
        for(AssetSearchResponse assetDet : arrayOfAssetData.getAssetDataItem()){
        	Row row = sheet.createRow(rowIndex++);
        	CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    	    Font font = sheet.getWorkbook().createFont();
    	    font.setBold(false);
    	    font.setFontName("Trebuchet MS");
    	    font.setFontHeightInPoints((short) 8);
    	    cellStyle.setFont(font);
    	    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    	    if(downloadAssetDetailsRequest.getFetchFilterDetails() == null || downloadAssetDetailsRequest.getFieldNames().isEmpty()){
    	    	createBodyCells(row, cellStyle, assetDet);
    	    }
    	    else{
    	    	createCellForFieldName(row, cellStyle, assetDet, downloadAssetDetailsRequest.getFieldNames());
    	    }
        }
	}
	
	private void createBodyCells(Row row, CellStyle cellStyle, AssetSearchResponse assetDet){
        int cellIndex = 0;
	    createCell(row, cellStyle, cellIndex++, (assetDet.getUnitNumber() != null) ? assetDet.getUnitNumber().toString() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getLicenceNumber() != null) ? assetDet.getLicenceNumber() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getOperationalStatus() != null) ? assetDet.getOperationalStatus() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getInOutStatus() != null) ? assetDet.getInOutStatus() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getPhysicalCompanyNumber() != null) ? assetDet.getPhysicalCompanyNumber().toString() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getRespnsblCompanyNumber() != null) ? assetDet.getRespnsblCompanyNumber().toString() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getLastParkLocCd() != null) ? assetDet.getLastParkLocCd() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getLastIntchKey() != null) ? assetDet.getLastIntchKey().toString() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getCountry() != null) ? assetDet.getCountry() : "");
	    createSecondHalfBodyCells(row, cellStyle, assetDet, cellIndex);
	}
	
	private void createSecondHalfBodyCells(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd){
		 int cellIndex = cellInd;
	    createCell(row, cellStyle, cellIndex++, (assetDet.getCategoryGroupCode() != null) ? assetDet.getCategoryGroupCode() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getRagStatus() != null) ? assetDet.getRagStatus() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getPhysicalBranchNumber() != null) ? assetDet.getPhysicalBranchNumber().toString() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getRespnsblBranchNumber() != null) ? assetDet.getRespnsblBranchNumber().toString() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getSerialNumber() != null) ? assetDet.getSerialNumber() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getManufacturer() != null) ? assetDet.getManufacturer() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getAvailableForSale() != null) ? assetDet.getAvailableForSale() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getCustomerAssetNumber() != null) ? assetDet.getCustomerAssetNumber() : "");
	    createCell(row, cellStyle, cellIndex++, (assetDet.getCustomerName() != null) ? assetDet.getCustomerName() : "");
	    createThirdHalfBodyCells(row, cellStyle, assetDet, cellIndex);
	}
	
	private void createThirdHalfBodyCells(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd){
		 int cellIndex = cellInd;
	    createCell(row, cellStyle, cellIndex, (assetDet.getLicenceCountryName() != null) ? assetDet.getLicenceCountryName() : "");
	}
	
	private void createCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, List<String> fieldNames){
		int cellIndex = 0;
		for(String fieldName: fieldNames){
			createFirstCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createSecCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createThirdCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createFourthCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createFifthCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createSixthCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createSeventhCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createEighthCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createNinethCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createTenthCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createEleventhCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			createTwelvethCellForFieldName(row, cellStyle, assetDet, cellIndex,fieldName);
			cellIndex++;
		}
	}
	
	private void createFirstCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Unit Number".equalsIgnoreCase(fieldName)) {
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitNumber() != null) ? assetDet.getUnitNumber().toString() : "");
		} else if("Licence Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLicenceNumber() != null) ? assetDet.getLicenceNumber() : "");
		} else if("Operational Status".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getOperationalStatus() != null) ? assetDet.getOperationalStatus() : "");
		} else if("In Out Status".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getInOutStatus() != null) ? assetDet.getInOutStatus() : "");
		}
	}
	
	private void createSecCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Physical Company Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getPhysicalCompanyNumber() != null) ? assetDet.getPhysicalCompanyNumber().toString() : "");
		} else if("Respnsbl Company Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getRespnsblCompanyNumber() != null) ? assetDet.getRespnsblCompanyNumber().toString() : "");
		} else if("Last Park Loc Cd".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLastParkLocCd() != null) ? assetDet.getLastParkLocCd() : "");
		} else if("Last Intch Key".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLastIntchKey() != null) ? assetDet.getLastIntchKey().toString() : "");
		}
	}
	
	private void createThirdCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Country".equalsIgnoreCase(fieldName)){
				createCell(row, cellStyle, cellIndex, (assetDet.getCountry() != null) ? assetDet.getCountry() : "");
		} else if("Equipment Type".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getEquipmentType() != null) ? assetDet.getEquipmentType() : "");
		} else if("Category Group Code".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getCategoryGroupCode() != null) ? assetDet.getCategoryGroupCode() : "");
		} else if("Rag Status".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getRagStatus() != null) ? assetDet.getRagStatus() : "");
		}
	}
	
	private void createFourthCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Physical Branch Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getPhysicalBranchNumber() != null) ? assetDet.getPhysicalBranchNumber().toString() : "");
		} else if("Respnsbl Branch Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getRespnsblBranchNumber() != null) ? assetDet.getRespnsblBranchNumber().toString() : "");
		} else if("Serial Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getSerialNumber() != null) ? assetDet.getSerialNumber() : "");
		} else if("Manufacturer".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getManufacturer() != null) ? assetDet.getManufacturer() : "");
		}
	}
	
	private void createFifthCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Available For Sale".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getAvailableForSale() != null) ? assetDet.getAvailableForSale() : "");
		} else if("Customer Asset Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getCustomerAssetNumber() != null) ? assetDet.getCustomerAssetNumber() : "");
		} else if("Customer Name".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getCustomerName() != null) ? assetDet.getCustomerName() : "");
		} else if("Licence Country Name".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLicenceCountryName() != null) ? assetDet.getLicenceCountryName() : "");
		}
	}
	
	private void createSixthCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Is Third Party Asset".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getIsThirdPartyAsset() != null) ? assetDet.getIsThirdPartyAsset() : "");
		} else if("Equipment Owner Status".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getEquipmentOwnerStatus() != null) ? assetDet.getEquipmentOwnerStatus() : "");
		} else if("Customer Nr".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getCustomerNr() != null) ? assetDet.getCustomerNr().toString() : "");
		} else if("Unit Cd".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitCd() != null) ? assetDet.getUnitCd() : "");
		}
	}
	
	private void createSeventhCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Customer Number Combi".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getCustomerNumberCombi() != null) ? assetDet.getCustomerNumberCombi().toString() : "");
		} else if("Mot Date".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getMotDate() != null) ? assetDet.getMotDate() : "");
		} else if("Reservation".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getReservation() != null) ? assetDet.getReservation() : "");
		} else if("Po Number".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getPoNumber() != null) ? assetDet.getPoNumber() : "");
		}
	}
	
	private void createEighthCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Unit Comment".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitComment() != null) ? assetDet.getUnitComment() : "");
		} else if("Unit Status Comment".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitStatusComment() != null) ? assetDet.getUnitStatusComment() : "");
		} else if("Region".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getRegion() != null) ? assetDet.getRegion() : "");
		} else if("Remarketing Flag".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getRemarketingFlag() != null) ? assetDet.getRemarketingFlag() : "");
		}
	}
	
	private void createNinethCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Image Count".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getImageCount() != null) ? assetDet.getImageCount().toString() : "");
		} else if("Bcheck Service Date".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getBcheckServiceDate() != null) ? assetDet.getBcheckServiceDate() : "");
		} else if("Reefer Service Date".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getReeferServiceDate() != null) ? assetDet.getReeferServiceDate() : "");
		} else if("Customer Name New".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getCustomerNameNew() != null) ? assetDet.getCustomerNameNew() : "");
		}
	}
	
	private void createTenthCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Last Electric Hrs".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLastElectricHrs() != null) ? assetDet.getLastElectricHrs().toString() : "");
		} else if("Last Diesel Hrs".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLastDieselHrs() != null) ? assetDet.getLastDieselHrs().toString() : "");
		} else if("Last Km".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getLastKm() != null) ? assetDet.getLastKm().toString() : "");
		} else if("Unit Status Desc".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitStatusDesc() != null) ? assetDet.getUnitStatusDesc() : "");
		}
	}
	
	private void createEleventhCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Model Year".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getModelYear() != null) ? assetDet.getModelYear().toString() : "");
		} else if("Old Cat Group Cd".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getOldCatGroupCd() != null) ? assetDet.getOldCatGroupCd() : "");
		} else if("Pfr".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getPfr() != null) ? assetDet.getPfr() : "");
		} else if("Sold Date".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getSoldDate() != null) ? assetDet.getSoldDate() : "");
		}
	}
	
	private void createTwelvethCellForFieldName(Row row, CellStyle cellStyle, AssetSearchResponse assetDet, int cellInd,String fieldName){
		int cellIndex = cellInd;
		if("Unit Refurb Comment".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitRefurbComment() != null) ? assetDet.getUnitRefurbComment() : "");
		} else if("Tsp1".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getTsp1() != null) ? assetDet.getTsp1() : "");
		} else if("Unit New Used Leased Ind".equalsIgnoreCase(fieldName)){
			createCell(row, cellStyle, cellIndex, (assetDet.getUnitNewUsedLeasedInd() != null) ? assetDet.getUnitNewUsedLeasedInd() : "");
		}
	}
	
	private void createCell(Row row, CellStyle cellStyle, int cellIndex, String cellValue){
		Cell cell = row.createCell(cellIndex);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(cellValue);
	}
	
	private void createHeaderRow(Sheet sheet,List<String> headerList) {
	    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
	    Font font = sheet.getWorkbook().createFont();
	    font.setBold(true);
	    font.setFontName("Trebuchet MS");
	    font.setFontHeightInPoints((short) 10);
	    cellStyle.setFont(font);
	    cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
	    cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
	    cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
	      
	    Row row = sheet.createRow(0);
	    int cellIndex = 0;
	    for(String headerVal : headerList){
	    	sheet.setColumnWidth(cellIndex, 6000);
	    	Cell cell1 = row.createCell(cellIndex++);
		    cell1.setCellStyle(cellStyle);
		    cell1.setCellValue(headerVal);
        }
	}
	
	private List<String> addHeaders() {
		List<String> headerList = new ArrayList<>();
		headerList.add("Unit No");
		headerList.add("License No");
		headerList.add("Operational Status");
		headerList.add("In Out Status");
		headerList.add("Physical Company No");
		headerList.add("Responsible Company No");
		headerList.add("Last Park Loc Code");
		headerList.add("Last Intch Key");
		headerList.add("Country");
		headerList.add("Category Group Code");
		headerList.add("Rag Status");
		headerList.add("Physical Branch No");
		headerList.add("Responsible Branch No");
		headerList.add("Serial No");
		headerList.add("Manufacturer");
		headerList.add("Available For Sale");
		headerList.add("Customer Asset No");
		headerList.add("Customer Name");
		headerList.add("Licence Country Name");
		return headerList;
	}
}
