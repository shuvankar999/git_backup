package com.tip.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by 885155 on 6/14/2017.
 */

import com.tip.api.GenerateDocuRequest;
import com.tip.util.CommonUtil;
import com.tip.util.HibernateUtil;

@Controller
@RestController
@ComponentScan
@RequestMapping("/TIP/rest/mos-service/1.0/")
public class UploadController {
    private static final Logger LOG = Logger.getLogger(UploadController.class);
    private static String envVal = "default";


    public static String getEnvVal() {
        return envVal;
    }

    public static void setEnvVal(String envVal) {
        UploadController.envVal = envVal;
    }


    @Autowired
    Environment environment;
    @Autowired
    GenerateDocumentController generateDocumentController;

    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
    public @ResponseBody
    String multipleSave(@RequestPart("file") MultipartFile[] files, @RequestPart("appcode") String appCode, @RequestPart("insp_Type") String inspType, @RequestPart("intchKey") String intchKey, @RequestPart("lineNr") String lineNr, @RequestPart(value = "description", required = false) String description,
                        @RequestPart(value = "comment", required = false) String comment, @RequestPart("imgType") String imgType, @RequestPart("imgName") String imgName, @RequestPart("noOfPics") String noOfPics, @RequestPart("deviceSerialNum") String deviceSerialNum) {
        String profilenameVal = null;
        for (final String profileName : environment.getActiveProfiles()) {
            profilenameVal = profileName;
        }
        setEnvVal(profilenameVal);
        String fileName = null;
        Session session = HibernateUtil.getHibernateSessionOPSinv();
        SessionImpl sessionImpl = (SessionImpl) session;
        String msg = "";
        String imageLocation = CommonUtil.getImageLocation(sessionImpl, session, appCode, inspType);
        String directoryName = imageLocation.concat(intchKey);
        File directory = new File(directoryName);

        
        if (!directory.exists()) {
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {
                    String imageName = getImageName(inspType, intchKey, session, sessionImpl, imgName);
                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    
                    
                    /**Changed for fix Signarure Image issue on 26072017**/
                    System.out.println("OriginalFialeNma??????????????????"+fileName);
                    if(fileName.contains("_SE"))
                    {
                    	System.out.println("Inside SE Old File Name"+fileName);
                    	fileName=fileName.toString().replace("_SE", "_SD");
                    	System.out.println("Inside SE  New File Name"+fileName);
/*                    	Pattern.compile("_SE").matcher(fileName).replaceAll("_SD");
                    	System.out.println("??????????????"+fileName.toString().replace("_SE", "_SD"));
                    	System.out.println(Pattern.compile("_SE").matcher(fileName).replaceAll("_SD"));
                    	System.out.println("Inside SE  New File Name"+fileName);*/
                    	
                    }else if(fileName.contains("_SD"))
                    {
                    	System.out.println("Inside SD  ...Old File Name::::::::::::"+fileName);
                    	fileName=fileName.toString().replace("_SD", "_SE");
                    	System.out.println("Inside SD ...New File Name:::::::"+fileName);
                    	/*Pattern.compile("_SD").matcher(fileName).replaceAll("_SE");
                    	System.out.println("??????????????"+fileName.toString().replace("_SD", "_SE"));
                    	System.out.println(Pattern.compile("_SD").matcher(fileName).replaceAll("_SE"));
                    	System.out.println("Inside SD ...New File Name:::::::"+fileName);*/
                    }
                    
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(directory + "/" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName + "";
                    sessionImpl.connection().setAutoCommit(true);
                    Query query2 = session.getNamedQuery("saveMetaData");
                    query2.setParameter("appCd", CommonUtil
                            .checkNullObject(appCode));
                    query2.setParameter("intchKey", CommonUtil.convertStringToBigInt(CommonUtil
                            .checkNullObject(intchKey)));
                    query2.setParameter("lineNr", CommonUtil
                            .convertStringToInt(CommonUtil
                                    .checkNullObject(lineNr)));
                    query2.setParameter("description", CommonUtil
                            .checkNullObject(description));
                    query2.setParameter("comment", CommonUtil
                            .checkNullObject(comment));
                    query2.setParameter("inspType", CommonUtil
                            .checkNullObject(inspType));
                    query2.setParameter("imgType", CommonUtil
                            .checkNullObject(imgType));
                    query2.setParameter("imgName", CommonUtil
                            .checkNullObject(imageName));
                    query2.setParameter("noOfPics", CommonUtil.convertStringToInt(CommonUtil
                            .checkNullObject(noOfPics)));
                    query2.setParameter("imagePath", CommonUtil
                            .checkNullObject(imageLocation));
                    query2.setParameter("deviceSerialNum", CommonUtil
                            .checkNullObject(deviceSerialNum));
                    int count = (Integer) query2.uniqueResult();
                    sessionImpl.connection().setAutoCommit(false);
                    if (count == 0) {
                        msg += "File not saved successfully " + fileName + "";
                    }
                    sessionImpl.connection().setAutoCommit(true);
                    Query query3 = session.getNamedQuery("getImageCount");
                    query3.setParameter("intchKey", CommonUtil.convertStringToBigInt(CommonUtil
                            .checkNullObject(intchKey)));
                    query3.setParameter("inspType", CommonUtil
                            .checkNullObject(inspType));
                    int imageCount = (Integer) query3.uniqueResult();
                    sessionImpl.connection().setAutoCommit(false);
                    updateDocumentStatus(session, sessionImpl, intchKey, inspType, "updateImgReceivedFlg", null);
                    generateDocument(imageCount, noOfPics, inspType, intchKey);
                    updateDocumentStatus(session, sessionImpl, intchKey, inspType, "updateDocReceivedFlg", imageName);

                } catch (Exception e) {
                    LOG.error("Error message.." + e.getMessage());
                    return "You failed to upload " + fileName + ": " + e.getMessage() + " ";
                }
            }
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }
    }

