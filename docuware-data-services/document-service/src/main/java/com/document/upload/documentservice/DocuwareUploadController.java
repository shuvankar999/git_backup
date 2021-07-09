package com.document.upload.documentservice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.document.upload.documentservice.dao.DaoService;
import com.document.upload.documentservice.dao.GetDocuwareDetails;
import com.document.upload.documentservice.dao.UpdateDocData;
import com.document.upload.documentservice.data.CommonUtil;
import com.document.upload.documentservice.data.DocuwareDetails;
import com.document.upload.documentservice.data.DocuwareUtil;
import com.document.upload.documentservice.data.DownloadDocumentInput;
import com.document.upload.documentservice.data.DownloadDocumentResponse;
import com.document.upload.documentservice.data.SupplierDocUploadInput;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@ComponentScan
public class DocuwareUploadController {
	
	static IJapiClient client = null;
	static IFileCabinet fileCabinet = null;
    static Connection con = null;   
    
    @Autowired
	DaoService documentService;
    
    @Autowired
	DocuwareUtil docuUtil;
    
    @Autowired
    UpdateDocData updateDataService;
    
    @Autowired
	  GetDocuwareDetails docuDetails;
	
    
    @SuppressWarnings("deprecation")
   	@RequestMapping(value ="/service/documentservice/1.0/uploadDocument", method = RequestMethod.POST)
   		public String uploadDocToDocuware(@RequestParam("file") MultipartFile file,@RequestParam("SupplierId") String supplierId,
   				@RequestParam("DocType") String docType,
   				@RequestParam("InsuranceTypeId") String insuranceTypeId,
   				@RequestParam("InsuranceTypedesc") String insuranceTypedesc,
   				@RequestParam("InsuranceAmount") String insuranceAmount,
   				@RequestParam("ExpiryDate") String expiryDate,
   				@RequestParam("ContractType") String contractType,
   				@RequestParam("SelfApprovalLimit") String selfApprovalLimit,
   				@RequestParam("ContractStartDate") String contractStartDate,
   				@RequestParam("ContractExpireDate") String contractExpireDate,
   				@RequestParam("DocName") String docName,
   				@RequestParam("DocStatus") String docStatus,
   				@RequestParam("FullText") String fullText,
   				@RequestParam("UserId") String user,
   				@RequestParam("CreateDate") String createDate,
   				@RequestParam("MaintDate") String maintDate,
   				@RequestParam("MaintUser") String maintUser,
   				@RequestParam("AppCd") String appCd
   				) {
    	
    	
    	String arrayToJson = null;
    	String errorCd=null;
    //	String fileName=null;
   			int docId = -1;
   			Date contractStartDateNew=null;
   			Date contractExpireDateNew=null;
   			try {
   				
   				DocuwareDetails dd=docuDetails.getDocuwareDetails(appCd);
   				if(docType.equalsIgnoreCase("INSURANCE")) {
   					contractType="";
   					selfApprovalLimit="0";
   				 SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
   			    Date now = new Date();
   			    String strDate = sdfDate.format(now);
   			    System.out.println("start date :"+strDate);
   			    System.out.println("converted date :"+CommonUtil.convertStringDateTimeToStringMMDDYYYY(strDate));
   					System.out.println("new date string :"+new Date().toString());
   					contractStartDate=CommonUtil.convertStringDateTimeToStringMMDDYYYY(strDate);
   					System.out.println("contract start date :"+contractStartDate);
   					contractStartDateNew=new Date();
   					contractExpireDate=CommonUtil.convertStringDateTimeToStringMMDDYYYY(strDate);
   					System.out.println("contract end date :"+contractExpireDate);
   					contractExpireDateNew=new Date();
   				}
   				if(docType.equalsIgnoreCase("CONTRACT")) {
   					insuranceTypeId="0";
   					insuranceTypedesc=" ";
   				}
   				
   				
   				System.out.println("inside the methode");
   				
   		//	client = Japi.newClient("10.236.185.235".toString(),Integer.valueOf("9007"));
   				client = Japi.newClient(dd.getDocuwareIp().trim(),Integer.valueOf(dd.getDocuwarePort().trim()));
   		//	client.login("myintell_cust".toString(),"pa55word".toString(),"TIP Trailer Services Management B.V.".toString());
   				client.login(dd.getDocUser().trim(),dd.getDocPwd().trim(),dd.getDocOrg().trim());
   		//	client.login("DocuwareDevAdmin".toString(),"Pa55word".toString(),"TIP Trailer Services Management B.V.".toString());
   			fileCabinet = client.getFileCabinetByName(dd.getDocuCabinate());
   			IField [] f = fileCabinet.getAllFields();
   			System.out.println("fields :"+f);
   			for (int i = 0; i < f.length; i++) {
   				System.out.println(" lable @@@@@@"+f[i].getLabel()+"Type :"+f[i].getType());
   			}
   			
//   		  fileName = file.getOriginalFilename();
//          byte[] bytes = file.getBytes();
//          BufferedOutputStream buffStream =
//                  new BufferedOutputStream(new FileOutputStream(new File(fileName)));
//          buffStream.write(bytes);
//          buffStream.close();
          
   		 File convFile = new File(file.getOriginalFilename());
   	    convFile.createNewFile(); 
   	    FileOutputStream fos = new FileOutputStream(convFile); 
   	    fos.write(file.getBytes());
   	    fos.close(); 
   	    
//          File convFile = new File(file.getOriginalFilename());
//          file.transferTo(convFile);
        //  return convFile;
          
   		// define the files to be stored
   		//	File fileNew=new File(fileName);
   		File[] files = new File[]{convFile};
   		
   		System.out.println("Supplier Id"+supplierId+"*docType*"+docType+"*insurance Type Id*"+insuranceTypeId+"*Insurance Type desc*"+insuranceTypedesc+"*insurance amount*"+insuranceAmount+"*expiry Date*"+
   				expiryDate+"*contract Type*"+contractType+"*Self approval limit*"+selfApprovalLimit+"*contract start date*"+contractStartDate+"*contract expiry date*"+contractExpireDate+"*full text*"+fullText+"*doc status*"+docStatus+"*doc Name*"+docName+"**");
   		// define the meta data to be stored for the files
   		IMetaDataContent metaDataContent1 = new MetaDataContent();
   		metaDataContent1.setField(new Field("SUPPLIER_ID", FieldType.TEXT));
   		
   		metaDataContent1.setValue(CommonUtil.checkNullObject(supplierId));
   		
   		IMetaDataContent metaDataContent2 = new MetaDataContent();
   		metaDataContent2.setField(new Field("DOC_TYPE", FieldType.TEXT));
   		metaDataContent2.setValue(CommonUtil.checkNullObject(docType));
   		
   		IMetaDataContent metaDataContent3 = new MetaDataContent();
   		metaDataContent3.setField(new Field("INSURANCE_TYPE_ID", FieldType.TEXT));
   		metaDataContent3.setValue(CommonUtil.checkNullObject(insuranceTypeId));
   		
   		IMetaDataContent metaDataContent4 = new MetaDataContent();
   		metaDataContent4.setField(new Field("INSURANCE_TYPE_DESC", FieldType.TEXT));
   		metaDataContent4.setValue(CommonUtil.checkNullObject(insuranceTypedesc));
   		
   		IMetaDataContent metaDataContent5 = new MetaDataContent();
   		metaDataContent5.setField(new Field("INSURED_AMOUNT", FieldType.TEXT));
   		metaDataContent5.setValue(CommonUtil.checkNullObject(insuranceAmount));
   		
   		IMetaDataContent metaDataContent6 = new MetaDataContent();
   		metaDataContent6.setField(new Field("EXPIRY_DATE", FieldType.DATE));
   		if(CommonUtil.checkNullObject(expiryDate).equalsIgnoreCase("")) {
   			metaDataContent6.setValue(null);	
   		}else {
   		metaDataContent6.setValue(new Date((expiryDate)));
   		}
   		IMetaDataContent metaDataContent7 = new MetaDataContent();
   		metaDataContent7.setField(new Field("CONTRACT_TYPE", FieldType.TEXT));
   		metaDataContent7.setValue(CommonUtil.checkNullObject(contractType));
   		
   		IMetaDataContent metaDataContent8 = new MetaDataContent();
   		metaDataContent8.setField(new Field("SELF_APPROVAL_LIMIT", FieldType.TEXT));
   		metaDataContent8.setValue(CommonUtil.checkNullObject(selfApprovalLimit));
   		
   		IMetaDataContent metaDataContent9 = new MetaDataContent();
   		metaDataContent9.setField(new Field("CONTRACT_START_DATE", FieldType.DATE));
   		if(CommonUtil.checkNullObject(contractStartDate).equalsIgnoreCase("")) {
   			metaDataContent9.setValue(null);	
   		}else {
   			
   		metaDataContent9.setValue(contractStartDateNew);
   		}
   		
   	//	metaDataContent9.setValue(new Date(CommonUtil.checkNullObject(contractStartDate)));
   		
   		IMetaDataContent metaDataContent10 = new MetaDataContent();
   		metaDataContent10.setField(new Field("CONTRACT_EXPIRE_DATE", FieldType.DATE));
   		if(CommonUtil.checkNullObject(contractExpireDate).equalsIgnoreCase("")) {
   			metaDataContent10.setValue(null);	
   		}else {
   		metaDataContent10.setValue(contractExpireDateNew);
   		}
   	//	metaDataContent10.setValue(new Date(CommonUtil.checkNullObject(contractExpireDate)));
   		
   		IMetaDataContent metaDataContent11 = new MetaDataContent();
   		metaDataContent11.setField(new Field("DOC_NAME", FieldType.TEXT));
   		metaDataContent11.setValue(CommonUtil.checkNullObject(docName));
//   		
   		IMetaDataContent metaDataContent12 = new MetaDataContent();
   		metaDataContent12.setField(new Field("DOC_STATUS", FieldType.TEXT));
   		metaDataContent12.setValue(docStatus);
   		
   		IMetaDataContent metaDataContent13 = new MetaDataContent();
   		metaDataContent13.setField(new Field("Fulltext", FieldType.TEXT));
   		metaDataContent13.setValue(fullText);
   		
   		IMetaDataContent metaDataContent14 = new MetaDataContent();
   		metaDataContent14.setField(new Field("CREATE_DATE", FieldType.DATE));
   	
   		if(CommonUtil.checkNullObject(createDate).equalsIgnoreCase("")) {
   			metaDataContent14.setValue(null);	
   		}else {
   		metaDataContent14.setValue(new Date((createDate)));
   		}
   		
   		//metaDataContent14.setValue(new Date(createDate));
   		
   		IMetaDataContent metaDataContent15 = new MetaDataContent();
   		metaDataContent15.setField(new Field("MAINT_DATE DATETIME", FieldType.DATE));
   		if(CommonUtil.checkNullObject(maintDate).equalsIgnoreCase("")) {
   			metaDataContent15.setValue(null);	
   		}else {
   		metaDataContent15.setValue(new Date((maintDate)));
   		}
   	//	metaDataContent15.setValue(new Date(maintDate));
   		
   		IMetaDataContent metaDataContent16 = new MetaDataContent();
   		metaDataContent16.setField(new Field("MAINT_USER", FieldType.TEXT));
   		metaDataContent16.setValue(maintUser);
   		
   		IMetaDataContent metaDataContent17 = new MetaDataContent();
   		metaDataContent17.setField(new Field("CREATE_USER", FieldType.TEXT));
   		metaDataContent17.setValue(user);

   		IMetaDataContent[] metaDataContents = new MetaDataContent[17];
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
   		metaDataContents[15] = metaDataContent16;
   		metaDataContents[16] = metaDataContent17;
   		IKeywordContent[] keywordContents = new KeywordContent[1];
   		IKeywordContent keywordContent = new KeywordContent(new Field("SUPPLIER_ID", FieldType.KEYWORD)
   		, new String[]{new String(supplierId)});
   		keywordContents[0] = keywordContent;
   		

   		IMetaData metaData = new MetaData(metaDataContents, keywordContents);
   		
   			
   			docId = fileCabinet.storeDocument(files, metaData);		
   			System.out.println("Document Id :"+docId);
   			if(docId !=-1)
   			{
   				try {
   					SupplierDocUploadInput docInput=new SupplierDocUploadInput();
   			    	docInput.setSupplierId(supplierId);
   			    	docInput.setDocType(docType);
   			    	docInput.setInsuranceTypeId(insuranceTypeId);
   			    	docInput.setInsuranceTypedesc(insuranceTypedesc);
   			    	docInput.setInsuranceAmount(insuranceAmount);
   			    	docInput.setExpiryDate(expiryDate);
   			    	docInput.setContractType(contractType);
   			    	docInput.setSelfApprovalLimit(selfApprovalLimit);
   			    	docInput.setContractStartDate(contractStartDate);
   			    	docInput.setContractExpireDate(contractExpireDate);
   			    	docInput.setDocName(docName);
   			    	docInput.setDocStatus(docStatus);
   			    	docInput.setDocId(String.valueOf(docId));
   			    	docInput.setUserId(user);
   			    	
   					errorCd=documentService.insertDocumentData(docInput);
   				 arrayToJson = new ObjectMapper()
   						.writeValueAsString(errorCd);
   				 System.out.println("Response :"+arrayToJson);
   				} catch (NumberFormatException e) {
   					// TODO Auto-generated catch block
   					e.printStackTrace();
   				} catch(Exception e) {
   					
   				}
   			}
   		} catch (JapiFileCabinetException e) {
   			e.printStackTrace();
   		} catch (JapiServiceException e) {
   			e.printStackTrace(); 
   		} catch (JapiMetaDataValidationException e) {
   			e.printStackTrace();
   		}catch(Exception e) {
   			e.printStackTrace();
   		}
   		return arrayToJson;
   	}
    
