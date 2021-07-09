package com.tip.equipmentdetails.service;

import com.tip.equipmentdetails.model.FetchFilterDetails;
import com.tip.equipmentdetails.model.FetchFilterDetailsRequest;

@FunctionalInterface
public interface FetchFilterDetailsService {

	FetchFilterDetails getFilterDetils(FetchFilterDetailsRequest fetchFilterDetailsRequest);
}