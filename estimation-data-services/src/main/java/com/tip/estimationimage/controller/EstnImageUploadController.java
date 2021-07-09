package com.tip.estimationimage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tip.estimationimage.model.EstnImageRequest;
import com.tip.estimationimage.model.UploadEstnImageResponse;
import com.tip.estimationimage.service.EstnImageUploadService;

	@RestController
	@RequestMapping("/service/estimation-data-service/1.0/")
	public class EstnImageUploadController {
		
		@Autowired
		EstnImageUploadService estnImageUploadService;
		
		 @Autowired
			private Environment env;
		
		    @RequestMapping(value = "uploadEstnImages", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
		    @ResponseBody
		    public UploadEstnImageResponse multipleSave(@RequestPart("file") MultipartFile[] files,@RequestPart("estimationId") String estimationId,@RequestPart("estnworkorderId") String estnworkorderId,
		    		@RequestPart("estnWotId") String estnWOTId,@RequestPart("photoType") String photoType,@RequestPart("photoName") String photoName,@RequestPart("ssoId") String ssoId,@RequestPart("appCd") String appCd) {
		    
			boolean sizeFlag = true;
			UploadEstnImageResponse uploadEstnImageResponse = new UploadEstnImageResponse();
	    	for (int i = 0; i < files.length; i++) {
	    		if(files[i].getSize() > Long.valueOf(env.getProperty("img_file_size"))){
	    			sizeFlag = false;
	    			break;
	    		}
	        }
	    	if(sizeFlag){
	    		EstnImageRequest estnImageRequest = new EstnImageRequest();
		    	estnImageRequest.setEstimationId(estimationId);
		    	estnImageRequest.setEstnworkorderId(estnworkorderId);
		    	estnImageRequest.setEstnWotId(estnWOTId);
		    	estnImageRequest.setPhotoType(photoType);
		    	estnImageRequest.setPhotoName(photoName);
		    	estnImageRequest.setSsoId(ssoId);
		    	estnImageRequest.setAppCd(appCd);
		    	uploadEstnImageResponse = estnImageUploadService.uploadEstnImage(estnImageRequest,files);
	    	} else{
	    		uploadEstnImageResponse.setErrorCd(env.getProperty("img_upload_error_msg"));
	    	}
	    	return uploadEstnImageResponse;
	
	}
	}
