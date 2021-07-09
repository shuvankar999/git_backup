package com.tip.estimationimage.service;

import com.tip.estimationimage.model.FetchEstnImageRequest;
import com.tip.estimationimage.model.FetchEstnImageResponse;

@FunctionalInterface
public interface FetchEstnImageService {

	public FetchEstnImageResponse getEstnImage(FetchEstnImageRequest fetchEstnImageRequest);

}
