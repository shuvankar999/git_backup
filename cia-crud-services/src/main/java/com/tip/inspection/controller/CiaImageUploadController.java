package com.tip.inspection.controller;

import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tip.inspection.model.InspectionUploadImageRequest;
import com.tip.inspection.model.InspectionUploadImageResponse;
import com.tip.inspection.model.ResponseObject;
import com.tip.inspection.service.CiaGenerateDocumentService;
import com.tip.inspection.service.CiaImageCountService;
import com.tip.inspection.service.CiaImageUploadService;
import com.tip.inspection.service.DocuwareService;

	@RestController
	@RequestMapping("/service/cia-crud-service/1.0/")
public class CiaImageUploadController {

	@Autowired
	CiaImageUploadService ciaImageUploadService;
	
	@Autowired
	CiaGenerateDocumentService ciaGenerateDocumentService;

	@Autowired
	CiaImageCountService ciaImageCountService;
	
	@Autowired
	DocuwareService docuwareService;

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        return new MultipartConfigElement("");
    }
    
	@RequestMapping(value = "/uploadCiaImages", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public InspectionUploadImageResponse multipleSave(@RequestPart(value = "files",required = false) MultipartFile[] files,
			@RequestPart("inspId") String inspId, @RequestPart("inspCd") String inspCd,
			@RequestPart("inspType") String inspType, @RequestPart("damageLineNr") String damageLineNr,
			@RequestPart(value = "damageDescription", required = false) String damageDescription,
			@RequestPart("imgType") String imgType, @RequestPart("imgName") String imgName,
			@RequestPart("appCd") String appCd, @RequestPart("imgComment") String imgComment,
			@RequestPart("imgCount") String imgCount, @RequestPart("deviceSerialNr") String deviceSerialNr,
			@RequestPart(value = "prevImgPath" ,required = false) String prevImgPath, HttpServletResponse response) throws Exception {
		
		InspectionUploadImageResponse inspectionUploadImageResponse = new InspectionUploadImageResponse();
		InspectionUploadImageRequest inspectionUploadImageRequest = new InspectionUploadImageRequest();
		inspectionUploadImageRequest.setAppCd(appCd);
		inspectionUploadImageRequest.setInspId(inspId);
		inspectionUploadImageRequest.setInspCd(inspCd);
		inspectionUploadImageRequest.setInspType(inspType);
		inspectionUploadImageRequest.setDamageLineNr(damageLineNr);
		inspectionUploadImageRequest.setDamageDescription(damageDescription);
		inspectionUploadImageRequest.setImgType(imgType);
		inspectionUploadImageRequest.setImgName(imgName);
		inspectionUploadImageRequest.setImgComment(imgComment);
		inspectionUploadImageRequest.setImgCount(imgCount);
		inspectionUploadImageRequest.setDeviceSerialNr(deviceSerialNr);
		inspectionUploadImageRequest.setPrevImgPath(prevImgPath);
		inspectionUploadImageResponse = ciaImageUploadService.uploadCiaImage(inspectionUploadImageRequest, files);
		int count = ciaImageCountService.getImageCount(inspectionUploadImageRequest.getInspId(),
				inspectionUploadImageRequest.getInspType());
		if (count == Integer.parseInt(inspectionUploadImageRequest.getImgCount())) {
			ResponseObject responseObject = ciaGenerateDocumentService.generateDocument(inspectionUploadImageRequest.getInspType(),inspectionUploadImageRequest.getInspId());
			/*response.setContentType("application/pdf");
			response.setHeader("Content-disposition", "inline;filename=" + responseObject.getFile().getName());
			IOUtils.copy(responseObject.getInputStream(), response.getOutputStream());
			responseObject.getOutputStream().close();
			responseObject.getInputStream().close();*/
			Map<String,String> newMap = responseObject.getDocuwareInput();
			newMap.put("docname", responseObject.getFile().getName());
			int docId = docuwareService.getDocuwareId(newMap,responseObject.getFile());
			inspectionUploadImageResponse.setDocId(docId);
		}
		return inspectionUploadImageResponse;
		}
		
	}
