package com.tip.unitspec.service;

import java.net.UnknownHostException;
import java.util.HashMap;

import com.tip.unitspec.model.ArrayOfUnitSpecData;


public interface UnitSpecDataService {
	public ArrayOfUnitSpecData getUnitData(HashMap<String, String> fields) throws UnknownHostException;
	public Long getUnitSpecDataCount(HashMap<String, String> fields) throws UnknownHostException;
	public ArrayOfUnitSpecData unitDataSpecTextSearch(String text) throws UnknownHostException;
	public Long unitDataSpecTextSearchCount(String text) throws UnknownHostException;
}
