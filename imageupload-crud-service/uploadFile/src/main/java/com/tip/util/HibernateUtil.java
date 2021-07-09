package com.tip.util;


import com.tip.controller.UploadController;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	private static Session session;
	

	
	
	
	public static void getHibernateSessionFactoryOPSinv() {
		
		if(sessionFactory  == null){
			//sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
			sessionFactory = new Configuration().configure(getHibernateSessionOPSinvEnv()).buildSessionFactory();
		}
	}
	

	public static Session getHibernateSessionOPSinv() {
			
			getHibernateSessionFactoryOPSinv();
			session = sessionFactory.openSession();
			//LOG.info("session in getHibernateSessionOPSinv : "+session);
		return session;
	}
	
	
	public static String getHibernateSessionOPSinvEnv() {
		
		String hibernateFile=null;
		if(UploadController.getEnvVal().equalsIgnoreCase("dev"))
		{
			hibernateFile="hibernatedev.cfg.xml";
		}else if(UploadController.getEnvVal().equalsIgnoreCase("uat"))
		{
			hibernateFile="hibernateuat.cfg.xml";
		}else if(UploadController.getEnvVal().equalsIgnoreCase("bi"))
		{
			hibernateFile="hibernatebi.cfg.xml";
		}else if(UploadController.getEnvVal().equalsIgnoreCase("prd"))
		{
			hibernateFile="hibernateprd.cfg.xml";
		}
		else if(UploadController.getEnvVal().equalsIgnoreCase("qa"))
		{
			hibernateFile="hibernateuat.cfg.xml";
		}else{
			hibernateFile="hibernate.cfg.xml";
		}
	return hibernateFile;
}
	
	
	
	public static void closeHibernateSessionOPSinv() {
		
		if(session != null){
			session.close();
		}
	}
	
	public static String checkNullObject(Object val) {
		//Object value = val;
	//	boolean flag = false;
		
		if(val != null ){
			if("".equals(val.toString().trim())){
				return "";
			}
			return val.toString();
		}else {
			return "";
		}
	}
	public static void main(String[] ars){
		HibernateUtil.getHibernateSessionFactoryOPSinv();
	
	}
	
	
}
