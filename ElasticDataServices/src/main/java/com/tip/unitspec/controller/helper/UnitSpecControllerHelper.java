package com.tip.unitspec.controller.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tip.unitspec.model.UnitSpecData;

@Component
public class UnitSpecControllerHelper{

	public Map<String, String> setUnitFilter(UnitSpecData unitSpecData) {        
    	Map<String, String> fields = new HashMap<>(0);
    	if(unitSpecData.getUnitNr() != null && !unitSpecData.getUnitNr().toString().isEmpty())
    	{
    		fields.put("unit_nr", unitSpecData.getUnitNr().toString());
    	}
    	if(unitSpecData.getCatgrId() != null && !unitSpecData.getCatgrId().toString().isEmpty())
    	{
    		fields.put("catgr_id", unitSpecData.getCatgrId().toString());
    	}
    	if(unitSpecData.getUnitRegistr() != null && !unitSpecData.getUnitRegistr().isEmpty())
    	{
    		fields.put("unit_registr", unitSpecData.getUnitRegistr());
    	}
    	if(unitSpecData.getUnitBuilt() != null && !unitSpecData.getUnitBuilt().isEmpty())
    	{
    		fields.put("unit_built", unitSpecData.getUnitBuilt());
    	}
    	if(unitSpecData.getUnitModdate() != null && !unitSpecData.getUnitModdate().isEmpty())
    	{
    		fields.put("unit_moddate", unitSpecData.getUnitModdate());
    	}
    	if(unitSpecData.getUnitSpecStatus() != null && !unitSpecData.getUnitSpecStatus().isEmpty())
    	{
    		fields.put("unit_spec_status", unitSpecData.getUnitSpecStatus());
    	}
    	if(unitSpecData.getUnitOperationalStatus() != null && !unitSpecData.getUnitOperationalStatus().isEmpty())
    	{
    		fields.put("unit_operational_status", unitSpecData.getUnitOperationalStatus());
    	}
    	if(unitSpecData.getUnitCatgrpCode() != null && !unitSpecData.getUnitCatgrpCode().isEmpty())
    	{
    		fields.put("unit_catgrp_code", unitSpecData.getUnitCatgrpCode());
    	}
    	if(unitSpecData.getUnitSerialNr() != null && !unitSpecData.getUnitSerialNr().isEmpty())
    	{
    		fields.put("unit_serial_nr", unitSpecData.getUnitSerialNr());
    	}
    	if(unitSpecData.getUnitCategoryCd() != null && !unitSpecData.getUnitCategoryCd().isEmpty())
    	{
    		fields.put("unit_category_cd", unitSpecData.getUnitCategoryCd());
    	}
    	if(unitSpecData.getUnitGroupCd() != null && !unitSpecData.getUnitGroupCd().isEmpty())
    	{
    		fields.put("unit_group_cd", unitSpecData.getUnitGroupCd());
    	}
    	if(unitSpecData.getUnitManufacturer() != null && !unitSpecData.getUnitManufacturer().isEmpty())
    	{
    		fields.put("unit_manufacturer", unitSpecData.getUnitManufacturer());
    	}
    	if(unitSpecData.getUnitModelYear() != null && !unitSpecData.getUnitModelYear().toString().isEmpty())
    	{
    		fields.put("unit_model_year", unitSpecData.getUnitModelYear().toString());
    	}
    	if(unitSpecData.getUnitCostLastRefrnc() != null && !unitSpecData.getUnitCostLastRefrnc().isEmpty())
    	{
    		fields.put("unit_cost_last_refrnc", unitSpecData.getUnitCostLastRefrnc());
    	}
    	if(unitSpecData.getUnitLicenceNr() != null && !unitSpecData.getUnitLicenceNr().isEmpty())
    	{
    		fields.put("unit_licence_nr", unitSpecData.getUnitLicenceNr());
    	}
    	if(unitSpecData.getUnitLicenceCountryCd() != null && !unitSpecData.getUnitLicenceCountryCd().isEmpty())
    	{
    		fields.put("unit_licence_country_cd", unitSpecData.getUnitLicenceCountryCd());
    	}
    	if(unitSpecData.getUnitCd() != null && !unitSpecData.getUnitCd().isEmpty())
    	{
    		fields.put("unit_cd", unitSpecData.getUnitCd());
    	}
    	if(unitSpecData.getUnitNewUsedLeasedInd() != null && !unitSpecData.getUnitNewUsedLeasedInd().isEmpty())
    	{
    		fields.put("unit_new_used_leased_ind", unitSpecData.getUnitNewUsedLeasedInd());
    	}
    	if(unitSpecData.getUnitAvailableForSaleInd() != null && !unitSpecData.getUnitAvailableForSaleInd().isEmpty())
    	{
    		fields.put("unit_available_for_sale_ind", unitSpecData.getUnitAvailableForSaleInd());
    	}
    	if(unitSpecData.getUnitLienCd() != null && !unitSpecData.getUnitLienCd().isEmpty())
    	{
    		fields.put("unit_lien_cd", unitSpecData.getUnitLienCd());
    	}
    	if(unitSpecData.getUnitSoldDate() != null && !unitSpecData.getUnitSoldDate().isEmpty())
    	{
    		fields.put("unit_sold_date", unitSpecData.getUnitSoldDate());
    	}
    	if(unitSpecData.getUnitAquiredCompany() != null && !unitSpecData.getUnitAquiredCompany().isEmpty())
    	{
    		fields.put("unit_aquired_company", unitSpecData.getUnitAquiredCompany());
    	}
    	if(unitSpecData.getUnitAquiredRefrnc() != null && !unitSpecData.getUnitAquiredRefrnc().isEmpty())
    	{
    		fields.put("unit_aquired_refrnc", unitSpecData.getUnitAquiredRefrnc());
    	}
    	if(unitSpecData.getUnitCustomerRefrnc() != null && !unitSpecData.getUnitCustomerRefrnc().isEmpty())
    	{
    		fields.put("unit_customer_refrnc", unitSpecData.getUnitCustomerRefrnc());
    	}
    	if(unitSpecData.getInsrcCompanyNr() != null && !unitSpecData.getInsrcCompanyNr().isEmpty())
    	{
    		fields.put("insrc_company_nr", unitSpecData.getInsrcCompanyNr());
    	}
    	if(unitSpecData.getPoolUnitOwner() != null && !unitSpecData.getPoolUnitOwner().isEmpty())
    	{
    		fields.put("pool_unit_owner", unitSpecData.getPoolUnitOwner());
    	}
    	if(unitSpecData.getRegstnDate() != null && !unitSpecData.getRegstnDate().isEmpty())
    	{
    		fields.put("regstn_date", unitSpecData.getRegstnDate());
    	}
    	if(unitSpecData.getPoolGroupNr() != null && !unitSpecData.getPoolGroupNr().isEmpty())
    	{
    		fields.put("pool_group_nr", unitSpecData.getPoolGroupNr());
    	}
    	if(unitSpecData.getReeferMaintContract() != null && !unitSpecData.getReeferMaintContract().isEmpty())
    	{
    		fields.put("reefer_maint_contract", unitSpecData.getReeferMaintContract());
    	}
    	if(unitSpecData.getUnitTsp() != null && !unitSpecData.getUnitTsp().isEmpty())
    	{
    		fields.put("unit_tsp", unitSpecData.getUnitTsp());
    	}
    	if(unitSpecData.getUnitRefurbComment() != null && !unitSpecData.getUnitRefurbComment().isEmpty())
    	{
    		fields.put("unit_refurb_comment", unitSpecData.getUnitRefurbComment());
    	}           
        return fields;
    }
}

