package com.tip.estimation.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import com.tip.estimation.model.FetchCustBundleRequest;
import com.tip.estimation.model.FetchCustBundleResponse;

@FunctionalInterface
public interface FetchCustBundleService {
	public List<FetchCustBundleResponse> getCustBundle(@RequestBody FetchCustBundleRequest fetchCustBundleRequest);

}
