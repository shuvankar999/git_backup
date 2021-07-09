package com.tip.estimationimage.repository;

import com.tip.estimationimage.model.EstnImageRequest;


@FunctionalInterface
public interface EstnImageLocationRepository {
	
	public String getImageLocation(EstnImageRequest estnImageRequest);

}
