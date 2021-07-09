package com.tip.assetpdf.controller;

import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tip.assetpdf.model.CommonUtil;
import com.tip.assetpdf.model.UnitsMainData;
import com.tip.assetpdf.service.UnitServiceIfc;

@RestController
@RequestMapping("/service/elastic-data-service/1.0/")

public class AssetPDFController {

    @Autowired
    UnitServiceIfc unitService;
    
    

    public static final Logger logger = LoggerFactory.getLogger(AssetPDFController.class);

    @RequestMapping(value = "exportUnitSpec", method = RequestMethod.POST)
    @ResponseBody
    public void exportUnitSpec(@RequestParam("file") MultipartFile file,@RequestParam("unitNr") String unitNr,
				@RequestParam("unitCatgrpCode") String unitCatGroup,
				@RequestParam("printFormat") String printFormat,HttpServletResponse response) throws Exception {
    	 ByteArrayOutputStream outData;
    	  ByteArrayOutputStream catOutData;
    	  String errorMessage = "";
    	  String absGalleryPath="";
		try {
			
			outData = new ByteArrayOutputStream();
			logger.info("UnitBeanMB.first(..).... START");
			// String unitNr = "102403";
			// String unitCatGroup = "TCS31341NNNAF";
		//	String unitNr = unitNr;
		//	String unitCatGroup = assetData.getUnitCatgrpCode();
			// logger.info("unitNr : "+unitNr);
			logger.info("unitCatGroup : " + unitCatGroup);
			File imageFile = new File(file.getOriginalFilename());
			imageFile.createNewFile();
		    FileOutputStream fos = new FileOutputStream(imageFile);
		    fos.write(file.getBytes());
		    fos.close();
		 //   return convFile;
			Image image = ImageIO.read(new File(imageFile.getName()));
			//Image image =assetData.getFile();

			FileOutputStream out = null;
			FileInputStream in = null;

//			String option1 = req.getParameter("saveRadioButton");
//			String option2 = req.getParameter("saveRadioButton1");
			String heading = "";// unitService.getAxleAndCatDesc(unitCatGroup);
			String axle = unitService.getAxle(unitCatGroup);
			String catDesc = unitService.getCatDesc(unitCatGroup);
			String picLocation = "";
			if (!"0".equals(axle) || (!CommonUtil.checkNull(axle))) {
				heading = CommonUtil.checkNullObject(axle) + "-Axle "
						+ CommonUtil.checkNullObject(catDesc);
			} else {
				heading = CommonUtil.checkNullObject(catDesc);
			}
			logger.info("---axle--" + axle + "--catDesc---" + catDesc
					+ "-------heading--------:" + heading);

			if ("2".equals(printFormat)) {
				logger.info("*************FullSpecPrint******************");
				outData = unitService.getUnitFullSpecPrintDetails(unitNr,
						absGalleryPath);
			} else {
				if ("3".equals(printFormat)) {
					logger
							.info("*************CustomerAutoPicure******************");

					char[] cat = unitCatGroup.toCharArray();
					String str1 = String.valueOf(cat[1]);
					// str1.toLowerCase();
					String str2 = String.valueOf(cat[0]) + str1.toLowerCase();
					logger.info("str : " + str2 + "axle  :" + axle);
					// picLocation = absGalleryPath+"/images/"+str2+axle+".wmf";
					picLocation = absGalleryPath + "/images/" + str2 + axle
							+ ".PNG";
					// picLocation = absGalleryPath+"/images/Blank.bmp";
					logger
							.info("---------------Picture location------------------:"
									+ picLocation);
					outData = unitService.getNoPicture(unitNr, heading,
							absGalleryPath, true, picLocation,image);
				} else if ("4".equals(printFormat)) {
					logger
							.info("*************CustomerChoosePicure******************");
					// String path = req.getParameter("file");
					// String path = req.getParameter("file");
					String customerImagePath="";
					logger.info("path : " + file.getOriginalFilename());
					if (CommonUtil.checkNull(file.getOriginalFilename())) {

						/*
						 * File file12 = new
						 * File(absGalleryPath+"/images/customimage.wmf");
						 * 
						 * in = new FileInputStream(path); out = new
						 * FileOutputStream(file12); //logger.info("File Name is "
						 * + CommonUtil.checkNullObject(in)); int nbytes; byte[]
						 * buf = new byte[2048]; while ((nbytes = in.read(buf))
						 * != -1) { out.write(buf, 0, nbytes); } out.close();
						 */
						logger.info("before outData ....." + customerImagePath);
						outData = unitService.getNoPicture(unitNr, heading,
								absGalleryPath, true, customerImagePath,image);
					} else {
						outData = unitService.getNoPicture(unitNr, heading,
								absGalleryPath, false, customerImagePath,image);

					}
					// outData =
					// unitService.getNoPicture(unitNr,heading,absGalleryPath,true,path);
				} else if ("5".equals(printFormat)) {
					logger.info("*************CustomerNoPicure******************");
					outData = unitService.getNoPicture(unitNr, heading,
							absGalleryPath, false, picLocation,image);
				}
			}
			catOutData = new ByteArrayOutputStream();
			catOutData = outData;
			if (catOutData.size() == 0) {
				errorMessage = "No Records Found.";
			} else {
				errorMessage = "";
			}
			
			byte[] arr = catOutData.toByteArray();
		
			ServletOutputStream opstrm = response.getOutputStream();
			response.setContentType("application/pdf");					
			response.setHeader("Content-Disposition", "attachment; filename="+ "SpecPrint" + ".pdf");
			opstrm.write(arr);
			opstrm.flush();
			logger.info("outData : " + outData.size());
			logger.info("catOutData : " + outData.size());

		} catch (Exception e) {
e.printStackTrace();
			logger.error("ServiceException at UnitBeanMB.first()...."
					+ e.getMessage());
			//return "errorPage";
		} finally {
			logger.info("UnitBeanMB.first(..).... END");
		}
		//return "";

	}
    
    
    
  
    
   
    
 
}