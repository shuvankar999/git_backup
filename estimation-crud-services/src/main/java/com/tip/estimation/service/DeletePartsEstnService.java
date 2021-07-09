package com.tip.estimation.service;

import com.tip.estimation.model.DeletePartsRequest;

@FunctionalInterface
public interface DeletePartsEstnService {

	public Object deleteParts(DeletePartsRequest deletePartsRequest);

}
