package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.RejectReasonRequest;

@FunctionalInterface
public interface RejectReasonService {

	public Map<String, Object> getPopupList(RejectReasonRequest rejectReasonRequest);

}
