package com.tip.assetimage.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import com.tip.asset.util.AssetConstants;
import com.tip.assetimage.model.AssetImageDetails;
import com.tip.assetimage.model.AssetImageRequest;
import com.tip.assetimage.model.AssetImageResponse;
import com.tip.assetimage.model.DeleteAssetImageRequest;
import com.tip.assetimage.model.DeleteAssetImageResponse;
import com.tip.assetimage.model.UploadAssetImageResponse;
import com.tip.assetimage.repository.AssetImageLocationRepository;
import com.tip.assetimage.repository.AssetImageRepository;
import com.tip.assetimage.repository.UploadAssetImageRepository;
import com.tip.assetimage.service.AssetImageService;

@Service
public class AssetImageServiceImpl implements AssetImageService{
	
	static final Logger logger = LoggerFactory.getLogger(AssetImageServiceImpl.class);
	
	private static final String IMAGE = "image_";	
	private static final String IMAGE_FILE_TYPE = ".jpg";
	private String errorMsg = ""; 
	
	@Autowired
	AssetImageLocationRepository assetImageLocationRepository;
	
	@Autowired
	AssetImageRepository assetImageRepository;
	
	@Autowired
	UploadAssetImageRepository uploadAssetImageRepository;
	    
    @Override
	public AssetImageResponse getAssetDashboardImage(AssetImageRequest assetImageRequest) {
    	AssetImageResponse assetImageResponse = new AssetImageResponse();
    	List<AssetImageDetails> standardImagesLst = new ArrayList<>();
    	List<AssetImageDetails> additionalImagesLst = new ArrayList<>();
    	Map<String, Object> resultMap = assetImageRepository.getAssetDashboardImage(assetImageRequest);
		try {
			String imagesfolderpath = assetImageLocationRepository.getImageLocation(assetImageRequest);
			imagesfolderpath=imagesfolderpath+assetImageRequest.getAssetType()+"/"+assetImageRequest.getUnitNr()+"/Images/";
			createImageFolder(imagesfolderpath);
			for (Map.Entry<String, Object> entry : resultMap.entrySet())
			{
	            if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
	            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
	            	for(int i=0;i<lst.size();i++)
	            	{
	            		Map<String, Object> lImageMap = lst.get(i);
	            		AssetImageDetails assetImageDetails = new AssetImageDetails();
	            		if(lImageMap.get("Id") != null && lImageMap.get("Picture") != null)
	            		{
	            			int id = (int)lImageMap.get("Id");
	            			String unitNumber = lImageMap.get("AssetNr").toString();	    				
		    				String imageName = IMAGE+ id +"_"+ unitNumber +IMAGE_FILE_TYPE;
		    				File image = new File(imagesfolderpath + "/" + imageName);
		    				writeImage(image,lImageMap.get("Picture"));
		    				
		    				assetImageDetails.setImageName(imageName);
		    				assetImageDetails.setImagePath(image.getAbsolutePath());
		    				assetImageDetails.setImageId(id);
	            		}
	    				assetImageDetails.setImageType((String)lImageMap.get("ImageType"));
	    				assetImageDetails.setImageLable((String)lImageMap.get("ImageLable"));
	    				assetImageDetails.setAssetType((String)lImageMap.get("AssetType"));
	    				
	    				if(AssetConstants.STANDARD_IMAGES.equalsIgnoreCase((String)lImageMap.get("ImageGroup")))
	    					standardImagesLst.add(assetImageDetails);
	    				else
	    					additionalImagesLst.add(assetImageDetails);
	            	}
	            }
			}
			assetImageResponse.getStandardImages().addAll(standardImagesLst);
			assetImageResponse.getAdditionalImages().addAll(additionalImagesLst);
		} catch (Exception e) {
			errorMsg = "Exception Occured while fetching asset images";
			logger.error(errorMsg, e);
		}
		assetImageResponse.setErrorMsg(errorMsg);
        return assetImageResponse;
    }
    
    /*@Override
	public AssetImageResponse getAssetDashboardImage(AssetImageRequest assetImageRequest) {
    	AssetImageResponse assetImageResponse = new AssetImageResponse();
    	List<AssetImageDetails> assetImageDetailsLst = new ArrayList<>();
    	ResultSet resultSet = assetImageRepository.getAssetDashboardImage(assetImageRequest);
		try {
			String imagesfolderpath = assetImageLocationRepository.getImageLocation(assetImageRequest);
			imagesfolderpath=imagesfolderpath+assetImageRequest.getAssetType()+"/"+assetImageRequest.getUnitNr()+"/Images/";
			imagesfolderpath = "D:/" + assetImageRequest.getUnitNr();
			createImageFolder(imagesfolderpath);
			while (resultSet.next()) {
				String labelType = resultSet.getString(2);
				if(labelType.isEmpty())
					labelType = "Not Tagged";
				else if(labelType.trim().isEmpty())
					labelType = "Not Tagged";
				String unitNumber = resultSet.getString(3);
				String id = resultSet.getString(4);
				String imageName = IMAGE+ id +"_"+ unitNumber +IMAGE_FILE_TYPE;
				File image = new File(imagesfolderpath + "/" + imageName);
				writeImage(image,resultSet);
				AssetImageDetails assetImageDetails = new AssetImageDetails();
				assetImageDetails.setImageName(imageName);
				assetImageDetails.setLabelType(labelType);
				assetImageDetails.setImagePath(image.getAbsolutePath());
				assetImageDetailsLst.add(assetImageDetails);
			}
			assetImageResponse.getAssetImageDetails().addAll(assetImageDetailsLst);
		} catch (Exception e) {
			errorMsg = "Exception Occured while fetching asset images";
			logger.error(errorMsg, e);
		}
		assetImageResponse.setErrorMsg(errorMsg);
        return assetImageResponse;
    }*/
    
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
    
    private void writeImage(File image, Object imageContent) {
    	try
    	{
    	    FileOutputStream fos = new FileOutputStream(image);
    	    BufferedOutputStream bos = new BufferedOutputStream(fos);
    	    byte[] b = (byte[]) imageContent;
    	    bos.write(b);
    	    fos.close();
    	    bos.close();
    	}
    	catch ( IOException e)
    	{
    		errorMsg = "Exception Occured while writing asset images";
			logger.error(errorMsg, e);
    	}
    }
    
   /* private void writeImage(File image, ResultSet resultSet) {
    	FileOutputStream fos = null;
    	InputStream is = null;
		try {
			fos = new FileOutputStream(image);
	    	is = resultSet.getBinaryStream(1);
			byte[] buffer = new byte[1024];
	    	if(is!=null)
			{	  
				while (is.read(buffer) > 0) {
					fos.write(buffer);
				}
				is.close();
			}
	    	fos.close();
		} catch (SQLException | IOException e) {
			if(fos != null)
			{
				try {
					fos.close();
				} catch (IOException e1) {
					errorMsg = "Exception Occured while writing asset images";
					logger.error(errorMsg, e1);
				}
			}
			errorMsg = "Exception Occured while writing asset images";
			logger.error(errorMsg, e);
		}
    }*/
    
    @Override
	public UploadAssetImageResponse uploadAssetImage(AssetImageRequest assetImageRequest, MultipartFile[] files) {
    	UploadAssetImageResponse uploadAssetImageResponse = new UploadAssetImageResponse();
		try {
			String imagesfolderpath = assetImageLocationRepository.getImageLocation(assetImageRequest);
			imagesfolderpath = imagesfolderpath + assetImageRequest.getAssetType() + "/" + assetImageRequest.getUnitNr() + "/Images/";
			createImageFolder(imagesfolderpath);
			if (files != null && files.length > 0) {
	            for (int i = 0; i < files.length; i++) {
	            	uploadFile(files[i],imagesfolderpath,assetImageRequest,uploadAssetImageResponse);
	            }
	        } else {
	        	uploadAssetImageResponse.setMsg("Unable to upload. File is empty");
	        }
		} catch (Exception e) {
			uploadAssetImageResponse.setMsg("Exception Occured while uploading asset images");
			logger.error(errorMsg, e);
		}
        return uploadAssetImageResponse;
    }
    
    private void uploadFile(MultipartFile files, String imagesfolderpath, AssetImageRequest assetImageRequest, UploadAssetImageResponse uploadAssetImageResponse){
    	String fileName = null;
		try {
            fileName = files.getOriginalFilename();
            File file = new File(imagesfolderpath + "/" + fileName);
            byte[] bytes = files.getBytes();
            BufferedOutputStream buffStream =
                    new BufferedOutputStream(new FileOutputStream(file));
            buffStream.write(bytes);
            buffStream.close();
            
            uploadAssetImageResponse.setMsg(uploadAssetImageRepository.uploadImage(file, assetImageRequest));
        } catch (Exception e) {
        	logger.error("Error Occured while uploading asset images",e);
        	uploadAssetImageResponse.setMsg("Failed to upload " + fileName);
        }
    }
    
    @Override
	public DeleteAssetImageResponse deleteAssetImage(@RequestBody DeleteAssetImageRequest deleteImageRequest) {
    	DeleteAssetImageResponse deleteAssetImageResponse = new DeleteAssetImageResponse();
    	AssetImageRequest assetImageRequest = new AssetImageRequest();
    	assetImageRequest.setAppCd(deleteImageRequest.getAppCd());
    	assetImageRequest.setAssetType(deleteImageRequest.getAssetType());
    	assetImageRequest.setImageType(deleteImageRequest.getImageType());
    	assetImageRequest.setSsoId(deleteImageRequest.getSsoId());
    	assetImageRequest.setUnitNr(deleteImageRequest.getUnitNr());
		try {
			String imagesfolderpath = assetImageLocationRepository.getImageLocation(assetImageRequest);
			imagesfolderpath = imagesfolderpath + assetImageRequest.getAssetType() + "/" + assetImageRequest.getUnitNr() + "/Images/";
			File file = new File(imagesfolderpath + deleteImageRequest.getImageName());
			try {
				if(file.exists()){
					file.delete();
				}
				else
				{
					deleteAssetImageResponse.setMsg("File does not exist in central repository");
				}
				deleteAssetImageResponse.setMsg(uploadAssetImageRepository.deleteImage(deleteImageRequest));
			} catch (Exception e) {
				errorMsg = "Exception Occured In creating image folder: " + imagesfolderpath;
				logger.error(errorMsg, e);
			}
		} catch (Exception e) {
			deleteAssetImageResponse.setMsg("Exception Occured while deleting asset images");
			logger.error(errorMsg, e);
		}
        return deleteAssetImageResponse;
    }
}