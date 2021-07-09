package com.tip.repository;

import java.util.Map;

import com.tip.model.DeleteImageRequest;

/**
 * @author Shuvankar Paul
 * Created on Nov 10, 2017
 * 
 */
@FunctionalInterface
public interface ImageDeteleRepository {

	public Map<String, Object> removeImage(DeleteImageRequest deleteImageRequest);
}
