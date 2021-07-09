package com.tip.suppliermasterdata.service.impl;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.suppliermasterdata.model.AllProcedureDataResponse;
import com.tip.suppliermasterdata.model.BranchSettings;
import com.tip.suppliermasterdata.model.CompanyBranchData;
import com.tip.suppliermasterdata.model.CountryDetails;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataResponse;
import com.tip.suppliermasterdata.model.LanguageDetails;
import com.tip.suppliermasterdata.model.MasterDataDynamicResponse;
import com.tip.suppliermasterdata.model.MsuCapabilities;
import com.tip.suppliermasterdata.model.SupplierCapMotOptions;
import com.tip.suppliermasterdata.model.SupplierContract;
import com.tip.suppliermasterdata.model.SupplierCurrency;
import com.tip.suppliermasterdata.model.SupplierDocInsuStatus;
import com.tip.suppliermasterdata.model.SupplierStatus;
import com.tip.suppliermasterdata.model.SupplierTIPTankerData;
import com.tip.suppliermasterdata.model.VendorPriority;
import com.tip.suppliermasterdata.repository.FetchAllProceduresListRepository;
import com.tip.suppliermasterdata.repository.FetchDynamicMasterDataRepository;
import com.tip.suppliermasterdata.service.FetchDynamicMasterDataService;
import com.tip.suppliermasterdata.service.SupplierMasterDataFiltersService;

@Service
@Transactional
public class FetchDynamicMasterDataServiceImpl implements FetchDynamicMasterDataService {

    @Autowired
    FetchAllProceduresListRepository fetchAllProceduresListRepository;
    
    @Autowired
    FetchDynamicMasterDataRepository fetchDynamicMasterDataRepository;
    
    @Autowired
    SupplierMasterDataFiltersService supplierMasterDataFiltersService;
    
    public static final String PROC_DATA_KEY = "procDataList";

    static final Logger logger = LoggerFactory.getLogger(FetchDynamicMasterDataServiceImpl.class);
    
    Map<Integer, String> vendorCategoryMap = new HashMap();
    
    @Override
    public MasterDataDynamicResponse getAllProcedures(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest) {
    	FetchDynamicMasterDataResponse lFetchDynamicMasterDataResponse = fetchAllProceduresListRepository.getAllProcedures(fetchDynamicMasterDataRequest);
    	MasterDataDynamicResponse lMasterDataDynamicResponse = new MasterDataDynamicResponse();
    	for(AllProcedureDataResponse allProcedureDataResponse : lFetchDynamicMasterDataResponse.getAllProcedureDataResponseLst()){
    		if(allProcedureDataResponse.getKeyName() != null && allProcedureDataResponse.getProcName() != null){
    			Map<String, Object> procDataMap = fetchDynamicMasterDataRepository.getDynamicMasterData(fetchDynamicMasterDataRequest,allProcedureDataResponse);
    			setObjectLists(allProcedureDataResponse,procDataMap,lMasterDataDynamicResponse);
    		}
    	}
    	if("supplier".equalsIgnoreCase(fetchDynamicMasterDataRequest.getKeyName())) {
        	try {
        		List<String> vendorCategoryDescList = new ArrayList();
        		for(Integer id:fetchDynamicMasterDataRequest.getVendorCategoryIds()) {
        			vendorCategoryDescList.add(vendorCategoryMap.get(id));
        		}
				supplierMasterDataFiltersService.getSupplierElasticData(fetchDynamicMasterDataRequest, lMasterDataDynamicResponse,vendorCategoryDescList);
			} catch (UnknownHostException e) {
				logger.error("An error occured to retrieve the supplier data from elastic db"+e);
			}
    	}
		return lMasterDataDynamicResponse;
    }