    private void updateDocumentStatus(Session session, SessionImpl sessionImpl, String intchKey, String inspType, String notificationFlag, String docName) {
        try {
            sessionImpl.connection().setAutoCommit(true);
            Query query = session.getNamedQuery("updateDocumentStatus");
            query.setParameter("intchKey", CommonUtil.convertStringToBigInt(CommonUtil
                    .checkNullObject(intchKey)));
            query.setParameter("inspType", CommonUtil
                    .checkNullObject(inspType));
            query.setParameter("docName", CommonUtil
                    .checkNullObject(docName));
            query.setParameter("notifiFlag", CommonUtil
                    .checkNullObject(notificationFlag));
            query.uniqueResult();
            sessionImpl.connection().setAutoCommit(false);
        } catch (SQLException e) {
            LOG.error("Error message.." + e.getMessage());
        }
    }


    private String getImageName(String inspType, String intchKey, Session session, SessionImpl sessionImpl, String imgName) {
        try {
            if ("CII".equalsIgnoreCase(inspType)) {
                sessionImpl.connection().setAutoCommit(true);
                Query query1 = session.getNamedQuery("imageName");
                query1.setParameter("intchKey", CommonUtil.convertStringToBigInt(CommonUtil
                        .checkNullObject(intchKey)));
                query1.setParameter("inspType", CommonUtil
                        .checkNullObject(inspType));
                List result1 = query1.list();

                sessionImpl.connection().setAutoCommit(false);
                Iterator itr = result1.iterator();
                BigInteger intchKeyRetrieved = null;
                while (itr.hasNext()) {
                    Object[] obj = (Object[]) itr.next();
                    intchKeyRetrieved = CommonUtil.convertStringToBigInt(CommonUtil.checkNullObject(obj[0]));
                    // inspType = CommonUtil.checkNullObject(obj[1]);
                }
                if (!String.valueOf(intchKeyRetrieved).equalsIgnoreCase(intchKey)) {
                    imgName.replace(String.valueOf(intchKey).trim(), String.valueOf(intchKeyRetrieved).trim());
                    imgName.replace("CII", "II");

                }
            }
        } catch (SQLException e) {
            LOG.error("Error message.." + e.getMessage());
        }
        return imgName;
    }

    private void generateDocument(int imageCount, String noOfPics, String inspType, String intchKey) throws MalformedURLException {
        if (imageCount == CommonUtil.convertStringToInt(CommonUtil
                .checkNullObject(noOfPics))) {
            GenerateDocuRequest generateDocuRequest = new GenerateDocuRequest();
            generateDocuRequest.setInsType(inspType);
            generateDocuRequest.setIntchKey(CommonUtil.convertStringToLong(CommonUtil
                    .checkNullObject(intchKey)));
            generateDocumentController.generateDocument(generateDocuRequest);
        }
    }
}
