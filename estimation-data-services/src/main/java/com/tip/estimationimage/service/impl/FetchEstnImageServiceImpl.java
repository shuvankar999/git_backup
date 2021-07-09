package com.tip.estimationimage.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.estimationimage.model.FetchEstnImageRequest;
import com.tip.estimationimage.model.FetchEstnImageResponse;
import com.tip.estimationimage.model.ImageList;
import com.tip.estimationimage.repository.FetchEstnImageRespository;
import com.tip.estimationimage.service.FetchEstnImageService;



	@Service
	public class FetchEstnImageServiceImpl implements FetchEstnImageService{
		
		@Autowired
		FetchEstnImageRespository  fetchEstnImageRespository;
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		@Override
		public FetchEstnImageResponse getEstnImage(FetchEstnImageRequest fetchEstnImageRequest) {
			Map<String, Object> resultMap = fetchEstnImageRespository.getEstnImage(fetchEstnImageRequest);
			
			FetchEstnImageResponse fetchEstnImageResponse =new FetchEstnImageResponse();
			List<ImageList> estnImagelist = new ArrayList<ImageList>();
			
			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						for( Map<String, Object> imageMapObject : (List<Map<String, Object>>) entry.getValue()){
							ImageList imageList = new ImageList();
							imageList.setPhotoType((String) imageMapObject.get("photoType"));
							imageList.setPhotoName((String) imageMapObject.get("photoName"));
							imageList.setPhotoLoc((String) imageMapObject.get("photoLoc"));
							estnImagelist.add(imageList);
							
						}
						fetchEstnImageResponse.getEstnImagelist().addAll(estnImagelist);		
				}
			}
		}
		return fetchEstnImageResponse;
	}

}