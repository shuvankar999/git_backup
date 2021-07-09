package com.tip.asset.controller.helper;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.tip.asset.model.AssetData;
import com.tip.asset.model.DynamicPosVal;

@Component
public class AssetControllerHelper{

	public Map<String, String> setUnitFilter(AssetData assetData) {
	Map<String, String> fields = new HashMap<>(0);
	if(assetData.getUnitNr() != null)
	{
		fields.put("unit_nr", assetData.getUnitNr().toString());
	}
	if(assetData.getUnitSerialNr() != null && !assetData.getUnitSerialNr().isEmpty())
	{
		fields.put("unit_serial_nr", assetData.getUnitSerialNr());
	}
	if(assetData.getUnitLicenceNr() != null && !assetData.getUnitLicenceNr().isEmpty())
	{
		fields.put("unit_licence_nr", assetData.getUnitLicenceNr());
	}
	if(assetData.getUnitCd() != null && !assetData.getUnitCd().isEmpty())
	{
		fields.put("unit_cd", assetData.getUnitCd());
	}
	if(assetData.getUnitCustomerRefrnc() != null && !assetData.getUnitCustomerRefrnc().isEmpty())
	{
		fields.put("unit_customer_refrnc", assetData.getUnitCustomerRefrnc());
	}
        return fields;
    }

	public Map<String, String> setFilterForPosVals(DynamicPosVal dynamicPosVal) {
		Map<String, String> fields = new HashMap<>(0);
		if(dynamicPosVal.getSpecgrId() != null)
		{
			fields.put("specgr_id", dynamicPosVal.getSpecgrId().toString());
		}
		if(dynamicPosVal.getSpecitmSeq() != null)
		{
			fields.put("specitm_seq", dynamicPosVal.getSpecitmSeq().toString());
		}
		return fields;
	}
}