	private void setObjectLists(AllProcedureDataResponse allProcedureDataResponse, Map<String, Object> procDataMap,
			MasterDataDynamicResponse lMasterDataDynamicResponse) {
    	if(allProcedureDataResponse.getProcName() != null && "countryDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setCountryDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "languageDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setLanguageDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "currencyList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierCurrencyList(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierStatusList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierStatusCategoryList(procDataMap,lMasterDataDynamicResponse,allProcedureDataResponse.getObjName());
		}else if(allProcedureDataResponse.getProcName() != null && "supplierPriorityList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierPriorityList(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierCategoryList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierStatusCategoryList(procDataMap,lMasterDataDynamicResponse,allProcedureDataResponse.getObjName());
		}else if(allProcedureDataResponse.getProcName() != null && "companyBranchMappingList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setCompanyBranchData(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "branchSettingsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setBranchSettings(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "msuCapabilitiesLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setMsuCapabilities(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierContractTypesLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierContractTypes(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierDocInsuStatusLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierDocInsu(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierCapMOTOptionsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierCapMotOptions(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierTIPTankerIndLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierTanker(procDataMap,lMasterDataDynamicResponse);
		}
	}
    
	   private void setSupplierTanker(Map<String, Object> procDataMap,
			MasterDataDynamicResponse lMasterDataDynamicResponse) {
		   List<SupplierTIPTankerData> supplierTipTankerList = new ArrayList<>();
	    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
	    	for(Map<String, Object> branchMap: lst)
	    	{
	    		SupplierTIPTankerData supplierTIPTankerData = new SupplierTIPTankerData();
	    		supplierTIPTankerData.setTipTankerIndId((Integer)branchMap.get("TIP_Tanker_Ind_Id"));
	    		supplierTIPTankerData.setTipTankerIndDesc((String)branchMap.get("TIP_Tanker_Ind_Desc"));
	    		supplierTipTankerList.add(supplierTIPTankerData);
	    	}
	    	lMasterDataDynamicResponse.getSupplierTIPTankerIndLst().addAll(supplierTipTankerList);	
	}


	private void setBranchSettings(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
	    	List<BranchSettings> branchSettingsLst = new ArrayList<>();
	    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
	    	for(int i=0;i<lst.size();i++)
	    	{
	    		Map<String, Object> branchSettingsMap = lst.get(i);
	    		BranchSettings branchSettings = new BranchSettings();
	    		branchSettings.setBranchNr((Integer)branchSettingsMap.get("Branch_Nr"));
	    		branchSettings.setIntchNr((Integer)branchSettingsMap.get("Intch_Nr"));
	    		branchSettings.setBranchAddress((String)branchSettingsMap.get("Branch_Address"));
	    		branchSettings.setBranchDefCy((String)branchSettingsMap.get("Branch_Def_Cy"));
	    		branchSettings.setBranchDefTaxCd((String)branchSettingsMap.get("Branch_Def_Tax_Cd"));
	    		branchSettings.setBranchDefDueDate((Integer)branchSettingsMap.get("Branch_Def_Due_Date"));
	    		branchSettings.setBranchDefLang((String)branchSettingsMap.get("Branch_Def_Lang"));
	    		if(branchSettingsMap.get("Branch_Active_Startdate") != null)
	    			branchSettings.setBranchActiveStartdate((String)branchSettingsMap.get("Branch_Active_Startdate").toString());
	    		if(branchSettingsMap.get("Branch_Active_Enddate") != null)
	    			branchSettings.setBranchActiveEnddate((String)branchSettingsMap.get("Branch_Active_Enddate").toString());
	    		branchSettings.setBranchDefInvoiceType((String)branchSettingsMap.get("Branch_Def_Invoice_Type"));
	    		branchSettings.setBranchDefInvoiceCycle((String)branchSettingsMap.get("Branch_Def_Invoice_Cycle"));
	    		branchSettings.setBranchDefPayTermCd((String)branchSettingsMap.get("Branch_Def_Pay_Term_Cd"));
	    		branchSettings.setBranchDefApplLang((Integer)branchSettingsMap.get("Branch_Def_Appl_Lang"));
	    		branchSettings.setBranchDefPurchsOption((String)branchSettingsMap.get("Branch_Def_Purchs_Option"));
	    		branchSettings.setBranchDefCPIInd((String)branchSettingsMap.get("Branch_Def_CPI_Ind"));
	    		branchSettings.setBranchDefPeriodCd((String)branchSettingsMap.get("Branch_Def_Period_Cd"));
	    		branchSettings.setBranchDefTermCount((Integer)branchSettingsMap.get("Branch_Def_Term_Count"));
	    		branchSettings.setBranchDefInsrncPlanCd((String)branchSettingsMap.get("Branch_Def_Insrnc_Plan_Cd"));
	    		branchSettings.setBranchDefIntchCopies((Integer)branchSettingsMap.get("Branch_Def_Intch_Copies"));
	    		branchSettings.setBranchDefRateCopies((Integer)branchSettingsMap.get("Branch_Def_Rate_Copies"));
	    		branchSettings.setBranchDefRebillCopies((Integer)branchSettingsMap.get("Branch_Def_Rebill_Copies"));
	    		branchSettings.setBranchDefOtherCopies((Integer)branchSettingsMap.get("Branch_Def_Other_Copies"));
	    		branchSettings.setStopBillingAtContractEnd((String)branchSettingsMap.get("Stop_Billing_At_Contract_End"));
	    		branchSettings.setStopAccrualAtContractEnd((String)branchSettingsMap.get("Stop_Accrual_At_Contract_End"));
	    		branchSettings.setStopClacFCRAtContractEnd((String)branchSettingsMap.get("Stop_Clac_FCR_At_Contract_End"));
	    		branchSettings.setStopAutoCredit((String)branchSettingsMap.get("Stop_Auto_Credit"));
	    		branchSettings.setShowRVColumnInRates((String)branchSettingsMap.get("Show_RV_Column_In_Rates"));
	    		branchSettings.setBranchLogo((String)branchSettingsMap.get("Branch_Logo"));
	    		branchSettings.setDaysToReservationWarning((Integer)branchSettingsMap.get("Days_to_Reservation_Warning"));
	    		branchSettings.setHandheldActivated((String)branchSettingsMap.get("Handheld_Activated"));
	    		branchSettings.setServiceType1((String)branchSettingsMap.get("Service_Type1"));
	    		branchSettings.setServiceType2((String)branchSettingsMap.get("Service_Type2"));
	    		branchSettings.setServiceType3((String)branchSettingsMap.get("Service_Type3"));
	    		branchSettings.setServiceType4((String)branchSettingsMap.get("Service_Type4"));
	    		branchSettings.setIsRefIDActive((String)branchSettingsMap.get("is_RefID_Active"));
	    		branchSettingsLst.add(branchSettings);
	    	}
	    	lMasterDataDynamicResponse.getBranchSettingsLst().addAll(branchSettingsLst);
	    }
	   
    private void setCountryDetails(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<CountryDetails> countryDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lCountryDetailsMap = lst.get(i);
    		CountryDetails lCountryDetails = new CountryDetails();
    		lCountryDetails.setCountryCode((String)lCountryDetailsMap.get("country_code"));
    		lCountryDetails.setCountryDialingCd((String)lCountryDetailsMap.get("country_dialing_cd"));
    		lCountryDetails.setCountryName((String)lCountryDetailsMap.get("country_name"));
    		lCountryDetails.setCountrySkey((Integer)lCountryDetailsMap.get("country_skey"));
    		lCountryDetails.setCurrencyFormat((String)lCountryDetailsMap.get("currency_format"));
    		lCountryDetails.setCurrencyName((String)lCountryDetailsMap.get("currency_name"));
    		lCountryDetails.setPhoneCode((Integer)lCountryDetailsMap.get("phone_code"));
    		lCountryDetails.setPhoneFormat((String)lCountryDetailsMap.get("phone_format"));
    		lCountryDetails.setPostalCodeFormat((String)lCountryDetailsMap.get("postal_code_format"));
    		lCountryDetails.setRegionName((String)lCountryDetailsMap.get("region_name"));
    		lCountryDetails.setStateReqFlag((String)lCountryDetailsMap.get("state_req_flag"));
    		countryDetailsLst.add(lCountryDetails);
    	}
    	lMasterDataDynamicResponse.getCountryDetailsLst().addAll(countryDetailsLst);
    }
    
