package com.tip.units.controller.helper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.tip.units.model.UnitsData;


@Component
public class UnitsControllerHelper{

	public Map<String, Object> setUnitFilter(UnitsData unitData) {        
		HashMap<String, Object> fields = new HashMap<>(0);
    	if(unitData.getCustomerNr() != null && !unitData.getCustomerNr().toString().isEmpty())
    	{
    		fields.put("customer_nr", unitData.getCustomerNr());
    	}
    	if(unitData.getCompanyNr() != null && !unitData.getCompanyNr().toString().isEmpty())
    	{
    		fields.put("company_nr", unitData.getCompanyNr());
    	}
    	if(unitData.getUnitCd() != null && !unitData.getUnitCd().toString().isEmpty())
    	{
    		fields.put("unit_cd", unitData.getUnitCd());
    	}
    	if(unitData.getUnitNr() != null && !unitData.getUnitNr().toString().isEmpty())
    	{
    		fields.put("unit_nr", unitData.getUnitNr());
    	}
        return fields;
    }
}