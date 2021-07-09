package com.tip.estimation.service;

import com.tip.estimation.model.VoidEstnWotRequest;
import com.tip.estimation.model.VoidEstnWotResponse;

@FunctionalInterface
public interface VoidEstimationWotService {

	public VoidEstnWotResponse voidEstnWot(VoidEstnWotRequest voidEstnWotRequest);

}
