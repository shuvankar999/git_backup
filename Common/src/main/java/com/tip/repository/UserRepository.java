package com.tip.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import com.tip.model.AccessObject;
import com.tip.model.ErrorCd;
import com.tip.model.RoleAccess;
import com.tip.model.UserObj;
import com.tip.model.UserObject;

@Repository
public class UserRepository {

	 @Autowired
	    private JdbcTemplate jdbcTemplate;

		// Find all customers, thanks Java 8, you can create a custom RowMapper like this :
	    public List<UserObj> findAll() {

	        List<UserObj> result = jdbcTemplate.query(
	                "SELECT Empl_Id, Empl_Name FROM UserObj",
	                (rs, rowNum) -> new UserObj(rs.getInt("Empl_Id"),
	                        rs.getString("Empl_Name"))
	        );

	        return result;
	    }

/*		// Add new User
	    public void addCustomer(String name) {

	        jdbcTemplate.update("INSERT INTO UserObj(Empl_Name) VALUES (?)",
	                name);

	    }*/
	   // "execute Wshp_ws_get_Access '502352204'",
	    
	    public List<UserObject> getUserAccess(String ssoid) {
	    	System.out.println("<>>>>>>>>>>>>>>>>>>>>>"+ssoid);
	        List<UserObject> result = jdbcTemplate.query(
	                "execute OPSwshp..Wshp_ws_get_Access '"+ssoid+"'",
	                (rs, rowNum) -> new UserObject(rs.getString("Appl_Group_Id"),
	                        rs.getString("Application_Name"),rs.getString("error_Cd"))
	        );
	        
	        return result;
	    }

	    
	    
	    
	/*    public void getUserAccess(String ssoid) {
	    	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("Wshp_ws_get_Access");

	    	Map<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("SSO_Id", ssoid);
		 
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	 
			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			
			 Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			        String key = (String) entry.getKey();
			        Object value = (Object) entry.getValue();
			        System.out.println("Key: "+key);
			        System.out.println("Value: "+value);
			    }
	    }

	    */
	    
	    @SuppressWarnings("unchecked")
		public RoleAccess retrieveUserAccessBasedOnRole(String sso)
	    {
	     
	    	RoleAccess roleAccess = new RoleAccess();
	    	ErrorCd errorCd = new ErrorCd();
	    	
	     List<AccessObject> accessObjectList=new ArrayList<AccessObject>();
	         
	     SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("Wshp_ws_get_Access");
	         Map<String, Object> inParamMap = new HashMap<String, Object>();
	      inParamMap.put("SSO_ID", sso);
	      
	      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	     
	      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
	      
	       Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
	          while (it.hasNext()) {
	              Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	              String key = (String) entry.getKey();
	              Object value = (Object) entry.getValue();
	              System.out.println("Key: "+key);
	              System.out.println("Value: "+value);
	              if(key.equalsIgnoreCase("#result-set-1"))
	              {
	               accessObjectList=(List<AccessObject>) entry.getValue();
	              }
	          }
	          if(null != accessObjectList && !accessObjectList.isEmpty()){	        	  
	        	  errorCd.setErrorCode("SUCCESS");
	        	  errorCd.setErrorMessage("No Issues, User is authorized.");
	        	  
	        	  roleAccess.setErrorCd(errorCd);
	        	  roleAccess.setAccessObjectList(accessObjectList);
	          }
	          else{
	        	  errorCd.setErrorCode("AUTH_ERROR");
	        	  errorCd.setErrorMessage("You are not authorized to access any site.");
	        	  
	        	  roleAccess.setErrorCd(errorCd);
	        	  roleAccess.setAccessObjectList(null);
	          }
	        
	     return roleAccess;

	    }	    
	    
