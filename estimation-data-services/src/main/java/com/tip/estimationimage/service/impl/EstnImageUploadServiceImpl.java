package com.tip.estimationimage.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tip.estimationimage.model.EstnImageRequest;
import com.tip.estimationimage.model.UploadEstnImageResponse;
import com.tip.estimationimage.repository.EstnImageLocationRepository;
import com.tip.estimationimage.repository.EstnImageUploadRepository;
import com.tip.estimationimage.service.EstnImageUploadService;

@Service
public class EstnImageUploadServiceImpl implements EstnImageUploadService {

	static final Logger logger = LoggerFactory.getLogger(EstnImageUploadServiceImpl.class);

	private String errorMsg = "";

	@Autowired
	EstnImageUploadRepository estnImageUploadRepository;

	@Autowired
	EstnImageLocationRepository estnImageLocationRepository;

	@Override
	public UploadEstnImageResponse uploadEstnImage(EstnImageRequest estnImageRequest, MultipartFile[] files) {
		UploadEstnImageResponse uploadEstnImageResponse = new UploadEstnImageResponse();
		try {

			String imagesfolderpath = estnImageLocationRepository.getImageLocation(estnImageRequest);
			
			imagesfolderpath = imagesfolderpath + estnImageRequest.getEstimationId()+"/"+ estnImageRequest.getPhotoType()+"/";
			createImageFolder(imagesfolderpath);
			if (files != null && files.length > 0) {
				for (int i = 0; i < files.length; i++) {
					uploadFile(files[i], imagesfolderpath, estnImageRequest, uploadEstnImageResponse);
				}
			} else {
				uploadEstnImageResponse.setErrorCd("Unable to upload. File is empty");
			}
		} catch (Exception e) {
			uploadEstnImageResponse.setErrorCd("Exception Occured while uploading estimation images");
			logger.error(errorMsg, e);
		}
		return uploadEstnImageResponse;
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

	private void uploadFile(MultipartFile files, String imagesfolderpath, EstnImageRequest estnImageRequest,
			UploadEstnImageResponse uploadEstnImageResponse) {
		String fileName = null;
		try {
			fileName = files.getOriginalFilename();
			File file = new File(imagesfolderpath + "/" + estnImageRequest.getPhotoName());		
			byte[] bytes = files.getBytes();
			BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(file));
			buffStream.write(bytes);
			buffStream.close();

			uploadEstnImageResponse.setErrorCd(estnImageUploadRepository.uploadEstnImage(estnImageRequest));
		} catch (Exception e) {
			logger.error("Error Occured while uploading estimation images", e);
			uploadEstnImageResponse.setErrorCd("Failed to upload " + fileName);
		}
	}
}
