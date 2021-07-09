/**
 * 
 */
package com.tip.assetpdf.service;

import java.awt.Image;
import java.io.ByteArrayOutputStream;




/**
 * @author hm66010
 *
 */
public interface UnitServiceIfc {
	


public ByteArrayOutputStream getUnitFullSpecPrintDetails(String unitNr, String absGalleryPath);
public String getAxle(String unitCatGroup);
public String getCatDesc(String unitCatGroup);
public ByteArrayOutputStream getNoPicture(String unitNr, String heading, String absGalleryPath, boolean picture, String pictureLocation,Image image);


}
 