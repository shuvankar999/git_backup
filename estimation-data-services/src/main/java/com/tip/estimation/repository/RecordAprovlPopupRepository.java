package com.tip.estimation.repository;

import java.util.Map;

import com.tip.estimation.model.RecordAprovlPopupRequest;

@FunctionalInterface
public interface RecordAprovlPopupRepository {
	public Map<String, Object> getPopupList(RecordAprovlPopupRequest recordAprovlPopupRequest);

}
