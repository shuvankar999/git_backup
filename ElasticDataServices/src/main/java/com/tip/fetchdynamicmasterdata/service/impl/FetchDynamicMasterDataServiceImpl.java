package com.tip.fetchdynamicmasterdata.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.fetchdynamicmasterdata.model.AddressTypeDetails;
import com.tip.fetchdynamicmasterdata.model.AllProcedureDataResponse;
import com.tip.fetchdynamicmasterdata.model.AssetOperationalStatusDetails;
import com.tip.fetchdynamicmasterdata.model.BranchSettings;
import com.tip.fetchdynamicmasterdata.model.CompanyBranchData;
import com.tip.fetchdynamicmasterdata.model.CompanyDetails;
import com.tip.fetchdynamicmasterdata.model.CountryDetails;
import com.tip.fetchdynamicmasterdata.model.CustomerRefTypeValuesDetails;
import com.tip.fetchdynamicmasterdata.model.EntitlementInvoiceDetails;
import com.tip.fetchdynamicmasterdata.model.EntitlementLORDetails;
import com.tip.fetchdynamicmasterdata.model.EntitlementPoValidDetails;
import com.tip.fetchdynamicmasterdata.model.EquipDynamicTabFieldPossibleValues;
import com.tip.fetchdynamicmasterdata.model.EquipDynamicTabFields;
import com.tip.fetchdynamicmasterdata.model.EquipDynamicTabsDetails;
import com.tip.fetchdynamicmasterdata.model.EquipmentCountryDetails;
import com.tip.fetchdynamicmasterdata.model.EquipmentTypes;
import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataResponse;
import com.tip.fetchdynamicmasterdata.model.LanguageDetails;
import com.tip.fetchdynamicmasterdata.model.Manufacturer;
import com.tip.fetchdynamicmasterdata.model.MasterDataDynamicResponse;
import com.tip.fetchdynamicmasterdata.model.ParkingLocationDetails;
import com.tip.fetchdynamicmasterdata.model.SupplierCurrency;
import com.tip.fetchdynamicmasterdata.model.SupplierStatus;
import com.tip.fetchdynamicmasterdata.model.TpuRefTypeValuesDetails;
import com.tip.fetchdynamicmasterdata.model.TyreBrands;
import com.tip.fetchdynamicmasterdata.model.VendorPriority;
import com.tip.fetchdynamicmasterdata.repository.FetchAllProceduresListRepository;
import com.tip.fetchdynamicmasterdata.repository.FetchDynamicMasterDataRepository;
import com.tip.fetchdynamicmasterdata.service.FetchDynamicMasterDataService;

@Service
@Transactional
public class FetchDynamicMasterDataServiceImpl implements FetchDynamicMasterDataService {

    @Autowired
    FetchAllProceduresListRepository fetchAllProceduresListRepository;
    
    @Autowired
    FetchDynamicMasterDataRepository fetchDynamicMasterDataRepository;
    
