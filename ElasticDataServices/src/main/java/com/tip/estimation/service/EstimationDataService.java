package com.tip.estimation.service;

import java.net.UnknownHostException;

@FunctionalInterface
public interface EstimationDataService {
	
	public Long estimationDataTextSearchCount(String text) throws UnknownHostException;

}