	    @SuppressWarnings("unchecked")
			public RoleAccess retrieveUserAccessBasedOnMatrixRole(String sso)
		    {
		     
		    	RoleAccess roleAccess = new RoleAccess();
		    	ErrorCd errorCd = new ErrorCd();
		    	
		     List<AccessObject> accessObjectList=new ArrayList<AccessObject>();
		         
		     SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("Matrix_ws_get_Access");
		         Map<String, Object> inParamMap = new HashMap<String, Object>();
		      inParamMap.put("SSO_ID", sso);
		      
		      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		     
		      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		      
		       Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
		          while (it.hasNext()) {
		              Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		              String key = (String) entry.getKey();
		              Object value = (Object) entry.getValue();
		              System.out.println("Key: "+key);
		              System.out.println("Value: "+value);
		              if(key.equalsIgnoreCase("#result-set-1"))
		              {
		               accessObjectList=(List<AccessObject>) entry.getValue();
		              }
		          }
		          if(null != accessObjectList && !accessObjectList.isEmpty()){	        	  
		        	  errorCd.setErrorCode("SUCCESS");
		        	  errorCd.setErrorMessage("No Issues, User is authorized.");		        	  
		        	  roleAccess.setErrorCd(errorCd);
		        	  roleAccess.setAccessObjectList(accessObjectList);
		          }
		          else{
		        	  errorCd.setErrorCode("AUTH_ERROR");
		        	  errorCd.setErrorMessage("You are not authorized to access any site.");		        	  
		        	  roleAccess.setErrorCd(errorCd);
		        	  roleAccess.setAccessObjectList(null);
		          }
		        
		     return roleAccess;
		    }
	    
	    
	    @SuppressWarnings("unchecked")
			public RoleAccess retrieveUserAccessBasedOnSetCustomerRole(String sso)
		    {
		     
		    	RoleAccess roleAccess = new RoleAccess();
		    	ErrorCd errorCd = new ErrorCd();
		    	
		     List<AccessObject> accessObjectList=new ArrayList<AccessObject>();
		         
		     SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("CustomerSetup_ws_get_Access");
		         Map<String, Object> inParamMap = new HashMap<String, Object>();
		      inParamMap.put("SSO_ID", sso);
		      
		      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		     
		      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		      
		       Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
		          while (it.hasNext()) {
		              Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		              String key = (String) entry.getKey();
		              Object value = (Object) entry.getValue();
		              System.out.println("Key: "+key);
		              System.out.println("Value: "+value);
		              if(key.equalsIgnoreCase("#result-set-1"))
		              {
		               accessObjectList=(List<AccessObject>) entry.getValue();
		              }
		          }
		          if(null != accessObjectList && !accessObjectList.isEmpty()){	        	  
		        	  errorCd.setErrorCode("SUCCESS");
		        	  errorCd.setErrorMessage("No Issues, User is authorized.");		        	  
		        	  roleAccess.setErrorCd(errorCd);
		        	  roleAccess.setAccessObjectList(accessObjectList);
		          }
		          else{
		        	  errorCd.setErrorCode("AUTH_ERROR");
		        	  errorCd.setErrorMessage("You are not authorized to access any site.");		        	  
		        	  roleAccess.setErrorCd(errorCd);
		        	  roleAccess.setAccessObjectList(null);
		          }
		        
		     return roleAccess;
		    }
	    
	    
	    
