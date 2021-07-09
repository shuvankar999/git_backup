package com.tip.inspection.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.tip.inspection.main.DatatypeCommonUtility;
import com.tip.inspection.main.InspectionCrudConstants;
import com.tip.inspection.model.UserProfileObject;
import com.tip.inspection.model.UserProfileStatusObject;
import com.tip.inspection.repository.UserProfileRepository;

@Repository
public class UserProfileRepositoryImpl implements UserProfileRepository, CallableStatementCreator  {
	
	static final Logger logger = LoggerFactory.getLogger(UserProfileRepositoryImpl.class);
	
	private String procedureCall;
	
	private UserProfileObject userProfileObject;
	
	private String ssoId="";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void setUserProfileObject(UserProfileObject userProfileObject) {
		this.userProfileObject = userProfileObject;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public UserProfileStatusObject saveUserProfile(UserProfileObject userProfileObject) {
		
		UserProfileStatusObject userProfileStatusObject = new UserProfileStatusObject();
		
		Map<String, Object> resultMap = null;
		try {
			SqlParameter userEmailParam = new SqlParameter(Types.VARCHAR);
			SqlParameter loginPwdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter tokenNrParam = new SqlParameter(Types.DECIMAL);
			SqlParameter accountCodeParam = new SqlParameter(Types.VARCHAR);
			SqlParameter userLoginIdParam = new SqlParameter(Types.VARCHAR);
			SqlParameter userTitleTextParam = new SqlParameter(Types.VARCHAR);
			SqlParameter forenameParam = new SqlParameter(Types.VARCHAR);
			SqlParameter surnameParam = new SqlParameter(Types.VARCHAR);
			SqlParameter roleParam = new SqlParameter(Types.DECIMAL);
			SqlParameter pinParam = new SqlParameter(Types.INTEGER);
			SqlParameter locationParam = new SqlParameter(Types.VARCHAR);
			SqlParameter langIdParam = new SqlParameter(Types.INTEGER);
			SqlParameter phoneNrParam = new SqlParameter(Types.VARCHAR);
			SqlParameter customerNrParam = new SqlParameter(Types.DECIMAL);

			List paramList = new ArrayList();
			paramList.add(userEmailParam);
			paramList.add(loginPwdParam);
			paramList.add(tokenNrParam);
			paramList.add(accountCodeParam);
			paramList.add(userLoginIdParam);
			paramList.add(userTitleTextParam);
			paramList.add(forenameParam);
			paramList.add(surnameParam);
			paramList.add(roleParam);
			paramList.add(pinParam);
			paramList.add(locationParam);
			paramList.add(langIdParam);
			paramList.add(phoneNrParam);
			paramList.add(customerNrParam);

			UserProfileRepositoryImpl userProfileRepositoryImpl = new UserProfileRepositoryImpl();
			userProfileRepositoryImpl.userProfileObject = userProfileObject;
			userProfileRepositoryImpl.procedureCall = "{call "
					+ InspectionCrudConstants.PROC_SAVE_USER_PROFILE+ " (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			resultMap = jdbcTemplate.call(userProfileRepositoryImpl, paramList);

			if (null != resultMap) {
				for (Map.Entry<String, Object> entry : resultMap.entrySet())
				{
					if ("#result-set-1".equalsIgnoreCase(entry.getKey())) {
						List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
						for (int i = 0; i < lst.size(); i++) {
							Map<String, Object> errorMap = lst.get(i);
							userProfileStatusObject.setErrorCd((String) errorMap.get("Error_Cd"));
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("An error occurred while saving user profile details: " + e);
			userProfileStatusObject.setErrorCd(e.toString().substring(91,147));
		}
		return userProfileStatusObject;
	}

	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);
		callableStatement.setString(1, userProfileObject.getUserEmail());
		callableStatement.setString(2, userProfileObject.getLoginPwd());
		callableStatement.setString(3, userProfileObject.getTokenNr());
		callableStatement.setString(4, userProfileObject.getAccountCode());
		callableStatement.setString(5, userProfileObject.getUserLoginId());
		callableStatement.setString(6, userProfileObject.getUserTitleTxt());
		callableStatement.setString(7, userProfileObject.getUserForenameTxt());
		callableStatement.setString(8, userProfileObject.getUserSurnameTxt());
		DatatypeCommonUtility.checkNull(9, callableStatement, userProfileObject.getUserRoleId());
		DatatypeCommonUtility.checkNull(10, callableStatement, userProfileObject.getUserPin());
		callableStatement.setString(11, userProfileObject.getLocation());
		DatatypeCommonUtility.checkNull(12, callableStatement, userProfileObject.getLanguageId());
		callableStatement.setString(13, userProfileObject.getPhoneNr());
		DatatypeCommonUtility.checkNull(14, callableStatement, userProfileObject.getCustomerNr());
		connection.setAutoCommit(false);
		return callableStatement;
	}

	@Override
	public String fetchCIAprivateKey(String ssoId) {
		this.ssoId = ssoId;
		Map<String, Object> resultMap = null;
		String privateKeyAsString = null;
		
		
		// TODO Auto-generated method stub
		CallableStatement statement = null;
		Connection connection = null;
		connection = DataSourceUtils.getConnection(jdbcTemplate.getDataSource());
		  String strSPCall = "{call  OPScia..CIA_get_login_key(?)}";
		//  String responseMsg="";
		//  boolean tokenValid=false;
			try {
				statement = connection.prepareCall(strSPCall);
				statement.setString(1, ssoId);
				//statement.setString(2, pwd);
		
			ResultSet rs=statement.executeQuery();
				while(rs.next()) {
					
				
					privateKeyAsString=rs.getString("Private_Key");

				}		
				
				while( rs != null )
	            {
	                System.out.println( "\n---Result set---\n" );
	                while (rs.next())
	                {
	                	privateKeyAsString=rs.getString("Public_Key");
	                }
	                if( statement.getMoreResults() )
	                {
	                    rs = statement.getResultSet();
	                }
	                else
	                {
	                    rs.close();
	                    rs = null;
	                }
	            }
				statement.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			//		return responseMsg;
				//	
					
		
		
		return privateKeyAsString;
	}
}

	