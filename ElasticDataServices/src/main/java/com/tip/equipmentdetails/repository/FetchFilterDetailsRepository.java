package com.tip.equipmentdetails.repository;

import java.util.Map;

import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface FetchFilterDetailsRepository {

	Map<String, Object> getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);
}