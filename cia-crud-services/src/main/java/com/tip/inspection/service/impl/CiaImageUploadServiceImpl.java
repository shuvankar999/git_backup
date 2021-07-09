package com.tip.inspection.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.io.IOUtils;
//import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.tip.inspection.model.InspectionUploadImageRequest;
import com.tip.inspection.model.InspectionUploadImageResponse;
import com.tip.inspection.repository.CiaImagePathRepository;
import com.tip.inspection.repository.CiaImageUploadRepository;
import com.tip.inspection.service.CiaImageUploadService;

@Service
@Transactional
public class CiaImageUploadServiceImpl implements CiaImageUploadService {
	
	static final Logger logger = LoggerFactory.getLogger(CiaImageUploadServiceImpl.class); 
	
	private String errorMsg = "";
	
	@Autowired
	CiaImagePathRepository ciaImagePathRepository;
	
	@Autowired
	CiaImageUploadRepository ciaImageUploadRepository;
	
	@Override
	public InspectionUploadImageResponse uploadCiaImage(InspectionUploadImageRequest inspectionUploadImageRequest,
			MultipartFile[] files) throws Exception {
		InspectionUploadImageResponse inspectionUploadImageResponse = new InspectionUploadImageResponse();
		try {
			String imagesfolderpath = ciaImagePathRepository.getImageLocation(inspectionUploadImageRequest);
			imagesfolderpath = imagesfolderpath + inspectionUploadImageRequest.getInspId() + "/";
			if (inspectionUploadImageRequest.getPrevImgPath() != null && files == null || files.length == 0) {
				String prevImgPath= inspectionUploadImageRequest.getPrevImgPath();
				createImageFolder(imagesfolderpath);
				File opFile = new File(imagesfolderpath + inspectionUploadImageRequest.getImgName());
				uploadImgFromUrl(prevImgPath,opFile);
				inspectionUploadImageRequest.setImgPath(imagesfolderpath);
				inspectionUploadImageResponse.setErrorCd(ciaImageUploadRepository.uploadCiaImage(inspectionUploadImageRequest));
			} else if ((inspectionUploadImageRequest.getPrevImgPath() == null || "".equals(inspectionUploadImageRequest.getPrevImgPath())) && files.length > 0) {
				logger.info("ImageNewPath "+imagesfolderpath);
				createImageFolder(imagesfolderpath);
				for (int i = 0; i < files.length; i++) {
					uploadFile(files[i], imagesfolderpath, inspectionUploadImageRequest, inspectionUploadImageResponse);
				} 
			} else {
				inspectionUploadImageResponse.setErrorCd("Request having missing file or prevImagePath");
			}
		}catch (Exception e) {
				inspectionUploadImageResponse.setErrorCd("Exception Occured while uploading inspection images");
				logger.error(errorMsg, e);
		}
		return inspectionUploadImageResponse;
	}
	
	private void uploadImgFromUrl(String prevImgPath, File opFile) throws IOException{
		InputStream is = null;
		try {
			  URL url = new URL(prevImgPath);
			  is = url.openStream ();
			  byte[] bytesArray = IOUtils.toByteArray(is);
			  BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(opFile));
			  buffStream.write(bytesArray);
			  buffStream.close();
		}
		catch (IOException e) {
			logger.error("Failed while reading bytes from %s: %s", prevImgPath, e);
		}
		finally {
		  if (is != null) {
			  is.close();
		  	}
		}
	}

	private void uploadFile(MultipartFile files, String imagesfolderpath,
			InspectionUploadImageRequest inspectionUploadImageRequest,
			InspectionUploadImageResponse inspectionUploadImageResponse) {
		String fileName = null;
		try {
			fileName = inspectionUploadImageRequest.getImgName();
			File file = new File(imagesfolderpath + "/" + fileName);
			byte[] bytes = files.getBytes();
			BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file));
			buffStream.write(bytes);
			buffStream.close();
			inspectionUploadImageRequest.setImgPath(imagesfolderpath);
			inspectionUploadImageResponse.setErrorCd(ciaImageUploadRepository.uploadCiaImage(inspectionUploadImageRequest));
		} catch (Exception e) {
			logger.error("Error Occured while uploading estimation images", e);
			inspectionUploadImageResponse.setErrorCd("Failed to upload " + fileName);
		}
		
	}

	private void createImageFolder(String imagesfolderpath) {
		File file = new File(imagesfolderpath);
		try {
			if (!file.exists()) {
				file.mkdirs();
			}
		} catch (Exception e) {
			errorMsg = "Exception Occured In creating image folder: " + imagesfolderpath;
			logger.error(errorMsg, e);
		}
		
	}
	
	 
}