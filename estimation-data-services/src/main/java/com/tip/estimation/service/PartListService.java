package com.tip.estimation.service;

import com.tip.estimation.model.PartListRequest;
import com.tip.estimation.model.PartListResponse;
@FunctionalInterface
public interface PartListService {

	public PartListResponse getPartList(PartListRequest partListRequest);

}
