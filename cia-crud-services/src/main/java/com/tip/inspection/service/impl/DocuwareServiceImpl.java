package com.tip.inspection.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.docuware.japi.client.IJapiClient;
import com.docuware.japi.client.Japi;
import com.docuware.japi.document.IKeywordContent;
import com.docuware.japi.document.IMetaData;
import com.docuware.japi.document.IMetaDataContent;
import com.docuware.japi.document.KeywordContent;
import com.docuware.japi.document.MetaData;
import com.docuware.japi.document.MetaDataContent;
import com.docuware.japi.exception.JapiFileCabinetException;
import com.docuware.japi.exception.JapiMetaDataValidationException;
import com.docuware.japi.exception.JapiServiceException;
import com.docuware.japi.filecabinet.Field;
import com.docuware.japi.filecabinet.FieldType;
import com.docuware.japi.filecabinet.IField;
import com.docuware.japi.filecabinet.IFileCabinet;
import com.tip.inspection.main.DatatypeCommonUtility;
import com.tip.inspection.model.CiaInspectionDocuware;
import com.tip.inspection.model.DocuwareDetails;
import com.tip.inspection.service.DocuwareService;
import com.tip.inspection.service.GetDocuwareDetailsService;
import com.tip.inspection.service.SaveDocuwareDataService;

@Service
public class DocuwareServiceImpl implements DocuwareService{
	
	static IJapiClient client = null;
	static IFileCabinet fileCabinet = null;
    static Connection con = null;   
    
	 @Autowired
	GetDocuwareDetailsService docuDetails;
	
	 @Autowired
	 SaveDocuwareDataService saveDocuwareDataService;
	        
