package com.tip.rplanner.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tip.rplanner.model.WpRequest;
import com.tip.rplanner.repository.FetchRplanWpDetailsRepository;
import com.tip.rplanner.service.FetchRplanWpDetailsService;

@Service
@Transactional
public class FetchRplanWpDetailsServiceImpl implements FetchRplanWpDetailsService {

	@Autowired
	FetchRplanWpDetailsRepository fetchRplanWpDetailsRepository;

	@Override
	public Object getWpdetails(WpRequest wpRequest) {
		
		Map<String, Object> resultMap = fetchRplanWpDetailsRepository.getWpdetails(wpRequest);
		Object returnObj = null;
		for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
			if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
				List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
				returnObj = lst.get(0);
			}
		}
		return returnObj;
	}
}
