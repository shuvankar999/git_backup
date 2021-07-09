package com.tip.asset.service;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import com.tip.asset.model.ArrayOfDynamicPosVal;

public interface DynamicPosValService {
   
	public ArrayOfDynamicPosVal getDynamicPosVals(Map<String, String> fields) throws UnknownHostException;
	public Map<String,List<String>> getAllDynamicPosVals(List<Map<String, String>> fielsList) throws UnknownHostException;
}