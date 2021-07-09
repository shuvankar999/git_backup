package com.tip.inspection.service;

import java.io.File;
import java.util.Map;

@FunctionalInterface
public interface DocuwareService {

	public int getDocuwareId(Map<String, String> newMap,File file);
	
}