    public static final String PROC_DATA_KEY = "procDataList";

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
		return lMasterDataDynamicResponse;
    }
    
    private void setObjectLists(AllProcedureDataResponse allProcedureDataResponse, Map<String, Object> procDataMap,
			MasterDataDynamicResponse lMasterDataDynamicResponse) {
    	if(allProcedureDataResponse.getProcName() != null && "countryDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setCountryDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "companyDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setCompanyDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "languageDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setLanguageDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "entitlementInvoiceDropdownDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEntitlInvoiceDrpdwnDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "entitlementLORDropdownDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEntitlLORDrpdwnDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "addressTypeDropdownDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setAddressTypeDrpdwnDetails(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "parkingLocationsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setParkingLocations(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "assetOperationalStatusLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setAssetOperationalStatus(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "customerRefTypeValuesLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setCustomerRefTypeValues(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "tpuRefTypeValuesLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setTpuRefTypeValues(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "equipDynamicTabsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEquipDynamicTabs(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "equipDynamicTabFieldsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEquipDynamicTabFields(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "equipDynamicTabFieldPossibleValuesLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEquipDynamicTabFieldPossibleVal(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "equipTypesLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEquipmentTypes(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "equipCountryLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEquipmentCountry(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "entitlementPoValidForDropdownDetailsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setEntitlementPoValid(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "tyreBrandsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setTyreBrands(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "branchSettingsLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setBranchSettings(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "assetManufacturerLst".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setAssetManuf(procDataMap,lMasterDataDynamicResponse);
		}
    	
		else if(allProcedureDataResponse.getProcName() != null && "currencyList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierCurrencyList(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierStatusList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierStatusCategoryList(procDataMap,lMasterDataDynamicResponse,allProcedureDataResponse.getObjName());
		}else if(allProcedureDataResponse.getProcName() != null && "supplierPriorityList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierPriorityList(procDataMap,lMasterDataDynamicResponse);
		}else if(allProcedureDataResponse.getProcName() != null && "supplierCategoryList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setSupplierStatusCategoryList(procDataMap,lMasterDataDynamicResponse,allProcedureDataResponse.getObjName());
		}else if(allProcedureDataResponse.getProcName() != null && "companyBranchMappingList".equalsIgnoreCase(allProcedureDataResponse.getObjName())){
			setCompanyBranchData(procDataMap,lMasterDataDynamicResponse);
		}
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
    
    private void setCompanyDetails(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<CompanyDetails> companyDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lCompanyDetailsMap = lst.get(i);
    		CompanyDetails lCompanyDetails = new CompanyDetails();
    		lCompanyDetails.setCompanyNr((Integer)lCompanyDetailsMap.get("company_nr"));
    		lCompanyDetails.setCompanyName((String)lCompanyDetailsMap.get("company_name"));
    		lCompanyDetails.setCompanyFunctionalCurrencyCd((String)lCompanyDetailsMap.get("company_functional_currency_cd"));
    		lCompanyDetails.setCountrySkey((Integer)lCompanyDetailsMap.get("country_skey"));
    		lCompanyDetails.setCountryName((String)lCompanyDetailsMap.get("country_name"));
    		lCompanyDetails.setCountryCode((String)lCompanyDetailsMap.get("country_code"));
    		lCompanyDetails.setPhoneCode((Integer)lCompanyDetailsMap.get("phone_code"));
    		lCompanyDetails.setCurrencyName((String)lCompanyDetailsMap.get("currency_name"));
    		lCompanyDetails.setCountryDialingCd((String)lCompanyDetailsMap.get("country_dialing_cd"));      		
    		lCompanyDetails.setRegionName((String)lCompanyDetailsMap.get("region_name"));
    		companyDetailsLst.add(lCompanyDetails);
    	}
    	lMasterDataDynamicResponse.getCompanyDetailsLst().addAll(companyDetailsLst);
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
    
    private void setEntitlInvoiceDrpdwnDetails(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EntitlementInvoiceDetails> entitlementInvoiceDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEntitlementInvoiceDetailsMap = lst.get(i);
    		EntitlementInvoiceDetails lEntitlementInvoiceDetails = new EntitlementInvoiceDetails();
    		lEntitlementInvoiceDetails.setInvoice((Integer)lEntitlementInvoiceDetailsMap.get("Invoice"));
    		lEntitlementInvoiceDetails.setInvoiceValue((String)lEntitlementInvoiceDetailsMap.get("Invoice_Value"));
    		entitlementInvoiceDetailsLst.add(lEntitlementInvoiceDetails);
    	}
    	lMasterDataDynamicResponse.getEntitlementInvoiceDropdownDetailsLst().addAll(entitlementInvoiceDetailsLst);
    }
    
    private void setEntitlLORDrpdwnDetails(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EntitlementLORDetails> entitlementLORDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEntitlementLORDetailsMap = lst.get(i);
    		EntitlementLORDetails lEntitlementLORDetails = new EntitlementLORDetails();
    		lEntitlementLORDetails.setLOR((Integer)lEntitlementLORDetailsMap.get("LOR"));
    		lEntitlementLORDetails.setLORValue((String)lEntitlementLORDetailsMap.get("LOR_Value"));
    		entitlementLORDetailsLst.add(lEntitlementLORDetails);
    	}
    	lMasterDataDynamicResponse.getEntitlementLORDropdownDetailsLst().addAll(entitlementLORDetailsLst);
    }
    
    private void setAddressTypeDrpdwnDetails(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<AddressTypeDetails> addressTypeDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lAddressTypeDetailsMap = lst.get(i);
    		AddressTypeDetails lAddressTypeDetails = new AddressTypeDetails();
    		lAddressTypeDetails.setAddressType((Integer)lAddressTypeDetailsMap.get("Address_Type"));
    		lAddressTypeDetails.setAddressTypeDesc((String)lAddressTypeDetailsMap.get("Address_Type_Desc"));
    		addressTypeDetailsLst.add(lAddressTypeDetails);
    	}
    	lMasterDataDynamicResponse.getAddressTypeDropdownDetailsLst().addAll(addressTypeDetailsLst);
    }
    
    private void setParkingLocations(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<ParkingLocationDetails> parkingLocationDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lParkingLocationDetailsMap = lst.get(i);
    		ParkingLocationDetails lParkingLocationDetails = new ParkingLocationDetails();
    		lParkingLocationDetails.setParkingLocationCd((String)lParkingLocationDetailsMap.get("Parking_Location_Cd"));
    		lParkingLocationDetails.setBranchNr((String)lParkingLocationDetailsMap.get("Branch_Nr"));
    		lParkingLocationDetails.setCompanyNr((String)lParkingLocationDetailsMap.get("Company_Nr"));
    		lParkingLocationDetails.setParkingLoctnDesc((String)lParkingLocationDetailsMap.get("Parking_Loctn_Desc"));
    		parkingLocationDetailsLst.add(lParkingLocationDetails);
    	}
    	lMasterDataDynamicResponse.getParkingLocationDetailsLst().addAll(parkingLocationDetailsLst);
    }
    
    private void setAssetOperationalStatus(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<AssetOperationalStatusDetails> assetOperationalStatusDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lAssetOperationalStatusDetailsMap = lst.get(i);
    		AssetOperationalStatusDetails lAssetOperationalStatusDetails = new AssetOperationalStatusDetails();
    		lAssetOperationalStatusDetails.setStatusCd((String)lAssetOperationalStatusDetailsMap.get("Status_Cd"));
    		lAssetOperationalStatusDetails.setStatusDesc((String)lAssetOperationalStatusDetailsMap.get("Status_Desc"));
    		assetOperationalStatusDetailsLst.add(lAssetOperationalStatusDetails);
    	}
    	lMasterDataDynamicResponse.getAssetOperationalStatusDetailsLst().addAll(assetOperationalStatusDetailsLst);
    }
    
    private void setCustomerRefTypeValues(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<CustomerRefTypeValuesDetails> customerRefTypeValuesDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lCustomerRefTypeValuesDetailsMap = lst.get(i);
    		CustomerRefTypeValuesDetails lCustomerRefTypeValuesDetails = new CustomerRefTypeValuesDetails();
    		lCustomerRefTypeValuesDetails.setRefTypeId((Integer)lCustomerRefTypeValuesDetailsMap.get("ref_type_id"));
    		lCustomerRefTypeValuesDetails.setDescription((String)lCustomerRefTypeValuesDetailsMap.get("description"));
    		lCustomerRefTypeValuesDetails.setRefId((Integer)lCustomerRefTypeValuesDetailsMap.get("ref_id"));
    		lCustomerRefTypeValuesDetails.setRrvDescription((String)lCustomerRefTypeValuesDetailsMap.get("rrv_description"));
    		lCustomerRefTypeValuesDetails.setSeq((Integer)lCustomerRefTypeValuesDetailsMap.get("seq"));
    		lCustomerRefTypeValuesDetails.setActiveFlag((String)lCustomerRefTypeValuesDetailsMap.get("active_flag"));
    		lCustomerRefTypeValuesDetails.setReservedFlag((String)lCustomerRefTypeValuesDetailsMap.get("reserved_flag"));
    		customerRefTypeValuesDetailsLst.add(lCustomerRefTypeValuesDetails);
    	}
    	lMasterDataDynamicResponse.getCustomerRefTypeValuesDetailsLst().addAll(customerRefTypeValuesDetailsLst);
    }
    
    private void setTpuRefTypeValues(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<TpuRefTypeValuesDetails> tpuRefTypeValuesDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lTpuRefTypeValuesDetailsMap = lst.get(i);
    		TpuRefTypeValuesDetails lTpuRefTypeValuesDetails = new TpuRefTypeValuesDetails();
    		lTpuRefTypeValuesDetails.setRefTypeId((Integer)lTpuRefTypeValuesDetailsMap.get("Ref_Type_Id"));
    		lTpuRefTypeValuesDetails.setRefTypeDesc((String)lTpuRefTypeValuesDetailsMap.get("Ref_Type_Desc"));
    		lTpuRefTypeValuesDetails.setRefId((Integer)lTpuRefTypeValuesDetailsMap.get("Ref_Id"));
    		lTpuRefTypeValuesDetails.setRrvDescription((String)lTpuRefTypeValuesDetailsMap.get("rrv_description"));
    		lTpuRefTypeValuesDetails.setRefIdSeq((Integer)lTpuRefTypeValuesDetailsMap.get("Ref_Id_Seq"));
    		lTpuRefTypeValuesDetails.setRefIdActive((String)lTpuRefTypeValuesDetailsMap.get("Ref_Id_Active"));
    		lTpuRefTypeValuesDetails.setRefIdReserved((String)lTpuRefTypeValuesDetailsMap.get("Ref_Id_Reserved"));
    		tpuRefTypeValuesDetailsLst.add(lTpuRefTypeValuesDetails);
    	}
    	lMasterDataDynamicResponse.getTpuRefTypeValuesDetailsLst().addAll(tpuRefTypeValuesDetailsLst);
    }
    
    private void setEquipDynamicTabs(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EquipDynamicTabsDetails> equipDynamicTabsDetailsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEquipDynamicTabsDetailsMap = lst.get(i);
    		EquipDynamicTabsDetails lEquipDynamicTabsDetails = new EquipDynamicTabsDetails();
    		lEquipDynamicTabsDetails.setCatgrId((Integer)lEquipDynamicTabsDetailsMap.get("Catgr_id"));
    		lEquipDynamicTabsDetails.setCatgrDesc((String)lEquipDynamicTabsDetailsMap.get("Catgr_Desc"));
    		lEquipDynamicTabsDetails.setCatgrShortn((String)lEquipDynamicTabsDetailsMap.get("Catgr_Shortn"));
    		lEquipDynamicTabsDetails.setSpecgrId((Integer)lEquipDynamicTabsDetailsMap.get("Specgr_id"));
    		lEquipDynamicTabsDetails.setSpecgrShortn((String)lEquipDynamicTabsDetailsMap.get("Specgr_Shortn"));
    		lEquipDynamicTabsDetails.setSpecgrDescr((String)lEquipDynamicTabsDetailsMap.get("Specgr_Descr"));
    		equipDynamicTabsDetailsLst.add(lEquipDynamicTabsDetails);
    	}
    	lMasterDataDynamicResponse.getEquipDynamicTabsLst().addAll(equipDynamicTabsDetailsLst);
    }
    
    private void setEquipDynamicTabFields(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EquipDynamicTabFields> equipDynamicTabFieldsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEquipDynamicTabFieldsMap = lst.get(i);
    		EquipDynamicTabFields lEquipDynamicTabFields = new EquipDynamicTabFields();
    		lEquipDynamicTabFields.setCatgrDesc((String)lEquipDynamicTabFieldsMap.get("Catgr_Desc"));
    		lEquipDynamicTabFields.setCatgrShortn((String)lEquipDynamicTabFieldsMap.get("Catgr_Shortn"));
    		lEquipDynamicTabFields.setSpecgrShortn((String)lEquipDynamicTabFieldsMap.get("Specgr_Shortn"));
    		lEquipDynamicTabFields.setSpecgrDescr((String)lEquipDynamicTabFieldsMap.get("Specgr_Descr"));
    		lEquipDynamicTabFields.setCatgrId((Integer)lEquipDynamicTabFieldsMap.get("Catgr_Id"));
    		lEquipDynamicTabFields.setSpecgrId((Integer)lEquipDynamicTabFieldsMap.get("Specgr_Id"));
    		lEquipDynamicTabFields.setSpecitmSeq((Integer)lEquipDynamicTabFieldsMap.get("Specitm_Seq"));
    		lEquipDynamicTabFields.setAudId((String)lEquipDynamicTabFieldsMap.get("Aud_id"));            		
    		lEquipDynamicTabFields.setUsgUsed((String)lEquipDynamicTabFieldsMap.get("Usg_Used"));
    		lEquipDynamicTabFields.setSpecitmDescr((String)lEquipDynamicTabFieldsMap.get("Specitm_Descr"));
    		lEquipDynamicTabFields.setSpecitmShortn((String)lEquipDynamicTabFieldsMap.get("Specitm_Shortn"));
    		lEquipDynamicTabFields.setSpecitmReq((String)lEquipDynamicTabFieldsMap.get("Specitm_Req"));
    		lEquipDynamicTabFields.setSpecitmValType((String)lEquipDynamicTabFieldsMap.get("Specitm_Val_Type"));
    		lEquipDynamicTabFields.setSpecitmHelp((String)lEquipDynamicTabFieldsMap.get("Specitm_Help"));
    		lEquipDynamicTabFields.setSpecitmLimit((String)lEquipDynamicTabFieldsMap.get("Specitm_Limit"));
    		lEquipDynamicTabFields.setSpecitmPosMin((Integer)lEquipDynamicTabFieldsMap.get("Specitm_Pos_Min"));            		
    		lEquipDynamicTabFields.setSpecitmPosMax((Integer)lEquipDynamicTabFieldsMap.get("Specitm_Pos_Max"));
    		lEquipDynamicTabFields.setSpecitmValMin((Integer)lEquipDynamicTabFieldsMap.get("Specitm_Val_Min"));
    		lEquipDynamicTabFields.setSpecitmValMax((Integer)lEquipDynamicTabFieldsMap.get("Specitm_Val_Max"));
    		lEquipDynamicTabFields.setSpecitmSignal((String)lEquipDynamicTabFieldsMap.get("Specitm_Signal"));
    		lEquipDynamicTabFields.setModBy((String)lEquipDynamicTabFieldsMap.get("Mod_By"));
    		if(lEquipDynamicTabFieldsMap.get("Mod_Date") != null)
    			lEquipDynamicTabFields.setModDate((String)lEquipDynamicTabFieldsMap.get("Mod_Date").toString());
    		lEquipDynamicTabFields.setSpecitmDateValue((String)lEquipDynamicTabFieldsMap.get("Specitm_Date_Value"));
    		lEquipDynamicTabFields.setSpecitmOrder((Integer)lEquipDynamicTabFieldsMap.get("Specitm_Order"));
    		equipDynamicTabFieldsLst.add(lEquipDynamicTabFields);	
    	}
    	lMasterDataDynamicResponse.getEquipDynamicTabFieldsLst().addAll(equipDynamicTabFieldsLst);
    }
    
    private void setEquipDynamicTabFieldPossibleVal(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EquipDynamicTabFieldPossibleValues> equipDynamicTabFieldPossibleValuesLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEquipDynamicTabFieldPossibleValuesMap = lst.get(i);
    		EquipDynamicTabFieldPossibleValues lEquipDynamicTabFieldPossibleValues = new EquipDynamicTabFieldPossibleValues();
    		lEquipDynamicTabFieldPossibleValues.setSpecgrId((Integer)lEquipDynamicTabFieldPossibleValuesMap.get("Specgr_Id"));
    		lEquipDynamicTabFieldPossibleValues.setSpecitmSeq((Integer)lEquipDynamicTabFieldPossibleValuesMap.get("Specitm_Seq"));
    		lEquipDynamicTabFieldPossibleValues.setSpecvalSeq((Integer)lEquipDynamicTabFieldPossibleValuesMap.get("Specval_Seq"));
    		lEquipDynamicTabFieldPossibleValues.setSpecvalDescr((String)lEquipDynamicTabFieldPossibleValuesMap.get("Specval_Descr"));
    		lEquipDynamicTabFieldPossibleValues.setModBy((String)lEquipDynamicTabFieldPossibleValuesMap.get("Mod_By"));
    		if(lEquipDynamicTabFieldPossibleValuesMap.get("Mod_Date") != null)
    			lEquipDynamicTabFieldPossibleValues.setModDate((String)lEquipDynamicTabFieldPossibleValuesMap.get("Mod_Date").toString());
    		lEquipDynamicTabFieldPossibleValues.setSpecvalShortcode((String)lEquipDynamicTabFieldPossibleValuesMap.get("Specval_Shortcode"));
    		equipDynamicTabFieldPossibleValuesLst.add(lEquipDynamicTabFieldPossibleValues);
    	}
    	lMasterDataDynamicResponse.getEquipDynamicTabFieldPossibleValuesLst().addAll(equipDynamicTabFieldPossibleValuesLst);
    }
    
    private void setEquipmentTypes(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EquipmentTypes> equipmentTypesLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEquipmentTypesMap = lst.get(i);
    		EquipmentTypes lEquipmentTypes = new EquipmentTypes();
    		lEquipmentTypes.setCatgrId((String)lEquipmentTypesMap.get("Catgr_Id"));
    		lEquipmentTypes.setCatgrDesc((String)lEquipmentTypesMap.get("Catgr_Desc"));
    		lEquipmentTypes.setCatgrLevel((String)lEquipmentTypesMap.get("Catgr_Level"));
    		lEquipmentTypes.setCatgrOf((Integer)lEquipmentTypesMap.get("Catgr_Of"));
    		lEquipmentTypes.setCatgrShortn((String)lEquipmentTypesMap.get("Catgr_Shortn"));
    		lEquipmentTypes.setCatgrCode((String)lEquipmentTypesMap.get("Catgr_Code"));
    		equipmentTypesLst.add(lEquipmentTypes);
    	}
    	lMasterDataDynamicResponse.getEquipmentTypesLst().addAll(equipmentTypesLst);
    }
    
    private void setEquipmentCountry(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EquipmentCountryDetails> equipmentCountryLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEquipmentCountrysMap = lst.get(i);
    		EquipmentCountryDetails lEquipmentCountryDetails = new EquipmentCountryDetails();
    		lEquipmentCountryDetails.setCountrySkey((Integer)lEquipmentCountrysMap.get("country_skey"));
    		lEquipmentCountryDetails.setCountryName((String)lEquipmentCountrysMap.get("country_name"));
    		lEquipmentCountryDetails.setCountryCode((String)lEquipmentCountrysMap.get("country_code"));
    		equipmentCountryLst.add(lEquipmentCountryDetails);
    	}
    	lMasterDataDynamicResponse.getEquipmentCountryLst().addAll(equipmentCountryLst);
    }
    
    private void setEntitlementPoValid(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<EntitlementPoValidDetails> entitlementPoValidLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lEntitlementPoValidMap = lst.get(i);
    		EntitlementPoValidDetails lEntitlementPoValidDetails = new EntitlementPoValidDetails();
    		lEntitlementPoValidDetails.setPOValidFor((Integer)lEntitlementPoValidMap.get("PO_Valid_For"));
    		lEntitlementPoValidDetails.setPOValidForValue((String)lEntitlementPoValidMap.get("PO_Valid_For_Value"));
    		entitlementPoValidLst.add(lEntitlementPoValidDetails);
    	}
    	lMasterDataDynamicResponse.getEntitlementPoValidLst().addAll(entitlementPoValidLst);
    }
    
    private void setTyreBrands(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<TyreBrands> tyreBrandsLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> lTyreBrandsMap = lst.get(i);
    		TyreBrands tyreBrands = new TyreBrands();
    		tyreBrands.setLanguageId((String)lTyreBrandsMap.get("Language_Id"));
    		tyreBrands.setTyreCd((String)lTyreBrandsMap.get("Tyre_Cd"));
    		tyreBrands.setTyreDesc((String)lTyreBrandsMap.get("Tyre_Desc"));
    		tyreBrands.setLanguageName((String)lTyreBrandsMap.get("Language_Name"));
    		tyreBrandsLst.add(tyreBrands);
    	}
    	lMasterDataDynamicResponse.getTyreBrandsLst().addAll(tyreBrandsLst);
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
    
    private void setAssetManuf(Map<String, Object> procDataMap, MasterDataDynamicResponse lMasterDataDynamicResponse){
    	List<Manufacturer> manufLst = new ArrayList<>();
    	List<Map<String, Object>> lst = (List<Map<String, Object>>) procDataMap.get(PROC_DATA_KEY);
    	for(int i=0;i<lst.size();i++)
    	{
    		Map<String, Object> assetManufMap = lst.get(i);
    		Manufacturer manufacturer = new Manufacturer();
    		manufacturer.setAssetManufacturer((String)assetManufMap.get("AssetManufacterer"));
    		manufacturer.setManufName((String)assetManufMap.get("Name"));
    		manufLst.add(manufacturer);
    	}
    	lMasterDataDynamicResponse.getAssetManufacturerList().addAll(manufLst);
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
    		supplierList.add(supplierStatus);
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
    		companyBranchData.setEntityId((String)branchMap.get("Entity_Id"));
    		companyBranchData.setCompanyNr((Integer)branchMap.get("Company_Nr"));
    		companyBranchData.setCountryCd((String)branchMap.get("Country_Cd"));
    		companyBranchDataList.add(companyBranchData);
    	}
    	lMasterDataDynamicResponse.getCompanyBranchMappingList().addAll(companyBranchDataList);
    }
}