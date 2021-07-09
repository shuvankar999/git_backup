package com.tip.estimation.repository;

import java.math.BigDecimal;
import java.util.List;

import com.tip.estimation.model.EnrichAddtnlPartsObject;
import com.tip.estimation.model.VersionObject;

@FunctionalInterface
public interface CreateSupplPartsRepository {
	
	public String savePartsSuppl(List<EnrichAddtnlPartsObject> partList,BigDecimal estimationId, VersionObject versionObject, String ssoId);

}
