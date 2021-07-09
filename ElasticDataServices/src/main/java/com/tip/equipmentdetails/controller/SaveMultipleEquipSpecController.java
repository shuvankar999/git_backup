package com.tip.equipmentdetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.equipmentdetails.model.MultiCopyRequest;
import com.tip.equipmentdetails.model.MultiCopyResponse;
import com.tip.equipmentdetails.service.SaveMultipleEquipSpecService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class SaveMultipleEquipSpecController {
	
	@Autowired
	SaveMultipleEquipSpecService saveMultipleEquipSpecService;
	
	@PostMapping("copyToMultipleEquip")
	@ResponseBody
	public MultiCopyResponse updateMultipleEquip(@RequestBody MultiCopyRequest multiCopyRequest) {
		
		return saveMultipleEquipSpecService.updateMultipleEquip(multiCopyRequest);
	}
	
	@PostMapping("copyAll")
	@ResponseBody
	public MultiCopyResponse updateAll(@RequestBody MultiCopyRequest multiCopyRequest) {
		
		return saveMultipleEquipSpecService.updateAll(multiCopyRequest);
	}
	
	/*public String[] frameArrayString(List<UnitNrData> a) {
		int len = a.size();
		int remin = len % 1000;
		int quotent = len / 1000;

		StringBuffer sb = new StringBuffer();
		String[] abc = new String[50];

		for (int i = 0, j = 0; i < len; i++) {
			UnitNrData unitNrData = a.get(i);
			if (i == 0) {
				sb.append(unitNrData.getUnit_Nr() + ",");
				if (len == 1) {
					abc[0] = sb.toString();
				}
				continue;
			} else {
				if (i % 1000 != 0) {
					sb.append(unitNrData.getUnit_Nr() + ",");
				} else if (i % 1000 == 0) {
					abc[j] = sb.toString();
					j++;
					sb = new StringBuffer();
					sb.append(unitNrData.getUnit_Nr() + ",");
				}

				if (len <= 1000 && len == i + 1) {
					abc[j] = sb.toString();
				}

				if (remin > 0 && len == i + 1) {
					abc[j] = sb.toString();
				}

				if ((len % 1000) == 0 && len == i + 1) {
					abc[j] = sb.toString();
				}
			}
		}
		
		 * for(int k=0;k<50;k++){ System.out.println( ">>>"+k+">>>"+abc[k]); }
		 
		return abc;
	}*/
}