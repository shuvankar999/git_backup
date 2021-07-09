package com.tip.estimation.repository;

import java.math.BigDecimal;
import java.util.List;

import com.tip.estimation.model.EnrichWoObject;
import com.tip.estimation.model.VersionObject;

public interface CreateSupplLabourRepository {

	public String saveLabourSuppl(List<EnrichWoObject> enrichWoObjectList,BigDecimal estimationId,VersionObject versionObject, String ssoId);
}
