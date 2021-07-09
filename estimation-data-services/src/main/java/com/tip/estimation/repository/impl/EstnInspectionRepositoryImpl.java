package com.tip.estimation.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import com.tip.estimation.model.EstnInspectionRequest;
import com.tip.estimation.repository.EstnInspectionRepository;
import com.tip.util.EstimationConstant;

@Repository
public class EstnInspectionRepositoryImpl implements  EstnInspectionRepository, CallableStatementCreator{
	
	static final Logger logger = LoggerFactory.getLogger(EstnInspectionRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private EstnInspectionRequest estnInspectionRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public void setInspectionRequest(EstnInspectionRequest estnInspectionRequest) {
        this.estnInspectionRequest = estnInspectionRequest;
    }

	
    @SuppressWarnings({"rawtypes", "unchecked"})
	@Override
	public Map<String, Object> getInspectionDetails(EstnInspectionRequest estnInspectionRequest) {
    	Map<String, Object> resultMap = null;

        Map<String, Object> inspectionMap = new HashMap();

        try {

            SqlParameter estimationIdparam = new SqlParameter(Types.INTEGER);
            SqlParameter branchIdparam = new SqlParameter(Types.VARCHAR);
            SqlParameter ssoIdparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(estimationIdparam);
            paramList.add(branchIdparam);
            paramList.add(ssoIdparam);
            EstnInspectionRepositoryImpl estnInspectionRepositoryImpl = new EstnInspectionRepositoryImpl();
            estnInspectionRepositoryImpl.estnInspectionRequest = estnInspectionRequest;
            estnInspectionRepositoryImpl.procedureCall = "{call " + EstimationConstant.PROC_START_ESTN_INSPECTION + "(?, ?, ?)}";
           
            resultMap = jdbcTemplate.call((CallableStatementCreator) estnInspectionRepositoryImpl, paramList);

            
            
            for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            	String key = entry.getKey();
            	Object value = entry.getValue();
            	
            	logger.info("Key: " + key);
                logger.info("Value: " + value);
                
    			if (("#result-set-1").equalsIgnoreCase(key)) {
    				 inspectionMap.put("InspectionDetails", entry.getValue());	
    			
    			}  else if (("Error_Cd").equalsIgnoreCase(key)) {
                    inspectionMap.put("InspectionDetails", entry.getValue());
                }
            }
            
        } catch (Exception e) {
            logger.error("An error occurred while fetching inspection details: " + e);
        }
        
        return inspectionMap;
	}
	
	
	@Override
	public CallableStatement createCallableStatement(Connection connection) throws SQLException {
		connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setBigDecimal(1, estnInspectionRequest.getEstimationId());
        callableStatement.setString(2, estnInspectionRequest.getBranchId());
        callableStatement.setString(3, estnInspectionRequest.getSsoId());
        connection.setAutoCommit(true);
        return callableStatement;
	}

}
