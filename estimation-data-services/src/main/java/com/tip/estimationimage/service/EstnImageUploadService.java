package com.tip.estimationimage.service;

import org.springframework.web.multipart.MultipartFile;

import com.tip.estimationimage.model.EstnImageRequest;
import com.tip.estimationimage.model.UploadEstnImageResponse;

@FunctionalInterface
public interface EstnImageUploadService {

  public UploadEstnImageResponse uploadEstnImage(EstnImageRequest estnImageRequest, MultipartFile[] files);

}
