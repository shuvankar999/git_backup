/**
 * 
 */
package com.tip.assetpdf.repository;

import java.awt.Image;
import java.io.ByteArrayOutputStream;






/**
 * @author hm66010
 *
 */
public interface unitDaoInf {

	
	
	public ByteArrayOutputStream getNoPicture(String unitNr, String heading, String uri, boolean picture, String pictureLocation,byte[] image);
	
	public ByteArrayOutputStream getUnitFullSpecPrintDetails(String unitNr, String uri);

	public String getAxle(String unitCatGroup);

	String getCatDesc(String unitCatGroup);

	
	


}