    private void setLanguageDetails(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<LanguageDetails> languageDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lLanguageDetailsMap = lst.get(i);
    		LanguageDetails lLanguageDetails = new LanguageDetails();
    		lLanguageDetails.setLanguageCd((String)lLanguageDetailsMap.get("Language_Cd"));
    		lLanguageDetails.setLanguageId((Integer)lLanguageDetailsMap.get("Language_Id"));
    		lLanguageDetails.setLanguageName((String)lLanguageDetailsMap.get("Language_Name"));
    		languageDetailsLst.add(lLanguageDetails);
    	}
    	lMasterDataDynamicResponse.getLanguageDetailsLst().addAll(languageDetailsLst);
    }    
    private void setSupplierCurrencyList(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<SupplierCurrency> supplierCurrencyList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> currencyMap: lst)
    	{
    		SupplierCurrency supplierCurrency = new SupplierCurrency();
    		supplierCurrency.setLegacyCurr((String)currencyMap.get("legacy_curr"));
    		supplierCurrency.setCurrCode((String)currencyMap.get("curr_code"));
    		supplierCurrencyList.add(supplierCurrency);
    	}
    	lMasterDataDynamicResponse.getCurrencyList().addAll(supplierCurrencyList);
    }
    private void setSupplierStatusCategoryList(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse, String objName){
    	List<SupplierStatus> supplierList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> statusMap: lst)
    	{
    		SupplierStatus supplierStatus = new SupplierStatus();
    		supplierStatus.setRefId((Integer)statusMap.get("Ref_Id"));
    		supplierStatus.setRefIdDesc((String)statusMap.get("Ref_Id_Desc"));
    		supplierStatus.setRefIdReserved((String)statusMap.get("Ref_Id_Reserved"));
    		supplierStatus.setRefIdDescDisplay((String)statusMap.get("Ref_Id_Desc_Display"));
    		supplierStatus.setTextCd((String)statusMap.get("Text_Cd"));
    		supplierStatus.setEntityCd((String)statusMap.get("Entity_Cd"));
    		supplierList.add(supplierStatus);
    		vendorCategoryMap.put((Integer)statusMap.get("Ref_Id"), (String)statusMap.get("Ref_Id_Desc"));
    	}
    	if("supplierStatusList".equalsIgnoreCase(objName))
    		lMasterDataDynamicResponse.getSupplierStatusList().addAll(supplierList);
    	else if("supplierCategoryList".equalsIgnoreCase(objName))
    		lMasterDataDynamicResponse.getSupplierCategoryList().addAll(supplierList);
    } 
    private void setSupplierPriorityList(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<VendorPriority> supplierPriorityList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> currencyMap: lst)
    	{
    		VendorPriority vendorPriority = new VendorPriority();
    		vendorPriority.setVendorPriority((String)currencyMap.get("Vendor_Priority"));
    		vendorPriority.setVendorPriorityDesc((String)currencyMap.get("Vendor_Priority_Desc"));
    		vendorPriority.setTextCd((String)currencyMap.get("Text_Cd"));
    		vendorPriority.setEntityCd((String)currencyMap.get("Entity_Cd"));
    		supplierPriorityList.add(vendorPriority);
    	}
    	lMasterDataDynamicResponse.getSupplierPriorityList().addAll(supplierPriorityList);
    }
    private void setCompanyBranchData(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<CompanyBranchData> companyBranchDataList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> branchMap: lst)
    	{
    		CompanyBranchData companyBranchData = new CompanyBranchData();
    		companyBranchData.setBranchId((String)branchMap.get("Branch_Id"));
    		companyBranchData.setBranchNr((Integer)branchMap.get("Branch_Nr"));
    		companyBranchData.setBranchName((String)branchMap.get("Branch_Name"));
    		companyBranchData.setEntityId((String)branchMap.get("Entity_Id"));
    		companyBranchData.setCompanyNr((Integer)branchMap.get("Company_Nr"));
    		companyBranchData.setCountryCd((String)branchMap.get("Country_Cd"));
    		companyBranchDataList.add(companyBranchData);
    	}
    	lMasterDataDynamicResponse.getCompanyBranchMappingList().addAll(companyBranchDataList);
    }
    private void setMsuCapabilities(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<MsuCapabilities> msuCapabilitiesList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> branchMap: lst)
    	{
    		MsuCapabilities msuCapabilities = new MsuCapabilities();
    		msuCapabilities.setCapabilityId((Integer)branchMap.get("Capability_Id"));
    		msuCapabilities.setCapabilityDesc((String)branchMap.get("Capability_Desc"));
    		
    		msuCapabilitiesList.add(msuCapabilities);
    	}
    	lMasterDataDynamicResponse.getMsuCapabilitiesList().addAll(msuCapabilitiesList);
    }
    private void setSupplierContractTypes(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<SupplierContract> supplierContractList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> branchMap: lst)
    	{
    		SupplierContract supplierContract = new SupplierContract();
    		supplierContract.setContractType((String)branchMap.get("Contract_Type"));
    		supplierContract.setContractTypeDesc((String)branchMap.get("Contract_Type_Desc"));
    		supplierContractList.add(supplierContract);
    	}
    	lMasterDataDynamicResponse.getSupplierContractTypesList().addAll(supplierContractList);
    }
    private void setSupplierDocInsu(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<SupplierDocInsuStatus> supplierDocInsuList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> branchMap: lst)
    	{
    		SupplierDocInsuStatus supplierDocInsuStatus = new SupplierDocInsuStatus();
    		supplierDocInsuStatus.setStatusId((Integer)branchMap.get("Status_Id"));
    		supplierDocInsuStatus.setStatusDesc((String)branchMap.get("Status_Desc"));
    		supplierDocInsuList.add(supplierDocInsuStatus);
    	}
    	lMasterDataDynamicResponse.getSupplierDocInsuStatusList().addAll(supplierDocInsuList);
    }
    private void setSupplierCapMotOptions(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<SupplierCapMotOptions> supplierCapMotOptionsList = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(Map<String, Object> branchMap: lst)
    	{
    		SupplierCapMotOptions supplierCapMotOptions = new SupplierCapMotOptions();
    		supplierCapMotOptions.setMotInd((Integer)branchMap.get("MOT_Ind"));
    		supplierCapMotOptions.setMotDesc((String)branchMap.get("MOT_Desc"));
    		supplierCapMotOptionsList.add(supplierCapMotOptions);
    	}
    	lMasterDataDynamicResponse.getSupplierCapMotOptionsList().addAll(supplierCapMotOptionsList);
    }
}