	    @SuppressWarnings("unchecked")
			public RoleAccess retrieveUserAccessBasedOnEquipRole(String sso)
		    {
		     
		    	RoleAccess roleAccess = new RoleAccess();
		    	ErrorCd errorCd = new ErrorCd();
		    	
		     List<AccessObject> accessObjectList=new ArrayList<AccessObject>();
		         
		     SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("EquipSetup_ws_get_Access");
		         Map<String, Object> inParamMap = new HashMap<String, Object>();
		      inParamMap.put("SSO_ID", sso);
		      
		      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
		     
		      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		      
		       Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
		          while (it.hasNext()) {
		              Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
		              String key = (String) entry.getKey();
		              Object value = (Object) entry.getValue();
		              System.out.println("Key: "+key);
		              System.out.println("Value: "+value);
		              if(key.equalsIgnoreCase("#result-set-1"))
		              {
		               accessObjectList=(List<AccessObject>) entry.getValue();
		              }
		          }
		          if(null != accessObjectList && !accessObjectList.isEmpty()){	        	  
		        	  errorCd.setErrorCode("SUCCESS");
		        	  errorCd.setErrorMessage("No Issues, User is authorized.");		        	  
		        	  roleAccess.setErrorCd(errorCd);
		        	  roleAccess.setAccessObjectList(accessObjectList);
		          }
		          else{
		        	  errorCd.setErrorCode("AUTH_ERROR");
		        	  errorCd.setErrorMessage("You are not authorized to access any site.");		        	  
		        	  roleAccess.setErrorCd(errorCd);
		        	  roleAccess.setAccessObjectList(null);
		          }
		        
		     return roleAccess;
		    }
	    
	    
	    
	    @SuppressWarnings("unchecked")
	  		public RoleAccess retrieveCMUserAccessBasedOnRole(String sso)
	  	    {
	  	     
	  	    	RoleAccess roleAccess = new RoleAccess();
	  	    	ErrorCd errorCd = new ErrorCd();
	  	    	
	  	     List<AccessObject> accessObjectList=new ArrayList<AccessObject>();
	  	         
	  	     SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("CM_ws_get_Access");
	  	         Map<String, Object> inParamMap = new HashMap<String, Object>();
	  	      inParamMap.put("SSO_ID", sso);
	  	      
	  	      SqlParameterSource in = new MapSqlParameterSource(inParamMap);
	  	     
	  	      Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
	  	      
	  	       Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
	  	          while (it.hasNext()) {
	  	              Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
	  	              String key = (String) entry.getKey();
	  	              Object value = (Object) entry.getValue();
	  	              System.out.println("Key: "+key);
	  	              System.out.println("Value: "+value);
	  	              if(key.equalsIgnoreCase("#result-set-1"))
	  	              {
	  	               accessObjectList=(List<AccessObject>) entry.getValue();
	  	              }
	  	          }
	  	          if(null != accessObjectList && !accessObjectList.isEmpty()){	        	  
	  	        	  errorCd.setErrorCode("SUCCESS");
	  	        	  errorCd.setErrorMessage("No Issues, User is authorized.");
	  	        	  
	  	        	  roleAccess.setErrorCd(errorCd);
	  	        	  roleAccess.setAccessObjectList(accessObjectList);
	  	          }
	  	          else{
	  	        	  errorCd.setErrorCode("AUTH_ERROR");
	  	        	  errorCd.setErrorMessage("You are not authorized to access any site.");
	  	        	  
	  	        	  roleAccess.setErrorCd(errorCd);
	  	        	  roleAccess.setAccessObjectList(null);
	  	          }
	  	        
	  	     return roleAccess;

	  	    }
	    
	    
	    @SuppressWarnings("unchecked")
		public HashMap<String,Object> retrieveUserNameDetailsMOS(String sso)
	    {
	    	
	    	SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("MOS_ws_get_Access");

	    	HashMap<String, Object> inParamMap = new HashMap<String, Object>();
			inParamMap.put("SSO_Id", sso);
		 
			SqlParameterSource in = new MapSqlParameterSource(inParamMap);
			HashMap<String, Object> userListmap = new HashMap<String, Object>();
			Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
			
			 Iterator<Entry<String, Object>> it = simpleJdbcCallResult.entrySet().iterator();
			    while (it.hasNext()) {
			        Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
			        String key = (String) entry.getKey();
			        Object value = (Object) entry.getValue();
			        System.out.println("Key: "+key);
			        System.out.println("Value: "+value);
			        if(key.equalsIgnoreCase("#result-set-1"))
			        {
			        	userListmap.put("UserNameDetails",entry.getValue());
			        }
			    }
				return userListmap;
	    	}
	    
}

