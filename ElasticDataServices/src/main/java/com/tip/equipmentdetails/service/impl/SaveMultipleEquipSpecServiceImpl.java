package com.tip.equipmentdetails.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.asset.util.AssetConstants;
import com.tip.equipmentdetails.model.MultiCopyRequest;
import com.tip.equipmentdetails.model.MultiCopyResponse;
import com.tip.equipmentdetails.repository.SaveMultipleEquipAllRepository;
import com.tip.equipmentdetails.repository.SaveMultipleEquipSpecRepository;
import com.tip.equipmentdetails.service.FetchFilterDetailsService;
import com.tip.equipmentdetails.service.SaveMultipleEquipSpecService;

@Service
public class SaveMultipleEquipSpecServiceImpl implements SaveMultipleEquipSpecService{
	
	 public static final Logger logger = LoggerFactory.getLogger(FetchFilterDetailsService.class);

	@Autowired
	SaveMultipleEquipSpecRepository saveMultipleEquipSpecRepository;
	
	@Autowired
	SaveMultipleEquipAllRepository saveMultipleEquipAllRepository;
	
	@Override
	public MultiCopyResponse updateMultipleEquip(MultiCopyRequest multiCopyRequest) {
		Map<String, Object> dbColumnMap = new HashMap<>();
		List<String> listOfdbColumnUnits = new ArrayList<>();
		List<String> listOfdbColumnUnitsDynamic = new ArrayList<>();
		for(String column:multiCopyRequest.getDbColumnNames()) {
			if(AssetConstants.ListOfUnitsCol.contains(column))
				listOfdbColumnUnits.add(column);
			else if(column.equalsIgnoreCase("PO_Nr"))
				listOfdbColumnUnits.add("Unit_Cost_Last_Refrnc");
			else
				listOfdbColumnUnitsDynamic.add(column);
		}
		dbColumnMap.put("unitsColumns", listOfdbColumnUnits);
		dbColumnMap.put("unitsDynamicColumns", listOfdbColumnUnitsDynamic);
		return saveMultipleEquipSpecRepository.updateMultipleEquip(multiCopyRequest,dbColumnMap);
	}

	@Override
	public MultiCopyResponse updateAll(MultiCopyRequest multiCopyRequest) {
		
		/**
		 * Legacy application code snippets begins
		 * 
		 * */
		int len = multiCopyRequest.getDestinationEquipmentNrs().size();
		int remin = len % 1000;
		int quotent = len / 1000;

		StringBuffer sb = new StringBuffer();
		String[] finalStringArray = new String[50];

		for (int i = 0, j = 0; i < len; i++) {
			Integer unitNr = multiCopyRequest.getDestinationEquipmentNrs().get(i);
			if (i == 0) {
				sb.append(unitNr + ",");
				if (len == 1) {
					finalStringArray[0] = sb.toString();
				}
				continue;
			} else {
				if (i % 1000 != 0) {
					sb.append(unitNr + ",");
				} else if (i % 1000 == 0) {
					finalStringArray[j] = sb.toString();
					j++;
					sb = new StringBuffer();
					sb.append(unitNr + ",");
				}

				if (len <= 1000 && len == i + 1) {
					finalStringArray[j] = sb.toString();
				}

				if (remin > 0 && len == i + 1) {
					finalStringArray[j] = sb.toString();
				}

				if (remin == 0 && len == i + 1) {
					finalStringArray[j] = sb.toString();
				}
			}
		}
		
		/** Legacy application code ends **/
		
		return saveMultipleEquipAllRepository.updateAll(multiCopyRequest.getSourceEquipmentNr(), finalStringArray);
	}
}
