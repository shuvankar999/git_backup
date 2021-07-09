package com.tip.fetchattention.repository.impl;

import com.tip.fetchattention.model.Attention;
import com.tip.fetchattention.model.AttentionRequest;
import com.tip.fetchattention.model.AttentionResponse;
import com.tip.fetchattention.repository.FetchAttentionRepository;
import com.tip.workorder.util.WorkOrderConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;
import java.util.Map.Entry;

@Repository
public class FetchAttentionRepositoryImpl implements FetchAttentionRepository, CallableStatementCreator {


    static final Logger logger = LoggerFactory.getLogger(FetchAttentionRepositoryImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String procedureCall;
    private AttentionRequest attentionRequest;

    public void setAttentionRequest(AttentionRequest attentionRequest) {
        this.attentionRequest = attentionRequest;
    }

    @Autowired
    public void setDataSource(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }


    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public AttentionResponse fetchAttentionData(AttentionRequest attentionRequest) {

        AttentionResponse attentionResponse = new AttentionResponse();
        Map<String, Object> resultMap = null;
        List<Attention> attentionList = new ArrayList();
        Map<String, Object> attentionListMap = new HashMap();
        try {

            SqlParameter unitNrSQLparam = new SqlParameter(Types.INTEGER);
            SqlParameter langIdSQLParam = new SqlParameter(Types.INTEGER);
            SqlParameter errorCdSQLparam = new SqlParameter(Types.VARCHAR);

            List paramList = new ArrayList();
            paramList.add(unitNrSQLparam);
            paramList.add(langIdSQLParam);
            paramList.add(errorCdSQLparam);

            FetchAttentionRepositoryImpl lFetchAttentionRepositoryImpl = new FetchAttentionRepositoryImpl();
            lFetchAttentionRepositoryImpl.procedureCall = "{call Wshp_Attention_Details(?, ?, ?)}";
            lFetchAttentionRepositoryImpl.attentionRequest = attentionRequest;
            resultMap = jdbcTemplate.call((CallableStatementCreator) lFetchAttentionRepositoryImpl, paramList);


            Iterator<Entry<String, Object>> it = resultMap.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                String key = entry.getKey();
                Object value = entry.getValue();
                logger.info("Key: " + key);
                logger.info("Value: " + value);
                if (("#result-set-1").equalsIgnoreCase(key)) {
                    attentionListMap.put("Ast_CustRequirements", entry.getValue());
                } else if (("#result-set-2").equalsIgnoreCase(key)) {
                    attentionListMap.put("Ast_ContractDataList", entry.getValue());
                } else if (WorkOrderConstants.ERROR_CD.equalsIgnoreCase(key)) {
                    attentionListMap.put(WorkOrderConstants.ERROR_CD, entry.getValue());
                }
            }

        } catch (Exception e) {
            logger.error("An error occurred while fetching attention data: " + e);
        }

        attentionResponse.setAttentionList(attentionList);
        attentionResponse.setAttentionListMap(attentionListMap);
        return attentionResponse;
    }

    @Override
    public CallableStatement createCallableStatement(Connection connection) throws SQLException {
        connection.setAutoCommit(false);
        CallableStatement callableStatement = connection.prepareCall(procedureCall);
        callableStatement.setInt(1, attentionRequest.getUnitNr());
        callableStatement.setInt(2, attentionRequest.getLangId());
        callableStatement.setString(3, "NULL");
        connection.setAutoCommit(true);
        return callableStatement;
    }

}
