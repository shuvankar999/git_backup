package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.RejectReasonRequest;

@FunctionalInterface
public interface RejectReasonRepository {
	public Map<String, Object> getPopupList(RejectReasonRequest rejectReasonRequest) ;

}
