
package com.document.upload.documentservice.data;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.document.upload.documentservice.dao.GetDocuwareDetails;
import com.docuware.japi.client.IJapiClient;
import com.docuware.japi.client.Japi;
import com.docuware.japi.document.IDocumentStream;
import com.docuware.japi.document.ISectionStream;
import com.docuware.japi.document.SectionFileType;
import com.docuware.japi.exception.JapiAuthenticationException;
import com.docuware.japi.exception.JapiServiceException;
import com.docuware.japi.filecabinet.IFileCabinet;


@Component
public class DocuwareUtil {

//	@Value("${docu_user}")
//	String docu_user;
//	@Value("${docu_pass}")
//	String docu_pass;
//	@Value("${docu_ip}")
//	String docu_ip;
//	@Value("${docu_port}")
//	String docu_port;
//	@Value("${docu_org}")
//	String docu_org;
//	@Value("${docu_cabinate}")
//	String docu_cabinate;
	
	
	
	 @Autowired
	   GetDocuwareDetails docuDetails;
	
	private static final Logger LOG = Logger.getLogger(DocuwareUtil.class);
	public  DownloadDocumentResponse getPDFDocument(DownloadDocumentInput input)throws Exception {
		
	
		//HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	//	UserData userData = (UserData) httpSession.getAttribute("userData");
		DocuwareDetails dd=docuDetails.getDocuwareDetails(input.getAppCd());
		IJapiClient client = null;	
	//	System.out.println("userData.getDocuwareIP()---"+userData.getDocuwareIP()+"---userData.getDocuwarePort()---"+userData.getDocuwarePort());
	//	System.out.println("userData.getDocuwareUserId()---"+userData.getDocuwareUserId()+"---userData.getDocuwarePwd()---"+userData.getDocuwarePwd()+"--userData.getDocuwareOrg()--"+userData.getDocuwareOrg());
		System.out.println("before japi  ilenet connection !!");
		client = Japi.newClient(dd.getDocuwareIp().trim(), Integer.parseInt(dd.getDocuwarePort().trim()));
		System.out.println("client :"+client);
		IFileCabinet fileCabinet = null;
		String filepath=null;
		DownloadDocumentResponse responseObj=new DownloadDocumentResponse();
		try {			
			client.login(dd.getDocUser().trim(),dd.getDocPwd().trim(),dd.getDocOrg().trim());
			System.out.println("after login  :"+dd.getDocUser().trim());
			System.out.println("docuip :"+dd.getDocuwareIp().trim());
			fileCabinet = client.getFileCabinetByName(dd.getDocuCabinate().trim());
			System.out.println("fileCabinet :"+fileCabinet);
		//	filepath=docName+".pdf";
			System.out.println("docIds.get(0)..............." +input.getDocumentId() );
			IDocumentStream documentStream = fileCabinet.openDocumentStream(Integer.parseInt(input.getDocumentId()));
			ISectionStream sectionStream = documentStream.getSectionDownloadStream(0, SectionFileType.ORIGINAL);
			sectionStream.openFileRead();
			File file =  new File(input.getDocumentName().trim());			
			LOG.info("file...................................."+ file);
			FileOutputStream file_output = null;
			file.createNewFile();
			file_output = new FileOutputStream(file);
			 InputStream inputStream = new FileInputStream(file);
			while (true) {
				byte byteArray[] = null;
				byteArray = sectionStream.read(3 * 8 * 1024);
				if (byteArray == null || byteArray.length == 0)
					break;
				file_output.write(byteArray);
			}
			file_output.close();
			sectionStream.close();
			fileCabinet.closeDocumentStream(documentStream);
			
			  responseObj.setFile(file);
		        responseObj.setInputStream(inputStream);
		        responseObj.setOutputStream(file_output);
		        
		} catch (JapiAuthenticationException e) {
			//e.printStackTrace();
			LOG.error("Exception in DocuwareUtil---"+e.getMessage());
			e.printStackTrace();
		} catch (JapiServiceException e) {
			LOG.error("Exception in DocuwareUtil-2--"+e.getMessage());
			e.printStackTrace();
		}catch(IOException e){
			LOG.error("An error occurred while generating excel for asset detail: " + e);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
		if(!client.equals(null))
		{
			client.logout();
		}
		}
		
		return responseObj;
	}
	
	
	

}
