package com.tip.suppliermasterdata.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.supplier.main.SupplierDataConstants;
import com.tip.suppliermasterdata.model.AllProcedureDataResponse;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.suppliermasterdata.model.FetchDynamicMasterDataResponse;
import com.tip.suppliermasterdata.repository.FetchAllProceduresListRepository;


@Repository
public class FetchAllProceduresListRepositoryImpl implements FetchAllProceduresListRepository, CallableStatementCreator {

    static final Logger logger = Logger.getLogger(FetchAllProceduresListRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest;

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public FetchDynamicMasterDataResponse getAllProcedures(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest) {

        Map<String, Object> resultMap = new HashMap<>();
        FetchDynamicMasterDataResponse lFetchDynamicMasterDataResponse = new FetchDynamicMasterDataResponse();
        List<AllProcedureDataResponse> allProcedureDataResponseLst = new ArrayList<>();
        try {

            List paramList = new ArrayList();

            FetchAllProceduresListRepositoryImpl lFetchAllProceduresListRepositoryImpl = new FetchAllProceduresListRepositoryImpl();
            lFetchAllProceduresListRepositoryImpl.fetchDynamicMasterDataRequest = fetchDynamicMasterDataRequest;
            lFetchAllProceduresListRepositoryImpl.procedureCall = "{call " + SupplierDataConstants.PROC_FETCH_ALL_PROCEDURES + "(?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchAllProceduresListRepositoryImpl, paramList);
        } catch (Exception e) {
        	logger.error("Error Encountered in getDynamicMasterData service " ,e);
        }
        
        for (Map.Entry<String, Object> entry : resultMap.entrySet())
		{
            if (("#result-set-1").equalsIgnoreCase(entry.getKey())) {
            	List<Map<String, Object>> lst = (List<Map<String, Object>>) entry.getValue();
            	for(int i=0;i<lst.size();i++)
            	{
            		Map<String, Object> lCountryDetailsMap = lst.get(i);
            		AllProcedureDataResponse lAllProcedureDataResponse = new AllProcedureDataResponse();
            		lAllProcedureDataResponse.setKeyName((String)lCountryDetailsMap.get("key_name"));
            		lAllProcedureDataResponse.setObjName((String)lCountryDetailsMap.get("obj_name"));
            		lAllProcedureDataResponse.setProcName((String)lCountryDetailsMap.get("proc_name"));
            		allProcedureDataResponseLst.add(lAllProcedureDataResponse);		                	
            	}
            }
		}
        lFetchDynamicMasterDataResponse.getAllProcedureDataResponseLst().addAll(allProcedureDataResponseLst);
        return lFetchDynamicMasterDataResponse;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setString(1, fetchDynamicMasterDataRequest.getKeyName());
        callableStatement.setString(2, fetchDynamicMasterDataRequest.getSsoId());
        connection.setAutoCommit(true);
        return callableStatement;
    }
    
	public void setFetchDynamicMasterDataRequest(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest) {
		this.fetchDynamicMasterDataRequest = fetchDynamicMasterDataRequest;
	}

}
