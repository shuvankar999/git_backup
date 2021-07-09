package com.tip.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.tip.model.AppsNavigationData;
import com.tip.model.AppsNavigationDataInput;
import com.tip.model.AppsNavigationDataModel;
import com.tip.model.Branches;
import com.tip.model.DefaultProfileData;
import com.tip.model.FetchDefaultProfileInput;
import com.tip.model.Language;
import com.tip.model.ModuleAccessData;
import com.tip.model.ModuleDataAccessInput;
import com.tip.model.UpdateProfileInput;
import com.tip.repository.FetchDefaultProfileRepository;

@Repository
public class FetchDefaultProfileRepositoryImpl implements FetchDefaultProfileRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public HashMap getDefaultProfileSettings(FetchDefaultProfileInput fetchDefault) {
		HashMap result=new HashMap();
		CallableStatement statement = null;
		Connection connection = null;
		
		String error_Cd=null;
		  ResultSet rs=null;
		try {
			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			connection.setAutoCommit(true);
			  String strSPCall = "{call  OPSeps..MTX_get_user_branches_lang(?,?,?)}";
			statement = connection.prepareCall(strSPCall);
			statement.setString(1, fetchDefault.getSsoId());
			statement.setString(2, fetchDefault.getAppCd());
		
			statement.registerOutParameter(3,Types.VARCHAR);
			statement.executeQuery();
			connection.setAutoCommit(false);
		
			
			rs = statement.getResultSet();         
		       // Get the first result set        2 
		//	HashMap<String,String> languageData=new HashMap<String,String>();
			
			ArrayList<Language> languages=new ArrayList<Language>();
			while (rs.next()) {                     // Position the cursor             3 
				Language lang=new Language();
				lang.setLanguageId(rs.getInt(1));
				lang.setLanguageName(rs.getString(2));
				languages.add(lang);
			}
			result.put("Languages",languages);
			statement.getMoreResults();                 // Point to the second result set  4a 
			                                        // and close the first result set
			rs = statement.getResultSet();   
			// Get the second result set       4b 
			DefaultProfileData defaultProfile=new DefaultProfileData();
			while (rs.next()) {    
				defaultProfile.setBranchId(rs.getString(1));// Position the cursor             4c 
				defaultProfile.setBranchName(rs.getString(2));
				defaultProfile.setCompanyNr(rs.getString(3));
				defaultProfile.setCountry(rs.getString(4));
				defaultProfile.setDefaultLanguage(rs.getString(5));
			
			}
			
			result.put("defaultProfile", defaultProfile);
			
			statement.getMoreResults();      
			rs = statement.getResultSet();  
			
			
			
			ArrayList<Branches> branchData=new ArrayList<Branches>();
			
			
			
			while (rs.next()) {   
				Branches br=new Branches();
				br.setBranchId(rs.getString(1));
				br.setBranchName(rs.getString(2));
				br.setCompanyNr(rs.getString(3));
				br.setDefaultLanguage(rs.getString(4));
				branchData.add(br);
			}
			
			result.put("Branches", branchData);
			error_Cd=statement.getString(3);
			
			result.put("ErrorCode", error_Cd);
			rs.close();                             // Close the result set
			statement.close();                          // Close the statement
			
			
		
								
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public HashMap getModuleAccessData(ModuleDataAccessInput moduleDataInput) {
		HashMap result=new HashMap();
		CallableStatement statement = null;
		Connection connection = null;
		
		String error_Cd=null;
		  ResultSet rs=null;
		try {
			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			connection.setAutoCommit(true);
			  String strSPCall = "{call  OPSeps..MTX_get_module_access(?)}";
			statement = connection.prepareCall(strSPCall);
			statement.setString(1, moduleDataInput.getSsoId());
			statement.executeQuery();
			connection.setAutoCommit(false);
		
			
			rs = statement.getResultSet();         
		       // Get the first result set        2 
			ArrayList<ModuleAccessData> moduleData=new ArrayList<ModuleAccessData>();
			while (rs.next()) {                     // Position the cursor             3 
				ModuleAccessData module=new ModuleAccessData();
				module.setModuleName(rs.getString(1));
				module.setCreateAccess(rs.getString(2));// Print the value
				module.setReadAccess(rs.getString(3));
				module.setUpdateAccess(rs.getString(4));
				module.setDeleteAccess(rs.getString(5));
				moduleData.add(module);
			}
			result.put("ModuleAccessData",moduleData);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
			return result;
	}

	@Override
	public HashMap updateProfile(UpdateProfileInput updateProfileInput) {
		HashMap result=new HashMap();
		CallableStatement statement = null;
		Connection connection = null;
		
		String error_Cd=null;
		//  ResultSet rs=null;
		try {
			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			connection.setAutoCommit(true);
			  String strSPCall = "{call  OPSeps..MTX_User_Preference_Upd(?,?,?,?,?)}";
			statement = connection.prepareCall(strSPCall);
			statement.setString(1, updateProfileInput.getSsoId());
			statement.setString(2, updateProfileInput.getBranchId());
			statement.setString(3, updateProfileInput.getAppCd());
			statement.setInt(4, updateProfileInput.getLanguageId());
			statement.registerOutParameter(5,Types.VARCHAR);
		
			statement.executeUpdate();
			connection.setAutoCommit(false);
		
			
			error_Cd = statement.getString(5);         
		       // Get the first result set        2 
	//		ArrayList<ModuleAccessData> moduleData=new ArrayList<ModuleAccessData>();
		
			result.put("ErrorCode",error_Cd);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
			return result;
	}
	
	
	
	@Override
	public HashMap updateAppsNavigationData(AppsNavigationDataInput appsNavigationInput) {
		HashMap result=new HashMap();
		CallableStatement statement = null;
		Connection connection = null;
		
		String error_Cd=null;
		String erro_Desc=null;
		//  ResultSet rs=null;
		try {
			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			connection.setAutoCommit(true);
			  String strSPCall = "{call  OPSeps..MTX_Apps_Navigation_Upd(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			statement = connection.prepareCall(strSPCall);
			statement.setInt(1, appsNavigationInput.getGroupId());
			statement.setString(2, appsNavigationInput.getEmpId());
			statement.setString(3, appsNavigationInput.getEmpName());
			statement.setString (4, appsNavigationInput.getAppName());
			statement.setString(5, appsNavigationInput.getEmpRole());
			statement.setString(6, appsNavigationInput.getSessionId());
			statement.setString(7, appsNavigationInput.getBranchId());
			statement.setInt(8, appsNavigationInput.getLanguageId());
			statement.setString(9, appsNavigationInput.getParentApp());
			statement.setString(10, appsNavigationInput.getAssetNumber());
			statement.setString(11, appsNavigationInput.getCustomerNumber());
			statement.setString(12, appsNavigationInput.getWpNumber());
			statement.setString(13, appsNavigationInput.getSupplierNumber());
			statement.setString(14, appsNavigationInput.getEstimationId());
			statement.setString(15,appsNavigationInput.getParentAppLink());
			statement.setString(16, appsNavigationInput.getAppNameLink());
			statement.registerOutParameter(17,Types.VARCHAR);
			statement.registerOutParameter(18,Types.VARCHAR);
		
			statement.executeUpdate();
			connection.setAutoCommit(false);
		
			
			error_Cd = statement.getString(17);     
			erro_Desc =statement.getString(18);   
		       // Get the first result set        2 
	//		ArrayList<ModuleAccessData> moduleData=new ArrayList<ModuleAccessData>();
		
			result.put("ErrorCode",error_Cd);
			result.put("ErrorDesc", erro_Desc);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
			return result;
	}
	
	
	
	
	
	
	
	
	@Override
	public HashMap getAppsNavigationData(AppsNavigationDataModel appsNavigationDataModel) {
		HashMap result=new HashMap();
		CallableStatement statement = null;
		Connection connection = null;
		
		String error_Cd=null;
		  ResultSet rs=null;
		try {
			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			connection.setAutoCommit(true);
			  String strSPCall = "{call  OPSeps..MTX_get_Apps_Navigation_Data('"+appsNavigationDataModel.getEmpId()+"')}";
			statement = connection.prepareCall(strSPCall);
			//statement.registerOutParameter(1,Types.VARCHAR);
			statement.executeQuery();
			connection.setAutoCommit(false);
		//	error_Cd = statement.getString(1);  
			
			rs = statement.getResultSet();    
			System.out.println("result set :"+rs);
		       // Get the first result set        2 
			ArrayList<AppsNavigationData> appsNaviData=new ArrayList<AppsNavigationData>();
			while (rs.next()) {                     // Position the cursor             3 
				AppsNavigationData appsData=new AppsNavigationData();
				appsData.setGroupId(rs.getInt(1));
				appsData.setEmpId(rs.getString(2));
				appsData.setEmpName(rs.getString(3));
				appsData.setAppName(rs.getString(4));
				appsData.setEmpRole(rs.getString(5));
				appsData.setSessionId(rs.getString(6));
				appsData.setBranchId(rs.getString(7));
				appsData.setLanguageId(rs.getInt(8));
				appsData.setParentApp(rs.getString(9));
				appsData.setAssetNumber(rs.getString(10));
				appsData.setCustomerNumber(rs.getString(11));
				appsData.setWpNumber(rs.getString(12));
				appsData.setSupplierNumber(rs.getString(13));
				appsData.setEstimationId(rs.getString(14));
				appsData.setCreateUser(rs.getString(15));
				appsData.setCreateDate(rs.getString(16));
				appsData.setMaintUser(rs.getString(17));
				appsData.setMaintDate(rs.getString(18));
				appsData.setParentAppLink(rs.getString(19));
				appsData.setAppNameLink(rs.getString(20));
				appsNaviData.add(appsData);
			}
			result.put("AppsNavigationData",appsNaviData);
		//	result.put("ErrorCode",error_Cd);
		
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
			return result;
	}

	@Override
	public HashMap insertAppsNavigationData(AppsNavigationDataInput appsNavigationInput) {
		HashMap result=new HashMap();
		CallableStatement statement = null;
		Connection connection = null;
		
		String error_Cd=null;
		String erro_Desc=null;
		//  ResultSet rs=null;
		try {
			connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
			connection.setAutoCommit(true);
			  String strSPCall = "{call  OPSeps..MTX_Apps_Navigation_Insert(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			statement = connection.prepareCall(strSPCall);
			statement.setInt(1, appsNavigationInput.getGroupId());
			statement.setString(2, appsNavigationInput.getEmpId());
			statement.setString(3, appsNavigationInput.getEmpName());
			statement.setString (4, appsNavigationInput.getAppName());
			statement.setString(5, appsNavigationInput.getEmpRole());
			statement.setString(6, appsNavigationInput.getSessionId());
			statement.setString(7, appsNavigationInput.getBranchId());
			statement.setInt(8, appsNavigationInput.getLanguageId());
			statement.setString(9, appsNavigationInput.getParentApp());
			statement.setString(10, appsNavigationInput.getAssetNumber());
			statement.setString(11, appsNavigationInput.getCustomerNumber());
			statement.setString(12, appsNavigationInput.getWpNumber());
			statement.registerOutParameter(13,Types.VARCHAR);
			statement.registerOutParameter(14,Types.VARCHAR);
		
			statement.executeUpdate();
			connection.setAutoCommit(false);
		
			
			error_Cd = statement.getString(13);     
			erro_Desc =statement.getString(14);   
		       // Get the first result set        2 
	//		ArrayList<ModuleAccessData> moduleData=new ArrayList<ModuleAccessData>();
		
			result.put("ErrorCode",error_Cd);
			result.put("ErroDesc", erro_Desc);
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
			
			return result;
	}
	
	

}
