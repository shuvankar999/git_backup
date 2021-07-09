package com.tip.inspection.service;

import com.tip.inspection.model.AddCiaAssestRequest;
import com.tip.inspection.model.AddCiaAssestResponse;

@FunctionalInterface
public interface CiaAssestService {

	public AddCiaAssestResponse saveCiaAssest(AddCiaAssestRequest addCiaAssestRequest);

}
