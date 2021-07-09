package com.tip.fetchdynamicmasterdata.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tip.asset.util.DatatypeCommonUtility;
import com.tip.fetchdynamicmasterdata.model.AllProcedureDataResponse;
import com.tip.fetchdynamicmasterdata.model.FetchDynamicMasterDataRequest;
import com.tip.fetchdynamicmasterdata.repository.FetchDynamicMasterDataRepository;

@Repository
public class FetchDynamicMasterDataRepositoryImpl implements FetchDynamicMasterDataRepository, CallableStatementCreator {

    static final Logger logger = Logger.getLogger(FetchDynamicMasterDataRepositoryImpl.class);

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
    public Map<String, Object> getDynamicMasterData(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest, AllProcedureDataResponse allProcedureDataResponse) {

        Map<String, Object> resultMap = new HashMap<>();
        Map<String, Object> procDataMap = new HashMap<>();
        try {

            List paramList = new ArrayList();

            FetchDynamicMasterDataRepositoryImpl lFetchDynamicMasterDataRepositoryImpl = new FetchDynamicMasterDataRepositoryImpl();
            lFetchDynamicMasterDataRepositoryImpl.fetchDynamicMasterDataRequest = fetchDynamicMasterDataRequest;
            lFetchDynamicMasterDataRepositoryImpl.procedureCall = "{call " + allProcedureDataResponse.getProcName() + "(?,?)}";
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchDynamicMasterDataRepositoryImpl, paramList);
        } catch (Exception e) {
        	logger.error("Error Encountered in getDynamicMasterData service " ,e);
        }
        Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> entry = it.next();
            String key = entry.getKey();

            if (("#result-set-1").equalsIgnoreCase(key)) {
            	procDataMap.put("procDataList", entry.getValue());
            }
        }
        return procDataMap;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        DatatypeCommonUtility.checkNull(1, callableStatement, fetchDynamicMasterDataRequest.getBranchNr());
        DatatypeCommonUtility.checkNull(2, callableStatement, fetchDynamicMasterDataRequest.getLanguageId());
        connection.setAutoCommit(true);
        return callableStatement;
    }

	public void setFetchDynamicMasterDataRequest(FetchDynamicMasterDataRequest fetchDynamicMasterDataRequest) {
		this.fetchDynamicMasterDataRequest = fetchDynamicMasterDataRequest;
	}

}
