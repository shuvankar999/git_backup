package com.tip.estimationimage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.estimationimage.model.FetchEstnImageRequest;
import com.tip.estimationimage.model.FetchEstnImageResponse;
import com.tip.estimationimage.service.FetchEstnImageService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class FetchEstnImageController {
		
		@Autowired
		FetchEstnImageService fetchEstnImageService;
		
		 	@RequestMapping(value = "fetchEstnImages", method = RequestMethod.POST)
		    @ResponseBody
		    public FetchEstnImageResponse getEstnImage(@RequestBody FetchEstnImageRequest fetchEstnImageRequest) {
		    	return fetchEstnImageService.getEstnImage(fetchEstnImageRequest);
		    }
	
	}
