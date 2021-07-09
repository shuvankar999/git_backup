package com.tip.assetimage.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tip.assetimage.model.AssetImageAccRequest;
import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.repository.AssetImageLocationRepository;
import com.tip.assetimage.repository.AssetImageRepository;
import com.tip.assetimage.repository.impl.AssetImageAccRepositoryImpl;
import com.tip.assetimage.service.AssetImageAccService;

@Service
public class AssetImageAccServiceImpl implements AssetImageAccService{
	
	static final Logger logger = LoggerFactory.getLogger(AssetImageAccServiceImpl.class);
	
	private String errorMsg = ""; 
	
	@Autowired
	AssetImageLocationRepository assetImageLocationRepository;
	
	@Autowired
	AssetImageRepository assetImageRepository;
	
	@Autowired
	AssetImageAccRepositoryImpl assetImageAccRepositoryImpl;

    
    private void createImageFolder(String imagesfolderpath) {
    	File file = new File(imagesfolderpath);
		try {
			if(!file.exists()){
				file.mkdirs();
			}
		} catch (Exception e) {
			errorMsg = "Exception Occured In creating image folder: " + imagesfolderpath;
			logger.error(errorMsg, e);
		}
    }

    
    @Override
	public AssetImageAccRequest uploadAssetImageAcc(AssetImageAccRequest assetImageAccRequest, MultipartFile file) {
		try {
			AssetImageRequest assetImageRequest = new AssetImageRequest();
			assetImageRequest.setAppCd(assetImageAccRequest.getAppCd());
			String imagesfolderpath = assetImageLocationRepository.getImageLocation(assetImageRequest);
			imagesfolderpath = imagesfolderpath + assetImageAccRequest.getAssetType() + "/" + assetImageAccRequest.getEquipmentNr() + "/" +assetImageAccRequest.getImageType()+ "/" + assetImageAccRequest.getCode();
			createImageFolder(imagesfolderpath);
			if (file != null) {
	           uploadFile(file,imagesfolderpath,assetImageAccRequest);
	           boolean returnFlag = assetImageAccRepositoryImpl.updateImageLoc(assetImageAccRequest);
	           if(!returnFlag)
	        	   assetImageAccRequest.setImageLoc("");
	        }else{
	        	errorMsg = "Exception Occured while uploading asset images";
	        }
		} catch (Exception e) {
			errorMsg = "Exception Occured while uploading asset images";
			logger.error(errorMsg, e);
		}
		
		return assetImageAccRequest;
    }
    
    private void uploadFile(MultipartFile file, String imagesfolderpath, AssetImageAccRequest assetImageAccRequest){
    	String fileName = null;
		try {
            fileName = file.getOriginalFilename();
            File img = new File(imagesfolderpath + "/" + fileName);
            byte[] bytes = file.getBytes();
            BufferedOutputStream buffStream =
                    new BufferedOutputStream(new FileOutputStream(img));
            buffStream.write(bytes);
            buffStream.close();
            assetImageAccRequest.setImageLoc(assetImageAccRequest.getAssetType() + "/" + assetImageAccRequest.getEquipmentNr() + "/" +assetImageAccRequest.getImageType()+ "/" + assetImageAccRequest.getCode() + "/" + fileName);
        } catch (Exception e) {
        	logger.error("Error Occured while uploading asset images",e);
        }
    }
    
}