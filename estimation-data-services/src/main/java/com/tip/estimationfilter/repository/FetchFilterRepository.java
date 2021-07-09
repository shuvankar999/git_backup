package com.tip.estimationfilter.repository;

import java.util.Map;

import com.tip.estimationfilter.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface FetchFilterRepository {

	Map<String, Object> getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);

}
