/**
 * 
 */
package com.tip.assetpdf.service.impl;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tip.assetpdf.repository.unitDaoInf;
import com.tip.assetpdf.service.UnitServiceIfc;





/**
 * @author hm66010
 *
 */
@Service
public class UnitServiceImpl implements UnitServiceIfc {
	
	private static final Logger LOG = Logger.getLogger(UnitServiceImpl.class);
	
	@Autowired
	unitDaoInf unitDao;

	
	public String getAxle(String unitCatGroup){
		LOG.info("UnitServiceImpl.getAxle().........START");
		String axleCatDescData = null;
		 try
		 {		
			 axleCatDescData = unitDao.getAxle(unitCatGroup);
		 }
		 catch (Exception ex) {
	         LOG.error("DAOException at UnitServiceImpl.getAxle()......"+ ex.getMessage());
	        // throw new ServiceException("DAOException at UnitServiceImpl.getAxle()...."+ex.getMessage());
		}finally{
			LOG.info("UnitServiceImpl.getAxle().........END");
		}
		return axleCatDescData;
	
		}

	public String getCatDesc(String unitCatGroup){
		LOG.info("UnitServiceImpl.getCatDesc().........START");
		String axleCatDescData = null;
		 try
		 {		
			 axleCatDescData = unitDao.getCatDesc(unitCatGroup);
		 }
		 catch (Exception ex) {
	         LOG.error("DAOException at UnitServiceImpl.getCatDesc()......"+ ex.getMessage());
	        // throw new ServiceException("DAOException at UnitServiceImpl.getCatDesc()...."+ex.getMessage());
		}finally{
			LOG.info("UnitServiceImpl.getCatDesc().........END");
		}
		return axleCatDescData;
	}	
	public ByteArrayOutputStream getNoPicture(String unitNr, String heading,String uri,boolean picture, String pictureLocation,Image image)  {
		LOG.info("UnitServiceImpl.getNoPicture().........START");
		 ByteArrayOutputStream byteArrayOutputStream = null;
		 try
		 {		
			
			 RenderedImage bImage=(RenderedImage)image;
				 ByteArrayOutputStream bos = new ByteArrayOutputStream();
			      ImageIO.write(bImage,"PNG", bos );
			      byte [] data = bos.toByteArray();
			 byteArrayOutputStream = unitDao.getNoPicture(unitNr,heading,uri,picture,pictureLocation,data);
		 }
		 catch (Exception ex) {
			 ex.printStackTrace();
	         LOG.error("DAOException at UnitServiceImpl.getNoPicture()..........."+ ex.getMessage());
	       //  throw new ServiceException("DAOException at UnitServiceImpl.getNoPicture()............"+ex.getMessage());
		}finally{
			LOG.info("UnitServiceImpl.getNoPicture().........END");
		}
		return byteArrayOutputStream;
	}
	
	public ByteArrayOutputStream getUnitFullSpecPrintDetails(String unitNr,String uri){
		LOG.info("UnitServiceImpl.getUnitFullSpecPrintDetails().........START");
		ByteArrayOutputStream byteArrayOutputStream = null;
		 try
		 {		
			 byteArrayOutputStream = unitDao.getUnitFullSpecPrintDetails(unitNr,uri);
		 }
		 catch (Exception ex) {
	         LOG.error("DAOException at UnitServiceImpl.getUnitFullSpecPrintDetails()........."+ ex.getMessage());
	        // throw new ServiceException("DAOException at UnitServiceImpl.getUnitFullSpecPrintDetails()........."+ex.getMessage());
		}finally{
			LOG.info("UnitServiceImpl.getUnitFullSpecPrintDetails().........END");
		}
		return byteArrayOutputStream;
	}
	
	

	
	
}
	


