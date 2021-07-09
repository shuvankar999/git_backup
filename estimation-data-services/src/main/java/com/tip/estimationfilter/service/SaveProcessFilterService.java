package com.tip.estimationfilter.service;

import com.tip.estimationfilter.model.EnterpriseSearchDataEst;
import com.tip.estimationfilter.model.FetchFilterDetails;

@FunctionalInterface
public interface SaveProcessFilterService {

	EnterpriseSearchDataEst getFilterDetils(FetchFilterDetails fetchFilterDetails);

}
