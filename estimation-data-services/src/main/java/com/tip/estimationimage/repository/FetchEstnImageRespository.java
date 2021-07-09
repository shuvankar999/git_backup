package com.tip.estimationimage.repository;

import java.util.Map;

import com.tip.estimationimage.model.FetchEstnImageRequest;

@FunctionalInterface
public interface FetchEstnImageRespository {
	
	public Map<String, Object> getEstnImage(FetchEstnImageRequest fetchEstnImageRequest);

}
