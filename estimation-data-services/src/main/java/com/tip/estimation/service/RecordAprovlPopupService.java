package com.tip.estimation.service;

import java.util.Map;

import com.tip.estimation.model.RecordAprovlPopupRequest;

@FunctionalInterface
public interface RecordAprovlPopupService {

 public	Map<String, Object> getPopupList(RecordAprovlPopupRequest recordAprovlPopupRequest);

}
