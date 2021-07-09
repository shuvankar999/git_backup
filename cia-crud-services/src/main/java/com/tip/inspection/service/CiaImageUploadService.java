package com.tip.inspection.service;

import java.io.FileNotFoundException;

import org.springframework.web.multipart.MultipartFile;

import com.tip.inspection.model.InspectionUploadImageRequest;
import com.tip.inspection.model.InspectionUploadImageResponse;

public interface CiaImageUploadService {

	public InspectionUploadImageResponse uploadCiaImage(InspectionUploadImageRequest inspectionUploadImageRequest,
			MultipartFile[] files) throws FileNotFoundException, Exception;

}
