package com.tip.assetimage.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tip.assetimage.model.AssetImageAccRequest;
import com.tip.assetimage.service.AssetImageAccService;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")
public class AssetImageAccController {

	@Autowired
	AssetImageAccService assetImageAccService;
	
    @Autowired
	private Environment env;

	@RequestMapping(value = "uploadEquipPhoto", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public Object uploadImageAcc(@RequestPart("file") MultipartFile file,
			@RequestPart("equipmentNr") String equipmentNr, @RequestPart("appCd") String appCd, @RequestPart("assetType") String assetType,
			@RequestPart("imageType") String imageType, @RequestPart("code") String code, @RequestPart(value="seq", required=false) String seq, HttpServletRequest request) {
		AssetImageAccRequest responseJson = new AssetImageAccRequest();
		boolean sizeFlag = true;
		if(file.getSize() > Long.valueOf(env.getProperty("img_file_size"))){
			sizeFlag = false;
		}
		
		if(sizeFlag){
			AssetImageAccRequest assetImageAccRequest = new AssetImageAccRequest();
			assetImageAccRequest.setAssetType(assetType);
			assetImageAccRequest.setAppCd(appCd);
			assetImageAccRequest.setImageType(imageType);
			assetImageAccRequest.setCode(code);
			assetImageAccRequest.setSeq(seq);
			assetImageAccRequest.setEquipmentNr(Integer.valueOf(equipmentNr));
			assetImageAccRequest.setSsoId(request.getHeader("ssoId"));
			responseJson = assetImageAccService.uploadAssetImageAcc(assetImageAccRequest, file);
			return responseJson;
    	}else
    		return env.getProperty("img_upload_error_msg");
		
	}
}
