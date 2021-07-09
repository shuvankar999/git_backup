package com.tip.estimation.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimation.model.BundleDetails;
import com.tip.estimation.model.FetchCustBundleRequest;
import com.tip.estimation.model.FetchCustBundleResponse;
import com.tip.estimation.repository.FetchCustBundleRepository;
import com.tip.estimation.service.FetchCustBundleService;

@Service
public class FetchCustBundleServiceImpl implements FetchCustBundleService {

	@Autowired
	FetchCustBundleRepository fetchCustBundleRepository;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<FetchCustBundleResponse> getCustBundle(FetchCustBundleRequest fetchCustBundleRequest) {
		Map<String, Object> resultMap = fetchCustBundleRepository.getCustBundle(fetchCustBundleRequest);
		List<FetchCustBundleResponse> listResponse = new ArrayList();
		List<String> checkList = new ArrayList();
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				for (Map<String, Object> mapObj : (List<Map<String, Object>>) entry.getValue()) {
					FetchCustBundleResponse fetchCustBundleResponse = new FetchCustBundleResponse();
					if (!checkList.contains(mapObj.get("bundleName"))) {
						fetchCustBundleResponse.setBundleName((String) mapObj.get("bundleName"));
						checkList.add((String) mapObj.get("bundleName"));
						for (Map<String, Object> mapObj1 : (List<Map<String, Object>>) entry.getValue()) {
							BundleDetails bundle = new BundleDetails();
							if (fetchCustBundleResponse.getBundleName()
									.equalsIgnoreCase((String) mapObj1.get("bundleName"))) {
								bundle.setBundleName((String) mapObj1.get("bundleName"));
								bundle.setDnaCode((String) mapObj1.get("dnaCode"));
								bundle.setGroups((String) mapObj1.get("groups"));
								bundle.setSubGroup((String) mapObj1.get("subGroup"));
								bundle.setActivity((String) mapObj1.get("activity"));
								bundle.setMaintenanceAction((String) mapObj1.get("maintenanceAction"));
								bundle.setManufacturerId((String) mapObj1.get("manufacturerId"));
								bundle.setManufacturer((String) mapObj1.get("manufacturer"));
								bundle.setOePartNr((String) mapObj1.get("oePartNr"));
								bundle.setSupplierPartNr((String) mapObj1.get("supplierPartNr"));
								bundle.setPartsDescription((String) mapObj1.get("partsDescription"));
								if(mapObj1.get("fee")!=null) {
									bundle.setFees((BigDecimal) mapObj1.get("fee"));
								}
								
								fetchCustBundleResponse.getListOfBundle().add(bundle);
							}

						}
						listResponse.add(fetchCustBundleResponse);
					}
				}
			}
		}

		return listResponse;

	}

}
