package com.tip.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tip.model.FetchImageRequestMos;
import com.tip.model.FetchImageResponseMos;
import com.tip.util.CommonUtil;
import com.tip.util.HibernateUtil;

@Controller
@RestController
@ComponentScan
@RequestMapping("/TIP/rest/mos-service/1.0/")
public class FetchImageControllerMos {
	@Autowired
	private Environment environment;

	private static String envVal = "default";

	private static final Logger logger = LoggerFactory.getLogger(FetchImageControllerMos.class);

	public static String getEnvVal() {
		return envVal;
	}

	public static void setEnvVal(String envVal) {
		FetchImageControllerMos.envVal = envVal;
	}

	@RequestMapping(value = "fetchImages", method = RequestMethod.POST)
    @ResponseBody public 
    List<FetchImageResponseMos> fetchImages(@RequestBody FetchImageRequestMos fetchImageRequestMos) {
        String profilenameVal = null;
        List<FetchImageResponseMos> responseImageList = new ArrayList<>();
        for (final String profileName : environment.getActiveProfiles()) {
            profilenameVal = profileName;
        }
        setEnvVal(profilenameVal);
        Session session = HibernateUtil.getHibernateSessionOPSinv();

        SessionImpl sessionImpl = (SessionImpl) session;

        try {

            sessionImpl.connection().setAutoCommit(true);
            Query query = session.getNamedQuery("fetchImagesMos");
            query.setParameter("unNr", CommonUtil.convertStringToInt(CommonUtil
                    .checkNullObject(fetchImageRequestMos.getUnitNr())));
            query.setParameter("errorCd", CommonUtil
                    .checkNullObject(null));
            List result1 = query.list();


            if (result1.isEmpty()) {
            	logger.debug(" No images retrieved");
            }
            sessionImpl.connection().setAutoCommit(false);
            Iterator itr = result1.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                FetchImageResponseMos fetchImageResponse = new FetchImageResponseMos();
                fetchImageResponse.setUnitNr(CommonUtil.convertStringToInt(CommonUtil.checkNullObject(obj[0])));
                fetchImageResponse.setInspType(CommonUtil.checkNullObject(obj[1]));
                fetchImageResponse.setDamageLineNr(CommonUtil.convertStringToInt(CommonUtil.checkNullObject(obj[2])));
                fetchImageResponse.setDamageDescription(CommonUtil.checkNullObject(obj[3]));
                fetchImageResponse.setImgType(CommonUtil.checkNullObject(obj[4]));
                fetchImageResponse.setImgName(CommonUtil.checkNullObject(obj[5]));
                fetchImageResponse.setImgComment(CommonUtil.checkNullObject(obj[6]));
                fetchImageResponse.setImgCount(CommonUtil.convertStringToInt(CommonUtil.checkNullObject(obj[7])));
                fetchImageResponse.setImgPath(CommonUtil.checkNullObject(obj[8]));

                responseImageList.add(fetchImageResponse);
            }
        }catch (SQLException e1) {
    			logger.error("An error occurred while fetcching: " + e1);

        } catch (Exception e) {
            logger.error("Error message.." + e);
        }
        session.close();
        return responseImageList;
    }
}
