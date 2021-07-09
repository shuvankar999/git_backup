package com.tip.controller;

import com.tip.util.CommonUtil;
import com.tip.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 885155 on 6/22/2017.
 */
@Controller
@RestController
@ComponentScan
@RequestMapping("/TIP/rest/workshop-service/1.0/")
public class ImagesUploadControllerForWorkshop {

    private static String envVal = "default";
    private static final Logger LOG = Logger.getLogger(ImagesUploadControllerForWorkshop.class);

    public static String getEnvVal() {
        return envVal;
    }

    public static void setEnvVal(String envVal) {
        ImagesUploadControllerForWorkshop.envVal = envVal;
    }


    @Autowired
    private Environment environment;

    @RequestMapping(value = "uploadFiles", method = RequestMethod.POST)
    public @ResponseBody
    String multipleSave(@RequestPart("file") MultipartFile[] files, @RequestPart("appcode") String appCode, @RequestPart("insp_Type") String inspType, @RequestPart("wpNr") String workPacketNo, @RequestPart("woNr") String workOrderNumber, @RequestPart("wotNr") String workOrderTypeNumber,
                        @RequestPart("imgType") String imgType, @RequestPart("imgName") String imgName, @RequestPart("noOfImages") String noOfImages, @RequestPart("ssoId") String ssoId) {
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
        String directoryName = imageLocation.concat(workPacketNo);
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
        if (files != null && files.length > 0) {
            for (int i = 0; i < files.length; i++) {
                try {

                    fileName = files[i].getOriginalFilename();
                    byte[] bytes = files[i].getBytes();
                    BufferedOutputStream buffStream =
                            new BufferedOutputStream(new FileOutputStream(new File(directory + "/" + fileName)));
                    buffStream.write(bytes);
                    buffStream.close();
                    msg += "You have successfully uploaded " + fileName + "";
                    sessionImpl.connection().setAutoCommit(true);
                    Query query2 = session.getNamedQuery("uploadImages");
                    query2.setParameter("wpNr", CommonUtil.convertStringToBigDecimal(CommonUtil
                            .checkNullObject(workPacketNo)));
                    query2.setParameter("woNr", CommonUtil.convertStringToInt(CommonUtil
                            .checkNullObject(workOrderNumber)));
                    query2.setParameter("wotNr", CommonUtil.convertStringToInt(CommonUtil
                            .checkNullObject(workOrderTypeNumber)));
                    query2.setParameter("imgType", CommonUtil
                            .checkNullObject(imgType));
                    query2.setParameter("imgName", CommonUtil
                            .checkNullObject(imgName));
                    query2.setParameter("noOfImages", CommonUtil.convertStringToInt(CommonUtil
                            .checkNullObject(noOfImages)));
                    query2.setParameter("ssoId", CommonUtil
                            .checkNullObject(ssoId));
                    String errorCd = (String) query2.uniqueResult();
                    sessionImpl.connection().setAutoCommit(false);
                    if (!("SUCCESS".equalsIgnoreCase(errorCd))) {
                        msg += "File not saved successfully " + fileName + "";
                    }

                } catch (Exception e) {
                    LOG.error("Error message.."+e.getMessage());
                    return "You failed to upload " + fileName + ": " + e.getMessage() + " ";
                }
            }
            session.close();
            return msg;
        } else {
            return "Unable to upload. File is empty.";
        }


    }


    @RequestMapping(value = "fetchImages", method = RequestMethod.POST)
    public @ResponseBody
    List<Image> fetchImages(@RequestPart("wpNr") String workPacketNo, @RequestPart("woNr") String workOrderNumber, @RequestPart("wotNr") String workOrderTypeNumber) {
        String profilenameVal = null;
        List<Image> imageList = new ArrayList<>();
        for (final String profileName : environment.getActiveProfiles()) {
            profilenameVal = profileName;
        }
        setEnvVal(profilenameVal);
        String fileName = null;
        Session session = HibernateUtil.getHibernateSessionOPSinv();

        SessionImpl sessionImpl = (SessionImpl) session;

        try {

            sessionImpl.connection().setAutoCommit(true);
            Query query = session.getNamedQuery("fetchImages");
            query.setParameter("wpNr", CommonUtil.convertStringToBigDecimal(CommonUtil
                    .checkNullObject(workPacketNo)));
            query.setParameter("woNr", CommonUtil.convertStringToInt(CommonUtil
                    .checkNullObject(workOrderNumber)));
            query.setParameter("wotNr", CommonUtil.convertStringToInt(CommonUtil
                    .checkNullObject(workOrderTypeNumber)));
            query.setParameter("errorCd", CommonUtil
                    .checkNullObject(fileName));
            List result1 = query.list();


            if (result1.isEmpty()) {
                System.out.println(" No images retrieved");
            }
            sessionImpl.connection().setAutoCommit(false);
            Iterator itr = result1.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                Image image = new Image();
                image.setWorkPacketNumber(CommonUtil.convertStringToBigDecimal(CommonUtil.checkNullObject(obj[0])));
                image.setWorkOrderNumber(CommonUtil.convertStringToInt(CommonUtil.checkNullObject(obj[1])));
                image.setWorkOrderTaskNumber(CommonUtil.convertStringToInt(CommonUtil.checkNullObject(obj[2])));
                image.setImageType(CommonUtil.checkNullObject(obj[3]));
                image.setImageName(CommonUtil.checkNullObject(obj[4]));
                image.setImageLocation(CommonUtil.checkNullObject(obj[5]));
                image.setNoOfImages(CommonUtil.convertStringToInt(CommonUtil.checkNullObject(obj[6])));
                imageList.add(image);
            }
        } catch (SQLException e1) {
            LOG.error("Error message.."+e1.getMessage());

        } catch (Exception e) {
            LOG.error("Error message.."+e.getMessage());
            //return "You failed to upload " + fileName + ": " + e.getMessage() + " ";
        }
        session.close();
        return imageList;
    }
}


