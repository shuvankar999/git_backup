package com.tip.estimationimage.repository;

import com.tip.estimationimage.model.EstnImageRequest;

	@FunctionalInterface
	public interface EstnImageUploadRepository {
		
		public String uploadEstnImage(EstnImageRequest estnImageRequest);
	}
