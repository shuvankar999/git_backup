package com.tip.inspection.repository;

import com.tip.inspection.model.InspectionUploadImageRequest;

@FunctionalInterface
public interface CiaImagePathRepository {

	public String getImageLocation(InspectionUploadImageRequest inspectionUploadImageRequest);

}
