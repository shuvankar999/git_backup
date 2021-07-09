package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.RebillDetails;

@FunctionalInterface
public interface ImmediateInvGenerateRepository {
	public Map<String, Object> generateInv(RebillDetails rebillDetails);

}
