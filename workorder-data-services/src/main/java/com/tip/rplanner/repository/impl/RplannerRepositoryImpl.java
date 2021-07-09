package com.tip.rplanner.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.rplanner.model.ResourcePlannerRequest;
import com.tip.rplanner.repository.RplannerRepository;
import com.tip.workorder.util.DateUtility;

@Repository
public class RplannerRepositoryImpl implements RplannerRepository, CallableStatementCreator{

	static final Logger logger = LoggerFactory.getLogger(RplannerRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String procedureCall;
	
	private ResourcePlannerRequest resourcePlannerRequest;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> getResourcePlannerDetails(ResourcePlannerRequest resourcePlannerRequest) {
		
		this.resourcePlannerRequest = resourcePlannerRequest;
		
		Map<String, Object> rPlannerResponseMap = new HashMap(); 
		
		try {
			Map<String, Object> resultMap;

			SqlParameter branchIdSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter periodTypeSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter startDateSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter endDateSQLparam = new SqlParameter(Types.VARCHAR);
			SqlParameter startTimeSQLparam = new SqlParameter(Types.TIME);
			SqlParameter endTimeSQLparam = new SqlParameter(Types.TIME);
			SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

			List paramList = new ArrayList();
			paramList.add(branchIdSQLparam);
			paramList.add(periodTypeSQLparam);
			paramList.add(startDateSQLparam);
			paramList.add(endDateSQLparam);
			paramList.add(startTimeSQLparam);
			paramList.add(endTimeSQLparam);
			paramList.add(errorCdSQLparam);

			RplannerRepositoryImpl rplannerRepositoryImpl = new RplannerRepositoryImpl();
			rplannerRepositoryImpl.resourcePlannerRequest = resourcePlannerRequest;
			rplannerRepositoryImpl.procedureCall = "{call Wshp_Resrc_Planner_Job_List_R2(?, ?, ?, ?, ?, ?, ?)}";
			
			resultMap = jdbcTemplate.call(rplannerRepositoryImpl, paramList);

			Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();

			while (it.hasNext()) {
				Map.Entry<String, Object> entry = it.next();
				String key = entry.getKey();

				logger.info("key>>>"+key+"\n Value>>>"+entry.getValue());
				if (("#result-set-1").equalsIgnoreCase(key)) {
					rPlannerResponseMap.put("Techlist", entry.getValue());
				} else if (("#result-set-2").equalsIgnoreCase(key)) {
					rPlannerResponseMap.put("AssignedObjectlist", entry.getValue());
				} else if (("#result-set-3").equalsIgnoreCase(key)) {
					rPlannerResponseMap.put("Techbreakdetails", entry.getValue());
				}
			}
		} catch (Exception e) {
			StackTraceElement stackTraceElement = e.getStackTrace()[0];
			logger.error("An error occurred while fetching asset reading details : " + e);
			logger.error("At Line: " + stackTraceElement.getClassName()+":" + stackTraceElement.getLineNumber());
		}
		
		return rPlannerResponseMap;
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
		CallableStatement callableStatement = connection.prepareCall(procedureCall);		
		callableStatement.setString(1, resourcePlannerRequest.getBranchId());
		callableStatement.setString(2, resourcePlannerRequest.getPeriodType());
		callableStatement.setString(3, resourcePlannerRequest.getStartDate());
		callableStatement.setString(4, resourcePlannerRequest.getEndDate());
		callableStatement.setTime(5, new DateUtility().stringToSqlTimestamp(resourcePlannerRequest.getStartTime()));
		callableStatement.setTime(6, new DateUtility().stringToSqlTimestamp(resourcePlannerRequest.getEndTime()));
		callableStatement.setString(7, "NULL");
		connection.setAutoCommit(true);
		return callableStatement;
	}

	public void setResourcePlannerRequest(ResourcePlannerRequest resourcePlannerRequest){
		this.resourcePlannerRequest = resourcePlannerRequest;
	}
}
