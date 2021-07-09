package com.tip.asset.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.asset.model.AddInfoEquipDetails;
import com.tip.asset.model.ComplianceDocDetails;
import com.tip.asset.model.ComplianceEquipDetails;
import com.tip.asset.model.EquipmentDetails;
import com.tip.asset.model.EquipmentDetailsRequest;
import com.tip.asset.model.EquipmentDetailsResponse;
import com.tip.asset.repository.FetchEquipmentRepository;
import com.tip.asset.service.FetchEquipmentService;;

@Service
@Transactional
public class FetchEquipmentServiceImpl implements FetchEquipmentService {

	@Autowired
	FetchEquipmentRepository fetchEquipmentRepository;

	@SuppressWarnings("unchecked")
	@Override
	public EquipmentDetailsResponse fetchEquipmentDetails(EquipmentDetailsRequest equipmentDetailsRequest) {
		EquipmentDetailsResponse equipmentDetailsResponse = new EquipmentDetailsResponse();
		Map<String, Object> resultMap = fetchEquipmentRepository.fetchEquipmentDetails(equipmentDetailsRequest);
		if (null != resultMap) {
			for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
				if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					setEquipmentData(lst, equipmentDetailsResponse);
				} else if ("#result-set-2".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					setAddInfoEquipData(lst, equipmentDetailsResponse);
				} else if ("#result-set-3".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					setComplianceEquipData(lst, equipmentDetailsResponse);
				} else if ("#result-set-4".equalsIgnoreCase(entry.getKey())) {
					List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
					setComplianceDocData(lst, equipmentDetailsResponse);
				}

			}
		}
		return equipmentDetailsResponse;
	}

	private void setEquipmentData(List<Map<String, Object>> lst, EquipmentDetailsResponse equipmentDetailsResponse) {
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> equipmentDetailsMap = lst.get(i);
			EquipmentDetails equipmentDetails = new EquipmentDetails();
			equipmentDetails.setAssetnumber((Integer) equipmentDetailsMap.get("assetnumber"));
			equipmentDetails.setLicenceCountryCd(equipmentDetailsMap.get("licenceCountryCd") != null
					? equipmentDetailsMap.get("licenceCountryCd").toString().trim() : null);
			equipmentDetails.setLicenceNr((String) equipmentDetailsMap.get("licenceNr"));
			equipmentDetails.setCustEquipmentRefNr((String) equipmentDetailsMap.get("custEquipmentRefNr"));
			equipmentDetails.setSerialNumber((String) equipmentDetailsMap.get("serialNumber"));
			equipmentDetails.setEquipmentType((String) equipmentDetailsMap.get("equipmentType"));
			equipmentDetails.setEquipmentTypeDesc((String) equipmentDetailsMap.get("equipmentTypeDesc"));
			equipmentDetails.setEquipmentManufCd((String) equipmentDetailsMap.get("equipmentManufCd"));
			equipmentDetails.setEquipmentManufacturer((String) equipmentDetailsMap.get("equipmentManufacturer"));
			equipmentDetails.setEquipSubManufacturer((String) equipmentDetailsMap.get("equipSubManufacturer"));
			equipmentDetails.setNumberOfaxles((Integer) equipmentDetailsMap.get("numberOfaxles"));
			equipmentDetails.setCustomerName((String) equipmentDetailsMap.get("customerName"));
			equipmentDetails.setCustomerNr((Integer) equipmentDetailsMap.get("customerNr"));
			equipmentDetails.setSingleTyre((String) equipmentDetailsMap.get("singleTyre"));
			equipmentDetails.setTwinTyre((String) equipmentDetailsMap.get("twinTyre"));
			equipmentDetails.setBoth((String) equipmentDetailsMap.get("both"));
			equipmentDetails.setCategorygroupCd((String) equipmentDetailsMap.get("categorygroupCd"));
			equipmentDetails.setEquipmentStatus((String) equipmentDetailsMap.get("equipmentStatus"));
			equipmentDetails.setCustomerCountryCd((String) equipmentDetailsMap.get("customerCountryCd"));
			equipmentDetailsResponse.getEquipmentDetailslist().add(equipmentDetails);
		}
	}

	private void setAddInfoEquipData(List<Map<String, Object>> lst, EquipmentDetailsResponse equipmentDetailsResponse) {
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> addInfoEquipDetailsMap = lst.get(i);
			AddInfoEquipDetails addInfoEquipDetails = new AddInfoEquipDetails();
			addInfoEquipDetails.setBrakeType((String) addInfoEquipDetailsMap.get("brakeType"));
			addInfoEquipDetails.setModelYear((Integer) addInfoEquipDetailsMap.get("modelYear"));
			addInfoEquipDetails.setRoadsideContRefNr((String) addInfoEquipDetailsMap.get("roadsideContRefNr"));
			addInfoEquipDetails.setAxleMake((String) addInfoEquipDetailsMap.get("axleMake"));
			addInfoEquipDetails.setRoadsideAssitance((String) addInfoEquipDetailsMap.get("roadsideAssitance"));
			addInfoEquipDetails.setRoadTaxAuth((String) addInfoEquipDetailsMap.get("roadTaxAuth"));
			equipmentDetailsResponse.getAddInfoEquiplist().add(addInfoEquipDetails);
		}
	}

	private void setComplianceEquipData(List<Map<String, Object>> lst,
			EquipmentDetailsResponse equipmentDetailsResponse) {
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> complianceEquipDetailsMap = lst.get(i);
			ComplianceEquipDetails complianceEquipDetails = new ComplianceEquipDetails();
			complianceEquipDetails.setMotDueDate((String) complianceEquipDetailsMap.get("motDueDate"));
			complianceEquipDetails.setNextInspDate((String) complianceEquipDetailsMap.get("nextInspDate"));
			equipmentDetailsResponse.getComplianceEquiplist().add(complianceEquipDetails);
		}
	}

	private void setComplianceDocData(List<Map<String, Object>> lst, EquipmentDetailsResponse equipmentDetailsResponse) {
		for (int i = 0; i < lst.size(); i++) {
			Map<String, Object> complianceEquipDetailsMap = lst.get(i);
			ComplianceDocDetails complianceDocDetails = new ComplianceDocDetails();
			complianceDocDetails.setInspDate((String) complianceEquipDetailsMap.get("inspDate"));
			complianceDocDetails.setInspectionType((String) complianceEquipDetailsMap.get("inspectionType"));
			complianceDocDetails.setInspDocName((String) complianceEquipDetailsMap.get("InspDocName"));
			complianceDocDetails.setInspDocLoc((String) complianceEquipDetailsMap.get("inspDocLoc"));
			equipmentDetailsResponse.getComplianceDoclist().add(complianceDocDetails);
		}
	}
}