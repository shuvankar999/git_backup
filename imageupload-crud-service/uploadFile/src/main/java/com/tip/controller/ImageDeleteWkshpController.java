package com.tip.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tip.model.DeleteImageRequest;
import com.tip.service.ImageDeleteService;

/**
 * @author Shuvankar Paul Created on Nov 10, 2017
 * 
 */

@Controller
@RestController
@ComponentScan
@RequestMapping("/TIP/rest/workshop-service/1.0/")
public class ImageDeleteWkshpController {

	@Autowired
	private ImageDeleteService imageDeleteService;

	private Logger logger = LoggerFactory.getLogger(ImageDeleteWkshpController.class);


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "deleteImage", method = RequestMethod.POST)
	public Map<String, Object> removeImage(@RequestBody DeleteImageRequest deleteImageRequest) throws IOException {


		Map<String, Object> responseMap = new HashMap();
		
		if (deleteImageRequest.getImageName() != null && deleteImageRequest.getImageLocation()!=null) {
			
			logger.info("imgLocation>>>>>" + deleteImageRequest.getImageLocation());
			logger.info("ImgName>>>>>" + deleteImageRequest.getImageName());
			File imgFile = new File(deleteImageRequest.getImageLocation());
			if (!imgFile.exists()) {
				logger.error("File not exists!!");
				responseMap.put("Error_Cd", "File does not exists");
				responseMap.put("Image_Location", deleteImageRequest.getImageLocation());
			} else {
				try {
					imgFile.delete();
					responseMap = imageDeleteService.removeImage(deleteImageRequest);
				} catch (Exception e) {
					logger.error("An error occured while removing the image file: " + e);
				}
			}
		}
		return responseMap;
	}
}