    @SuppressWarnings("deprecation")
   	@RequestMapping(value ="/service/documentservice/1.0/updateDocumentData", method = RequestMethod.POST)
   		public String uploadDocToDocuware(@RequestBody final SupplierDocUploadInput updateDocument) {
    	String errorCd=null;
    	String arrayToJson = null;
    	try {
    		
    	errorCd=documentService.insertDocumentData(updateDocument);
    	arrayToJson=new ObjectMapper()
					.writeValueAsString(errorCd);
			 System.out.println("Response :"+arrayToJson);
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return arrayToJson;
    }
    
    
    
    @RequestMapping(value ="/service/documentservice/1.0/getDocument", method = RequestMethod.POST)
	public void  getDocument(@RequestBody final DownloadDocumentInput downloadDocInput,HttpServletResponse response) {
		try {
			
		DownloadDocumentResponse responseObject = docuUtil.getPDFDocument(downloadDocInput);
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=" + responseObject.getFile().getName());
		InputStream targetStream = new FileInputStream(responseObject.getFile());
			IOUtils.copy(targetStream, response.getOutputStream());

        responseObject.getOutputStream().close();
        targetStream.close();
		response.getOutputStream().close();
		 } catch (IOException e) {
			 e.printStackTrace();
	        	//logger.error("Error Encountered In Download Asset Details" , e);
	        }catch(Exception e){
	        	e.printStackTrace();
	        	//logger.error("Error Encountered In Download Asset Details" , e);
	        }
	}
 
}


