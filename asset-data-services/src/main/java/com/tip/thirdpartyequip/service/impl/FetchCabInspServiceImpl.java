
package com.tip.thirdpartyequip.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.thirdpartyequip.model.CabInspObj;
import com.tip.thirdpartyequip.model.EquipmentCabRequest;
import com.tip.thirdpartyequip.model.EquipmentCabResponse;
import com.tip.thirdpartyequip.repository.FetchCabInspRepository;
import com.tip.thirdpartyequip.service.FetchCabInspService;

@Service
public class FetchCabInspServiceImpl implements FetchCabInspService {

	@Autowired
	FetchCabInspRepository fetchCabInspRepository;


	@SuppressWarnings("unchecked")
	@Override
	public EquipmentCabResponse getCabDetails(EquipmentCabRequest equipmentCabRequest) {

		Map<String, Object> resultMap = fetchCabInspRepository.getCabDetails(equipmentCabRequest);
		EquipmentCabResponse equipmentCabResponse = new EquipmentCabResponse();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				setCabInspDetails(lst, equipmentCabResponse);
			}else if (("#result-set-2").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				equipmentCabResponse.setCabFuel((Double)lst.get(0).get("cabFuel"));
			}
		}
		return equipmentCabResponse;
	}

	private void setCabInspDetails(List<Map<String, Object>> lst, EquipmentCabResponse equipmentCabResponse) {
		for (int i = 0; i < lst.size(); i++) {
			CabInspObj cabInspObj = new CabInspObj();
			Map<String, Object> map = lst.get(i);
			cabInspObj.setCabDesc((String) map.get("cabDesc"));
			cabInspObj.setCabInspCd((String) map.get("cabInspCd"));
			cabInspObj.setCabInspCnt((Integer) map.get("cabInspCnt"));
			cabInspObj.setCabInspInd((String) map.get("cabInspInd"));
			cabInspObj.setCabInspRemarks((String) map.get("cabInspRemarks"));
			cabInspObj.setCabImageLoc((String) map.get("cabImageLoc"));
			cabInspObj.setCabInspType((String) map.get("cabInspType"));
			equipmentCabResponse.getCabInspList().add(cabInspObj);
		}
	}
}