	@Override
	public int getDocuwareId(Map<String, String> newMap,File file) {
		
		String inspId=null ,inspType=null ,inspDate = null, inspLocation=null,inspLocLongitude=null,inspLocLatitude=null,inspLocAltitude=null,inspDriverCompany=null,inspRemarks=null,userEmail=null,timeZoneOffset=null,docstatus=null,appCd="CIA";
    	String customerNr=null, unitNr=null , docName= null;
    	String errorCd = null;
    	int docId = -1;
    	for(Map.Entry<String, String> map : newMap.entrySet()){
    		if(map.getKey().equals("Insp_Id")){
    			inspId = map.getValue();
    		} else if(map.getKey().equals("Insp_Type")){
    			inspType = map.getValue();
    		}else if(map.getKey().equals("Insp_Date")){
    			inspDate = map.getValue();
    		}else if(map.getKey().equals("Unit_Nr")){
    			unitNr = map.getValue();
    		}else if(map.getKey().equals("customerNr")){
    			customerNr = map.getValue();
    		}else if(map.getKey().equals("Location")){
    			inspLocation = map.getValue();
    		}else if(map.getKey().equals("Insp_Loc_Longitude")){
    			inspLocLongitude = map.getValue();
    		}else if(map.getKey().equals("Insp_Loc_Latitude")){
    			inspLocLatitude = map.getValue();
    		}else if(map.getKey().equals("Insp_Driver_Company")){
    			inspDriverCompany = map.getValue();
    		}else if(map.getKey().equals("User_Email")){
    			userEmail = map.getValue();
    		}else if(map.getKey().equals("docname")){
    			docName = map.getValue();
    		}

    	}
    	inspDate = DatatypeCommonUtility.convertStringDateTimeToStringMMDDYYYY(inspDate);
    	try {
    		DocuwareDetails dd = docuDetails.getDocuwareDetails(appCd);
    		client = Japi.newClient(dd.getDocuwareIp().trim(), Integer.valueOf(dd.getDocuwarePort().trim()));
    		client.login(dd.getDocUser().trim(), dd.getDocPwd().trim(), dd.getDocOrg().trim());
    		fileCabinet = client.getFileCabinetByName(dd.getDocuCabinate());
    		
    		IField[] f = fileCabinet.getAllFields();
    		System.out.println("fields :" + f);
    		for (int i = 0; i < f.length; i++) {
    			System.out.println(" lable @@@@@@" + f[i].getLabel() + "Type :" + f[i].getType());
    		}

    		File convFile = new File(file.getAbsolutePath());
    		convFile.createNewFile();
    		FileOutputStream fos = new FileOutputStream(convFile);
    		byte[] bytesArray = new byte[(int) file.length()];
    		fos.write(bytesArray);
    		fos.close();

    		File[] files = new File[] { convFile };

    		IMetaDataContent metaDataContent1 = new MetaDataContent();
    		metaDataContent1.setField(new Field("INSP_ID", FieldType.TEXT));
    		metaDataContent1.setValue(DatatypeCommonUtility.checkNullObject(inspId));

    		IMetaDataContent metaDataContent2 = new MetaDataContent();
    		metaDataContent2.setField(new Field("INSP_TYPE", FieldType.TEXT));
    		metaDataContent2.setValue(DatatypeCommonUtility.checkNullObject(inspType));

    		IMetaDataContent metaDataContent3 = new MetaDataContent();
    		metaDataContent3.setField(new Field("CUSTOMER_NR", FieldType.TEXT));
    		metaDataContent3.setValue(DatatypeCommonUtility.checkNullObject((customerNr)));

    		IMetaDataContent metaDataContent4 = new MetaDataContent();
    		metaDataContent4.setField(new Field("UNIT_NR", FieldType.TEXT));
    		metaDataContent4.setValue(DatatypeCommonUtility.checkNullObject((unitNr)));

    		IMetaDataContent metaDataContent5 = new MetaDataContent();
    		metaDataContent5.setField(new Field("INSP_DATE", FieldType.DATE));
    		metaDataContent5.setValue(DatatypeCommonUtility.checkNullObject(inspDate));
    		if (DatatypeCommonUtility.checkNullObject(inspDate).equalsIgnoreCase("")) {
    			metaDataContent5.setValue(null);
    		}

    		IMetaDataContent metaDataContent6 = new MetaDataContent();
    		metaDataContent6.setField(new Field("INSP_LOCATION", FieldType.TEXT));
    		metaDataContent6.setValue(DatatypeCommonUtility.checkNullObject(inspLocation));

    		IMetaDataContent metaDataContent7 = new MetaDataContent();
    		metaDataContent7.setField(new Field("INSP_LOC_LONGITUDE", FieldType.TEXT));
    		metaDataContent7.setValue(DatatypeCommonUtility.checkNullObject((inspLocLongitude)));

    		IMetaDataContent metaDataContent8 = new MetaDataContent();
    		metaDataContent8.setField(new Field("INSP_LOC_LATITUDE", FieldType.TEXT));
    		metaDataContent8.setValue(DatatypeCommonUtility.checkNullObject((inspLocLatitude)));

    		IMetaDataContent metaDataContent9 = new MetaDataContent();
    		metaDataContent9.setField(new Field("INSP_LOC_ALTITUDE", FieldType.TEXT));
    		metaDataContent8.setValue(DatatypeCommonUtility.checkNullObject(inspLocAltitude));

    		IMetaDataContent metaDataContent10 = new MetaDataContent();
    		metaDataContent10.setField(new Field("INSP_DRIVER_COMPANY", FieldType.TEXT));
    		metaDataContent10.setValue(DatatypeCommonUtility.checkNullObject(inspDriverCompany));

    		IMetaDataContent metaDataContent11 = new MetaDataContent();
    		metaDataContent11.setField(new Field("INSP_REMARKS", FieldType.TEXT));
    		metaDataContent11.setValue(DatatypeCommonUtility.checkNullObject(inspRemarks));

    		IMetaDataContent metaDataContent12 = new MetaDataContent();
    		metaDataContent12.setField(new Field("USER_EMAIL", FieldType.TEXT));
    		metaDataContent12.setValue(userEmail);

    		IMetaDataContent metaDataContent13 = new MetaDataContent();
    		metaDataContent13.setField(new Field("TIME_ZONE_OFFSET", FieldType.TEXT));
    		metaDataContent13.setValue(timeZoneOffset);

    		IMetaDataContent metaDataContent14 = new MetaDataContent();
    		metaDataContent14.setField(new Field("DOC_NAME", FieldType.TEXT));
    		metaDataContent14.setValue(docName);
    		
    		
    		IMetaDataContent metaDataContent15 = new MetaDataContent();
    		metaDataContent15.setField(new Field("DOC_STATUS", FieldType.TEXT));
    		if(DatatypeCommonUtility.checkNullObject(docstatus).equalsIgnoreCase("")) {
       			metaDataContent15.setValue(null);	
       		}else {
       		metaDataContent15.setValue((docstatus));
       		}

    		IMetaDataContent[] metaDataContents = new MetaDataContent[15];
    		metaDataContents[0] = metaDataContent1;
    		metaDataContents[1] = metaDataContent2;
    		metaDataContents[2] = metaDataContent3;
    		metaDataContents[3] = metaDataContent4;
    		metaDataContents[4] = metaDataContent5;
    		metaDataContents[5] = metaDataContent6;
    		metaDataContents[6] = metaDataContent7;
    		metaDataContents[7] = metaDataContent8;
    		metaDataContents[8] = metaDataContent9;
    		metaDataContents[9] = metaDataContent10;
    		metaDataContents[10] = metaDataContent11;
    		metaDataContents[11] = metaDataContent12;
    		metaDataContents[12] = metaDataContent13;
    		metaDataContents[13] = metaDataContent14;
    		metaDataContents[14] = metaDataContent15;
    		IKeywordContent[] keywordContents = new KeywordContent[1];
    		
       		IKeywordContent keywordContent = new KeywordContent(new Field("INSP_ID", FieldType.KEYWORD)
       		, new String[]{new String(inspId)});
       		keywordContents[0] = keywordContent;
       		

    		IMetaData metaData = new MetaData(metaDataContents, keywordContents);
    		docId = fileCabinet.storeDocument(files, metaData);
    		System.out.println("Document Id :" + docId);
    		if (docId != -1) {
    			try {
    				CiaInspectionDocuware docInput = new CiaInspectionDocuware();
    				docInput.setAppCd(appCd);
    				docInput.setDocname(docName);
    				docInput.setDocuwarreDocId(docId);
    				docInput.setInspDate(inspDate);
    				docInput.setInspDriverCompany(inspDriverCompany);
    				docInput.setInspId((inspId != null) ? new BigDecimal(inspId) : null);
    				docInput.setInspLocAltitude((inspLocAltitude != null) ? new BigDecimal(inspLocAltitude) : null);
    				docInput.setInspLocation(inspLocation);
    				docInput.setInspLocLatitude((inspLocLatitude != null) ? new BigDecimal(inspLocLatitude) : null);
    				docInput.setInspLocLongitude((inspLocLongitude != null) ? new BigDecimal(inspLocLongitude) : null);
    				docInput.setInspRemarks(inspRemarks);
    				docInput.setInspType(inspType);
    				docInput.setTimeZoneOffset(timeZoneOffset);
    				docInput.setUnitNr(Integer.parseInt(unitNr));
    				docInput.setUserEmail(userEmail);
        			docInput.setDocstatus(docstatus);
        			docInput.setCustomerNr((customerNr != null) ? new BigDecimal(customerNr) : null);
    				errorCd = saveDocuwareDataService.insertDocumentData(docInput);
    			/*	arrayToJson = new ObjectMapper().writeValueAsString(errorCd);
    				arrayToJson 
    				System.out.println("Response :" + arrayToJson);*/
    			} catch (NumberFormatException e) {
    				e.printStackTrace();
    			} catch (Exception e) {

    			}
    		}
    	} catch (JapiFileCabinetException e) {
    		e.printStackTrace();
    	} catch (JapiServiceException e) {
    		e.printStackTrace();
    	} catch (JapiMetaDataValidationException e) {
    		e.printStackTrace();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return docId;
    
	}

}
