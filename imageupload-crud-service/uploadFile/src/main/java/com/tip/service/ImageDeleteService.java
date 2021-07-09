package com.tip.service;

import java.util.Map;

import com.tip.model.DeleteImageRequest;

/**
 * @author Shuvankar Paul
 * Created on Nov 10, 2017
 * 
 */
@FunctionalInterface
public interface ImageDeleteService {

	public Map<String, Object> removeImage(DeleteImageRequest deleteImageRequest);

}
