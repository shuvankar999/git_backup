package com.tip.service.impl;

import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.model.DeleteImageRequest;
import com.tip.repository.ImageDeteleRepository;
import com.tip.service.ImageDeleteService;

/**
 * @author Shuvankar Paul
 * Created on Nov 10, 2017
 * 
 */

@Service
@Transactional
public class ImageDeteleServiceImpl implements ImageDeleteService{

	@Autowired
	ImageDeteleRepository imageDeteleRepository;
	
	@Override
	public Map<String, Object> removeImage(DeleteImageRequest deleteImageRequest) {
		return imageDeteleRepository.removeImage(deleteImageRequest);
	}

}
