package com.tip.units.service;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.tip.units.model.ArrayOfUnitsData;

public interface UnitsDataService {
  
	public ArrayOfUnitsData getUnitsData(Map<String, Object> fields) throws UnknownHostException;
	public Long getUnitsDataCount(HashMap<String, Object> fields) throws UnknownHostException;
	public ArrayOfUnitsData unitsDataTextSearch(String text) throws UnknownHostException;
	public Long unitsDataTextSearchCount(String text) throws UnknownHostException;
}
