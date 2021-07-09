package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.RebillDetails;

@FunctionalInterface
public interface ImmediateInvRebillRepository {
	public Map<String, Object> saveRebillSave(RebillDetails rebillDetails);

}
