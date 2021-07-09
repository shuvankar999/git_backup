package com.tip.assetimage.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.model.AssetImageResponse;
import com.tip.assetimage.model.DeleteAssetImageRequest;
import com.tip.assetimage.model.DeleteAssetImageResponse;
import com.tip.assetimage.model.UploadAssetImageResponse;
import com.tip.assetimage.service.AssetImageService;


@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class AssetImageController {

    @Autowired
    AssetImageService assetImageService;
    
    @Autowired
	private Environment env;
    
    public static final Logger logger = LoggerFactory.getLogger(AssetImageController.class);

    @RequestMapping(value = "getAssetImageForDashboard", method = RequestMethod.POST)
    @ResponseBody
    public AssetImageResponse getAssetDashboardImage(@RequestBody AssetImageRequest assetImageRequest) {
    	return assetImageService.getAssetDashboardImage(assetImageRequest);
    }
    
    @RequestMapping(value = "uploadAssetImages", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public UploadAssetImageResponse multipleSave(@RequestPart("file") MultipartFile[] files,@RequestPart("unitNr") String unitNr,@RequestPart("appCd") String appCd,
    		@RequestPart("ssoId") String ssoId,@RequestPart("assetType") String assetType,@RequestPart("imageType") String imageType) {
    	boolean sizeFlag = true;
    	UploadAssetImageResponse uploadAssetImageResponse = new UploadAssetImageResponse();
    	for (int i = 0; i < files.length; i++) {
    		if(files[i].getSize() > Long.valueOf(env.getProperty("img_file_size"))){
    			sizeFlag = false;
    			break;
    		}
        }
    	if(sizeFlag){
    		AssetImageRequest assetImageRequest = new AssetImageRequest();
        	assetImageRequest.setAppCd(appCd);
        	assetImageRequest.setAssetType(assetType);
        	assetImageRequest.setImageType(imageType);
        	assetImageRequest.setSsoId(ssoId);
        	assetImageRequest.setUnitNr(unitNr);
        	uploadAssetImageResponse = assetImageService.uploadAssetImage(assetImageRequest,files);
    	} else{
    		uploadAssetImageResponse.setMsg(env.getProperty("img_upload_error_msg"));
    	}
    	return uploadAssetImageResponse;
    }
    
    @RequestMapping(value = "deleteAssetImage", method = RequestMethod.POST)
    @ResponseBody
    public DeleteAssetImageResponse deleteAssetImage(@RequestBody DeleteAssetImageRequest deleteImageRequest) {
    	return assetImageService.deleteAssetImage(deleteImageRequest);
    }
}