package com.tip.customer.service;

import java.net.UnknownHostException;

import com.tip.elasticsearch.model.EnterpriseSearchData;
import com.tip.equipmentdetails.model.FetchFilterDetails;

public interface CustomerProcessFilterService {

	public EnterpriseSearchData processFilterDetails(FetchFilterDetails filterDetails);
}