package com.tip.asset.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.asset.model.EquipmentDetails;
import com.tip.asset.model.EquipmentDetailsRequest;
import com.tip.asset.model.EquipmentDetailsResponse;
import com.tip.asset.repository.FetchEquipmentRepository;
import com.tip.asset.service.FetchEquipmentService;;

@Service
@Transactional
public class FetchEquipmentServiceImpl implements FetchEquipmentService{

	@Autowired
	FetchEquipmentRepository fetchEquipmentRepository;
	
	@Override
	public EquipmentDetailsResponse fetchEquipmentDetails(EquipmentDetailsRequest equipmentDetailsRequest)
	{
		EquipmentDetailsResponse equipmentDetailsResponse = new EquipmentDetailsResponse();
		List<EquipmentDetails> equipmentDetailslist = new ArrayList<>();
		Map<String, Object> resultMap = fetchEquipmentRepository.fetchEquipmentDetails(equipmentDetailsRequest);
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
				if("#result-set-1".equalsIgnoreCase(entry.getKey()))
                {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					setEquipmentData(lst, equipmentDetailslist);
                }
			}
		}
		equipmentDetailsResponse.getEquipmentDetailslist().addAll(equipmentDetailslist);
		return equipmentDetailsResponse;
	}

	private void setEquipmentData(List<Map<String, Object>> lst, List<EquipmentDetails> equipmentDetailslist) {
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> equipmentDetailsMap = lst.get(i);
			EquipmentDetails equipmentDetails = new EquipmentDetails();
			equipmentDetails.setAssetnumber((Integer) equipmentDetailsMap.get("assetnumber"));
			equipmentDetails.setLicenceCountryCd(equipmentDetailsMap.get("licenceCountryCd")!=null ? equipmentDetailsMap.get("licenceCountryCd").toString().trim() : null);
			equipmentDetails.setLicenceNr((String) equipmentDetailsMap.get("licenceNr"));
			equipmentDetails.setCustEquipmentRefNr((String) equipmentDetailsMap.get("custEquipmentRefNr"));
			equipmentDetails.setSerialNumber((String) equipmentDetailsMap.get("serialNumber"));
			equipmentDetails.setEquipmentType((String) equipmentDetailsMap.get("equipmentType"));
			equipmentDetails.setEquipmentTypeDesc((String) equipmentDetailsMap.get("equipmentTypeDesc"));			
			equipmentDetails.setEquipmentManufCd((String) equipmentDetailsMap.get("equipmentManufCd"));
			equipmentDetails.setEquipmentManufacturer((String) equipmentDetailsMap.get("equipmentManufacturer"));
			equipmentDetails.setNumberOfaxles((Integer) equipmentDetailsMap.get("numberOfaxles"));
			equipmentDetails.setCustomerName((String) equipmentDetailsMap.get("customerName"));
			equipmentDetails.setCustomerNr((Integer) equipmentDetailsMap.get("customerNr"));
			equipmentDetails.setSingleTyre((String) equipmentDetailsMap.get("singleTyre"));
			equipmentDetails.setTwinTyre((String) equipmentDetailsMap.get("twinTyre"));
			equipmentDetails.setBoth((String) equipmentDetailsMap.get("both"));
			equipmentDetails.setCategorygroupCd((String) equipmentDetailsMap.get("categorygroupCd"));
			equipmentDetailslist.add(equipmentDetails);
		}
	}
}