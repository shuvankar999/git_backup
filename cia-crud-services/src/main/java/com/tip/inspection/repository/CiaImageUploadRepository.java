package com.tip.inspection.repository;

import com.tip.inspection.model.InspectionUploadImageRequest;

@FunctionalInterface
public interface CiaImageUploadRepository {

	public String uploadCiaImage(InspectionUploadImageRequest inspectionUploadImageRequest);

}
