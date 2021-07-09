package com.tip.inspection.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.inspection.model.BaseModel;
import com.tip.inspection.model.ImageDetails;
import com.tip.inspection.model.InspectionDamageData;
import com.tip.inspection.model.InspectionItems;
import com.tip.inspection.model.ResponseObject;
import com.tip.inspection.repository.CiaGenerateDocumentRepository;
import com.tip.inspection.service.CiaGenerateDocumentService;
import com.tip.inspection.util.CiaReportUtil;

@Service
public class CiaGenerateDocumentServiceImpl implements CiaGenerateDocumentService{
	
	@Autowired
	CiaGenerateDocumentRepository ciaGenerateDocumentRepository;
	
	@Autowired
	CiaReportUtil ciaReportUtil;
	
	@SuppressWarnings({ "unused", "rawtypes" })
	@Override
	public ResponseObject generateDocument(String inspType ,String inspId) {
		ResponseObject res = new ResponseObject();
		BaseModel bm = new BaseModel();
		Map<String,Object> resultMap = ciaGenerateDocumentRepository.getDocumentData(new BigDecimal(inspId));
		List InspectionDetails = (List) resultMap.get("InspectionDetailsData");
		List InspectionItems = (List) resultMap.get("InspectionItemsList");
		List InspectionDamageData = (List) resultMap.get("InspectionDamageDataList");
		List ImageDetails = (List) resultMap.get("InspectionImageDataList");
		List Status = (List) resultMap.get("status");
		Map<String,Map<String,String>> map = setHeaderDetailsData(bm, InspectionDetails);
		
		/*setImageDetailsData(bm, InspectionItems);
		setImageDamageData(bm,InspectionDamageData);
		setImageDetails(bm,ImageDetails);
		bm.setInspType(inspType);
		bm.setInspId(inspId);*/
		
		res = ciaReportUtil.createPdf(map,inspType,inspId);
		
		for(Map.Entry<String,Map<String,String>> entry : map.entrySet()){
			if(entry.getKey().equals("docuwareInput")){
				Map<String,String> newMap = new HashMap<>();
				Map<String,String> docmap = entry.getValue();
				res.setDocuwareInput(newMap);
				res.getDocuwareInput().putAll(docmap);
			}
		}
		
		return res;
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private void setImageDetails(BaseModel bm, List imageDetails) {
		List<ImageDetails> list = new ArrayList<>();
		for(Object mapObj : imageDetails) {
			Map<String, Object> imageDetailsDataMap = (Map<String, Object>) mapObj;
			ImageDetails imageDetailsObj = new ImageDetails();
			imageDetailsObj.setInspCd((String) imageDetailsDataMap.get("Insp_Cd"));
		//	imageDetailsObj.setDamageLineNr(imageDetailsDataMap.get("Damage_Line_Nr")!=null ? Integer.parseInt((String) imageDetailsDataMap.get("Damage_Line_Nr")) : null);
			imageDetailsObj.setDamageLineNr((Integer)imageDetailsDataMap.get("Damage_Line_Nr"));
			imageDetailsObj.setDamageDescription((String) imageDetailsDataMap.get("Damage_Description"));
			imageDetailsObj.setImgType((String) imageDetailsDataMap.get("Img_Type"));
			imageDetailsObj.setImgName((String) imageDetailsDataMap.get("Img_Name"));
			imageDetailsObj.setImgComment((String) imageDetailsDataMap.get("Img_Comment"));
	//		imageDetailsObj.setImgCount(imageDetailsDataMap.get("Img_Count")!=null ? Integer.parseInt((String) imageDetailsDataMap.get("Img_Count")) : null);
			imageDetailsObj.setImgCount((Integer)imageDetailsDataMap.get("Img_Count"));
			imageDetailsObj.setImgPath((String) imageDetailsDataMap.get("Img_Path"));
			list.add(imageDetailsObj);
			}
		bm.getListOfImages().addAll(list);
	
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private void setImageDamageData(BaseModel bm, List inspectionDamageData) {
		List<InspectionDamageData> list = new ArrayList<>();
		for(Object mapObj : inspectionDamageData) {
			Map<String, Object> inspectionDamageDataMap = (Map<String, Object>) mapObj;
			InspectionDamageData damageDataObj = new InspectionDamageData();
		//	damageDataObj.setDamageLineNr(inspectionDamageDataMap.get("Damage_Line_Nr")!=null ? Integer.parseInt((String) inspectionDamageDataMap.get("Damage_Line_Nr")) : null);
			damageDataObj.setDamageLineNr((Integer)inspectionDamageDataMap.get("Damage_Line_Nr"));
			damageDataObj.setInspCd((String) inspectionDamageDataMap.get("Insp_Cd"));
			damageDataObj.setDNACd((String) inspectionDamageDataMap.get("DNA_Cd"));
			damageDataObj.setDescription((String) inspectionDamageDataMap.get("Description"));
			damageDataObj.setDamageDescription((String) inspectionDamageDataMap.get("Damage_Description"));
			damageDataObj.setDamageType((String) inspectionDamageDataMap.get("Damage_Type"));
			list.add(damageDataObj);
		}
		bm.getListInspectionDamageData().addAll(list);
	}

	@SuppressWarnings({ "unused", "unchecked" })
	private void setImageDetailsData(BaseModel bm, List  inspectionItems ) {
		List<InspectionItems> list = new ArrayList<>();
		for(Object mapObj : inspectionItems) {
			Map<String, Object> imageDetailsDataMap = (Map<String, Object>) mapObj;
			InspectionItems inspectionItemsObj = new InspectionItems();
			inspectionItemsObj.setInspCd((String) imageDetailsDataMap.get("Insp_Cd"));
			inspectionItemsObj.setInspInd((String) imageDetailsDataMap.get("Insp_Ind"));
			inspectionItemsObj.setInspIndDesc((String) imageDetailsDataMap.get("Insp_Ind_Desc"));
			inspectionItemsObj.setInspDesc((String) imageDetailsDataMap.get("Insp_Desc"));
			inspectionItemsObj.setInspComments((String) imageDetailsDataMap.get("Insp_Comments"));
			list.add(inspectionItemsObj);
		}
		bm.getListInspectionItems().addAll(list);
	}

	private Map<String,Map<String,String>> setHeaderDetailsData(BaseModel bm, List inspectionDetails) {
		Map<String,String> customerMap = new HashMap<>();
		Map<String,String> inspectionPerformDetailsMap = new HashMap<>();
		Map<String,String> inspectionPDFDetailsMap = new HashMap<>();
		Map<String,String> assetDetailsMap = new HashMap<>();
		Map<String,Map<String,String>> resultmap = new HashMap<>();
		Map<String,String> docuwareInput = new HashMap<>();
	/*	
		CustomerDetails customerDetails = new CustomerDetails();
		InspectionPerformDetails inspectionPerformDetails = new InspectionPerformDetails();
		InspectionPDFDetails inspectionPDFDetails = new InspectionPDFDetails();
		AssetDetails assetDetails = new AssetDetails();*/
		for(Object mapObj : inspectionDetails) {
			@SuppressWarnings("unchecked")
			Map<String, Object> inspectionDetailsDataMap = (Map<String, Object>) mapObj;
			String inspId = inspectionDetailsDataMap.get("Insp_Id").toString();
			//String inspType = 
			docuwareInput.put("Insp_Id", inspId);
			
			if(Integer.parseInt(inspectionDetailsDataMap.get("Sub_Group_Id").toString()) == 801){
				
				customerMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
				resultmap.put("Customer",customerMap);
				String[] customerNr = (String[]) inspectionDetailsDataMap.get("Item_Value").toString().split(" -");
				docuwareInput.put("CustomerNr", customerNr[0]);
				
				//customerDetails.setTitle((String) inspectionDetailsDataMap.get("Sub_Group_Desc"));
				/*customerDetails.setCustomerNameAndNumberHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
				customerDetails.setCustomerNameAndNumber((String) inspectionDetailsDataMap.get("Item_Value"));*/
				
			}else if(Integer.parseInt(inspectionDetailsDataMap.get("Sub_Group_Id").toString()) == 802){
				//assetDetailsMap.put("Title", (String) inspectionDetailsDataMap.get("Sub_Group_Desc"));
				
				//assetDetails.setTitle((String) inspectionDetailsDataMap.get("Sub_Group_Desc"));
				if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Unit_Nr")){
					
					assetDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"),(String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
				
					/*assetDetails.setEquipmentNumberHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					assetDetails.setEquipmentNumber(inspectionDetailsDataMap.get("Item_Value")!=null ? Integer.parseInt((String) inspectionDetailsDataMap.get("Item_Value")) : null);*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Unit_Cust_Reference")){
					
					assetDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"),(String) inspectionDetailsDataMap.get("Item_Value"));
					
					/*assetDetails.setCustomerEquipmentRefNumberHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					assetDetails.setCustomerEquipmentRefNumber((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Unit_Licence_Nr")){
					
					assetDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"),(String) inspectionDetailsDataMap.get("Item_Value"));
					
					/*assetDetails.setEquipmentLicenseNumberHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					assetDetails.setEquipmentLicenseNumber((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Catgr_Desc")){
					
					assetDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"),(String) inspectionDetailsDataMap.get("Item_Value"));
					
					/*assetDetails.setEquipmentTypeHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					assetDetails.setEquipmentType((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}
				
				resultmap.put((String) inspectionDetailsDataMap.get("Sub_Group_Desc"),assetDetailsMap);
				
			}else if(Integer.parseInt(inspectionDetailsDataMap.get("Sub_Group_Id").toString()) == 804 ){
				//inspectionPerformDetailsMap.put("Title", (String) inspectionDetailsDataMap.get("Sub_Group_Desc"));
				
				//inspectionPerformDetails.setTitle((String) inspectionDetailsDataMap.get("Sub_Group_Desc") );
				if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("User_Forename_Txt")){
					
					inspectionPerformDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
					/*inspectionPerformDetails.setInspectedByHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPerformDetails.setInspectedBy((String) inspectionDetailsDataMap.get("Item_Value"));*/
				} else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("User_Email")){
					
					inspectionPerformDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
					/*inspectionPerformDetails.setEmailAddressHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPerformDetails.setEmailAddress((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Phone_Nr")){
					
					inspectionPerformDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
				/*	inspectionPerformDetails.setPhoneNumberHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPerformDetails.setPhoneNumber((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Location")){
					
					inspectionPerformDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
					/*inspectionPerformDetails.setHomeLocationHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPerformDetails.setHomeLocation((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Insp_Driver_Company")){
					
					inspectionPerformDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
				}
				resultmap.put((String) inspectionDetailsDataMap.get("Sub_Group_Desc"),inspectionPerformDetailsMap);
			}else if(Integer.parseInt(inspectionDetailsDataMap.get("Sub_Group_Id").toString()) == 803 ){
				
			//	inspectionPDFDetailsMap.put("Title", (String) inspectionDetailsDataMap.get("Sub_Group_Desc"));
				
			//	inspectionPDFDetails.setTitle((String) inspectionDetailsDataMap.get("Sub_Group_Desc"));
				if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Insp_Date")){
					
					inspectionPDFDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
				/*	inspectionPDFDetails.setInspectedDateHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPDFDetails.setInspectedDate((String) inspectionDetailsDataMap.get("Item_Value"));*/
				} else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Insp_Type")){
				//	docuwareInput.put("docname", "CIAR_"+(String) inspectionDetailsDataMap.get("Item_Value")+"_"+inspId+".pdf");
					inspectionPDFDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
				/*	inspectionPDFDetails.setInspectedTypeHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPDFDetails.setInspectedType((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Insp_Location")){
					
					inspectionPDFDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
					
					/*inspectionPDFDetails.setInspectedLocationHeader((String) inspectionDetailsDataMap.get("Item_Desc"));
					inspectionPDFDetails.setInspectedLocation((String) inspectionDetailsDataMap.get("Item_Value"));*/
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Insp_Loc_Longitude")){
					
					inspectionPDFDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					
				}else if(inspectionDetailsDataMap.get("Item_Short_Desc").equals("Insp_Loc_Latitude")){
					
					inspectionPDFDetailsMap.put((String) inspectionDetailsDataMap.get("Item_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
					docuwareInput.put((String) inspectionDetailsDataMap.get("Item_Short_Desc"), (String) inspectionDetailsDataMap.get("Item_Value"));
		
				}
			}
			resultmap.put((String) inspectionDetailsDataMap.get("Sub_Group_Desc"),inspectionPDFDetailsMap);
			resultmap.put("docuwareInput",docuwareInput);
		}
		/*bm.setAssetDetails(assetDetails);
		bm.setCustomerDetails(customerDetails);
		bm.setInspectionDetails(inspectionPDFDetails);
		bm.setInspectionPerformedBy(inspectionPerformDetails);*/
		//res.getDocuwareInput()
		return resultmap;
	}

}
