package com.tip.rplanner.service;

import com.tip.rplanner.model.WpRequest;

@FunctionalInterface
public interface FetchRplanWpDetailsService {
	public Object getWpdetails(WpRequest wpRequest);

}
